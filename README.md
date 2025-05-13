# Game Centre API

> API REST construida con Spring Boot, autenticación JWT y MySQL.

### Descripción del Proyecto


**Game Centre** es una API RESTful que simula una plataforma de gestión de videojuegos, en la que distintos tipos de usuarios (Admins y Players) pueden gestionar juegos y franquicias.
Los Admins tienen privilegios de creación y edición, mientras que los Players pueden explorar otros jugadores y poseen juegos. Este proyecto implementa autenticación con JWT, validaciones, herencia entre clases con JPA, y buenas prácticas REST.

[**Presentación Game Centre**](https://www.canva.com/design/DAGnUSR4H0s/QhKcn_CFU50SfA2cVguQuw/view?utm_content=DAGnUSR4H0s&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=he8dc8c0c6d)
### Diagrama de clases


###  Diagrama de clases

                      +-----------------+
                      |     ERole       |
                      +-----------------+
                      | - ROLE_EDITOR   |
                      | - ROLE_CREATOR  |
                      | - ROLE_PLAYER   |
                      +-----------------+

                            ▲
                            │ (usado en User)

                      +-----------------+
                      |     User        |  <<abstract>>
                      +-----------------+
                      | - id: Long      |
                      | - username: String
                      | - password: String
                      | - role: ERole   |
                      +-----------------+

                            ▲           ▲
                            │           │
               +------------+           +-------------+
               |                                        |
        +-------------------+              +---------------------+
        |       Admin        |             |       Player        |
        +-------------------+              +---------------------+
        | - name: String     |             | - credit: int       |
        | - surname: String  |             | - games: List<Game> |
        +-------------------+              +---------------------+
        | +setRole(role)     |             |+setRole(ROLE_PLAYER)|
        +-------------------+              +---------------------+
                                                     ▲
                                                     │
                                            +-----------------+
                                            |      Game       |
                                            +-----------------+
                                            | - id: Long      |
                                            | - name: String  |
                                            | - duration: int |
                                            | - description: String |
                                            +-----------------+
                                            | * franchise: Franchise |
                                            +-----------------+
                                                     ▲
                                                     │
                                            +---------------------+
                                            |     Franchise       |
                                            +---------------------+
                                            | - id: Long          |
                                            | - name: String      |
                                            | - description: String |
                                            +---------------------+



###  Configuración del Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/ODS-Ironhack/gameCenter

2. Crea la base de datos en MySQL:
   ```bash
   CREATE DATABASE game_centre;

3. Configura tu archivo application.properties:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/game_centre 
   spring.datasource.username=tuUsuario
   spring.datasource.password=tuContraseña
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.hibernate.ddl-auto=update

> Nota: La clave secreta usada para firmar los tokens JWT (`secret_key`) está definida directamente dentro del servicio `JwtService.java`.



### Estructura de Carpetas

```text
src/
├── main/
│   ├── java/
│   │   └── com/project_adventure/lab/
│   │       ├── controllers/         # Endpoints REST
│   │       ├── dtos/                # DTOs para requests/responses
│   │       ├── exceptions/          # Manejo de errores personalizados
│   │       ├── filters/             # Filtros JWT
│   │       ├── models/              # Entidades (User, Admin, Player, Game, Franchise y ERole)
│   │       ├── repositories/        # Interfaces JPA
│   │       ├── security/            # Configuración Spring Security
│   │       ├── services/            # Lógica de negocio
│   │       └── LabApplication.java  # Clase principal
│
├── resources/
│   ├── data/
│   │   ├── franchises.sql
│   │   └── games.sql
│   ├── static/
│   └── application.properties
│
└── test/
    └── java/
        └── com/project_adventure/lab/
            └── ... (tests unitarios e integración)

```

###  Tecnologías Utilizadas

- Java 
- Spring Boot
- Spring Security con JWT
- Hibernate / JPA
- Lombok
- MySQL
- Maven
- JUnit 5 


###  Rutas y Roles

####  Autenticación
- `POST /api/auth/login` – Iniciar sesión
- `POST /api/auth/register` – Registrar usuario (Admin o Player)

####  Juegos
- `GET /api/game` – Ver juegos (todos los roles)
- `POST /api/game` – Crear juego (solo CREATOR)
- `PUT /api/game/{id}` – Editar juego (solo EDITOR)
- `PATCH /api/game/{id}` – Actualización parcial (EDITOR)
- `DELETE /api/game/{id}` – Eliminar juego (EDITOR)

####  Franquicias
- `GET /api/franchise` – Ver franquicias (todos los roles)
- `POST /api/franchise` – Crear franquicia (solo CREATOR)
- `PUT /api/franchise/{id}` – Editar franquicia (solo EDITOR)
- `DELETE /api/franchise/{id}` – Eliminar franquicia (solo EDITOR)

####  Usuarios
- `GET /api/player/username/{username}` – Ver perfil de jugador (PLAYER)
- `GET /api/player/admin/all` – Ver todos los jugadores (EDITOR, CREATOR)
- `PUT /api/player/{id}` – Editar jugador (solo EDITOR)

### Trabajo Futuro

- Proteger información del Player según el Role
- Posibilidad de implementar lógica para sumar o restar credit en el Player
- Mejorar relación entre Franchise y Game
- Implementar tests con MockMvc

