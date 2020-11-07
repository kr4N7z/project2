drop table messages;
drop table friends;
drop table users;

create table users(
	user_id serial PRIMARY KEY,
    user_type VARCHAR ( 10 ) NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL, 
    first_name VARCHAR (25) NOT NULL, 
    last_name VARCHAR (25) NOT NULL, 
   	last_latitude float,
   	last_longitude float,
    last_state varchar (25),
    created_on TIMESTAMP NOT NULL, 
    last_login TIMESTAMP
);

create table messages(
	message_id serial primary key,
	sender_id int4 references users(user_id),
	recived_id int4 references users(user_id),
	message varchar not null,
	sent_time timestamp not null
);

create table friends (
	sender_id int4 references users(user_id),
	reciever_id int4 references users(user_id),
	status boolean,
	primary key (sender_id, reciever_id)
);

