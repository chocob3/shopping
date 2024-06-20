document.addEventListener("DOMContentLoaded",function(){
	const price=documet.getElementById(price);
	const name=document.getElementById(name);
	const quantity=document.getElementById(quantity);
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
	
	
	
})