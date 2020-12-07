package com.example;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestBaiDuMap {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void getCoordinate() throws IOException {
        String lat = "39.91850710095061";
        String lng = "116.4260559234063";
        String key = "2nzmO52uNLLtCBRzmRWUsLigs2mqEtyd";
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + key + "&output=json&coordtype=wgs84ll&location=" + lat + "," + lng;
        String json = restTemplate.getForObject(url, String.class);

        /*
        {
            "status": 0,
            "result": {
                "location": {
                    "lng": 116.4260559234063,
                    "lat": 39.91850710095061
                },
                "formatted_address": "北京市东城区外交部街甲46-6号",
                "business": "东单,王府井,建国门",
                "addressComponent": {
                    "country": "中国",
                    "country_code": 0,
                    "country_code_iso": "CHN",
                    "country_code_iso2": "CN",
                    "province": "北京市",
                    "city": "北京市",
                    "city_level": 2,
                    "district": "东城区",
                    "town": "",
                    "town_code": "",
                    "adcode": "110101",
                    "street": "外交部街",
                    "street_number": "甲46-6号",
                    "direction": "附近",
                    "distance": "32"
                },
                "pois": [],
                "roads": [],
                "poiRegions": [],
                "sematic_description": "",
                "cityCode": 131
            }
        }
*/

        JSONObject obj = JSONObject.fromObject(json);
        String formatted_address = obj.getJSONObject("result").getString("formatted_address");
        String business = obj.getJSONObject("result").getString("business");
        System.out.println("formatted_address = " + formatted_address);
        System.out.println("business = " + business);
    }

    @Test
    public void getLngAndLat() throws IOException {
        String addr = "北京";
        String key = "vmoG32fVwWTqu8X3T7xdkLZAH09tFtDX";
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + addr + "&output=json&ak=" + key;
        String json = restTemplate.getForObject(url, String.class);

        /*
        {
            "status": 0,
            "result": {
                "location": {
                    "lng": 116.4133836971231,
                    "lat": 39.910924547299568
                },
                "precise": 0,
                "confidence": 20,
                "comprehension": 100,
                "level": "城市"
            }
        }
         */

        JSONObject obj = JSONObject.fromObject(json);
        double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
        double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
        System.out.println("lng = " + lng);
        System.out.println("lat = " + lat);
    }
}
