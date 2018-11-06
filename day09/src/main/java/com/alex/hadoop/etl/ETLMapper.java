package com.alex.hadoop.etl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] s = value.toString().split(" ");
        if(s.length<11){
            context.getCounter("ETLINFO","false").increment(1);
            return ;
        }else {
            for (String word:s) {


            }
            context.write(value,NullWritable.get());
            context.getCounter("ETLINFO","true").increment(1);
        }

    }
}
