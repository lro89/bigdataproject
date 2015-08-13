# bigdataproject
## Prerequisites:

1. Laden der fehlenden HBase-Libs in /user/oozie/sharelib/ mittels <code>hdfs dfs -put  /usr/lib/hive-hcatalog/share/hcatalog/storage-handlers/hbase/lib/* /user/oozie/share/lib/</code>


## Datenherkunft
1. [UFO-Daten](http://www.nuforc.org/webreports.html) 
2. [Wetter-Daten](http://www.ipm.ucdavis.edu/WEATHER/index.html)
3. [Counties California](http://www.counties.org/cities-within-each-county)

## Frontend
Eclipse Maven Project: UFO_PROJECT<br>
Umsetzung mit: Spring Boot + Spring MVC + Thymeleaf
