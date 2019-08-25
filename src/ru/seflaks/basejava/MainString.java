package ru.seflaks.basejava;

public class MainString {
    public static void main(String[] args) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};

        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
//            System.out.println(str);
            sb.append(str).append(", ");
        }
        System.out.println(sb.toString());

        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
//        String str2 = ("ab" + str3);
        System.out.println(str1 == str2);

    }
}
