# PedidosSpringRabbitMQ

Projeto demonstrando um consumo simples de uma fila RabbitMQ, bem como envio dos dados para o MongoDB.
Aqui também estão implementados tratamento de exceções e testes unitários :)

## Pré-requisitos

Para rodar o projeto, você precisa ter instalado em sua máquina:

- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão 11 ou superior)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Configuração

- Dentro da pasta do projeto, execute:
```bash
mvn clean install
```

- Dentro da pasta "local", execute
```bash
docker-compose up
```

## Exemplos de Requisições
1. Criar Cliente.
Para criar um novo cliente e armazená-lo no MongoDB, faça o seguinte POST:

```bash
curl --request POST \
  --url http://localhost:8080/clientes \
  --header 'content-type: application/json' \
  --data '{
  "nome": "exemplo",
  "documento": "12345"
}'
```

2. Enviar Mensagem de Pedido para o RabbitMQ.
Você pode enviar uma mensagem de pedido para a fila do RabbitMQ, que depois será enviada para o MongoDB.
Para isso, entre na interface do RabbitMQ abrindo o navegador e entrando no endereço: http://localhost:15672/#/
- Entre com usuário/senha: guest/guest.
- Escolha a fila "pedidos-criados"
- Envie o body de exemplo a seguir:

```bash
{
   "codigoPedido": 10,
   "codigoCliente": 45,
   "itens": [
      {
         "produto": "caneta",
         "quantidade": "100",
         "preco": 1.80
      },
      {
         "produto": "borracha",
         "quantidade": "120",
         "preco": 1.10
      }
   ]
}
```
3. Buscar pedidos de um cliente.
Para isso, faça o seguinte GET:
```bash
curl --request GET \
  --url http://localhost:8080/clientes/45/pedidos
```


## Obrigado pela visita :)

