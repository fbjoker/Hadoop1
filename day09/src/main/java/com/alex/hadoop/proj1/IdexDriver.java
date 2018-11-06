package com.alex.hadoop.proj1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class IdexDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(IdexDriver.class);
        job.setMapperClass(IndexMapper.class);
        job.setReducerClass(IndexReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\Hadoopdata\\in"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\Hadoopdata\\tmp"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?0:1);

        if(b){
        //2
        Job job2 = Job.getInstance(configuration);
        job2.setJarByClass(IdexDriver.class);
        job2.setMapperClass(IndexMapper2.class);
        job2.setReducerClass(IndexReducer2.class);

        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Text.class);

        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job2,new Path("D:\\Hadoopdata\\tmp\\part-r-00000"));
        FileOutputFormat.setOutputPath(job2,new Path("D:\\Hadoopdata\\out"));

        boolean b2 = job2.waitForCompletion(true);

        System.exit(b2?0:1);

        }



    }
}
