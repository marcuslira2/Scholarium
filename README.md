# Scholarium

O **Scholarium** é um sistema de gerenciamento escolar baseado em uma arquitetura de microserviços. Este repositório contém o código fonte do backend, desenvolvido com Java 17 e Spring Boot.

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **RabbitMQ** (Mensageria)
- **RestTemplate** (Comunicação entre serviços)
- **API Gateway** (Autenticação e roteamento)
- **MariaDB** (Banco de dados relacional)

## 🔄 Arquitetura

O backend do Scholarium é composto por vários microserviços independentes que se comunicam via RabbitMQ e REST. Os principais serviços incluem:

- **Profile Service**: Responsável pelo CRUD de usuários.
- **Authenticate-service**: Responsavel pela atuenticação dos usuarios.
- **API Gateway**: Centraliza a validação do token Jwt e roteamento de requisições.
- **Scholarium-server**: Eureka server.

### Fluxo de Comunicação

1. O **API Gateway** recebe as requisições do cliente.
2. As requisições são roteadas para os serviços correspondentes (Profile e outros, futuramente).
3. Mensagens assíncronas são enviadas via RabbitMQ para sincronização entre serviços. - EM ANDAMENTO

## 🔒 Configuração do Ambiente

### Requisitos

- **Java 17**
- **Maven** 3.6+
- **Docker** (opcional, para executar RabbitMQ e bancos de dados em contêineres)

### Variáveis de Ambiente

Certifique-se de configurar as variáveis de ambiente necessárias:

```bash
# Configuração do RabbitMQ
RABBITMQ_HOST=localhost
RABBITMQ_PORT=5672
RABBITMQ_USERNAME=guest
RABBITMQ_PASSWORD=guest

# Configuração do Banco de Dados
DB_URL=jdbc:postgresql://localhost:5432/scholarium
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Configuração do API Gateway
GATEWAY_SECRET_KEY=your_secret_key
```

### Executando Localmente

1. Clone o repositório:

   ```bash
   git clone https://github.com/marcuslira2/Scholarium.git
   cd scholarium
   ```

2. Compile e execute o projeto:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Inicie o RabbitMQ e os bancos de dados, se necessário:

   ```bash
   docker-compose up -d
   ```

## 📊 Estrutura do Projeto

```plaintext
scholarium/
├── scholarium-gateway/          # API Gateway e validador de token Jwt.
├── scholarium-server/           # Eureka server.
├── authenticate-service/        # API de autenticação de usuarios.
├── profile-service/             # Serviço de CRUD de funcionarios e alunos.
```

## 🛠️ Contribuição

Contribuições são bem-vindas! Para começar:

1. Faça um fork do repositório.
2. Crie um branch para suas modificações:

   ```bash
   git checkout -b minha-feature
   ```

3. Envie um Pull Request explicando suas alterações.

## ✉ Contato

Se tiver dúvidas ou sugestões, entre em contato:

- **Email**: marcuslira57@gmail.com
- **LinkedIn**: [Marcus Lira](https://www.linkedin.com/in/marcus-lira-923988196)

## © Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

