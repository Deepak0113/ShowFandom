const ajax = new XMLHttpRequest();

const addFriendAjax = (friendid) => {
	return new Promise((resolve, reject) => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				let res = JSON.parse(ajax.responseText);
				console.log(res.status);
				
				if(res.status === "FRIEND-ADDED"){
					console.log("added.........")
					resolve(res.message);
				} else{
					reject(res.message);
				}
			}
	    }
	
	    ajax.open("POST", "/MovieFandom/AddFriendServlet");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send(`friendid=${friendid}`);
	})
}

const getFriendsAjax = (friendType) => {
	return new Promise((resolve, reject) => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				let res = JSON.parse(ajax.responseText);
				resolve(res);
			}
	    }
	
	    ajax.open("POST", "/MovieFandom/GetFriendsServlet");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send(`friendtype=${friendType}`);
	})
}

const getFriendsCount = () => {
	return new Promise((resolve) => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				let res = JSON.parse(ajax.responseText);
				resolve(res);
			}
	    }
	
	    ajax.open("POST", "/MovieFandom/GetFriendsCountServlet");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send();
	})
}

const unfollowAjax = (friendId) => {
	return new Promise((resolve, reject) => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				if(ajax.responseText.trim() == "REMOVED")
					resolve();
				else reject();
			}
	    }
	
	    ajax.open("POST", "/MovieFandom/RemoveFriendServlet");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send(`friendid=${friendId}`);
	})
}