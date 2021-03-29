# compras-spring-boot - Instruçoes para execução do test

Swagger UI : http://localhost:8080/swagger-ui.html

Criar o database : 'compras-spring-boot-db' no banco de dados MYSQL

#Atenção

Antes de executar os testes unitarios, alterar o profile da aplicação no arquivo "application.properties" a propriedade "spring.profiles.active" para o valor "test",
ficando dessa forma: spring.profiles.active=test

Estou disponibilizando uma Collection Postman "API - Controle de Clientes e Compras.postman_collection.json" que se encontra na raiz do projeto e deve ser importada no Postman, nela ja contêm o json estruturado para o controle de clientes e compras
