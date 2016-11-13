package com.huluohu.hadoop.test0;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by foool on 2016/11/13.
 */
public class WordCount {
    public static class Map extends MapReduceBase implements Mapper<LongWritable,
            Text,Text,IntWritable>{
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(LongWritable longWritable, Text text, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
            String line = text.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);

            while (tokenizer.hasMoreTokens()){
                word.set(tokenizer.nextToken());
                outputCollector.collect(word,one);
            }
        }
    }


    public static class Reduce extends MapReduceBase implements Reducer<Text,
            IntWritable,Text,IntWritable>{
        @Override
        public void reduce(Text text, Iterator<IntWritable> iterator, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
            int sum = 0;

            while (iterator.hasNext()){
                sum += iterator.next().get();
            }
            outputCollector.collect(text,new IntWritable(sum));
        }
    }
}
