package com.moviefandom.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.moviefandom.dto.Cast;
import com.moviefandom.dto.CompleteMovie;
import com.moviefandom.dto.Movie;
import com.moviefandom.dto.Review;
import com.moviefandom.dto.ShowPreview;
import com.moviefandom.dto.User;
import com.moviefandom.statuscall.FriendStatus;
import com.moviefandom.statuscall.ShowStatus;
import com.moviefandom.statuscall.Status;
import com.moviefandom.statuscall.UserStatus;

public class Repository {
	private static Repository repository;
	private Connection connection;
	
	public static Repository getInstance() {
		if(repository == null) {
			repository = new Repository();
			repository.initSetup();
		}
		
		return repository;
	}
	
	/*----- INITIAL SETUP -----*/
	
	private void initSetup() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MovieDB", "root", "Deepak@123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*----- LOGIN USER -----*/
	
	public UserStatus loginUser(String username, String password) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select username, name, emailid, isblocked from User where username = ? and password = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return new UserStatus("SUCCESS-LOGGEDIN", new User(
						resultSet.getString("username"),
						resultSet.getString("name"),
						resultSet.getString("emailid"),
						resultSet.getBoolean("isblocked")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new UserStatus("USER-NOTEXIST");
	}
	
	
	/*----- SIGNUP USER -----*/
	
	public UserStatus signupUser(String username, String name, String email, String password) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("insert into User (username, name, emailid, password) values (?, ?, ?, ?)");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			
			preparedStatement.executeUpdate();
			return new UserStatus("SUCCESS-ADDED");
		} catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("Error message: " + e.getMessage());
			
			if(e.getMessage().contains("user.PRIMARY"))
				return new UserStatus("DUPLICATE-USERNAME");
			
			if(e.getMessage().contains("user.emailid"))
				return new UserStatus("DUPLICATE-EMAIL");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/*----- GET REVIEWS -----*/
	
