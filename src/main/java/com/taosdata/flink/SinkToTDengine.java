package com.taosdata.flink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import com.taosdata.model.Sensor;
import java.sql.*;
import java.util.Properties;


public class SinkToTDengine extends RichSinkFunction<Sensor> {
    Statement statement;
    private Connection connection;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        String driver = "com.taosdata.jdbc.rs.RestfulDriver";
        String host = "u05";
        String username = "root";
        String password = "taosdata";
        String prop = System.getProperty("java.library.path");
        System.out.println(prop);
        Class.forName( driver );
        Properties properties = new Properties();
        connection = DriverManager.getConnection("jdbc:TAOS-RS://" + host + ":6041/tt" + "?user=root&password=taosdata"
                , properties);
        statement = connection.createStatement();
        
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    @Override
    public void invoke(Sensor sensor, Context context) throws Exception {
        try {
            String sql = String.format("insert into sinktest.%s using sinktest.meters tags('%s') values(%d,%d,%f)", 
                                sensor.getLocation(),
                                sensor.getLocation(),
                                sensor.getTs(),
                                sensor.getVal(),
                                sensor.getCurrent()
                                );
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}