package com.alex.hadoop.proj3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FriendsMapper2 extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("\t");

        v.set(split[0]);
        String[] s = split[1].split(",");
        for (int i = 0; i < s.length; i++) {
            for (int j = i + 1; j < s.length; j++) {
                if (s[i].compareTo(s[j]) > 0) {
                    k.set(s[i] + "-->" + s[j]);

                } else {
                    k.set(s[j] + "-->" + s[i]);

                }
                context.write(k,v);

            }
        }
    }
}
