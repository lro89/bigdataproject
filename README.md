# bigdataproject
## Prerequisites:

1. HBase starten<br>
  sudo service hbase-master start<br>
  sudo service hbase-regionserver start<br>

2. Beschreibung der Ordner-Struktur <br>
  1. Datasources: Enthält die Rohdaten für die Pipeline <br>
  2. Scripts: Enthält alle benötigten Skripte (Python, Pig...) <br>
  3. UFO_FlumeAgent: Enthält das Java-Projekt für die *CustomSource* und weitere Ressourcen für den Flume-Agent (Eclipse-Project)<br>
  4. UFO_PROJECT: Enthält das Java-Spring-Projekt für das Frontend (Eclipse-Maven-Projekt)<br>
  5. Workflows: Enthält die Oozie-Workflows samt benötigter Lib-Dateien <br>
  6. ZIP-Dateien: Enthält vorbereitete Archive für den Import in das HDFS inkl. aller benötigten *Libs* (u.a. für HBase-Ausführung in Oozie)<br>
  7. ufo-map-reduce: Enthält ein Java-Projekt mit einem implementierten MapReduce-Algorithmus auf Basis der neuen API. Die JAR wird im Workflow verwendet.


## Datenherkunft
1. [UFO-Daten](http://www.nuforc.org/webreports.html) 
2. [Wetter-Daten](http://www.ipm.ucdavis.edu/WEATHER/index.html)
3. [Counties California](http://www.counties.org/cities-within-each-county)
4. [Google County Codes](https://www.google.com/fusiontables/DataSource?docid=196LqydLhOq1Wl9612hNhcGoh4vUmRjTaiFvDhA#rows:id=1) => Vorhanden in counties_cities_code, nur für MapRed-Input

# Ausführung
## Flume
1. Als su in /usr/lib/flume-ng/conf/flume-env.sh.template <code>export JAVA_OPTS="-Xms100m -Xmx2000m -Dcom.sun.management.jmxremote"
</code> einkommentieren. Template aus dem Namen entfernen
2. Als cloudera auf der NameNode (nicht HDFS) im Verzeichnis /home/cloudera/workspace/bigdataproject/UFO_FlumeAgent/ das ZIP 'Flume_Plugin.zip'entpacken
3. Im HDFS unter 'user/cloudera/' die Ordnerstruktur 'Flume/UFO' anlegen (sollte am Ende folgendermaßen aussehen: 'user/cloudera/Flume/UFO'). In den Ordner 'UFO' wird später der Output des Flume-Agents geschrieben.
4. Falls die Internetverbindung der VM über ein Proxy erfolgt, müssen die entsprechenden Informationen in der Konfig-Datei 'UFO-flume_WEBAGENT.properties' gepflegt werden.
5. Als cloudera den Befehl in der <code>start flume agent.sh</code> anpassen, rauskopieren und absenden
6. Die Ausführung des Agents kann einige Minuten in Anspruch nehmen. Sobald die Meldung "Writer callback called" erscheint, kann der Agent mit Strg+C beendet werden. In dem HDFS-Ordner 'UFO' befindet sich anschließend eine Datei namens "UFO.{codestempel}" (Größe ca. 1.6 MB) mit den extrahierten UFO-Daten (vorausgesetzt der Agent lief ohne Fehler).

## Oozie-Workflows
### Allgemeines
1. Die unter *ZIP-Dateien* abgelegten Archive sind in das HDFS-Verzeichnis /home/cloudera/ zu importieren. Die ZIP-Ordner enthalten auch alle JARs, die für die Ausführung des Workflows benötigt werden (inkl. der HBase-JARs!, jeweils im Unterorder *libs*)
2. Der Flume-Job muss vor Anstoß der Workflows gelaufen sein, sodass im Order /user/cloudera/Flume/UFO/ eine Datei mit aufbereiteten Ufo-Daten liegt.
3. Auf der Cloudera-VM (nicht im HDFS) ist der Order workflows ebenfalls abzulegen. Von dort aus werden anhand der job.properties-Dateien die Workflows gestartet. <br>
4. Ggf.: Bekanntmachung von Oozie in der Console: <code>$ export OOZIE_URL=http://vm-cluster-node1:11000/oozie</code>

### Ausführung
**!!! ACHTUNG !!!** 
Auf der Quickstart-VM ist zwischendurch immer wieder der Status des HBase-Masters und des HBase-Regionservers zu prüfen. Laufen diese nicht, bricht der Workflow ab.

#### Vollständig automatisierte Ausführung
1. **Workflow** 

### Kommandos
**Start**: <code>oozie job -run -oozie http://quickstart.cloudera:11000/oozie/ -config ***path/to/partial/workflow***/job.properties</code> <br>
**Kontolle**: <code>oozie job -oozie http://quickstart.cloudera:11000/oozie/ -info 0000001-150816094600884-oozie-oozi-W</code> <br>
**Logs:** <code>oozie job -oozie http://quickstart.cloudera:11000/oozie/ -log 0000001-150816094600884-oozie-oozi-W</code> <br>

## Frontend
Eclipse Maven Project: UFO_PROJECT<br>
Umsetzung mit: Spring Boot + Spring MVC + Thymeleaf
####Einstellungen zum Starten des Projektes:
Projekt Bild-Path: Java 7 auswählen<br>
Projekt-Properties: Java Compile Level: Java 7<br>
Auf der Console in das UFO_PROJECT wechseln, anschließend folgendes ausführen:<br>
<code>mvn install</code> oder <code>mvn clean package</code><br>
Server starten: <code>java -jar target/ufoproject-0.0.1-SNAPSHOT.jar</code><br>
Die Webanwendung ist unter <code>http://localhost:8080/</code> zu erreichen

## Bei auftretenden Problemen
### Manuelle Ausführung der Einzelschritte in Oozie (**nach vollständig automatisierter Ausführung nicht mehr notwendig!**)
1. ExtractUFOData
2. ExtractWeatherTables
3. LoadStaticData
4. FillFrontendTables

### MapReduce - manueller Start (**nach vollständig automatisierter Ausführung nicht mehr notwendig!**)
1. Maven muss auf der VM installiert sein!
2. <code>hadoop jar ufo-map-reduce-1.0-SNAPSHOT-mrjob.jar ufojoin /user/cloudera/Staging/UFO /user/cloudera/Datasources/Meta/CountiesCitiesCode ufooutput</code>

### Oozie Workflow kann Action 'HiveSkript' nicht ausführen
Fehler: 
```
Failing Oozie Launcher, Main class [org.apache.oozie.action.hadoop.HiveMain], main() threw exception, hive-site.xml (Permission denied)     
java.io.FileNotFoundException: hive-site.xml (Permission denied) at java.io.FileOutputStream.open(Native Method) at  
java.io.FileOutputStream.<init>(FileOutputStream.java:221) 
...
```
Behebung:   
- Im HDFS den Ordner 'Workflows/Workflow' bearbeiten:
  - Die hive-site.xml in 'hive-oozie-site.xml' umbenennen
  - In der workflow.xml die hive-oozie-site.xml als job-xml angeben
  - Oozie neu starten

