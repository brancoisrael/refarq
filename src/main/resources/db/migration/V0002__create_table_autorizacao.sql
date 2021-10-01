create sequence seq_menu no minvalue no maxvalue;
create table tb_menu(
	id bigint default nextval('seq_menu') primary key,	
	nome varchar(50) unique not null,
	descricao varchar(500) null,
	icone varchar(100) null,
	path varchar(200) null,
	hierarquia integer unique
);

create table tb_funcionalidade(
	id bigint primary key,
	nome varchar(500) unique not null,
	rota varchar(200) not null,
	verbo varchar(10) not null,
	id_menu bigint null
);

alter table tb_funcionalidade add constraint fk01_tb_funcionalidade foreign key (id_menu) references tb_menu (id) on delete no action;

create sequence seq_perfil no minvalue no maxvalue;
create table tb_perfil(
	id bigint default nextval('seq_perfil') primary key,
	nome varchar(50) unique not null,
	descricao varchar(500)
);

create table tb_funcionalidade_perfil(
	id_funcionalidade bigint not null,
	id_perfil bigint not null,
	primary key (id_funcionalidade,id_perfil)
);

alter table tb_funcionalidade_perfil add constraint fk01_tb_funcionalidade_perfil foreign key (id_perfil) references tb_perfil (id) on delete no action;
alter table tb_funcionalidade_perfil add constraint fk02_tb_funcionalidade_perfil foreign key (id_funcionalidade) references tb_funcionalidade (id) on delete no action;



