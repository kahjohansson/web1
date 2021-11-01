USE sistema_agendamento;

INSERT INTO usuarios (cpf, nome, email, senha, papel) VALUES ('11111111111', 'Admin Admin', 'admin@email.com', '1234', 'admin');
INSERT INTO usuarios (cpf, nome, email, senha, papel) VALUES ('22222222222', 'Cliente Cliente', 'cliente@email.com', '1234', 'cliente');
INSERT INTO usuarios (cpf, nome, email, senha, papel) VALUES ('3333333333', 'Profissional Profissional', 'profissional@email.com', '1234', 'profissional');

INSERT INTO administradores (cpf) VALUES ('22222222222');
INSERT INTO clientes (cpf, sexo, telefone, data_nascimento) VALUES ('22222222222', 'masculino', '5513999999999', '1990-02-02');
INSERT INTO profissionais (cpf, area, especialidade, curriculo) VALUES ('3333333333', 'psicologia', 'psicanálise lacaniana', 'curriculo_bianca_perez');

INSERT INTO consultas(cpfCliente, cpfProfissional, data) values ('22222222222', '3333333333', '2021/01/01');