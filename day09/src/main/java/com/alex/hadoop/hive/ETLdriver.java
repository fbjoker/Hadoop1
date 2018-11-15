package com.alex.hadoop.hive;

import com.alex.hadoop.etl.ETLDriver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ETLdriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(ETLDriver.class);
        job.setMapperClass(ETLMapper.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\HadoopCluster\\Hadoopdata\\video"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\HadoopCluster\\Hadoopdata\\out"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?1:0);

    }
}
