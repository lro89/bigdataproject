package org.fhmuenster.bde.mr.preparation;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.join.TupleWritable;

/**
 * {@link Mapper} für die Ausführung des Joins <br>
 * <br>
 * 
 * <b>Funktionsweise des Mappers: </b> Der Key enthalt den Schlüssel, das TupleWritable (value) enthält die Werte, die
 * zu dem Schlüssel gehören. <br>
 * Im ersten Schritt wird der Key zu einer Zeile hinzugefügt, im zweiten Schritt folgen die Werte.
 * 
 * @author Lena
 */
public class JoinValuesMapper extends Mapper<Text, TupleWritable, NullWritable, Text> {

    // KeyIn, ValueIn, KeyOut, Valueout
    private static final NullWritable nullKey = NullWritable.get();

    private Text output = new Text();

    private StringBuilder stringBuilder = new StringBuilder();

    private String separator;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        separator = context.getConfiguration().get("separator");
    }

    protected void map(Text key, TupleWritable value, Context context) throws IOException, InterruptedException {
        stringBuilder.append(key).append(separator);
        for (Writable writable : value) {
            stringBuilder.append(writable.toString()).append(separator);
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        output.set(stringBuilder.toString());
        context.write(nullKey, output);
        stringBuilder.setLength(0);
    }
}