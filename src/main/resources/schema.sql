CREATE SEQUENCE IF NOT EXISTS  url_sequence
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1;
  
  
 CREATE TABLE IF NOT EXISTS TABLE_URL  (
    id int,
    url varchar(255),
    shorturl varchar(255),
    timestamp date
); 