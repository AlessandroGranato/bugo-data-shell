create sequence bds.bds_devices_seq start 1;
create sequence bds.bds_users_seq start 1;
create sequence bds.bds_temperatures_seq start 1;

create table bds.bds_devices (
    id bigint not null default nextval('bds.bds_devices_seq') primary key,
    device_identifier varchar(50) not null,
    creation_date timestamp not null default now(),
    constraint uk_devices_1 unique (device_identifier)
);

create table bds.bds_users (
    id bigint not null default nextval('bds.bds_users_seq') primary key,
    user_identifier varchar(50) not null,
    device_id bigint not null,
    creation_date timestamp not null default now(),
    constraint uk_users_1 unique (user_identifier),
    constraint fk_devices foreign key (device_id) references bds.bds_devices (id)
);

create table bds.bds_temperatures (
    id bigint not null default nextval('bds.bds_temperatures_seq') primary key,
    device_id bigint not null,
    value numeric (5, 2) not null,
    creation_date timestamp not null default now(),
    constraint fk_devices foreign key (device_id) references bds.bds_devices (id)
);