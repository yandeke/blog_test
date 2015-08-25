package com.ydk.util;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *@author :ydk
 *@date: 2015-7-6
 *@version: 
 */

public class CookieUtil {

    /**
     * �������ƶ�ȡCookie
     *
     */
    public static String readCookie(HttpServletRequest request, HttpServletResponse response,
                               String name) throws ServletException, IOException {
        String value = null;
        if (name != null) {
            Cookie cookies[] = request.getCookies();
            if (cookies != null && cookies.length >= 2) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (name.equals(cookie.getName())) {
                        value = cookie.getValue();
                    }
                }
            }
        }
        return value;
    }

    /**
     * ���cookie
     */
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * дCookie
     */
    public static void writeCookie(HttpServletRequest request, HttpServletResponse response,
                              String name, String value, int time) throws ServletException, IOException {

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(time);
        response.addCookie(cookie);
    }

    /*@Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = readCokie(request, response, "username");
        System.out.println(username);
        clearCokie(request, response);
        writeCokie(request, response, "username", "username", 30);
    }*/
}
