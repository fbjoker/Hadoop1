package com.alex.hadoop.etl;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WordBean implements Writable {

    private String word;
    private int count;

    public WordBean() {
    }

    public WordBean(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return word + '\t' + count;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(word);
        out.writeInt(count);

    }

    public void readFields(DataInput in) throws IOException {
        word = in.readUTF();
        count = in.readInt();
    }
}
