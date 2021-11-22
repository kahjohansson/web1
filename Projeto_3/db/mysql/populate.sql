USE sistema_agendamento;

-- Senha dos Usuarios = 1234
-- Hash da senha = $2a$10$8ilmdCEu.RvTbscpOu4ztOZMjLeACzzoeLmzJTnaeqbOLghoGrxcG 

INSERT INTO usuarios (cpf, nome, email, senha, papel) VALUES ('11111111111', 'Admin Admin', 'admin@email.com', '$2a$10$8ilmdCEu.RvTbscpOu4ztOZMjLeACzzoeLmzJTnaeqbOLghoGrxcG', 'admin');
INSERT INTO usuarios (cpf, nome, email, senha, papel) VALUES ('22222222222', 'Cliente Cliente', 'cliente@email.com', '$2a$10$8ilmdCEu.RvTbscpOu4ztOZMjLeACzzoeLmzJTnaeqbOLghoGrxcG', 'cliente');
INSERT INTO usuarios (cpf, nome, email, senha, papel) VALUES ('3333333333', 'Profissional Profissional', 'profissional@email.com', '$2a$10$8ilmdCEu.RvTbscpOu4ztOZMjLeACzzoeLmzJTnaeqbOLghoGrxcG', 'profissional');

INSERT INTO administradores (cpf) VALUES ('22222222222');
INSERT INTO clientes (cpf, sexo, telefone, data_nascimento) VALUES ('22222222222', 'masculino', '5513999999999', '1990-02-02');
INSERT INTO profissionais (cpf, area, especialidade, curriculo) VALUES ('3333333333', 'psicologia', 'psicanálise lacaniana', 'curriculo_bianca_perez');

INSERT INTO consultas(cpfCliente, cpfProfissional, data, horario) values ('22222222222', '3333333333', '2021/01/01', 10);