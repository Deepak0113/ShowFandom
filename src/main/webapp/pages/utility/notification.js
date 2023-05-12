const notification = document.getElementById("notification__container");

const successNotification = (message) => {
	console.log("notification")
	notification.children[0].innerText = message;
	notification.classList.add("success__notification");
	notification.addEventListener("animationend", (e) => {
    	e.animationName === "fade-out" && notification.classList.remove("success__notification");
	})
}