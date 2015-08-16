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


## Oozie-Workflows
1. Order *Workflows* muss ins HDFS unter /user/cloudera/ geladen werden
2. Ebenso die Ordner *Scripts* und *Datasources*
3. Auf der Cloudera-VM ist der Order workflows ebenfalls abzulegen. Von dort aus werden anhand der job.properties-Dateien die Workflows gestartet. <br>

**Start**: <code>oozie job -run -oozie http://quickstart.cloudera:11000/oozie/ -config workflows/job.properties</code> <br>
**Kontolle**: <code>oozie job -oozie http://quickstart.cloudera:11000/oozie/ -info 0000001-150816094600884-oozie-oozi-W</code> <br>
**Logs:** <code>oozie job -oozie http://quickstart.cloudera:11000/oozie/ -log 0000001-150816094600884-oozie-oozi-W</code> <br>




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


