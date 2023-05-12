const ajax = new XMLHttpRequest();

function signupAjax(username, name, emailid, password){
	
	let promise = new Promise((resolve, reject) => {
		ajax.onreadystatechange = () => {
			if(ajax.readyState == 4){
				let resp = JSON.parse(ajax.responseText);
				console.log(resp);
				if(resp.status === "SUCCESS-ADDED"){
					resolve(resp.message);
				} else{
					reject(resp.message);
				}
			}
		}
		
		ajax.open("POST", "/MovieFandom/SignupServlet");
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajax.send(`name=${name}&username=${username}&email=${emailid}&password=${password}`);
	});
	
	return promise;
}


function loginAjax(username, password){
	
	let promise = new Promise((resolve, reject) => {
		ajax.onreadystatechange = () => {
			if(ajax.readyState == 4){
				let resp = JSON.parse(ajax.responseText);
				console.log("Logged: " + resp.status);
				if(resp.status === "SUCCESS-LOGGEDIN"){
					sessionStorage.setItem("username", resp.username);
					sessionStorage.setItem("email", resp.email);    
					sessionStorage.setItem("name", resp.name);        
					resolve();
				} else{
					console.log("reject call")
					reject(resp.message);
				}
			}
		}
		
		ajax.open("POST", "/MovieFandom/LoginServlet");
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajax.send(`username=${username}&password=${password}`);
	});
	
	return promise;
}