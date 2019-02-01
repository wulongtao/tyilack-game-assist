package com.tyilack.assist.util;

import java.math.BigDecimal;

/**
 *
 * @author giantan
 * @date 2018/7/24
 */
public class NumberUtil {

    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        if (str == null) {
            return false;
        } else {
            str = str.trim();
        }
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            //异常 说明包含非数字。
            return false;
        }
        return true;
    }

    /**
     * 保留两位小数，如果是整数，则不需要保留
     * @param value
     * @return
     */
    public static String scaleTwo(String value) {
        if (!isNumeric(value)) {
            return value;
        }

        double dValue = new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        if (dValue == (long)dValue) {
            return (long)dValue + "";
        }

        return new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 保留两位小数，如果是整数，则不需要保留
     * @param value
     * @return
     */
    public static String scaleTwoDouble(String value) {
        if (!isNumeric(value)) {
            return value;
        }

        double dValue = new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        if (dValue == (long)dValue) {
            return (long)dValue + "";
        }

        return new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString();
    }

}
