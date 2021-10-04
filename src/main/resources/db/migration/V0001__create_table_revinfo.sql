create sequence hibernate_sequence start 1;

create table revinfo(
	rev bigint primary key,
	revtstmp bigint
);