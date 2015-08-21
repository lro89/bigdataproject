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
4. [Google County Codes](https://www.google.com/fusiontables/DataSource?docid=196LqydLhOq1Wl9612hNhcGoh4vUmRjTaiFvDhA#rows:id=1) => Vorhanden in counties_cities_code, nur für MapRed-Input

## Flume
1. Als su in /usr/lib/flume-ng/conf/flume-env.sh.template <code>export JAVA_OPTS="-Xms100m -Xmx2000m -Dcom.sun.management.jmxremote"
</code> einkommentieren. Template aus dem Namen entfernen
2. Als cloudera auf der NameNode (nicht HDFS) im Verzeichnis /home/cloudera/workspace/bigdataproject/UFO_FlumeAgent/ das ZIP entpacken
3. Als cloudera den Befehl in der <code>start flume agent.sh</code> anpassen, rauskopieren und absenden

## Oozie-Workflows
### Allgemeines
1. Order *Workflows* muss ins HDFS unter /user/cloudera/ geladen werden
2. Ebenso die Ordner *Scripts* und *Datasources*
3. Auf der Cloudera-VM (nicht im HDFS) ist der Order workflows ebenfalls abzulegen. Von dort aus werden anhand der job.properties-Dateien die Workflows gestartet. <br>
4. Bekanntmachung von Oozie in der Console: <code>$ export OOZIE_URL=http://vm-cluster-node1:11000/oozie</code>

### Ausführungsreihenfolge
1. ExtractUFOData
2. ExtractWeatherTables
3. LoadStaticData
4. FillFrontendTables

### Kommandos
**Start**: <code>oozie job -run -oozie http://quickstart.cloudera:11000/oozie/ -config workflows/**WORKFLOW**/job.properties</code> <br>
**Kontolle**: <code>oozie job -oozie http://quickstart.cloudera:11000/oozie/ -info 0000001-150816094600884-oozie-oozi-W</code> <br>
**Logs:** <code>oozie job -oozie http://quickstart.cloudera:11000/oozie/ -log 0000001-150816094600884-oozie-oozi-W</code> <br>

## MapReduce - manueller Start
1. Maven muss auf der VM installiert sein!
2. <code>hadoop jar ufo-map-reduce-1.0-SNAPSHOT-mrjob.jar ufojoin /user/cloudera/Staging/UFO /user/cloudera/Datasources/Meta/CountiesCitiesCode ufooutput</code>

## Frontend
Eclipse Maven Project: UFO_PROJECT<br>
Umsetzung mit: Spring Boot + Spring MVC + Thymeleaf
####Einstellungen zum Starten des Projektes:
Projekt Bild-Path: Java 7 auswählen<br>
Projekt-Properties: Java Compile Level: Java 7<br>
Auf der Console in das UFO_PROJECT wechseln, anschließend folgendes ausführen:<br>
mvn install<br>
Server starten: java -jar target/ufoproject-0.0.1-SNAPSHOT.jar<br>

## Aufgetrene Probleme
- Oozie-Konfiguration (Herausfinden der richtigen Libs, mapreduce.input.format.type vs mapreduce.inputformat.type
- Proxy-Einstellungen für Flume?

#### Oozie Workflow 'FillFrontendTables' kann Action 'HiveSkript' nicht ausführen
Fehler: 
```
Failing Oozie Launcher, Main class [org.apache.oozie.action.hadoop.HiveMain], main() threw exception, hive-site.xml (Permission denied)     
java.io.FileNotFoundException: hive-site.xml (Permission denied) at java.io.FileOutputStream.open(Native Method) at  
java.io.FileOutputStream.<init>(FileOutputStream.java:221) 
...
```
Behebung:   
- HDFS Ordner 'FillFrontendTables' löschen
- Ordner offline bearbeiten:
  - Die hive-site.xml in 'hive-oozie-site.xml' umbenennen
  - In der workflow.xml die hive-oozie-site.xml als job-xml angeben
  - Den libraries-libs.zip Ordner neu packen mit der neuen hive-oozie-site.xml und workflow.xml (die alten Dateien löschen)
- FillFrontendTables ins HDFS hochladen
- Darin, den libraries-libs.zip Ordner hochladen und anschliessend in 'lib' umbenennen

