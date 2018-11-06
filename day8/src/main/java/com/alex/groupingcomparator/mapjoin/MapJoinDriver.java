package com.alex.groupingcomparator.mapjoin;


import com.alex.groupingcomparator.OrderReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;

public class MapJoinDriver {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(MapJoinDriver.class);

        job.setMapperClass(MapJoinMapper.class);
        job.setNumReduceTasks(0);



        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        URI[] uris = new URI[1];
        uris[0] = URI.create("file:///C:/Hadoopdata/pd.txt");
        job.setCacheFiles(uris);

        FileInputFormat.setInputPaths(job,new Path("C:\\Hadoopdata\\in\\order.txt"));
        FileOutputFormat.setOutputPath(job,new Path("C:\\Hadoopdata\\out"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?1:0);


    }
}
