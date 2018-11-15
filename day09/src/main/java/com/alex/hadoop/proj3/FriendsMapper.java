package com.alex.hadoop.proj3;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FriendsMapper  extends Mapper<Text,Text,Text,Text> {
    Text k=new Text();
    Text v=new Text();
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        v.set(key.toString());

        String[] s = value.toString().split(",");
        for (String s1 : s) {
            if (StringUtils.isNotEmpty(s1)){

                k.set(s1);
                context.write(k,v);
            }


        }
    }
}
