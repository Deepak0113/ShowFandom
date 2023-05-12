const loginLayout = document.getElementById("login__layout");
const signupLayout = document.getElementById("signup__layout");
const loginBtn = document.getElementById("loginBtn");
const signupBtn = document.getElementById("signupBtn");
const inputUsername = document.getElementById("username");
const inputName = document.getElementById("name");
const inputEmail = document.getElementById("email");
const inputPassword = document.getElementById("password");
const warningTags = document.querySelectorAll(".input__container .warning");

const loginInputUsername = document.getElementById("login_username");
const loginInputPassword = document.getElementById("login_password");

let isLogin = true;

document.getElementById("toSignup").addEventListener("click", handleSwitchLayout);
document.getElementById("toLogin").addEventListener("click", handleSwitchLayout);

signupBtn.addEventListener("click", () => {
	let username = inputUsername.value;
	let name = inputName.value;
	let email = inputEmail.value;
	let password = inputPassword.value;
	
	for(let warningTag of warningTags){
		warningTag.style.display = "none";
	}
	
	if(!validateName(name)){
		warningTags[2].style.display = "block";
	} else if(!validateName(username)){
		warningTags[3].style.display = "block";
	} else if(!validateEmail(email)){
		warningTags[4].style.display = "block";
	} else if(!validatePassword(password)){
		warningTags[5].style.display = "block";
	} else{
		signupAjax(name, username, email, password)
		.then(handleSignedUp, handleFailed);
	}
})

loginBtn.addEventListener("click", () => {
	let username = loginInputUsername.value;
	let password = loginInputPassword.value;
	
	for(let warningTag of warningTags){
		warningTag.style.display = "none";
	}
	
	if(!validateName(username)){
		warningTags[0].style.display = "block";
	} else if(!validatePassword(password)){
		warningTags[1].style.display = "block";
	} else{
		loginAjax(username, password)
		.then(handleLoggedIn, handleFailed);
	}
})

function handleSwitchLayout() {
    if(isLogin){
        loginLayout.style.display = "none";
        signupLayout.style.display = "flex";
    } else{
        loginLayout.style.display = "flex";
        signupLayout.style.display = "none";
    }

    isLogin = !isLogin;
}

function handleSignedUp(message){
	handleSwitchLayout();
	//showPopupMessage(message, "success");
}

function handleLoggedIn(){
	location.href = "/MovieFandom/pages/home/home.html";
}

function handleFailed(message){
	alert(message)
	//showPopupMessage(message, "success");
}