package com.example.demo.controller;


import com.example.demo.common.util.IpUtils;
import com.example.demo.common.util.WeatherUtil;
import com.example.demo.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 天气
 * @author 99200
 */
@RestController
@Api(tags = "天气模块")
public class WeatherController {


    @GetMapping("/selectWeather")
    @ApiOperation("查询天气")
    public Result selectWeather(String districtId){

      return Result.ok(WeatherUtil.weather(districtId));
    }


    @GetMapping("/searchSites")
    @ApiOperation("地点搜索")
    public Result searchSites(String query,String tag,String region){
        return Result.ok(WeatherUtil.SearchByLocation(query,tag,region)) ;
    }


    @GetMapping("/importReminder")
    @ApiOperation("输入提示")
    public Result importReminder(String query,String region){
      return Result.ok(WeatherUtil.importHint(query,region));
    }


    @GetMapping("/getLocation")
    @ApiOperation("定位")
    public Result getLocation(HttpServletRequest request){
        String ip = IpUtils.getIpAddr(request);
        return Result.ok(WeatherUtil.sas(ip));
    }

}
