# Projeto 1 - Sistema de agendamento

Este projeto foi desenvolvido por:
- Karina Mayumi Johansson 
- Reynold Navarro Mazo

O sistema segue o conjunto de requisitos C1

___
## Como usar?

### Criação e povoamento do banco de dados

```
mysql -u root -p
source db/MySql/create.sql;
source db/MySql/populate.sql;
```

### Compilação e deploy

```
mvn compile
mvn tomcat7:deploy
```
___
## Requisitos cumpridos

| Requisito | Cumprido?                    | Observação                                                        | Participação de<br>cada integrante |
|-----------|------------------------------|-------------------------------------------------------------------|------------------------------------|
| R1        | Parcialmente<br>implementado | Somente a atualização<br>não funciona, porém<br>está implementada | 60% Karina<br>40% Reynold          |
| R2        | Parcialmente<br>implementado | Somente a atualização<br>não funciona, porém<br>está implementada | 40% Karina<br>60% Reynold          |
| R3        | Parcialmente<br>implementado | Somente os filtros<br>não foram implementados                     | 30% Karina<br>70% Reynold          |
| R4        | Parcialmente<br>implemente   | Somente não foi<br>implementado o envio<br>de e-mail              | 30% Karina<br>70% Reynold          |
| R5        | Implementado                 | -                                                                 | 0% Karina<br>100% Reynold          |
| R6        | Implementado                 | -                                                                 | 60% Karina<br>40% Reynold          |
| R7        | Implementado                 | -                                                                 | 90% Karina<br>10% Reynold          |
| R8        | Implementado                 | -                                                                 | 50% Karina<br>50% Reynold          |