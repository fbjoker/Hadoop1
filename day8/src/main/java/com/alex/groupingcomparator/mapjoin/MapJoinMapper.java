package com.alex.groupingcomparator.mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

public class MapJoinMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    private OrderBean k = new OrderBean();
    private HashMap<String, String> map = new HashMap<String, String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath().toString();

        BufferedReader bf = new BufferedReader(new FileReader(path));

        String len;
        while (StringUtils.isNotEmpty(len = bf.readLine())) {
            String[] split = len.split("\t");
            map.put(split[0], split[1]);

        }
        bf.close();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");

        k.setId(split[0]);
        k.setPid("");
        k.setPname(map.get(split[1]));
        k.setAmount(Integer.parseInt(split[2]));
        k.setFlag("");
        context.write(k,NullWritable.get());



    }
}
