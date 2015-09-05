package org.fhmuenster.bde.mr.ufocount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UfoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final IntWritable ONE = new IntWritable(1);

    private Text cityAndShape = new Text();

    @Override
    protected void map(LongWritable offset, Text line, Context context) throws IOException, InterruptedException {
        String tmpString = line.toString();
        String[] fields = tmpString.split(";");

        String cityFromStream = fields[1].replace("\"", "");
        String shapeFromStream = fields[3].replace("\"", "");

        cityAndShape.set(cityFromStream + ";" + shapeFromStream + ";");
        context.write(cityAndShape, ONE);
    }
}
