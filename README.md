# desafio-microservices-banco

Tecnologias utilizadas:

1. Micro Servicos
2. Arquitetura Circuit Breaker
3. Spring Cloud com Eureka e Zuul
4. Serviço de Discovery e Gateway
4. JWT
5. Documentacao com Swagger UI


Instruçoes:

1. executar mvn clean install na pasta "\microservices-banco"
2. rodar o comando "docker-compose up" dentro do diretorio \microservices-banco\core 
    
3. Startar os servicos na seguinte ordem*:
  1.discovery [Port:8081]
  2.gateway [Port:8080] 
  3.Auth  
  3.core [Port:8085] *
  4.conta-corente [Port:8082]
  5.cadastro-pessoa [Port:8083]
  
  *Adicionei uma configuracao no bootstrap da applicaçao que cria o banco de dados, importante que a porta 3308 esteja liberada.
 
 A documentaçao da API estara disponivel em:
 
 http://localhost:8080/gateway/pessoa/swagger-ui.html
 
 http://localhost:8080/gateway/conta-corrente/swagger-ui.html
 
  
 
 Eureka Server:
 http://localhost:8081/
 
 **Atualização:
 
 Adicionado o microserviço de autentificaçao via token. Para realizar chamadas via postman será necessario realizar o login e adicionar o token no header da requisiçao desejada:
 
 http://localhost:8080/gateway/auth/login
 
  Deixei as chamadas via swagger sem a necessidade de autentificação para facilitar os testes.
  
  Também adicionei um usuario padrao no bd no bootstrap para facilitar: {user:TESTES, password:"teste123"}
  
  
 
 
