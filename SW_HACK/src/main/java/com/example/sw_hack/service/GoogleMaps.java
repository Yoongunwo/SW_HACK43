package com.example.sw_hack.service;

import com.example.sw_hack.admin.LatLng;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=
// Montreal&alternatives=true&key=AIzaSyBiol6ZqdyDaOKqUS5EoI0Syk38c02lnpE
@Service
public class GoogleMaps {

    private final RestTemplate restTemplate;

    public GoogleMaps(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> makeDiretcion(String origin, String destination, boolean alternatives) throws JsonProcessingException {
        String apiUrl = "https://maps.googleapis.com/maps/api/directions/json";
        String param1 = "?origin="+origin+"&destination="+destination+"&alternatives="+alternatives+"&mode=walking"
                +"&key=AIzaSyBiol6ZqdyDaOKqUS5EoI0Syk38c02lnpE";
        String url = apiUrl+param1;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,null,String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode routes = root.path("routes");
        // 이제 'routes'나 JSON의 다른 부분 내에 있는 개별 필드에 접근할 수 있습니다.
        // 필요하다면 이것들을 Java 객체로 변환할 수도 있습니다.
        int routIndex=0;
        // 예를 들어, 각 구간(leg)의 거리를 모두 출력하려면 다음과 같이 할 수 있습니다:
        for (JsonNode route : routes) {
            for (JsonNode leg : route.path("legs")) {
                for(JsonNode step: leg.path("steps")){
                    String encodedPolyline = step.path("polyline").path("points").asText();
                    List<LatLng> decodedPath = decode(encodedPolyline);
                    for (LatLng point : decodedPath) {
                        System.out.println(point);
                    }
                }
            }
            routIndex++;
        }
        System.out.println(routIndex);
        return response;
    }

    public static List<LatLng> decode(final String encodedPath) {
        int len = encodedPath.length();

        // For speed we preallocate to an upper bound on the final length, then
        // truncate the array before returning.
        final List<LatLng> path = new ArrayList<>();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new LatLng(lat * 1e-5, lng * 1e-5));
        }

        return path;
    }

        private static final String API_KEY = "AIzaSyBiol6ZqdyDaOKqUS5EoI0Syk38c02lnpE";
        private static final String LATITUDE = "37.5665";
        private static final String LONGITUDE = "126.9780";
        private static final String RADIUS = "1000";

        public double[][] getRandomPlace(long km) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                    + LATITUDE + ","
                    + LONGITUDE + "&radius="
                    + String.valueOf(Integer.parseInt(RADIUS)*km) + "&key="
                    + API_KEY;

            String response = restTemplate.getForObject(url, String.class);

            // JSON 문자열을 파싱하기 위해 JsonParser를 사용합니다.
            JsonParser jsonParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = jsonParser.parseMap(response);
            double[][] list = extractLatLngs(map);


            // 결과를 가져옵니다.
            ArrayList<Object> places = (ArrayList<Object>) map.get("results");

            // 결과 중 랜덤하게 하나를 선택합니다.
//            Random rand = new Random();
//            Map<String, Object> place = (Map<String, Object>) places.get(rand.nextInt(places.size()));

            return list;

    }

    private double[][] extractLatLngs(Map<String, Object> map) {
        List<Map<String, Object>> latLngList = new ArrayList<>();
        List<Map<String, Object>> results = (List<Map<String, Object>>) map.get("results");
        double arr[][] = new double [results.size()][2];
        int i=0;
        for (Map<String, Object> result : results) {
            Map<String, Object> geometry = (Map<String, Object>) result.get("geometry");
            Map<String, Object> location = (Map<String, Object>) geometry.get("location");

//            Map<String, Object> resultMap = new HashMap<>();
            arr[i][0]= (double) location.get("lat");
            arr[i++][1]=(double)location.get("lng");
//            resultMap.put("lat", location.get("lat"));
//            resultMap.put("lng", location.get("lng"));
//            latLngList.add(resultMap);
        }
        return arr;
    }
}
