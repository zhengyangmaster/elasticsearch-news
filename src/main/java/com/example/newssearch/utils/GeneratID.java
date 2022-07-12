package com.example.newssearch.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/11
 */
public class GeneratID {

    public static String getDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getRandomNum(int num){
        String numStr = "";
        for(int i = 0; i < num; i++){
            numStr += (int)(10*(Math.random()));
        }
        return numStr;
    }
    /**
     * 生成id
     * @return
     */
    public static Long getGeneratID(){
        String sformat = "MMddhhmmssSSS";
        int num = 3;
        String idStr = getDate(sformat) + getRandomNum(num);
        Long id = Long.valueOf(idStr);
        return id;
    }


}
