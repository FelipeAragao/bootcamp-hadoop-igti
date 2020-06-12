#!/bin/bash

cd /usr/local/sqoop

bin/sqoop import \
    --connect jdbc:mysql://localhost/IGTI?zeroDateTimeBehavior=convert_To_Null \
    --username root -password igti  \
    --table CLIENTE_VENDA -m 1 \
    --bindir /usr/local/sqoop/lib \
    --target-dir /igti