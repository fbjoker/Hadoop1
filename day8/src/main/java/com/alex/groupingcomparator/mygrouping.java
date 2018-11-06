package com.alex.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class mygrouping extends WritableComparator {
    public mygrouping() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean oa= (OrderBean) a;
        OrderBean ob= (OrderBean) b;



        return oa.getId().compareTo(ob.getId());
    }
}
