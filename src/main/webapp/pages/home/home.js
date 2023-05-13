const searchTag = document.getElementById("search");
const cardContainer = document.getElementById("grid");

const showTypeOption = document.getElementById("showtypeoption");
const genresOption = document.getElementById("genresoptions");
const ratingTypeOption = document.getElementById("ratingtypesoption");
const countriesOption = document.getElementById("countriesoption");
const languagesOption = document.getElementById("languagesoption");
const sortOption = document.getElementById("sortoption");


let showArray = [];


// ONLOAD GET ALL DATA
window.onload = () => {
	// check session login
	handleLoginIndication();
	
	// get required load data
	getCategoryData(showTypeOption.value)
	.then((res) => {
		generateGenres(res.genre);
		generateRatingTypes(res.ratingtypes);
		generateCountries(res.countries);
	});
}


// logout button
document.getElementById("logoutBtn").addEventListener("click", () => {
    logoutAjax()
    .then(() => {
		location.reload();
	});
})


// SEARCH MOVIES WHEN ENTER IS CLICKED
searchTag.addEventListener("keypress", (e) => {
	if(e.key === "Enter"){
		searchShow();
	}
})


// GET TYPE WHEN IT IS CHANGED
showTypeOption.addEventListener("change", () => {
	getCategoryData(showTypeOption.value)
	.then((res) => {
		generateGenres(res.genre);
		generateRatingTypes(res.ratingtypes);
		generateCountries(res.countries);
	})
})


// HANDLE LOGGIN INDICATION
function handleLoginIndication(){
	if(sessionStorage.getItem("username") !== null){
		document.querySelector(".profile__nav").style.display = "block";
		document.querySelector(".link__button").style.display = "none";
		
	} else{
		document.querySelector(".profile__nav").style.display = "none";
	}
}


// GENERATE GENRES
function generateGenres(genres){
	genresOption.innerHTML = '<option value="" selected>Genre</option>';
	for(let genre of genres){
		genresOption.innerHTML += `<option value="${genre}">${genre}</option>`;
	}
}


// GENERATE COUNTRIES
function generateCountries(countries){
	countriesOption.innerHTML = '<option value="" selected>Country</option>';
	for(let country of countries){
		countriesOption.innerHTML += `<option value="${country}">${country}</option>`;
	}
}


// GENERATE RATING TYPES
function generateRatingTypes(ratingTypes){
	ratingTypeOption.innerHTML = '<option value="" selected>Rating Type</option>';
	for(let ratingType of ratingTypes){
		ratingTypeOption.innerHTML += `<option value="${ratingType}">${ratingType}</option>`;
	}
}


function searchShow(){
	let showType = showTypeOption.value;
	let genre = genresOption.value;
	let ratingType = ratingTypeOption.value;
	let country = countriesOption.value;
	let searchname = searchTag.value;
	let sortType = sortOption.value;
	
	console.log({
		showType: showType,
		searchName: searchname,
		genre: genre,
		ratingType: ratingType,
		country: country,
		sortType: sortType
	});
	
	searchShowAjax(showType, searchname, genre, ratingType, country)
	.then((res) => {
		showArray = res;
		console.log(showArray);
		
		sortShows(showType, showArray)
		.then((res) => {
			console.log(res);
			generateCards(res);
		});
	})
}


// GENERATE CARDS
function generateCards(movies){
	cardContainer.innerHTML = "";
	for(let movie of movies){
		cardContainer.innerHTML += `<a class="card" href="http://localhost:8082/MovieFandom/pages/show/movie.html?movie=${movie.id}" target="_blank">
                    <img src="${movie.posterimgurl}" alt="" class="card__image">
                    <div class="card__info">
                    	<p>${movie.name}</p>
                    </div>
                </a>`;
	}
}


sortOption.addEventListener("change", (e) => {
	const showType = e.target.value;
	console.log(showArray);
	sortShows(showType, showArray)
	.then((res) => {
		console.log(res);
		generateCards(res);
	});
})


function sortShows(sortType, array){
	return new Promise((resolve) => {
		if(sortType == "imdb-rating-low-high"){
			array.sort((a,b) => {return (a.imdbrating-b.imdbrating)});
			resolve(array);
		}
		else if(sortType == "imdb-rating-high-low"){
			array.sort((a,b) => {return (a.imdbrating-b.imdbrating)*-1});
			resolve(array);
		}
		else if(sortType == "rotten-toatoes-rating-low-high"){
			array.sort((a,b) => {return (a.rottenorangerating-b.rottenorangerating)});
			resolve(array);
		}
		else if(sortType == "rotten-toatoes-rating-high-low"){
			array.sort((a,b) => {return (a.rottenorangerating-b.rottenorangerating)*-1});
			resolve(array);
		}
		else if(sortType == "show-fandom-rating-low-high"){
			array.sort((a,b) => {return (a.showfandomrating-b.showfandomrating)});
			resolve(array);
		}
		else if(sortType == "show-fandom-rating-high-low"){
			array.sort((a,b) => {return (a.showfandomrating-b.showfandomrating)*-1});
			resolve(array);
		}
		else{
			resolve(showArray);
		}
	})
}

