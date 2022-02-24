# What is TD-Flink
a java project,read from TDengine and write to TDengine on Flink.
# Building 
## Install build dependencies
To install openjdk-8 and maven:
```
sudo apt-get install -y openjdk-8-jdk maven 
```
To install flink:
```
wget https://dlcdn.apache.org/flink/flink-1.14.3/flink-1.14.3-bin-scala_2.12.tgz
tar zxf flink-1.14.3-bin-scala_2.12.tgz -C /usr/local
```
## Build
```
mvn clean package
```
# Run
* start flink
```
/usr/local/flink-1.14.3/bin/start-cluster.sh
```
* run the job
```
/usr/local/flink-1.14.3/bin/flink run target/
```
