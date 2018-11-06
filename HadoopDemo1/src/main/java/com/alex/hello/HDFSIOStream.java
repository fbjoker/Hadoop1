package com.alex.hello;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class HDFSIOStream {

    //1. 上传
    @Test
    public void uploadByStream() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream("c:\\111.txt");
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop102:9000"), configuration, "alex");

        FSDataOutputStream fos = fileSystem.create(new Path("/1_Stream.txt"));

        IOUtils.copyBytes(fis, fos, 1024, true);

        fileSystem.close();
    }

    //2. 下载
    @Test
    public void downloadByStream() throws IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop102:9000"), configuration, "atguigu");

        FSDataInputStream fis = fileSystem.open(new Path("/1_Stream.txt"));

        FileOutputStream fos = new FileOutputStream("d:\\1_downStream.txt");

        IOUtils.copyBytes(fis, fos, 1024, true);

        fileSystem.close();
    }

    //3. 分段下载
    @Test
    public void fileDownload1() throws Exception{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://hadoop102:9000"), conf, "atguigu");

        FSDataInputStream fis = fs.open(new Path("/hadoop"));

        FileOutputStream fos = new FileOutputStream("d:\\1");

        byte [] buf = new byte[1024];

        for (int i=0; i<128 * 1024; i++) {
            int read = fis.read(buf);
            fos.write(buf);
        }

        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);

        fs.close();

    }

    @Test
    public void fileDownload2() throws Exception{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://hadoop102:9000"), conf, "atguigu");

        FSDataInputStream fis = fs.open(new Path("/hadoop"));

        FileOutputStream fos = new FileOutputStream("d:\\2");

        fis.seek(128 * 1024 *1024);

        IOUtils.copyBytes(fis, fos, 1024, true);

        fs.close();
    }
}
