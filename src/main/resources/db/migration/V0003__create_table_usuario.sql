create sequence seq_usuario no minvalue no maxvalue;
create table tb_usuario(
	id bigint default nextval('seq_usuario') primary key,	
	nome varchar(200) not null,
	idade smallint
	
);

create table tb_usuario_aud(
	rev bigint not null,
	revtype smallint,
	id bigint not null,	
	nome varchar(200) ,
	idade smallint,	
	primary key (rev,id)
);

alter table tb_usuario_aud add constraint fk01_tb_usuario_aud foreign key (rev) references revinfo (rev) on delete no action;