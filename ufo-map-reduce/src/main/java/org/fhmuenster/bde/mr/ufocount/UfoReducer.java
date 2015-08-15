package org.fhmuenster.bde.mr.ufocount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UfoReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable sum = new IntWritable();

    @Override
    protected void reduce(Text city, Iterable<IntWritable> values, Context context) throws IOException,
            InterruptedException {
        int total = 0;
        for (IntWritable val : values) {
            total += val.get();
        }
        sum.set(total);
        context.write(city, sum);
    }
}
