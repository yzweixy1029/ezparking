package com.jsnu.yls.graduation.plugin.utils;

/**
 * 字符串相关工具类
 *
 * Created by Obvious on 2015/9/28.
 */
public class StringUtil {

    /**
     * 首字母转大写
     *
     * @param string
     * @return
     */
    public static String firstLetterToUpper(String string) {
        char[] buffer = string.toCharArray();
        buffer[0] = Character.toUpperCase(string.charAt(0));
        return new String(buffer);
    }

}