	public List<Review> getReviews(int movieid){
		List<Review> reviews = new ArrayList<>();
		Statement s = null;
		
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery("select name, review_id, review, rating, review_timestamp from MovieReview mr inner join User u on u.emailid = mr.emailid and mr.movie_id = " + movieid + " order by review_timestamp desc");
			while(rs.next()) {
				reviews.add(new Review(
							rs.getInt("review_id"),
							rs.getString("name"),
							rs.getString("review"),
							rs.getInt("rating"),
							rs.getString("review_timestamp")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reviews;
	}
	
	
	/*----- POST REVIEW -----*/
	
	public boolean postReviews(int movieid, String emailid, String review, int rating, LocalDateTime timestamp){
		Statement s = null;
		
		try {
			s = connection.createStatement();
			String valuesStr = movieid + ",'" + emailid + "','" + review + "'," + rating + ",'" + Timestamp.valueOf(timestamp) + "'";
			boolean isAdded = s.execute("insert into MovieReview (movie_id, emailid, review, rating, review_timestamp) values ("+ valuesStr +")");
			return isAdded;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	/*----- GET WATCHLIST -----*/

	public List<String> getWatchListTags(String username) {
		List<String> watchList = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select watchlistname from watchlist where username = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				watchList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return watchList;
	}

	
	/*----- ADD WATCHLIST -----*/
	
	public boolean addWatchListTags(String tagName, String username) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("insert into watchlist (watchlistname, username) values (?, ?);");
			preparedStatement.setString(1, tagName);
			preparedStatement.setString(2, username);
			preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	/*----- GET GENRES -----*/
	public List<String> getGenres() {
		List<String> genres = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select genre from movieseriesgenres");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				genres.add(resultSet.getString("genre"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return genres;
	}
	
	/*----- GET GENRES -----*/
	public List<String> getAnimeGenres() {
		List<String> genres = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select genre from animegenres");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				genres.add(resultSet.getString("genre"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return genres;
	}
	 
	
	/*----- GET RATING TYPE -----*/
	public List<String> getRatingType() {
		List<String> ratingTypes = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select rating from ratingtypes");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				ratingTypes.add(resultSet.getString("rating"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ratingTypes;
	}
	
	
	/*----- GET COUNTRIES MOVIES -----*/
	public List<String> getCountriesMovies() {
		List<String> countries = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select country from moviecountry;");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				countries.add(resultSet.getString("country"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return countries;
	}
	
	/*----- GET COUNTRIES MOVIES -----*/
	public List<String> getCountriesAnime() {
		List<String> countries = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select distinct c.country from countries c left join animetb as mtb on mtb.country = c.id;");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				countries.add(resultSet.getString("country"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return countries;
	}
	
	/*----- GET COUNTRIES MOVIES -----*/
	public List<String> getCountriesSeries() {
		List<String> countries = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select distinct c.country from countries c left join seriestb as mtb on mtb.country = c.id;");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				countries.add(resultSet.getString("country"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return countries;
	}
	
	
	/*----- SEARCH MOVIE -----*/
	public List<ShowPreview> searchMovie(String name, String ratingType, String country, String[] genres){
		List<ShowPreview> movies = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		System.out.println("name: " + name);
		
		try {
			System.out.println("name: " + name);
			System.out.println("showtype: " + ratingType);
			System.out.println("Country: " + country);
			System.out.println("genres: " + genres);
			preparedStatement = connection.prepareStatement("select mtb.id, name, releasedate, imdbrating, rottenorangerating, showfandomrating, posterimgurl, rating from movietb mtb\r\n"
					+ "inner join moviecountry mc on mc.id = mtb.countryid\r\n"
					+ "inner join ratingtypes rt on rt.id = mtb.ratingTypeId\r\n"
					+ "where\r\n"
					+ "(name like ? or name like ?) and \r\n"
					+ "country like ? and rating like ? and mtb.id in (select distinct mtb.id as mtbid from movietb mtb\r\n"
					+ "inner join moviegenrerelation mgr on mgr.movieid = mtb.id\r\n"
					+ "inner join movieseriesgenres msg on msg.id = mgr.genreid) order by\r\n"
					+ "case when name like ? then 1 else 2 end;");
			
			preparedStatement.setString(1, name+"%");
			preparedStatement.setString(2, "%"+name+"%");
			preparedStatement.setString(3, "%"+country);
			preparedStatement.setString(4, "%"+ratingType);
			preparedStatement.setString(5, name+"%");
			
			System.out.println(preparedStatement.toString());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				movies.add(new ShowPreview(
						resultSet.getString("id"),
						resultSet.getString("name"),
						resultSet.getString("releasedate"),
						resultSet.getFloat("imdbrating"),
						resultSet.getInt("rottenorangerating"),
						resultSet.getInt("showfandomrating"),
						resultSet.getString("posterimgurl"),
						resultSet.getString("rating")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movies;
	}
	
	
	/*----- ADD FRIEND -----*/
	public Status addFriend(String friendId, String username) {
		PreparedStatement preparedStatement = null;
		
		try {
			if(isUserExists(friendId)) {
				preparedStatement = connection.prepareStatement("insert into friends (follower, following) values (?,?)");
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, friendId);
				preparedStatement.executeUpdate();
				return new Status("FRIEND-ADDED");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			return new Status("DUPLICATE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Status("USER-NOT-EXIST");
	}
	
	private boolean isUserExists(String username) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("select username from user where username = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public List<String> getFollowers(String username) {
		PreparedStatement preparedStatement = null;
		List<String> followers = new ArrayList<>();
		
		try {
			preparedStatement = connection.prepareStatement("select follower from friends where following = ? order by timestamp desc");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) 
				followers.add(resultSet.getString("follower"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return followers;
	}
	
	public List<String> getFollowing(String username) {
		PreparedStatement preparedStatement = null;
		List<String> followings = new ArrayList<>();
		
		try {
			preparedStatement = connection.prepareStatement("select following from friends where follower = ? order by timestamp desc");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) 
				followings.add(resultSet.getString("following"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return followings;
	}
	
	public int getTotalFollowing(String username) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select count(*) as count from friends where follower = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt("count");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getTotalFollowers(String username) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("select count(*) as count from friends where following = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean removeFollowing(String username, String friendid) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("delete from friends where follower=? and following=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, friendid);
			preparedStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/*----- GET SHOW MOVIES -----*/
	public ShowStatus getShowMovie(String movieid) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("select * from movietb where id = ?");
			preparedStatement.setString(1, movieid);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ShowStatus("NOT-AVAILABLE");
	}
	
	private void getShowMovieGenres() {
		PreparedStatement preparedStatement = null;
		
	}
	
	private void getShowMovieCasts() {
		PreparedStatement preparedStatement = null;
	}
}

















