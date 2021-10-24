USE sistema_agendamento;

INSERT INTO usuarios (cpf, nome, email, senha) VALUES ('11111111111', 'Carla Marques', 'carla.marques@email.com', 'admin');
INSERT INTO usuarios (cpf, nome, email, senha) VALUES ('22222222222', 'André Ramos', 'andre.ramos@email.com', 'cliente');
INSERT INTO usuarios (cpf, nome, email, senha) VALUES ('3333333333', 'Bianca Perez', 'bianca.perez@email.com', 'profissional');

INSERT INTO administradores (cpf) VALUES ('22222222222');
INSERT INTO clientes (cpf, sexo, telefone, data_nascimento) VALUES ('22222222222', 'masculino', '5513999999999', '1990-02-02');
INSERT INTO profissionais (cpf, area, especialidade, curriculo) VALUES ('3333333333', 'psicologia', 'psicanálise lacaniana', 'curriculo_bianca_perez');

INSERT INTO consultas(cpfCliente, cpfProfissional, data) values ('22222222222', '3333333333', '2021/01/01');