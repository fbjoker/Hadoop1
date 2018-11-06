package com.alex.hadoop.etl;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text k= new Text();
    IntWritable v=new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] s = value.toString().split(" ");
        if(s.length<11){
            context.getCounter("ETLINFO","false").increment(1);
            return ;
        }else {
            for (String word:s) {

                k.set(word);
                context.write(k,v);
            }
            context.getCounter("ETLINFO","true").increment(1);
        }

    }
}
