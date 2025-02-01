# Rest API para la gestión de blogs

Rest API para una aplicación que permite la gestión de blogs. Esta aplicación permite crear, actualizar, obtener blogs y registrar comentarios en los blogs.

## Características

Para la realización de este proyecto se utilizaron diversas estrategias, tecnologías y frameworks que permiten una solución simple pero a la vez robusta, escalable y fácil de mantener. A continuación se detallan los elementos clave:

  + Spring Boot: Es la base del proyecto, proporcionando un entorno de desarrollo rápido y simplificado para aplicaciones Java. Facilita la configuración automática y la creación de aplicaciones independientes con servidores embebidos como Tomcat o Jetty. Gracias a su enfoque en la convención sobre configuración, se reduce el tiempo de desarrollo y la complejidad.

  + Spring Data JPA: Permite la integración sencilla con bases de datos relacionales. Utilizando Hibernate como proveedor de JPA, se facilita la implementación de operaciones CRUD (crear, leer, actualizar, eliminar) sin necesidad de escribir consultas SQL complejas, y se aprovechan las ventajas de la programación orientada a objetos.

  + RESTful API: En el caso de querer proporcionar acceso remoto a los datos del blog (por ejemplo, para consumo por una aplicación móvil o frontend independiente), se utilizó Spring Web para crear una API RESTful. Esto facilita la exposición de los datos en formato JSON y la gestión de recursos mediante los métodos estándar de HTTP (GET, POST, PUT, DELETE).

  + Base de datos: Normalmente se utiliza una base de datos relacional como MySQL o PostgreSQL para almacenar los datos del blog (autores, publicaciones, comentarios, etc.). En este caso y para una instalación sin dependencias de herramientas de gestión de bases de datos, se utilizó SQLite, sin embargo, es posible cambiar el origen de datos a MySQL o PostgreSQL sin mayor complicación.
  
  + Manejo de errores y excepciones: Spring Boot facilita la creación de un manejo centralizado de errores utilizando `@ControllerAdvice`. Esta anotación permite interceptar excepciones específicas o generales en todos los controladores de la aplicación y devolver respuestas adecuadas (por ejemplo, mensajes de error estructurados en formato JSON para una API REST). Esto proporciona una experiencia coherente y controlada en cuanto a errores y excepciones.

  + Pruebas: Se emplean herramientas como JUnit y Spring Test para realizar pruebas unitarias e integradas, que aún están pendientes de desarrollo.

  + Docker: Se configuró el archivo Dockerfile para un rápido despliegue en equipos que no cuentan con el JDK principalmente.

## Pasos para la instalación

**1. Clonar el repositorio**

```bash
git clone https://github.com/blueshabat/blog-management.git
```

**2. Ejecutar la aplicación usando maven**

```bash
mvn spring-boot:run
```
La aplicación se ejecutará en la ruta <http://localhost:8080>

## Pasos para la instalación con Docker

**1. Generar el archivo .jar de la aplicación**

```bash
./mvnw clean package
```

**2. Crear la imagen para el contenedor**

```bash
docker build -t blog-management .
```

**3. Ejecutar el contenedor**

```bash
docker run -p 8080:8080 blog-management
```

La aplicación se ejecutará en la ruta <http://localhost:8080>

## Descripción de los endpoints

La app define los siguientes controladores

### Author

| Método | Url | Decripción | Ejemplo de petición | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/authors | Registra un nuevo autor  | [JSON](#add-author) |

### Blog

| Method | Url | Decripción | Ejemplo de petición |
| ------ | --- | ----------- | ------------------------- |
| POST    | /api/blogs | Registra un nuevo blog | [JSON](#add-blog) |
| GET    | /api/blogs/{id} | Obtiene un blog dado un id | |
| GET    | /api/blogs | Obtiene el listado de todos los blogs | |
| PUT    | /api/blogs/{id} | Actualiza la información de un blog | [JSON](#update-blog) |

### Comment

| Método | Url | Decripción | Ejemplo de petición | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/comments | Registra un nuevo comentario en un blog  | [JSON](#add-comment) |

Para realizar las pruebas se puede importar el archivo `postman-collection.json` en Postman.

## Ejemplos de peticiones

##### <a id="add-author">Registrar un autor -> /api/autors</a>
```json
{
    "name": "Juan",
    "firstLastName": "Perez",
    "secondLastName": "Perez",
    "birthdate": "1991-12-12",
    "country": "BOLIVIA",
    "email": "jperez@gmail.com"
}
```

##### <a id="add-blog">Registrar un blog -> /api/blogs</a>
```json
{
    "authorId": 1,
    "title": "Lorem ipsum",
    "topic": "Comunicación",
    "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    "periodicity": "DIARIA",
    "allowComments": false
}
```

##### <a id="update-blog">Actualizar un blog -> /api/blogs/{id}</a>
```json
{
    "authorId": 1,
    "title": "Lorem ipsum DOLO",
    "topic": "Comunicación",
    "content": "Lorem ipsum dolo",
    "periodicity": "SEMANAL",
    "allowComments": true
}
```

##### <a id="add-comment">Registrar un comentario -> /api/comments</a>
```json
{
    "blogId": 1,
    "score": 5,
    "text": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    "authorName": "Juan Perez",
    "authorEmail": "jperez@gmail.com",
    "authorCountry": "BOLIVIA"
}
```
