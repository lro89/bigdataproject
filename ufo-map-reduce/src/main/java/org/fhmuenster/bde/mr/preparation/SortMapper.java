package org.fhmuenster.bde.mr.preparation;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * {@link Mapper} für das Sortieren der Input-Dateien
 * 
 * @author Lena
 *
 */
public class SortMapper extends Mapper<LongWritable, Text, Text, Text> {

    private int keyIndex;

    private Splitter splitter;

    private Joiner joiner;

    private Text joinKey = new Text();

    /**
     * Vorbereitung des Context
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String separator = context.getConfiguration().get("separator");
        keyIndex = Integer.parseInt(context.getConfiguration().get("keyIndex"));
        // Splitten mit Delimiter, z.B. ";"
        splitter = Splitter.on(separator);
        // Zusammenführung mit Delimiter, z.B. ";"
        joiner = Joiner.on(separator);
    }

    /**
     * Mapping
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        value.set(value.toString().replace("\"", ""));
        Iterable<String> values = splitter.split(value.toString());
        // Festlegung des Join-Keys mit Angabe der Spalte "keyIndex"
        joinKey.set(Iterables.get(values, keyIndex));
        if (keyIndex != 0) {
            // Umsortierung der Spalten, falls der Join Key nicht an erster Stelle ist
            value.set(reorderValue(values, keyIndex));
        }
        context.write(joinKey, value);
    }

    /**
     * Methode für die Umsortierung eingehender Spalten <br>
     * 
     * @param value
     * @param index
     * @return Umsortierung, Join-Key in erster Spalte
     */
    private String reorderValue(Iterable<String> value, int index) {
        List<String> tmp = Lists.newArrayList(value);
        String extractFirstCol = tmp.get(0);
        String newFirstCol = tmp.get(index);
        tmp.set(0, newFirstCol);
        tmp.set(index, extractFirstCol);
        return joiner.join(tmp);
    }
}