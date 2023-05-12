const ajax = new XMLHttpRequest();

const logoutAjax = () => {
	const promise = new Promise((resolve) => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				if(ajax.responseText.trim() === "success"){
					sessionStorage.clear();
					resolve();
				}
			}
	    }
	
	    ajax.open("POST", "/MovieFandom/LogoutServlet");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send();
	});
	
	return promise;
}

const getCategoryData = (showtype) => {
	const promise = new Promise((resolve) => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				let json = JSON.parse(ajax.responseText);
				resolve(json);
			}
	    }
	
	    ajax.open("POST", "/MovieFandom/GetLoadDataServlet");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send(`showtype=${showtype}`);
	})
	
	return promise;
}


const searchShowAjax = (showtype, searchname, genres, ratingtype, country) => {
	console.log("search ajax")
	const promise = new Promise((resolve) => {
		ajax.onreadystatechange = () => {
	        if(ajax.readyState == 4) {
				let json = JSON.parse(ajax.responseText);
				resolve(json);
			}
	    }
	    
	    console.log(showtype, searchname, genres, ratingtype, country);
	
	    ajax.open("POST", "/MovieFandom/SearchShowServlet");
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send(`showtype=${showtype}&searchname=${searchname}&genres=${genres}&ratingtype=${ratingtype}&country=${country}`);
	})
	
	return promise;
}