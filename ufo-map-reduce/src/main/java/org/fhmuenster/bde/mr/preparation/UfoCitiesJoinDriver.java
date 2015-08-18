package org.fhmuenster.bde.mr.preparation;

import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.join.CompositeInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import com.google.common.collect.Lists;

/**
 * Implementiert einen MapSide-Join <br>
 * <br>
 * Angelehnt an das Beispiel von @bbejeck {@link https
 * ://github.com/bbejeck/hadoop-algorithms/tree/master/src/bbejeck/mapred/joins/map}
 * 
 * @author Lena
 *
 */
public class UfoCitiesJoinDriver {

    public static void main(String[] args) throws Exception {
        // Delimiter beider Ausgangsdaten ist ein Semikolon
        String delimiter = ";";

        // In der UFO-Datei steht die Stadt an zweiter Stelle
        String keyIndexUFO = "1";
        // In der CitiesCounties-Datei steht die Stadt an erster Stelle
        String keyIndexCities = "0";

        // Prüfung der Parameter, ermöglichen zusätzlicher Aufrufparameter wie -jt local
        String[] otherArgs = new GenericOptionsParser(new Configuration(), args).getRemainingArgs();
        if (otherArgs.length != 3) {
            System.err.println("Usage: ufocount <in> <in> <out>");
            System.exit(2);
        }

        String ufoJobInputPath = otherArgs[0];
        String citiesJobInputPath = otherArgs[1];
        String outputPath = otherArgs[2];

        // Festlegung der Ausgabeordner
        String jobOneSortedPath = outputPath + "/UFO_sorted";
        String jobTwoSortedPath = outputPath + "/CITIES_sorted";

        // Starten des ersten Jobs - Auslesen und Sortieren der Aufbereiteten UFO-Datei
        Job sortUfoJob = Job.getInstance(getConfiguration(keyIndexUFO, delimiter));
        configureJob(sortUfoJob, "Sort UFOs", ufoJobInputPath, jobOneSortedPath, Text.class, SortMapper.class,
                SortReducer.class);

        // Starten des zweiten Jobs - Auslesen und Sortieren der CitiesCounties.csv
        Job sortCitiesJob = Job.getInstance(getConfiguration(keyIndexCities, delimiter));
        configureJob(sortCitiesJob, "Sort Cities", citiesJobInputPath, jobTwoSortedPath, Text.class, SortMapper.class,
                SortReducer.class);

        // Starten des MapJoin
        Job mapJoin = Job.getInstance(getMapJoinConfiguration(delimiter, jobOneSortedPath, jobTwoSortedPath));
        configureJob(mapJoin, "Map Join", jobOneSortedPath + "," + jobTwoSortedPath, outputPath + "/results",
                NullWritable.class, JoinValuesMapper.class, Reducer.class);
        mapJoin.setInputFormatClass(CompositeInputFormat.class);
        // Jobs starten
        List<Job> jobsToExecute = Lists.newArrayList(sortUfoJob, sortCitiesJob, mapJoin);
        int exitStatus = 0;
        for (Job job : jobsToExecute) {
            boolean jobSuccessful = job.waitForCompletion(true);
            if (!jobSuccessful) {
                System.out.println("Error with job " + job.getJobName() + "  " + job.getStatus().getFailureInfo());
                exitStatus = 1;
                break;
            }
        }
        System.exit(exitStatus);
    }

    /**
     * Auslagerung der Job-Configuration (Vermeidung von Code-Doppelungen).
     * 
     * @param job
     * @param jobName
     * @param inPath
     * @param outPath
     * @param mapOutputKeyClass
     * @param mapper
     * @param reducer
     * @throws java.io.IOException
     */
    @SuppressWarnings("rawtypes")
    private static void configureJob(Job job, String jobName, String inPath, String outPath,
            Class<?> mapOutputKeyClass, Class<? extends Mapper> mapper, Class<? extends Reducer> reducer)
            throws java.io.IOException {
        job.setJobName(jobName);
        job.setMapperClass(mapper);
        job.setReducerClass(reducer);
        job.setMapOutputKeyClass(mapOutputKeyClass);
        job.setJarByClass(UfoCitiesJoinDriver.class);
        FileInputFormat.addInputPath(job, new Path(inPath));
        FileOutputFormat.setOutputPath(job, new Path(outPath));
    }

    /**
     * Auslagerung für die Erzeugung der Join-{@link Configuration} <br>
     * <br>
     * Nimmt zwei und mehr Pfade entgegen, um mehrere Input-Dateien anzusprechen. Diese werden mit dem
     * {@link CompositeInputFormat} verbunden <br>
     * <br>
     * 
     * Der {@link KeyValueTextInputFormat} wird nach dem ersten Separator den Key festlegen und der Rest als Value
     * behandeln.<br>
     * <br>
     * 
     * <b>Wichtig:</b> Der eigentliche Join passiert vor dem Mapping!
     * 
     * @param separator
     * @param paths
     * @return
     */
    private static Configuration getMapJoinConfiguration(String separator, String... paths) {
        Configuration config = new Configuration();
        // Bestimmung des Separators (";")
        config.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", separator);

        String joinExpression = CompositeInputFormat.compose("inner", KeyValueTextInputFormat.class, paths);
        System.out.println(joinExpression);
        config.set("mapred.join.expr", joinExpression);
        config.set("separator", separator);
        return config;
    }

    /**
     * Auslagerung der Configuration für {@link JoinValuesMapper}
     * 
     * @param keyIndex
     * @param separator
     * @return
     */
    private static Configuration getConfiguration(String keyIndex, String separator) {
        Configuration config = new Configuration();
        config.set("keyIndex", keyIndex);
        config.set("separator", separator);
        return config;
    }

}