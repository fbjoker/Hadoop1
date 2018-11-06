package com.alex.groupingcomparator.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {
      OrderBean k=new OrderBean();
      String fileName;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fs= (FileSplit) context.getInputSplit();
        fileName = fs.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");

        if(fileName.contains("order")){

            k.setId(split[0]);
            k.setPid(split[1]);
            k.setPname("");
            k.setFlag("order");
            k.setAmount(Integer.parseInt(split[2]));

        }else {
            k.setId("");
            k.setPid(split[0]);
            k.setPname(split[1]);
            k.setFlag("pd");
            k.setAmount(0);
        }
       // System.out.println(k);
        context.write(k,NullWritable.get());
    }
}
