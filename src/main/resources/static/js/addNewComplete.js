document.addEventListener("DOMContentLoaded",function(){
	const productName=document.getElementById("productName");
	const imageContainer=document.getElementById("imageContainer");
	
	imageContainer.innerHTML="";
	let proName=productName.textContent.trim();
	let img=document.createElement("img");
	let mypara=document.createElement("p");
	mypara.textContent="写真"
	img.src="/img/"+proName+".jpg";
	img.alt=proName;
	
	imageContainer.appendChild(img);
	imageContainer.appendChild(img);
})