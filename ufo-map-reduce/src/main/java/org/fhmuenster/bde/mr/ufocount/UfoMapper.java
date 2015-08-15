package org.fhmuenster.bde.mr.ufocount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UfoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    public enum Counters {
        UNCHANGED, MODIFIED
    }

    private final IntWritable ONE = new IntWritable(1);

    // private Text word = new Text();

    private Text city = new Text();

    @Override
    protected void map(LongWritable offset, Text line, Context context) throws IOException, InterruptedException {
        String tmpString = line.toString();
        String[] fields = tmpString.split("\";\"");
        String cityFromStream = fields[0];
        city.set(cityFromStream);
        context.write(city, ONE);

        // StringTokenizer parser = new StringTokenizer(line.toString());
        // while (parser.hasMoreTokens()) {
        // String oldWord = parser.nextToken();
        // String newWord = oldWord.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase();
        // context.getCounter(oldWord.equals(newWord) ? Counters.UNCHANGED : Counters.MODIFIED).increment(1);
        // word.set(newWord);
        // context.write(word, ONE);
        // }
    }
}
