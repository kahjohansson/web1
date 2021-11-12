# Projeto 2 - Sistema de agendamento utilizando Spring MVC, Spring Data JPA, Spring Security e Thymeleaf

Este projeto foi desenvolvido por:
- Karina Mayumi Johansson 
- Reynold Navarro Mazo

O sistema segue o conjunto de requisitos C

___
## Como usar?

### Criação e povoamento do banco de dados

```
mysql -u root -p
source db/mysql/create.sql;
source db/mysql/populate.sql;
```

### Como rodar o projeto?

```
mvn spring-boot:run
```
___
## Requisitos cumpridos

| Requisito | Cumprido?                 | Observação                                                | Participação de cada integrante |
|-----------|---------------------------|-----------------------------------------------------------|---------------------------------|
| R1        | Implementado              | -                                                         | 30% - Karina 70% - Reynold      |
| R2        | Implementado              | -                                                         | 15% - Karina 85% - Reynold      |
| R3        | Parcialmente implementado | Somente o filtro não está implementado                    | 100% - Reynold                  |
| R4        | Implementado              | Não está implementado o disparo de e-mails                | 85% - Karina 15% - Reynold      |
| R5        | Implementado              | -                                                         | 100% - Karina                   |
| R6        | Parcialmente implementado | Está implementado, porém apresenta erro                   | 85% - Karina 15% - Reynold      |
| R7        | Parcialmente implementado | Está implementado, porém apresenta erro                   | 85% - Karina 15% - Reynold      |
| R8        | Implementado              | -                                                         | 50% - Karina 50% - Reynold      |
| R9        | Implementado              | Está totalmente implementado,  porém não está funcionando | 100% - Reynold                  |