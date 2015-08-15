# bigdataproject
## Prerequisites:

1. HBase starten<br>
  sudo service hbase-master start<br>
  sudo service hbase-regionserver start<br>

2. Laden der Source-Ordner in hdfs:/user/cloudera/ <br>
  2.1. Workflows: Enthält die Oozie-Workflows samt benötigter Lib-Dateien <br>
  2.2. Datasources: Enthält die Rohdaten für die Pipeline <br>
  2.3. Scripts: Enthält alle benötigten Skripte (Python, Pig...) <br>
  2.4. UFO: TODO <br>
  2.5. UFO_PROJEKT todo


## Datenherkunft
1. [UFO-Daten](http://www.nuforc.org/webreports.html) 
2. [Wetter-Daten](http://www.ipm.ucdavis.edu/WEATHER/index.html)
3. [Counties California](http://www.counties.org/cities-within-each-county)


## MapReduce
1. Maven muss auf der VM installiert sein!

## Frontend
Eclipse Maven Project: UFO_PROJECT<br>
Umsetzung mit: Spring Boot + Spring MVC + Thymeleaf
####Einstellungen zum Starten des Projektes:
Projekt Bild-Path: Java 7 auswählen<br>
Projekt-Properties: Java Compile Level: Java 7<br>
Auf der Console in das UFO_PROJECT wechseln, anschließend folgendes ausführen:<br>
mvn install<br>
Server starten: java -jar target/ufoproject-0.0.1-SNAPSHOT.jar<br>


