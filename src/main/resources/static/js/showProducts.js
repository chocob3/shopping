document.addEventListener("DOMContentLoaded", function() {
  //サイドバー
  const button = document.getElementById("openbtn");
  const closeBtn = document.getElementById("closebtn");
  const sidebar = document.getElementById("mySidebar");
  button.addEventListener("click", openNav);
  closeBtn.addEventListener("click", closeNav);
  //ポップアップウィンドウ
  const jsonFile = '/js/showProducts.json';
  const btnCells = document.querySelectorAll(".btn");

  const information = document.getElementById("information");
 
  
  function openNav() {
    sidebar.style.width = "250px";
  }

  function closeNav() {
    sidebar.style.width = "0";
  }

  async function loadJSON() {
    try {
      const response = await fetch(jsonFile);
      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Jsonファイルの読み込みに失敗しました");
    }
  }

  async function showDescription(event) {
    const productName = event.target.value.trim();
    const jsonData = await loadJSON();
    if (jsonData) {
		const product=jsonData.find(item=>item.name===productName);
		if(product){
			information.textContent=product.description;
		}else{
			information.textContent="当該商品の説明は更新中です";
		}
			
		} 
    
    const but = document.createElement("button");
    but.addEventListener("click", () => {
      information.textContent = "";
    });
    but.textContent = "X";
    information.appendChild(but);
  }

  btnCells.forEach(cell => {
    cell.addEventListener("click", showDescription);
  });
  
   //在庫がないときにボタンを消す
  const errmsg=document.getElementById("errmsg");
  const purchase=document.getElementById("purchase");
  if(errmsg.innerText==="現在在庫はございません"){
	purchase.style.display="none";
  }
  if(errmsg.innerText===""){
	purchase.style.display="block";
  }
})


/*function showDescription(event){
	if(closestyle.display==="none"){
	const productName=event.target.value.trim();
	var url='showProducts.json';
	fetch(url).then(function (data){
		return data.json();//読み込むデータをjsonに設定
	})
	.then(function(json){
		for(var i=0; i<json.length; i++){
			if(productName===json[i].name){
				information.textContent=json[i].description;
				closestyle.display="block";
			}
		}
	});	
	}else{
		closestyle.display="none";
	}
}
close.addEventListener("click",function(){
	boxstyle.display="none";
}) */

/*function showDescription(event){
	const productName=event.target.textContent.trim();
	const xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4&&xhr.status===200){
			const productData=JSON.parse(xhr.responseText);
			const product=productData.find(product=>product.name===productName);
			if(product){
				alert(product.description);
			}else{
				alert("情報はありません");
			}
		}
	};
	xhr.open("GET","../js/showProducts.json",true);
	shr.send();  */
