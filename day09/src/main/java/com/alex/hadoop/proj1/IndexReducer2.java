package com.alex.hadoop.proj1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class IndexReducer2  extends Reducer<Text,Text,Text,Text> {
    Text v=new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String  out="";
        for (Text val :
                values) {
            out+=val.toString()+"\t";
        }
        v.set(out);
        context.write(key,v);

    }
}
