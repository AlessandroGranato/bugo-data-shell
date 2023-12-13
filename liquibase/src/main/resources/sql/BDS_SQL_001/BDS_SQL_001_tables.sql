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
    user_identifier bigint not null primary key,
    device_id bigint not null,
    creation_date timestamp not null default now(),
    constraint fk_devices foreign key (device_id) references bds.bds_devices (id)
);

create table bds.bds_temperatures (
    id bigint not null default nextval('bds.bds_temperatures_seq') primary key,
    device_id bigint not null,
    value numeric (5, 2) not null,
    creation_date timestamp not null default now(),
    constraint fk_devices foreign key (device_id) references bds.bds_devices (id)
);