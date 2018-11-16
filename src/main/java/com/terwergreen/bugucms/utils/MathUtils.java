package com.terwergreen.bugucms.utils;

/**
 * @Author Terwer
 * @Date 2018/10/17 10:58
 * @Version 1.0
 * @Description 数学处理
 **/
public class MathUtils {
    /**
     * 行列式逆序数
     *
     * @param arrays 源行列式
     * @return 逆序数
     */
    public static int Hanglieshi(String[] arrays) {
        int sum = 0;
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(arrays[j]) > Integer.parseInt(arrays[i])) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
