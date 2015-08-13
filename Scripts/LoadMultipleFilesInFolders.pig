


inhalt = LOAD '$pfad' AS (line:chararray);

filtered = FILTER inhalt BY NOT(line MATCHES '^".*');

STORE filtered INTO '$savefileto';

