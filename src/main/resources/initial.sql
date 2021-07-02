CREATE TABLE transaction (
  id int NOT NULL AUTO_INCREMENT,
  user_id varchar(32) NOT NULL,
  product_id varchar(64) NOT NULL,
  product_category varchar(128) NOT NULL,
  amount decimal(14,2) NOT NULL,
  PRIMARY KEY (id)
);
