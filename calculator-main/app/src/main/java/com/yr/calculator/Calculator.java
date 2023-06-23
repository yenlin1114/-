package com.yr.calculator;

import android.util.Log;

public class Calculator {

    private String TAG = getClass().getSimpleName();

    public String basicOperate(String operator, String num1, String num2){
        if (!num1.equals("") && !num2.equals("")) {
            try {
                double n1 = Double.parseDouble(num1);
                double n2 = Double.parseDouble(num2);
                switch (operator){
                    case "+":
                        return format(n1 + n2);
                    case "-":
                        return format(n1 - n2);
                    case"*":
                        return format(n1 * n2);
                    case "/":
                        return format(n1 / n2);
                    default:
                        return "ERROR";
                }
            } catch (NumberFormatException e) {
                return "ERROR";
            }
        }
        return "";
    }

    public String factorial(String num) {
        try {
            double n = Double.parseDouble(num);

            if (n == 0) return format(1);
            if (n < 0 || (n % 1) != 0) return "ERROR";

            int product = 1;
            for (int i = 1; i <= n; i++) {
                product *= i;
            }
            return format(product);
        }catch (NumberFormatException e){
            return "ERROR";
        }
    }

    private String format(double num) {
        if (num % 1 == 0) {
            return Integer.toString((int) num);
        } else {
            return Double.toString(num);
        }
    }
}
