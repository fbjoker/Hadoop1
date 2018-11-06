package com.alex.groupingcomparator.reducejoin;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {

    private String id;
    private String pid;
    private int amount;
    private String pname;
    private String flag;

    public OrderBean() {
    }

    public OrderBean(String id, String pid, int amount, String pname, String flag) {
        this.id = id;
        this.pid = pid;
        this.amount = amount;
        this.pname = pname;
        this.flag = flag;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {

        return  id + "\t" + pname + "\t" + amount;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);

        out.writeUTF(pname);
        out.writeUTF(flag);


    }


    public void readFields(DataInput in) throws IOException {
        id = in.readUTF();
        pid = in.readUTF();
        amount = in.readInt();
        pname = in.readUTF();
        flag = in.readUTF();
    }


    public int compareTo(OrderBean o) {
        int reslut = pid.compareTo(o.getPid());
        if(reslut==0){
            if (this.flag.equals("pd")) {
                return -1;
            } else {
                return 1;
            }
//            return flag.compareTo(o.flag);
        }else {

            return reslut;
        }
    }
}
