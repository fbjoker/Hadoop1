package com.alex.groupingcomparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {
    OrderBean k= new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        k.setId(split[0]);
        k.setPid(split[1]);
        k.setPrice(Double.parseDouble(split[2]));

        context.write(k,NullWritable.get());

    }
}
