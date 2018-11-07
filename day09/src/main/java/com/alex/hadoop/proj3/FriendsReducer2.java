package com.alex.hadoop.proj3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FriendsReducer2  extends Reducer<Text,Text,Text,Text> {
Text v= new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer sb=new StringBuffer();

        for (Text value : values) {
            sb.append(value).append(" ");
        }
        v.set(sb.toString());

        context.write(key,v);
    }
}
