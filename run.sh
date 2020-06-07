#!/bin/bash

DIR=$(pwd)

echo "########################### now jar ###########################"
rm -r $DIR/target
mvn clean install

echo "########################### execute job hadoop ###########################"
hadoop fs -rm -r /user
hadoop jar $DIR/target/ExemploIGTI-1.0-SNAPSHOT.jar IGTI.ExemploIGTI