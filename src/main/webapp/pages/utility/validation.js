// VALIDATE EMAIL ID
const validateEmail = (email) => {
	return true;
    return email.match("");
}

// VALIDATE TEXT
const validateText = (text) => {
	return true;
    return !(text.trim() === "");
}

// VALIDATE NAME
const validateName = (name) => {
	return true;
    if(!validateText(name)) return false;
    return name.match("[^a-zA-Z0-9]");
}

// VALIDATE PASSWORD
const validatePassword = (password) => {
	return true;
    if(!validateText(password)) return false;
    if(password.length() < 8) return false;
    return password.match("");
}