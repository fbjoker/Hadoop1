package com.alex.hadoop.proj2;

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
    private  String flag="1";

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return  phone +"\t"+up + "\t" + down + "\t" + sum;
    }

    public int compareTo(FlowBean o) {
        int compare = flag.compareTo(o.flag);
        if (compare==0){
            return  compare(o.getSum(),sum );
        }
        return  compare;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(up);
        out.writeInt(down);
        out.writeInt(sum);
        out.writeUTF(phone);
        out.writeUTF(flag);

    }

    public void readFields(DataInput in) throws IOException {
        up=in.readInt();
        down = in.readInt();
        sum = in.readInt();
        phone=in.readUTF();
        flag=in.readUTF();
    }
}
