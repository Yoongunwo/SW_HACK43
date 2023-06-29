// Google Maps API를 초기화하는 함수
function initMap() {
    // 두 지점의 위도와 경도

    var pointA = { lat: 37.7749, lng: -122.4194 }; // 출발지
    var pointB = { lat: 34.0522, lng: -118.2437 }; // 도착지

    // 맵을 생성하고 초기 위치로 설정합니다
    var map = new google.maps.Map(document.getElementById('map'), {
        center: pointA,
        zoom: 10
    });

    // DirectionsService와 DirectionsRenderer 객체를 생성합니다
    var directionsService = new google.maps.DirectionsService();
    var directionsRenderer = new google.maps.DirectionsRenderer({
        map: map
    });

    // DirectionsService로 경로를 요청합니다
    calculateAndDisplayRoute(directionsService, directionsRenderer, pointA, pointB);
}

// 경로를 계산하고 표시하는 함수
function calculateAndDisplayRoute(directionsService, directionsRenderer, pointA, pointB) {
    directionsService.route(
        {
            origin: pointA,
            destination: pointB,
            travelMode: 'DRIVING'
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
