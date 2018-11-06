package com.alex.hadoop.proj1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class IndexMapper2 extends Mapper<LongWritable, Text,Text,Text> {
    Text k=new Text();
    Text v= new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("\t");
        k.set(split[0]);
        String val=split[1]+"-->"+split[2];
        v.set(val);
        context.write(k,v);
    }
}
