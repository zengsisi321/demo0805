package com.example.demo.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 * 天气
 * @author 99200
 */
public class WeatherUtil {

    private final static String key="oiW9tmxd4VosD94T8svzhhB7NW9Vc1yB";

    /**
     * 天气
     * @param districtId
     * @return
     */
    public static JSONObject weather(String districtId) {
        String url = "http://api.map.baidu.com/weather/v1/?district_id=DISTRICTID&data_type=all&ak=KEY"
            .replace("DISTRICTID", districtId)
            .replace("KEY",key);
        String json = new RestTemplate().getForObject(url, String.class);

        return JSONObject.parseObject(json);
    }


    /**
     * 搜索地点
     * @param query
     * @param tag
     * @param region
     * @return
     */
    public static JSONObject SearchByLocation(String query, String tag, String region) {
        String url = "http://api.map.baidu.com/place/v2/search?query=QUERY&tag=TAG&region=REGION&output=json&ak=KEY"
            .replace("QUERY", query)
            .replace("TAG", tag)
            .replace("REGION", region)
            .replace("KEY",key);
        String json = new RestTemplate().getForObject(url, String.class);
        return JSONObject.parseObject(json);
    }


    /**
     * 输入提示
     * @param query
     * @param region
     * @return
     */
    public static JSONObject importHint(String query, String region) {
        String url = "http://api.map.baidu.com/place/v2/suggestion?query=QUERY&region=REGION&city_limit=true&output=json&ak=KEY"
            .replace("QUERY", query)
            .replace("REGION", region)
            .replace("KEY",key);
        String json = new RestTemplate().getForObject(url, String.class);
        return JSONObject.parseObject(json);
    }

    /**
     * 获取ip
     * @param ip
     * @return
     */
    public static JSONObject gainIp(String ip){

        String url = "http://api.map.baidu.com/location/ip?ak=KEY&ip=IP&coor=bd09ll"
            .replace("IP", ip)
            .replace("KEY",key);
        String json = new RestTemplate().getForObject(url, String.class);
        return JSONObject.parseObject(json);
    }

}
