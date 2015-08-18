package org.fhmuenster.bde.mr.preparation;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer für das vorbereitende Sortieren für den folgenden Join im {@link JoinValuesMapper}-Job
 * 
 * @author Lena
 *
 */
public class SortReducer extends Reducer<Text, Text, NullWritable, Text> {

    private static final NullWritable nullKey = NullWritable.get();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // Ausgabe der einzelnen Zeilen
        for (Text value : values) {
            context.write(nullKey, value);
        }
    }
}