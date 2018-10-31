package top.sun;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/11 0011 8:03
 */
public class TestInteger {
    public static void main(String[] args) {
        String s="100";
        int i = Integer.parseInt(s);
        Integer integer = Integer.valueOf(s);
        System.out.println(i==integer);
        String s1 = String.valueOf(i);

    }
}
