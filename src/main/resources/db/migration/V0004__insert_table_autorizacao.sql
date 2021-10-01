insert into tb_funcionalidade (id,nome,rota,verbo) values (1,'Inserir usuário','/usuario','POST');
insert into tb_funcionalidade (id,nome,rota,verbo) values (2,'Modificar usuário','/usuario','PUT');
insert into tb_funcionalidade (id,nome,rota,verbo) values (3,'Excluir usuário','/usuario','DELETE');
insert into tb_funcionalidade (id,nome,rota,verbo) values (4,'Pesquisar usuário','/usuario*','GET');

insert into tb_perfil (nome, descricao) values ('Administrador','Administrador geral do sistema');

insert into tb_funcionalidade_perfil (id_funcionalidade, id_perfil) values (1,1);
insert into tb_funcionalidade_perfil (id_funcionalidade, id_perfil) values (2,1);
insert into tb_funcionalidade_perfil (id_funcionalidade, id_perfil) values (3,1);
insert into tb_funcionalidade_perfil (id_funcionalidade, id_perfil) values (4,1);
