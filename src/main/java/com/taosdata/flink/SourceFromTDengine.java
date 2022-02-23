package com.taosdata.flink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import com.taosdata.model.Sensor;

import java.sql.*;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;



public class SourceFromTDengine extends RichSourceFunction<Sensor> {


    Statement statement;
    private Connection connection;
    private String property;

    public SourceFromTDengine(){
        super();
    }
    
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        String driver = "com.taosdata.jdbc.rs.RestfulDriver";
        String host = "u05";
        String username = "root";
        String password = "taosdata";
        String prop = System.getProperty("java.library.path");
        Logger LOG = LoggerFactory.getLogger(SourceFromTDengine.class);
        LOG.info("java.library.path:{}", prop);
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
    public void run(SourceContext<Sensor> sourceContext) throws Exception {
        try {
            Logger LOG = LoggerFactory.getLogger(SourceFromTDengine.class);
            String sql = "select * from tt.meters";
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Sensor sensor = new Sensor( resultSet.getLong(1),
                        resultSet.getInt( "vol" ),
                        resultSet.getFloat( "current" ),
                        resultSet.getString( "location" ).trim());
                LOG.info("This message contains {} placeholders. {}", 2,sensor);

                sourceContext.collect( sensor );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancel() {

    }
}   