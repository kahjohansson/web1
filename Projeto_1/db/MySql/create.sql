create database SistemaAgendamento;
use SistemaAgendamento;

create table Cliente(
    cpf varchar(16) not null unique,
    nome varchar(256) not null,
    email varchar(256) not null unique,
    senha varchar(256) not null,
    telefone varchar(13) not null,
    sexo varchar(256) not null,
    dataNasc date not null,

    primary key (cpf)
);

insert into Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc) values ('1', 'Fulano', 'fulano@gmail.com', 'fulano', '19 1111-1111', 'Masculino', '2001/01/01');

insert into Cliente(cpf, nome, email, senha, telefone, sexo, dataNasc) values ('2', 'Cicrano', 'cicrano@gmail.com', 'cicrano', '19 2222-2222', 'Masculino', '2002/02/20');

SET GLOBAL time_zone = '-3:00';
