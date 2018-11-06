package com.alex.groupingcomparator.reducejoin;

import com.alex.groupingcomparator.OrderBean;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class myjoingrouping extends WritableComparator {
    public myjoingrouping() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean oa= (OrderBean) a;
        OrderBean ob= (OrderBean) b;



        return oa.getPid().compareTo(ob.getPid());
    }
}
