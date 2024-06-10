window.onload= function(){
	const closeBtn=document.getElementById("closeBtn");
	const data=document.querySelectorAll(".data");
	closeBtn.addEventListener("click",close);
	
	function close(){
		if(closeBtn.innerText==="履歴を表示"){
			 data.forEach(function(row) {
                row.style.display = "table-row";
            });
			closeBtn.innerText="履歴をたたむ";
		}else if(closeBtn.innerText==="履歴をたたむ"){
			  data.forEach(function(row) {
                row.style.display = "none";
            });
			closeBtn.innerText="履歴を表示"
		}	
	}
	
}