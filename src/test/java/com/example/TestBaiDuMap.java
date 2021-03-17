package com.example;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestBaiDuMap {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getCoordinate() {
		String lng = "116.55639";
		String lat = "39.876987";
        String key = "2nzmO52uNLLtCBRzmRWUsLigs2mqEtyd";
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + key + "&output=json&coordtype=wgs84ll&location=" + lat + "," + lng;
        String json = restTemplate.getForObject(url, String.class);
        JSONObject obj = JSONObject.fromObject(json);
        String formatted_address = obj.getJSONObject("result").getString("formatted_address");
        String business = obj.getJSONObject("result").getString("business");
        System.out.println("formatted_address = " + formatted_address);
        System.out.println("business = " + business);
    }

    @Test
    public void getLngAndLat() {
        String addr = "太原";
        String key = "vmoG32fVwWTqu8X3T7xdkLZAH09tFtDX";
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + addr + "&output=json&ak=" + key;
        String json = restTemplate.getForObject(url, String.class);
        JSONObject obj = JSONObject.fromObject(json);
        double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
        double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
        System.out.println("lng = " + lng);
        System.out.println("lat = " + lat);
    }
}
