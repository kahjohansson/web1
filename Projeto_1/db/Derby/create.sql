connect 'jdbc:derby:sistema_agendamento;create=true;user=root;password=root';

create table Cliente(
	cpf varchar(11) not null, 
	nome varchar(256) not null,
	email varchar(256) not null,
	senha varchar(256) not null,
	telefone varchar(11) not null,
	sexo varchar(32) not null,
	dataNasc date not null,
	
	constraint Cliente_PK primary key (cpf)
);

disconnect;

quit;
