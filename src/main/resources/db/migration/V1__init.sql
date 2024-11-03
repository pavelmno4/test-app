create table account (
    id int8 not null,
    balance numeric(19, 2) not null,
    initial_balance numeric(19, 2) not null,
    user_id int8, primary key (id)
);

create table email_data (
    id int8 not null, email varchar(200) not null,
    user_id int8, primary key (id)
);

create table phone_data (
    id int8 not null,
    phone varchar(13) not null,
    user_id int8, primary key (id)
);

create table usr (
    id int8 not null,
    date_of_birth date not null,
    name varchar(500) not null,
    password varchar(500) not null,
    primary key (id)
);

create index account_user_id_idx on account (user_id);

alter table if exists account drop constraint if exists UK_h6dr47em6vg85yuwt4e2roca4;
alter table if exists account add constraint UK_h6dr47em6vg85yuwt4e2roca4 unique (user_id);

create index email_data_user_id_idx on email_data (user_id);

alter table if exists email_data drop constraint if exists UK_nwt9u3p3dlhfekcxxh96j8x8o;
alter table if exists email_data add constraint UK_nwt9u3p3dlhfekcxxh96j8x8o unique (email);

create index phone_data_user_id_idx on phone_data (user_id);

alter table if exists phone_data drop constraint if exists UK_osdf5rk1hhd70y73s7aa2wafo;
alter table if exists phone_data add constraint UK_osdf5rk1hhd70y73s7aa2wafo unique (phone);

create sequence account_id_seq start 1 increment 1;
create sequence email_data_id_seq start 1 increment 1;
create sequence phone_data_id_seq start 1 increment 1;
create sequence usr_id_seq start 1 increment 1;

alter table if exists account add constraint account_user_id_fk foreign key (user_id) references usr;
alter table if exists email_data add constraint email_data_user_id_fk foreign key (user_id) references usr;
alter table if exists phone_data add constraint phone_data_user_id_fk foreign key (user_id) references usr;
