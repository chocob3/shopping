document.addEventListener("DOMContentLoaded",function(){
	const comment=document.querySelectorAll(".comment");
	const productName=document.querySelector(".productsName");
	const changeDiv=document.getElementById("changeDiv");
	const jsonFile = '/js/showProducts.json';

	async function loadJSON(){
		try{
			const response=await fetch(jsonFile);
			const data=await response.json();
			return data;
		}catch(error){
			console.error("Jsonファイルの読み込みに失敗");
		}
	}
	
	function changeDescription(event){
	    const  myPara=document.createElement("p");
	    myPara.textContent=event.target.id;
		const inputBox=document.createElement("input");
		const changeBtn=document.createElement("input");
		changeBtn.type="submit"
		changeBtn.textContent="変更を決定"
		inputBox.type="text";
		inputBox.id=myPara;
		myPara.appendChild(inputBox);
		changeDiv.appendChild(myPara);
		changeDiv.appendChild(changeBtn);
		
		changeBtn.addEventListener("click", function(){
		// 入力ボックスとボタンを元の状態に戻す
		changeDiv.innerHTML = "";
		});
		
	}	
	
	comment.forEach(async cell=>{
		const cellName=cell.id;
		const jsonData= await loadJSON();
		if(jsonData){
			const product=jsonData.find(item=>item.name==cellName);
			if(product){
				cell.textContent=product.description;
			}else{
				cell.textContent="当該商品の説明は更新中です";
			}
			cell.addEventListener("click",changeDescription)
		}
	})

})