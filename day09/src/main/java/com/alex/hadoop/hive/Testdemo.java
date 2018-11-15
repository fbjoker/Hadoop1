package com.alex.hadoop.hive;

public class Testdemo {
    public static void main(String[] args) {
        String str="6gmP4nk0EOE\tmwesch\t715\tHowto & DIY\t271\t1353059\t4.76\t10757\t3640\tEAVmB5dKZZ8\twLoBDKUtCJo\tUrBeKXJrEDo\tsFCZr3ZTKLk\twfbX5pSyHdI\tWUYjhn2n_D4\th2UXPA0RoXk\t7AgUS4R7Ed0\tj9wBBkH9lg8\t3Z-FaP95Tqg\tsQg5_T4VE-g\tncgcxGeCluM\t42qUqf3w_i4\tYhDlaq9Dw8I\tkGDirxOrkWs\tKrXrXHFgHI0\tT4b8xIslhks\twZP0XUsw94U\tzgVSgQJuRtM\tI9pp-Qh-wi0\n";
        String ss="6gmP4nk0EOE\tmwesch\t715\tHowto & DIY";
        String[] split = ss.split("\t");
        System.out.println(split.length);
        String s = ETLUITLS.etlMovie(str);
        System.out.println(s);

    }
}
