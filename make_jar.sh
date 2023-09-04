#!/bin/sh

lein uberjar
mv target/uberjar/ack-0.1.0-SNAPSHOT-standalone.jar acs.jar

