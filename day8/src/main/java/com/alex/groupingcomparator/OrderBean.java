package com.alex.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private String id;
    private String pid;
    private double price;

    public OrderBean() {
    }

    public OrderBean(String id, String pid, double price) {
        this.id = id;
        this.pid = pid;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + '\t' + pid + '\t' + price;
    }

    public int compareTo(OrderBean o) {
        int reslut = id.compareTo(o.getId());
        if(id==o.getId()){
            return  Double.compare(price,o.getPrice());//默认是降序
        }
        return reslut;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeDouble(price);

    }

    public void readFields(DataInput in) throws IOException {
        id = in.readUTF();
        pid = in.readUTF();
        price = in.readDouble();

    }
}
