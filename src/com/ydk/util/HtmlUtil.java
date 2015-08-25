package com.ydk.util;
/**
 *@author :ydk
 *@date: 2015-7-7
 *@version: 
 */
public class HtmlUtil {
	public static String htmlEscape(String input) {
        if(StringUtil.isNotEmpty(input)){
            return input;
        }
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#39;");   //IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
        input = input.replaceAll("\"", "&quot;"); //双引号也需要转义，所以加一个斜线对其进行转义
        input = input.replaceAll("\n", "<br/>");  //不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
        return input;
    }
	public static void main(String[] args) {
		System.out.println(htmlEscape("<a href='naxienian'>womendoushi</a>"));
	}
}
