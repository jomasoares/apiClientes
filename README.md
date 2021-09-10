# apiClientes

Projeto de API REST em Java com testes para entrevista técnica.

Segue o desafio:

*Você deverá criar 1 aplicação para cadastramento de notas fiscais conforme a seguir:
Back-end: aplicação JavaEE baseada em Web Services no padrão RESTful API.
Front-end:  aplicação a seu critério que se comunique com estes serviços.
O cadastro do cliente deve conter as seguintes informações:
Razão Social;
CNPJ;
Tipo de Regime Tributário (Simples Nacional ou Lucro Presumido);
E-mail.
Requisitos da aplicação:
Permitir o cadastro, Deleção e recuperação de clientes (empresas);
Tela para executar as ações de cadastro, deleção e recuperação do dado
Dashboard contendo uma análise dos dados(Quantidade de clientes, clientes por tipo de tributação e outro que você acha pertinente)
Apresentar link, ou video da aplicação e enviar código fontes.*

umm video de demonstração pode ser visto [neste link](https://drive.google.com/file/d/1h4jTNqc8vUp-L3snkjxJUMJrMMMrmtnB/view?usp=sharing). Como o foco do desafio é a aplicação REST em Java, todas as validações são feitas na API, nenhuma no front.

O projeto foi desenvolvido em Java 11 e possui as seguintes dependências:
* [**Spring Boot Dev tools**](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
* [**Spring Boot Web**](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
* [**Lombok**](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* [**JPA**](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
* [**H2**](https://h2database.com/html/main.html)
* [**Mapstruct**](https://mapstruct.org/)
* [**JUnit**](https://junit.org/)
* [**Mockito**](https://site.mockito.org/)
* [**Hamcrest**](http://hamcrest.org/)

## Executar o Projeto

Se você já possui o Maven instalado, execute na linha de comando:
```shell script
mvn spring-boot:run 
```
Se não possui, você pode usar o arquivo **mvnw** para a plataforma Linux ou **mvnw.cmd** para Windows, usando o mesmo argumento: **spring-boot:run**. Ambos os arquivos encontram-se na raiz do projeto.

Para executar o front-end, baixe [este repositório](https://github.com/jomasoares/desafioEntrevistaFront) e siga as instruções do README.md

## Documentação da API

A API  possui os seguintes *endpoints*:

| *Método HTTP* | *Endpoint*                 | *Ação*                                                          |
| :-----------: |--------------------------- |  -------------------------------------------------------------- |
| GET           | api/v1/clientes               | Retorna todos os clientes                          |
| GET           | api/v1/clientes/{id}        | Retorna o registro de um cliente com o id passado como argumento |
| GET           | api/v1/clientes/count       | Retorna o total de clientes cadastrados |
| POST          | api/v1/clientes               | Cadastra um novo cliente           |
| PUT           | api/v1/clientes/{id}        | Edita os dados de um cliente com o id passado como argumento                        |
| DELETE        | api/v1/clientes/{id}          | Deleta um cliente com id passado como argumento|
| GET           | api/v1/notasFiscais        | Retorna todos as notas fiscais |
| POST           | api/v1/notasFiscais        | Cria uma nova nota fiscal|
| DELETE           | api/v1/notasFiscais/{id}        | Deleta uma nota fiscal com id passado como argumento|

Os endpoints *GET api/v1/clientes* e *GET api/v1/notasFiscais* possuem ainda parâmetros do tipo query:

| *Método HTTP* | *Endpoint com query param*         | *Ação*                                                                              |
| :-----------: |---------------------------------- |  ----------------------------------------------------------------------------------- |
| GET           | api/v1/clientes?regimeTributario=LUCRO_PRESUMIDO | Retorna todos os clientes com regime tributário lucro presumido|
| GET           | api/v1/clientes?regimeTributario=SIMPLES_NACIONAL | Retorna todos os clientes com regime tributário simples nacional|
| GET           | /api/v1/notasFiscais?clienteId={clienteId} | Retorna todas as notas fiscais de um cliente com id passado como argumento |
