const followersTag = document.getElementById("followerslist");
const followingTag = document.getElementById("followinglist");

const followerBtn = document.getElementById("followerBtn");
const followingBtn = document.getElementById("followingBtn");

const friendIdTag = document.getElementById("friendId");
const sendReqBtn = document.getElementById("sendReqBtn");

const popupTag = document.querySelector(".popup");

const interestCount = document.querySelectorAll(".interest p:nth-child(1)");


let followers = [];
let following = [];
let followersCount = 0;
let followingCount = 0;

let isFriends = false;


window.onload = () => {
	getFriendsCount()
	.then((res) => {
		followersCount = res.followersCount;
		followingCount = res.followingCount;
		generateProfileCount();
	});
}

sendReqBtn.addEventListener("click", () => {
	const friendId = friendIdTag.value;
	addFriendAjax(friendId)
	.then(handleFriendAdded, handleError);
	friendIdTag.value = "";
})

followerBtn.addEventListener("click", () => {
	if(!isFriends)
		document.querySelector(".friends__container").style.display = "block";
	isFriends = true;
	followersTag.style.display = "block";
	followingTag.style.display = "none";
	
	if(followers.length === 0){
		getFriendsAjax("follower")
		.then((res) => {
			followers = res;
			generateFollowers();
		})
	}
})

followingBtn.addEventListener("click", () => {
	if(!isFriends)
		document.querySelector(".friends__container").style.display = "block";
	isFriends = true;
	followingTag.style.display = "block";
	followersTag.style.display = "none";
	
	if(following.length === 0){
		getFriendsAjax("following")
		.then((res) => {
			following = res;
			generateFollowing();
		})
	}
})



function generateProfileCount(){
	interestCount[0].innerText = followersCount;
	interestCount[1].innerText = followingCount;
}

function generateFollowers(){
	followersTag.innerHTML = "";
	for(let follower of followers){
		followersTag.innerHTML += `<div class="friend__card">
                    <div class="card__about">
                        <img src="https://i.pravatar.cc/150?img=47" alt="">
                        <p>${follower}</p>
                    </div>
                </div>`;
    }
}

function generateFollowing(){
	followingTag.innerHTML = "";
	for(let followingUser of following){
		followingTag.append(generateFriendCard(followingUser));
    }
}

function handleFriendAdded(message){
	followingTag.prepend(generateFriendCard(friendIdTag.value));
	following = [friendIdTag.value, ...following];
	followingCount += 1;
	successNotification(`Started following ${followingTag}`);
	generateProfileCount();
}

function handleError(message){
	console.log(message);
}

function generateFriendCard(followingUser){
	const friendCard = document.createElement("div");
	friendCard.className = "friend__card";
	
	friendCard.innerHTML = `<div class="card__about">
                        <img src="https://i.pravatar.cc/150?img=47" alt="">
                        <p>${followingUser}</p>
                    </div>`;
	
	const button = document.createElement("button");
	button.className = "button";
	button.innerText = "Following";
	button.addEventListener("click", () => {
		unfollowAjax(followingUser)
		.then(()=>{
			handleRemoveFriend(followingUser, friendCard);
		});
	})
	
	friendCard.appendChild(button);
	
	return friendCard;
}

function handleRemoveFriend(followingUser, friendCard){
	successNotification("Unfollowed " + followingUser);
	followingCount-=1;
	generateProfileCount();
	friendCard.remove();
}










