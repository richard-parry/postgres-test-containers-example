CREATE SCHEMA IF NOT EXISTS psql;

create extension if not exists pgcrypto;
-- drop table psql.customer;
CREATE TABLE IF not exists  psql.customer (
   ID serial PRIMARY KEY     NOT NULL,
   first_name           varchar(50)    NOT NULL,
   last_name varchar(50) not null,
   sort_code text not null,
   account_number text not null
);


insert into psql.customer (first_name, last_name, sort_code, account_number) values ('Billy', 'Bob', pgp_sym_encrypt('1', 'secret_password', 'cipher-algo=aes256'), pgp_sym_encrypt('2', 'secret_password', 'cipher-algo=aes256')) ;
insert into psql.customer (first_name, last_name, sort_code, account_number) values ('David', 'Williams', pgp_sym_encrypt('231243', 'secret_password', 'cipher-algo=aes256'), pgp_sym_encrypt('12345678', 'secret_password', 'cipher-algo=aes256')) ;
insert into psql.customer (first_name, last_name, sort_code, account_number) values ('Encrpyted', 'Jones', pgp_sym_encrypt('231243', 'secret_password', 'cipher-algo=aes256'), pgp_sym_encrypt('12345678', 'secret_password', 'cipher-algo=aes256')) ;

select * from psql.customer;	
select pgp_sym_decrypt(sort_code::bytea, 'secret_password')	 from psql.customer where id = 3; 
select * from psql.customer;	
select * from psql.customer where sort_code = '231243';	
select * from psql.customer where pgp_sym_decrypt(sort_code::bytea, 'secret_password') = '1';



commit;