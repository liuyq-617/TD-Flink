package com.taosdata;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import com.taosdata.model.Sensor;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ReadFromTDengine {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Sensor> SensorList = env.addSource( new com.taosdata.flink.SourceFromTDengine() );
        Logger LOG = LoggerFactory.getLogger(ReadFromTDengine.class);
        LOG.info("main:This message contains {} placeholders. {}", 2, 11);
        SensorList.print();

        SensorList.addSink( new com.taosdata.flink.SinkToTDengine() );       
        env.execute();

    }
}