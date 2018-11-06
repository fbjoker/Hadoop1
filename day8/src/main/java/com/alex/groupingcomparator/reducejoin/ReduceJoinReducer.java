package com.alex.groupingcomparator.reducejoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class ReduceJoinReducer  extends Reducer<OrderBean, NullWritable,OrderBean,NullWritable> {
   // String name;
   // OrderBean k=new OrderBean();
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

      /*  for (NullWritable bean:values   ) {
            context.write(key,NullWritable.get());

        }*/
       Iterator<NullWritable> iterator = values.iterator();
        iterator.next();
       String  name= key.getPname();
        while (iterator.hasNext()){
            iterator.next();

            key.setPname(name);

            context.write(key,NullWritable.get());
        }



    }
}
