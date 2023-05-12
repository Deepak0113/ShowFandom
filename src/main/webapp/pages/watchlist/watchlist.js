const popup = document.querySelector(".popup");
const popupContent = document.querySelector(".popup .popup__content");
const navList = document.querySelector(".navbar");

let tag;

window.onload = () => {
	fetchWatchListTags()
	.then((res) => {
		generateWatchListTags(res);
	})
}

document.getElementById("newlistBtn").addEventListener("click", () => {
	console.log("working list new");
	popup.style.display = "flex";
})

document.getElementById("cancelBtn").addEventListener("click", () => {
    popup.style.display = "none";
})

document.getElementById("createBtn").addEventListener("click", () => {
	let newListName = document.getElementById("newlistname").value;
	addWatchListTag(newListName)
	.then((res) => {
		popup.style.display = "none";
		if(res){
			navList.innerHTML += `<p class="nav">${newListName}</p>`;
		} else{
			
		}
	})
})

function generateWatchListTags(watchListArray){
	for(let tag of watchListArray){
		navList.innerHTML += `<p class="nav">${tag}</p>`
	}
}