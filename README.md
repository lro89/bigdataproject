# bigdataproject
## Prerequisites:

1. HBase starten<br>
  sudo service hbase-master start<br>
  sudo service hbase-regionserver start<br>

2. Laden der fehlenden HBase-Libs in /user/oozie/sharelib/ mittels <code>hdfs dfs -put  /usr/lib/hive-hcatalog/share/hcatalog/storage-handlers/hbase/lib/* /user/oozie/share/lib/</code>



## Datenherkunft
1. [UFO-Daten](http://www.nuforc.org/webreports.html) 
2. [Wetter-Daten](http://www.ipm.ucdavis.edu/WEATHER/index.html)
3. [Counties California](http://www.counties.org/cities-within-each-county)

## Frontend
Eclipse Maven Project: UFO_PROJECT<br>
Umsetzung mit: Spring Boot + Spring MVC + Thymeleaf
####Einstellungen zum Starten des Projektes:
Projekt Bild-Path: Java 7 auswählen<br>
Projekt-Properties: Java Compile Level: Java 7<br>
Auf der Console in das UFO_PROJECT wechseln, anschließend folgendes ausführen:<br>
mvn install<br>
java -jar target/ufoproject-0.0.1-SNAPSHOT<br>


