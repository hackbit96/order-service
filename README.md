# Order Service

## Descripción
Microservicio para el reto técnico Interbank utilizando Java 17, Spring WebFlux y Azure SDKs.

## Ejecutar localmente

```bash
mvn clean spring-boot:run
```

## Construir imagen Docker

```bash
docker build -t order-service:latest .
docker run -p 8081:8080 order-service
```
