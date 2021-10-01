create sequence hibernate_sequence no minvalue no maxvalue;

create table revinfo(
	rev bigint primary key,
	revtstmp bigint
);