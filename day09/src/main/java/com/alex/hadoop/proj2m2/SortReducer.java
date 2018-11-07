package com.alex.hadoop.proj2m2;


import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class SortReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
    FlowBean v = new FlowBean();

    // int i=0;
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        for (int i = 0; i < 10; i++) {
            if (iterator.hasNext()) {
                Text k = iterator.next();
                context.write(k, key);
            }
        }
    }
}