@echo off
:: set environment maven and java
set MVN_HOME=E:\environment\maven\apache-maven-3.8.5-education
:: set path environment
set path=%MVN_HOME%\bin
:: call command line build maven
mvn clean install -DSkipTests