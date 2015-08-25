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
        input = input.replaceAll("'", "&#39;");   //IE�ݲ�֧�ֵ����ŵ�ʵ������,��֧�ֵ����ŵ�ʵ����,�ʵ�����ת���ʵ����,�����ַ�ת���ʵ������
        input = input.replaceAll("\"", "&quot;"); //˫����Ҳ��Ҫת�壬���Լ�һ��б�߶������ת��
        input = input.replaceAll("\n", "<br/>");  //���ܰ�\n�Ĺ��˷���ǰ�棬��Ϊ��Ҫ��<��>���ˣ������ͻᵼ��<br/>ʧЧ��
        return input;
    }
	public static void main(String[] args) {
		System.out.println(htmlEscape("<a href='naxienian'>womendoushi</a>"));
	}
}
