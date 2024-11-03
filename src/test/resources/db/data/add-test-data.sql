----- 1
insert into usr (id, date_of_birth, name, password) values (
	nextval('usr_id_seq'), '1987-11-24', 'Gennady', 'polka'
);

insert into account (id, initial_balance, balance, user_id) values (
	nextval('account_id_seq'), 1000.00, 1000.00, currval('usr_id_seq')
);

insert into email_data (id, email, user_id) values (
	nextval('email_data_id_seq'), 'gena@yandex.ru', currval('usr_id_seq')
);

insert into phone_data (id, phone, user_id) values (
	nextval('phone_data_id_seq'), '79207865445', currval('usr_id_seq')
);

----- 2
insert into usr (id, date_of_birth, name, password) values (
	nextval('usr_id_seq'), '1988-05-21', 'Boris', '002'
);

insert into account (id, initial_balance, balance, user_id) values (
	nextval('account_id_seq'), 1200.00, 1200.00, currval('usr_id_seq')
);

insert into email_data (id, email, user_id) values (
	nextval('email_data_id_seq'), 'boris@gmail.com', currval('usr_id_seq')
);

insert into phone_data (id, phone, user_id) values (
	nextval('phone_data_id_seq'), '79218845115', currval('usr_id_seq')
);