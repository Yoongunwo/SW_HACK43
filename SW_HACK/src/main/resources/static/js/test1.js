// Google Maps API를 초기화하는 함수

navigator.geolocation.getCurrentPosition(initMap, showErrorMsg);
function showErrorMsg(error) { // 실패했을때 실행
    switch(error.code) {
        case error.PERMISSION_DENIED:
            loc.innerHTML = "이 문장은 사용자가 Geolocation API의 사용 요청을 거부했을 때 나타납니다!"
            break;

        case error.POSITION_UNAVAILABLE:
            loc.innerHTML = "이 문장은 가져온 위치 정보를 사용할 수 없을 때 나타납니다!"
            break;

        case error.TIMEOUT:
            loc.innerHTML = "이 문장은 위치 정보를 가져오기 위한 요청이 허용 시간을 초과했을 때 나타납니다!"
            break;

        case error.UNKNOWN_ERROR:
            loc.innerHTML = "이 문장은 알 수 없는 오류가 발생했을 때 나타납니다!"
            break;
    }
}

/*function initMap(goalLat, goalLng) {
    // 두 지점의 위도와 경도
    var userLat = position.coords.latitude;
    var userLng = position.coords.longitude;
    var userPoint = { lat: userLat, lng: userLng }; // 출발지
    var pointB = { lat: goalLat, lng: goalLng }; // 도착지

    // 맵을 생성하고 초기 위치로 설정합니다
    var map = new google.maps.Map(document.getElementById('map'), {
        center: userPoint,
        zoom: 10
    });

    // DirectionsService와 DirectionsRenderer 객체를 생성합니다
    var directionsService = new google.maps.DirectionsService();
    var directionsRenderer = new google.maps.DirectionsRenderer({
        map: map
    });

    // DirectionsService로 경로를 요청합니다
    calculateAndDisplayRoute(directionsService, directionsRenderer, userPoint, pointB);
}*/

// 경로를 계산하고 표시하는 함수
/*
function calculateAndDisplayRoute(directionsService, directionsRenderer, userPoint, pointB) {
    directionsService.route(
        {
            origin: userPoint,
            destination: pointB,
            travelMode: 'WALKING'
        },
        function(response, status) {
            if (status === 'OK') {
                directionsRenderer.setDirections(response);
            } else {
                window.alert('경로 요청에 실패했습니다. 오류 코드: ' + status);
            }
        }
    );
}
*/
