package com.alex.hadoop.proj2;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class SortReducer extends Reducer<FlowBean, NullWritable,FlowBean,NullWritable> {
    FlowBean v= new FlowBean();
    int i=0;
    @Override
    protected void reduce(FlowBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> iterator = values.iterator();

       /* for (int i=0;i<10;i++){
            if(iterator.hasNext()){

                iterator.next();
            }*/
            i++;
        System.out.println(i);
            if(i<11){

                context.write(key,NullWritable.get());
            }


    }
}
