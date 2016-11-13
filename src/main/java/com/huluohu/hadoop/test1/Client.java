package com.huluohu.hadoop.test1;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by foool on 2016/11/13.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        int ret = ToolRunner.run(new WordCount(), args);
        System.exit(ret);
    }
}
