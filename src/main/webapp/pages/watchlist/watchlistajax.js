const ajax = new XMLHttpRequest();

const fetchWatchListTags = () => {
	const promise = new Promise((resolve) => {
		ajax.onreadystatechange = () => {
			if(ajax.readyState == 4){
				let watchListTags = JSON.parse(ajax.responseText);
				resolve(watchListTags);
			}	
		}
		
		ajax.open("POST", "/MovieFandom/GetWatchListTagServlet");
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajax.send();
	})
	
	return promise;
}

const addWatchListTag = (tagName) => {
	console.log(tagName);
	const promise = new Promise((resolve) => {
		ajax.onreadystatechange = () => {
			if(ajax.readyState == 4){
				let watchListTags = JSON.parse(ajax.responseText);
				console.log(ajax.responseText);
				resolve(watchListTags);
			}
		}
		
		ajax.open("POST", "/MovieFandom/AddWatchListTagServlet");
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajax.send(`tagName=${tagName}`);
	})
	
	return promise; 
}