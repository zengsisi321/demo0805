package com.example.demo.common.util;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

/**
 * gson工具
 */
public class GsonUtil {


    public static final Gson GSON=new Gson();


    /**
     * 隐藏公共构造函数
     */
    private GsonUtil(){
        throw new IllegalStateException("GsonUtil class");
    }


    /**
     * object转json
     * @param object
     * @return
     */
    public static String getGsonString(Object object){
        if(object!=null){
            return GSON.toJson(object);
        }
        return null;
    }


    /**
     * Json转成对象
     *
     * @param gsonString
     * @param cls
     * @return 对象
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        return GSON.fromJson(gsonString, cls);
    }

    /**
     * json转成list<T>
     *
     * @param gsonString
     * @param cls
     * @return list<T>
     */
    public static <T> List<T> gsonToList(String gsonString, Class<T> cls) {
        if (gsonString == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
        for (JsonElement jsonElement : array) {
            list.add(GSON.fromJson(jsonElement, cls));
        }
        return list;
    }

    /**
     * 判断字符串是否可以转化为JSON数组
     *
     * @param content
     * @return
     */
    public static boolean isJsonArray(String content) {
        try {
            JsonObject asJsonObject = new JsonParser().parse(content).getAsJsonObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
