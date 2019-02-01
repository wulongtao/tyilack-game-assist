package com.tyilack.assist.util;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author giantan
 * @date 2018/6/12
 */
@Slf4j
public class PatternUtils {
    /**
     * 提取双引号里面的内容
     */
    private static final Pattern DOUBLE_QUOTES_PATTERN = Pattern.compile("\"(.*?)\"");

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param content
     * @return
     */
    public static List<String> getSubContents(String content, String regex){
        List<String> contentList = new ArrayList<String>();
        try {
            // 匹配的模式
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(content);
            while (m.find()) {
                int i = 1;
                contentList.add(m.group(i));
                i++;
            }
        } catch (Exception e) {
            log.error("正则表达式解析错误，正则为：${(.*?)}", e);
        }

        return contentList;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param content
     * @param regex
     * @return
     */
    public static String getContentInLabel(String content,String regex){
        Pattern pattern = Pattern.compile(regex);// 匹配的模式
        Matcher m = pattern.matcher(content);
        while(m.find()){
            return m.group(1);
        }

        return "";
    }

    /**
     * 在给定的字符串文本中，提取被双引号包住的内容
     * @param content
     * @return
     */
    public static Set<String> getContentsInString(String content) {
        Set<String> contentSet = new HashSet<>();
        Matcher m =  DOUBLE_QUOTES_PATTERN.matcher(content);
        while(m.find())
        {
            contentSet.add(m.group());
        }

        return contentSet;
    }


}