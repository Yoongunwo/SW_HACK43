// Google Maps API를 초기화하는 함수
let map;

function initMap() {
    // 초기 위치를 설정합니다 (위도: 37.7749, 경도: -122.4194)
    var initialLocation = { lat: 37.7749, lng: -122.4194 };

    // 맵을 생성하고 초기 위치로 설정합니다
    var map = new google.maps.Map(
        document.getElementById('map'), {
            center: initialLocation,
            zoom: 10
        });

    // 마커를 생성하고 초기 위치에 추가합니다
    var marker = new google.maps.Marker({
        position: initialLocation,
        map: map,
        title: 'Initial Location'
    });

    // 사용자가 새로운 위치를 클릭할 때마다 마커를 이동합니다
    map.addListener('click', function(event) {
        marker.setPosition(event.latLng);
        map.panTo(event.latLng);
    });
}
