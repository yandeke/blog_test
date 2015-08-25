package com.ydk.util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *@author :ydk
 *@date: 2015-7-6
 *@version: 
 */

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 把一个对象转换成json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){
        return gson.toJson(object);
    }

    /**
     * 把json字符串转换成一个对象
      * @param json
     * @param clazz 对象类型
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Class<T> clazz){
       return gson.fromJson(json,clazz);
    }

    /**
     * 支持列表类型转换 比如：TypeToken<List<Person>>(){}.getType()
     * @param json
     * @param type  new TypeToken<List<Person>>(){}.getType()
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Type type){
        return (T)gson.fromJson(json,type);
    }


    public static void jsonResponse(HttpServletResponse response,int code,String msg, Object obj){
        jsonResponse(response,"utf-8",code,msg,obj);
    }

    public static void jsonResponse(HttpServletResponse response,String encode,int code,String msg, Object obj){
        response.setContentType("application/json");
        response.setCharacterEncoding(encode);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", String.valueOf(code));
        map.put("msg", msg);
        map.put("data", obj);
        try {
            response.getWriter().write(JsonUtil.toJson(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJsonStringObject(int code,String msg, Object obj) throws IOException{
        Map<String, Object> map = new HashMap<String, Object>();
        if (code < 1) {
            if (msg == null || msg.length() == 0) {
                msg = "处理错误，请稍后再试";
            }
        }
        map.put("code", String.valueOf(code));
        map.put("msg", msg);
        map.put("data", obj);

        //    没Gson好用 一些转化会报错 resin下也不能使用
    /*    JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();*/

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(map);
    }
}
