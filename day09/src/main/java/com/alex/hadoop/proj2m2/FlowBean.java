package com.alex.hadoop.proj2m2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static java.lang.Integer.compare;

public class FlowBean implements WritableComparable<FlowBean> {
    private  String phone;
    private int up;
    private int down;
    private int sum;


    public FlowBean(int up, int down) {
        this.up = up;
        this.down = down;
        this.sum = up+down;
    }

    public  void set(int up, int down){
        this.up = up;
        this.down = down;
        this.sum = up+down;
    }
    public FlowBean() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }


    @Override
    public String toString() {
        return  up + "\t" + down + "\t" + sum;
    }

    public int compareTo(FlowBean o) {

            return  compare(o.getSum(),sum );

    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(up);
        out.writeInt(down);
        out.writeInt(sum);

    }

    public void readFields(DataInput in) throws IOException {
        up=in.readInt();
        down = in.readInt();
        sum = in.readInt();
    }
}
