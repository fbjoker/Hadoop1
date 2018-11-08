package com.alex.hadoop.proj3;

import java.io.File;
import java.io.IOException;

public class Testfile {

    public static void main(String[] args) throws IOException {
        for (int i=0;i<100000;i++){
            File file = new File("D:\\test\\" + i + ".txt");
            file.createNewFile();

        }
    }
}
