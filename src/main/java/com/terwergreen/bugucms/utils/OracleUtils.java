package com.terwergreen.bugucms.utils;

import oracle.sql.CLOB;
import oracle.sql.NCLOB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

/**
 * Oracle帮助类
 */
public class OracleUtils {
    /**
     * Clob字段的通用转换
     *
     * @return 转好的字符串，
     **/
    public static String ClobToString(CLOB clob) throws SQLException, IOException {
        String reString = "";   //拼接变量
        Reader is = clob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString(); //转换成字符串，进行返回
        return reString;
    }

    /**
     * NClob字段的通用转换
     *
     * @return 转好的字符串，
     **/
    public static String NClobToString(NCLOB nclob) throws SQLException, IOException {
        String reString = "";   //拼接变量
        Reader is = nclob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString(); //转换成字符串，进行返回
        return reString;
    }
}
