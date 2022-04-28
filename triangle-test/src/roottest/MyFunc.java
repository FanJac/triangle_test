package roottest;

import java.util.ArrayList;
import java.util.Collections;

public class MyFunc {
    public static ArrayList<String> generate_test_result(int[] array, int type) {
        int a_nom = (array[0] + array[1]) / 2;
        int b_nom = (array[2] + array[3]) / 2;
        int c_nom = (array[4] + array[5]) / 2;
        int[] a_list = {array[0] - 1, array[0], array[0] + 1, a_nom, array[1] - 1, array[1], array[1] + 1};
        int[] b_list = {array[2] - 1, array[2], array[2] + 1, b_nom, array[3] - 1, array[3], array[3] + 1};
        int[] c_list = {array[4] - 1, array[4], array[4] + 1, c_nom, array[5] - 1, array[5], array[5] + 1};
        ArrayList<String> out_result = new ArrayList<>();
        String split_line = "+---------------------------------------------------------------------------------------+";
        out_result.add(split_line);
        out_result.add("｜序号\t｜a边长\t｜b边长\t｜c边长\t｜三角形类型\t｜");
        out_result.add(split_line);

        if (type == 0) {
            int index = 1;
            for (int j = 1; j <= 5; j++) {
                String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                out_result.add("｜" + index + "\t" + "｜" + a_nom + "\t" + "｜" + b_nom + "\t" + "｜" +
                        c_list[j] + "\t" + "｜" + result_type + "\t" + "｜");
                index++;
            }
            for (int j = 1; j <= 5; j++) {
                String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                out_result.add("｜" + index + "\t" + "｜" + a_nom + "\t" + "｜" + b_list[j] + "\t" + "｜" +
                        c_nom + "\t" + "｜" + result_type + "\t" + "｜");
                index++;
            }
            for (int j = 1; j <= 5; j++) {
                String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                out_result.add("｜" + index + "\t" + "｜" + a_list[j] + "\t" + "｜" + b_nom + "\t" + "｜" +
                        c_nom + "\t" + "｜" + result_type + "\t" + "｜");
                index++;
            }
        }
        if (type == 1) {
            int index = 1;
            for (int j = 0; j < 7; j++) {
                String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                out_result.add("｜" + index + "\t" + "｜" + a_nom + "\t" + "｜" + b_nom + "\t" + "｜" +
                        c_list[j] + "\t" + "｜" + result_type + "\t" + "｜");
                index++;
            }
            for (int j = 0; j < 7; j++) {
                String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                out_result.add("｜" + index + "\t" + "｜" + a_nom + "\t" + "｜" + b_list[j] + "\t" + "｜" +
                        c_nom + "\t" + "｜" + result_type + "\t" + "｜");
                index++;
            }
            for (int j = 0; j < 7; j++) {
                String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                out_result.add("｜" + index + "\t" + "｜" + a_list[j] + "\t" + "｜" + b_nom + "\t" + "｜" +
                        c_nom + "\t" + "｜" + result_type + "\t" + "｜");
                index++;
            }
        }
        if (type == 2) {
            int index = 1;
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 5; j++) {
                    for (int k = 1; k <= 5; k++) {
                        String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                        out_result.add("｜" + index + "\t" + "｜" + a_list[i] + "\t" + "｜" + b_list[j] + "\t" + "｜" +
                                c_list[k] + "\t" + "｜" + result_type + "\t" + "｜");
                        index++;
                    }
                }
            }
        }
        if (type == 3){
            int index = 1;
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    for (int k = 0; k < 7; k++) {
                        String result_type = whichTriangle(a_nom, b_nom, c_list[j], array);
                        out_result.add("｜" + index + "\t" + "｜" + a_list[i] + "\t" + "｜" + b_list[j] + "\t" + "｜" +
                                c_list[k] + "\t" + "｜" + result_type + "\t" + "｜");
                        index++;
                    }
                }
            }
        }
        out_result.add(split_line);
        return out_result;
    }

    public static String whichTriangle(int a, int b, int c, int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        if (a < array[0] || a > array[1] || b < array[2] || b > array[3] || c < array[4] || c > array[5]) {
            return "非法输入数据";
        }
        list.add(a);
        list.add(b);
        list.add(c);
        Collections.sort(list);
        int min = list.get(0);
        int mid = list.get(1);
        int max = list.get(2);
        if (min > 0) {
            if (min + mid <= max) {
                return "非三角形"; //不能构成三角形
            }
            else{
                if (min == mid || mid == max) {
                    if(min == max){
                        return "等边三角形";
                    }
                    return "等腰三角形"; //等腰三角形
                }
            }
        }
        return "非三角形"; //不能构成三角形
    }
}
