package com.alex.groupingcomparator;

import org.apache.commons.lang.ObjectUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OderDriver  {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(OderDriver.class);

        job.setMapperClass(OrderMapper.class);
        job.setReducerClass( OrderReducer.class);

        job.setGroupingComparatorClass(mygrouping.class);


        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("C:\\Hadoopdata\\in"));
        FileOutputFormat.setOutputPath(job,new Path("C:\\Hadoopdata\\out"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?1:0);



    }
}
