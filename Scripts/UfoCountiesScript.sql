CREATE EXTERNAL TABLE ufo_counties(
  City String, Date String, State String, Shape String, Duration String, Description String, Posted String, County String, CountyCode String)
 ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
 STORED AS TEXTFILE
 LOCATION '/user/cloudera/ufooutput/results';
