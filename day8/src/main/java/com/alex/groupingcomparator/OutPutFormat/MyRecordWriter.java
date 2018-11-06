package com.alex.groupingcomparator.OutPutFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyRecordWriter extends RecordWriter<LongWritable, Text> {

    //两个输出路径

    private FSDataOutputStream taget;
    private  FSDataOutputStream other;
    public MyRecordWriter(TaskAttemptContext job) throws IOException {
        Configuration conf = job.getConfiguration();

        FileSystem fs = FileSystem.get(conf);

        String path = conf.get(FileOutputFormat.OUTDIR);

        taget=fs.create( new Path(path+"\\taget.log"));
        other=fs.create(new Path(path+"\\other.log"));



    }

    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        String str = value.toString() + "\r\n";
        if(str.contains("atguigu")){
            taget.write(str.getBytes());
        }else {
            other.write(str.getBytes());
        }
    }

    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(taget);
        IOUtils.closeStream(other);
    }
}
