const ajax = new XMLHttpRequest();

// GET LOAD DATA
const getShowAjax = (type, showid) => {
	const promise = new Promise(() => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				if(ajax.responseText.trim() === "success"){
					sessionStorage.clear();
					resolve();
				}
			}
	    }
	    
	    ajax.open("POST", "/MovieFandom/GetShowMovie");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send();
	});
	
	return promise;
}


// ADD REVIEW
const addReview = () => {
	
}


// REMOVE REVIEW
const removeReview = () => {
	
}