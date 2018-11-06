package com.hadoop.demo.partitionsort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements WritableComparable<FlowBean> {
    private  long up;
    private  long down;
    private  long sum;

    public FlowBean() {

    }

    public FlowBean(long up, long down, long sum) {
        this.up = up;
        this.down = down;
        this.sum = sum;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "FlowBean{" +
                "up=" + up +
                ", down=" + down +
                ", sum=" + sum +
                '}';
    }

    public int compareTo(FlowBean o) {
        if(sum>o.getSum()){
            return 1;
        }else if (sum==0){
            return 0;
        }else {

            return -1;
        }
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(up);
        out.writeLong(down);
        out.writeLong(sum);
    }

    public void readFields(DataInput in) throws IOException {
        up=in.readLong();
        down=in.readLong();
        sum=in.readLong();

    }
}
