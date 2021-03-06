CREATE SCHEMA IF NOT EXISTS sistema_agendamento DEFAULT CHARACTER SET utf8;
USE sistema_agendamento;


CREATE TABLE IF NOT EXISTS usuarios (
  cpf VARCHAR(11) NOT NULL,
  nome VARCHAR(256) NOT NULL,
  email VARCHAR(256) NOT NULL,
  senha VARCHAR(256) NOT NULL,
  papel VARCHAR(64) NOT NULL,
  PRIMARY KEY (cpf));


CREATE TABLE IF NOT EXISTS administradores (
  cpf VARCHAR(11) NOT NULL,
  PRIMARY KEY (cpf),
  CONSTRAINT fk_administrador_usuario
    FOREIGN KEY (cpf)
    REFERENCES usuarios (cpf)
    ON DELETE CASCADE
  );


CREATE TABLE IF NOT EXISTS clientes (
  cpf VARCHAR(11) NOT NULL,
  sexo VARCHAR(32) NOT NULL,
  telefone VARCHAR(13) NOT NULL,
  data_nascimento DATE NOT NULL,
  PRIMARY KEY (cpf),
  CONSTRAINT fk_usuario_cliente
    FOREIGN KEY (cpf)
    REFERENCES usuarios (cpf)
    ON DELETE CASCADE
  );


CREATE TABLE IF NOT EXISTS profissionais (
  cpf VARCHAR(11) NOT NULL,
  area VARCHAR(256) NOT NULL,
  especialidade VARCHAR(256) NOT NULL,
  curriculo VARCHAR(512) NOT NULL,
  PRIMARY KEY (cpf),
  CONSTRAINT fk_profissional_usuario
    FOREIGN KEY (cpf)
    REFERENCES usuarios (cpf)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS consultas(
  id INT AUTO_INCREMENT NOT NULL,
	cpfCliente varchar(11) NOT NULL,
	cpfProfissional varchar(14) NOT NULL,
	data DATE NOT NULL,
  horario INT NOT NULL,

  CONSTRAINT fk_cpf_cliente
  FOREIGN KEY (cpfCliente) 
  REFERENCES clientes(cpf) 
  ON DELETE CASCADE,

  CONSTRAINT fk_cpf_profissional
  FOREIGN KEY (cpfProfissional)
  REFERENCES profissionais(cpf)
  ON DELETE CASCADE,
    
 	PRIMARY KEY (id)
);

SET GLOBAL time_zone = '-3:00';