document.addEventListener("DOMContentLoaded",function(){
	const productsName=document.querySelectorAll(".productsname");
	const imageContainer=document.getElementById("imageContainer");

	imageContainer.innerHTML="";
for (let i = 0; i < productsName.length; i++) {
        let product = productsName[i].textContent.trim();
        
        let img = document.createElement("img");
        img.src = "/img/"+product + ".jpg";
        img.alt = product.trim();
        
        imageContainer.appendChild(img);
	}
	    function scrollImages() {
        var container = document.getElementById("imageContainer");
        container.scrollLeft += 1; // 1ピクセルずつ右にスクロール
        // 最後までスクロールしたら最初に戻る
        if (container.scrollLeft >= container.scrollWidth - container.clientWidth) {
            container.scrollLeft = 0;
        }
    }
    // 100ミリ秒ごとにscrollImages関数を呼び出す
    setInterval(scrollImages, 100);
	
});