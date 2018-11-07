package com.alex.hadoop.proj2m2;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMapper extends Mapper<LongWritable, Text,FlowBean, Text> {
    FlowBean k=new FlowBean();
    Text v=new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] s = value.toString().split("\t");
         v.set(s[0]);
         int up= Integer.parseInt(s[1]);
         int down=Integer.parseInt(s[2]);

         k.set(up,down);

         context.write(k,v);


    }
}
