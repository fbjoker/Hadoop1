package com.alex.hadoop.hive;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.IFile;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
  Text  k=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.导入数据
        String str = value.toString();
        //2.数据清洗
        String s = ETLUITLS.etlMovie(str);
        //3.输出
        if(StringUtils.isBlank(s)) return;
        k.set(s);
        context.write(k,NullWritable.get());
    }
}
