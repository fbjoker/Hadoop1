package com.alex.groupingcomparator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class OrderReducer extends Reducer<OrderBean, NullWritable,OrderBean,NullWritable> {
    OrderBean k= new OrderBean();
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        Iterator<NullWritable> iterator = values.iterator();
       iterator.next();
        context.write(key,NullWritable.get());

      /*  for (int i = 0; i < 2; i++) {
            if (iterator.hasNext())
                context.write(key, iterator.next());
        }*/
    }
}
