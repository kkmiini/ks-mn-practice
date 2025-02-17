package com.ksinfo.pointgame.util;

import java.util.Random;

public class RandomNumberUtil {
    public static int generateUniqueThreeDigitNumber() {
        Random random = new Random();
        int num;
        
        while (true) {
            num = random.nextInt(900) + 100; // 100~999 범위
            String numStr = String.valueOf(num);
            
            if (numStr.charAt(0) != numStr.charAt(1) && 
                numStr.charAt(0) != numStr.charAt(2) && 
                numStr.charAt(1) != numStr.charAt(2)) {
                return num;
            }
        }
    }
}