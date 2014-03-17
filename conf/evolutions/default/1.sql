# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table reservation (
  record_locator            varchar(255) not null,
  traveler_name             varchar(255),
  constraint pk_reservation primary key (record_locator))
;

create sequence reservation_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists reservation;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists reservation_seq;

