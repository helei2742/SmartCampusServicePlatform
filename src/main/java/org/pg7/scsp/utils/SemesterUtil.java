package org.pg7.scsp.utils;


import java.time.LocalDateTime;

public class SemesterUtil {
    public static boolean isAllowedSemester(String semester) {
        if(semester.charAt(5) != '春'&&semester.charAt(5) != '秋'){
            return false;
        }
        int year = 0;
        for (int i = 0; i < 4; i++) {
            int a = semester.charAt(i) - '0';
            if(a<=9&&a>=0){
                year = year*10+a;
            }else {
                return false;
            }
        }
        return year < 3000 && year >= 1900;
    }

    public static String getCurrentSemester(){
        LocalDateTime now = LocalDateTime.now();

        int mouth = now.getMonthValue();
        int year = now.getYear();
        return year+"年"+(mouth<=6?"春":"秋")+"季";
    }
}
