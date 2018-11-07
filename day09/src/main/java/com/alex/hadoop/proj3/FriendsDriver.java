package com.alex.hadoop.proj3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FriendsDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf=new Configuration();
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR,":");
        Job job = Job.getInstance(conf);
        job.setMapperClass(FriendsMapper.class);
        job.setReducerClass(FriendsReducer.class);

        //设置输入格式
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapOutputKeyClass(Text.class);

        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("C:\\Hadoop\\in"));
        FileOutputFormat.setOutputPath(job,new Path("C:\\Hadoop\\tmp"));



        Job job2 = Job.getInstance(new Configuration());
        job2.setMapperClass(FriendsMapper2.class);
        job2.setReducerClass(FriendsReducer2.class);

        job2.setMapOutputValueClass(Text.class);
        job2.setMapOutputKeyClass(Text.class);

        job2.setOutputValueClass(Text.class);
        job2.setOutputKeyClass(Text.class);
        FileInputFormat.setInputPaths(job2,new Path("C:\\Hadoop\\tmp\\"));
        FileOutputFormat.setOutputPath(job2,new Path("C:\\Hadoop\\out"));

        boolean b = job.waitForCompletion(true);
        if(b){

            boolean b2 = job2.waitForCompletion(true);

            System.exit(b2?1:0);
        }



    }
}
