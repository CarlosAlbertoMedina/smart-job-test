# smart-job-test

Prueba técnica en Java para Smart Job.

## Descripción de la prueba

Desarrollar una aplicación que exponga una API RESTful para creación de usuarios.  
Todos los endpoints deben aceptar y retornar solamente JSON, incluyendo los mensajes de error.

## Diseño técnico

[Ver diseño técnico](https://github.com/CarlosAlbertoMedina/smart-job-test/blob/main/Dise%C3%B1o%20tecnico.png)

## Tecnologías utilizadas

- **Spring Boot 3** (Framework principal)
- **Maven** (Gestor de dependencias y construcción)
- **Java 17** (Versión del JDK)
- **H2 Database** (Base de datos en memoria para pruebas)
- **Spring Data JPA** (Persistencia de datos)
- **Spring Validation** (Validaciones de entrada)
- **Lombok** (Reducción de código repetitivo)
- **JWT** (Generación de token de acceso, configurado en `application.yml`)
- **Springdoc OpenAPI** (Documentación Swagger)

## Configuración y ejecución

### Requisitos previos
- Java 17 instalado
- Maven instalado
- IDE recomendado: IntelliJ IDEA o Eclipse

### Clonar y ejecutar

```bash
git clone https://github.com/CarlosAlbertoMedina/smart-job-test.git
cd smart-job-test
./mvnw spring-boot:run

o también puedes correr directamente la clase principal:
RegistrationApplication.java desde tu IDE.

### Swagger UI:
http://localhost:8080/swagger-ui/index.html

### Consola H2 (Base de datos):
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:registrationdb
User: sa
Password: (dejar en blanco)

