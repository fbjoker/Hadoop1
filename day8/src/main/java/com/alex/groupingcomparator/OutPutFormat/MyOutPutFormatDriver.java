package com.alex.groupingcomparator.OutPutFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyOutPutFormatDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(MyOutPutFormatDriver.class);

        job.setOutputFormatClass(MyOutPutFormat.class);

        FileInputFormat.setInputPaths(job,new Path("C:\\Hadoopdata\\in"));
        FileOutputFormat.setOutputPath(job,new Path("C:\\Hadoopdata\\out"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?1:0);
    }
}
