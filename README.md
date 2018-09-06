# Spring boot Alura com Docker 

Aplicacao desenvolvida do curos da alura utilizando banco de 
dados Mysql e docker

### Configuração do docker

Crie um arquivo chamado docker-compose.yml
Com o seguinte conteúdo:

```sh
  db:
    image: mysql
    restart: always
    volumes:
      - << sua pasta onde deverá esta localizado o dump da base de dados>>:/root
    expose:
      - "3306"
    environment:
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_USER: alura
      MYSQL_PASSWORD: alura
    ports:
     - "3306:3306"
```

Após a criação do docker execute o comando:

```sh
docker-compose up -d
```

E o seu containder será criado.
Para acessar o contêiner execute o comando:

```sh
  docker exec -it mysql_db_1 bash
```

Agora estamos dentro do docker e vamos conectar a base de dados com usuário 
root que definimos na criação do contêiner
Execute o comando a seguir para efetuar a conexão com a base de dados:

```sh
mysql --user=root --password=123456 
```

Após conectamos com usuário root vamos fornece previlegios ao usuário alura
com o comando a seguir:

```sh
GRANT ALL PRIVILEGES ON * . * TO 'alura'@'%';
```

Digite exit para fazer o log do usuário atual
Conecte a base de dados com usuário alura  

```sh
mysql --user=alura --password=alura
```

Crie o banco de dados

```sh
create database listavip;
```

Selecione o banco de dados

```sh
use listavip;
```
Execute o DUMP

```sh
source root//backup_database.sql
```

 
Lista usuários
select host, user from mysql.user; 
