package com.alex.hadoop.proj2;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {


    public GroupComparator() {

        super(FlowBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
          FlowBean fa= (FlowBean) a;
          FlowBean fb= (FlowBean) b;

          return  fa.getFlag().compareTo(fb.getFlag());

    }
}
