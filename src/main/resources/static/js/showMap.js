window.onload = function() {
    let map;
    const para=document.getElementById("para");
    let directionsRender;
    const showRouteButton=document.getElementById("showRouteButton");
    let currentposition;
    let position;
    async function initMap() {
        position = { lat: -34.397, lng: 150.644 };
        const latlng = new google.maps.LatLng(-34.397, 150.644);
         
        map = new google.maps.Map(document.getElementById("map"), {
            center: position,
            mapId: "DEMO_MAP_ID",
            zoom: 8
        });
        
        directionsRender=new google.maps.DirectionsRenderer();
        directionsRender.setMap(map);
        
        const marker = new google.maps.Marker({
            position: position,
            map: map,
            title: "This area"
        });
        //初期の座標から土地名を取り出す
        const geocoder=new google.maps.Geocoder();
    geocoder.geocode({ 'location': latlng }, function(results, status) {
		
		const myPara=document.createElement("p");
		if (status === google.maps.GeocoderStatus.OK) {	
        if (results[0]) {
            // results[0] には逆ジオコーディングの結果が含まれます
            const location=results[0].formatted_address; // 地名や住所が含まれるフォーマットされたアドレスを表示
            myPara.textContent="本社所在地:"+location;
        } else {
            myPara.textContent='No results found';
        }
    } else {
       myPara='Geocoder failed due to: ' + status;
    }
    
    para.appendChild(myPara);
});
  //現在地を取得する
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function(geoPosition) {
                     currentposition = {
                        lat: geoPosition.coords.latitude,
                        lng: geoPosition.coords.longitude
                    };

                    new google.maps.Marker({
                        position: currentposition,
                        map: map,
                        title: "Your Location"
                    });

                    map.setCenter(currentposition);
                    
                },
                function() {
                    handleLocationError(true, map.getCenter());
                }
            );

        } else {
            handleLocationError(false, map.getCenter());
        }
    }

    initMap();
    
    function showRoute(){
		if(currentposition){
			calculateAndDisplayRoute(currentposition,position);
		}else{
			alert("現在地の取得ができませんでした");
		}
	}
	
    
    function calculateAndDisplayRoute(origin,destination){
		const directionsService=new google.maps.DirectionsService();
		directionsService.route(
			{
				origin:origin,
				destination:destination,
				travelMode: google.maps.TravelMode.DRIVING
			},
			function(response, status) {
                if (status === "OK") {
                    directionsRenderer.setDirections(response);
                } else {
                    window.alert("Directions request failed due to " + status);
                }
                }
			
		);
	}
	
	showRouteButton.addEventListener("click",showRoute);

    function handleLocationError(browserHasGeolocation, pos) {
        var infoWindow = new google.maps.InfoWindow;
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
        infoWindow.open(map);
    }
    

};
