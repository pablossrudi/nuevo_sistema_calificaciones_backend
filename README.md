## SISTEMA DE CALIFICACIONES

> ### OBSERVACIÓN
> Este proyecto se realizó como trabajo entregable para el modulo "Desarrollo de Aplicaciones JEE con Spring Framework" del bootcamp "Full Stack Java Trainee", impartido por E-Camp Edutecno, pero con algunos cambios pequeños que se indicarán en la descripción del proyecto.

---
> ### DESCRIPCIÓN:
>
> La Universidad de Los Andes necesita desarrollar una aplicación que trabaje en conjunto con un api para llevar el control de notas de los estudiantes en carreras de pregrado y debe cumplir con las siguientes características.
>
> La funcionalidad que se debe desarrollar es de un sistema capaz de manejar datos de alumnos y para saber que materias tienen asignadas.
>
> Para lograr la funcionalidad, lo que se espera es que se desarrolle un servicio REST utilizando Spring, para contener la lógica de los alumnos con sus respectivas materias. Además, este servicio REST debe contener una autenticación mediante JWT.
>
> Se deben probar los endpoints para crear (se agrego editar y elimniar) alumnos, materias y usuarios (se agregaron los endpoints de crear, editar y eliminar las notas de los alumnos en sus respectivas materias. Esto para darle sentido al nombre del proyecto), ya sea usando Insomnia u otro cliente para probar servicios REST.
>
> Se deben ingresar datos de un usuario para que se pueda iniciar sesión y obtener un token JWT, además de insertar alumnos con algunas materias.
>
> Para visualizar los datos se debe desarrollar una aplicación con Spring (aunque en el trabajo original se ocupó Thymeleaf, en este proyecto se utilizó REACT para el frontend), en la que se deba iniciar sesión a través de un formulario y pueda validarse contra el servicio REST que contiene los usuarios. Luego, se debe visualizar una vista con un menú donde se vean los alumnos existentes con sus respectivas materias.

---
## DOCUMENTACIÓN DE LA API

### Usuarios de prueba

- **Usuario Administrador:**
```json
{
  "userName": "admin",
  "password": "admin123"
}
```

- **Usuario Cliente:**
```json
{
  "userName": "client",
  "password": "hola123"
}
```

---

### Endpoints

---
#### Auth Endpoints

#### `POST` /api/auth/login

>A esta ruta pude acceder cualquier persona

**Descripción:** Iniciar sesión.

**Request Body:**
```json
{
  "userName": "admin",
  "password": "admin123"
}
```

**Response:**
- Status: `200 OK`
- Body:
```json
{
  "role": "ROLE_ADMIN",
  "jwt": "eyJhbGciOiJIUzI1NiJ9...."
}
```
---

#### `POST` /api/auth/register

>A esta ruta solo pueden acceder los usuarios con el rol `ROLE_ADMIN`

**Descripción:** registra usuario.

**Request Body:**
```json
{
  "userName": "prueba_usuario",
  "rut": "12321344-1",
  "password": "hola123",
  "email": "pruebausuario@usuario.com",
  "roles": [
    "ROLE_CLIENT"
  ]
}
```

**Response:**
- Status: `200 OK`
- Body:
```json
{
  "userId": 3,
  "userName": "prueba_usuario",
  "rut": "12321344-1",
  "email": "pruebausuario@usuario.com",
  "roles": [
    "ROLE_CLIENT"
  ],
  "active": true
}
```
---

#### User Endpoints

#### `GET` /api/users

>A esta ruta puden acceder los usuarios con los roles `ROLE_ADMIN` y `ROLE_CLIENT`

**Descripción:** Recupera una lista de users.

**Response:**
- Status: `200 OK`
- Body:
```json
[
  {
    "userId": 1,
    "userName": "admin",
    "rut": "26.931.652-7",
    "email": "admin@example.com",
    "roles": [
      "ROLE_ADMIN"
    ],
    "active": true
  },
  {
    "userId": 2,
    "userName": "client",
    "rut": "12.321.344-1",
    "email": "client@example.com",
    "roles": [
      "ROLE_CLIENT"
    ],
    "active": true
  }
]
```
---

#### `PATCH` /api/users/{userId}

>A esta ruta solo pueden acceder los usuarios con el rol `ROLE_ADMIN`

**Descripción:** actualiza el estado booleano del usuario seleccionado (`true` o `false`).

**Request Body:**
```json
{
  "active": false
}
```

**Response:**
- Status: `200 OK`
- Body:
```json
{
  "userId": 2,
  "userName": "client",
  "rut": "12.321.344-1",
  "email": "client@example.com",
  "roles": [
    "ROLE_CLIENT"
  ],
  "active": false
}
```
---



#### Alumno Endpoints

#### `GET` /api/alumnos

>A esta ruta puden acceder los usuarios con los roles `ROLE_ADMIN` y `ROLE_CLIENT`

**Descripción:** Recupera una lista paginada de alumnos.

**Request Parameters:**
- `page` (optional, default: `0`): Número de página.
- `size` (optional, default: `5`): Tamaño de la página.

**Response:**
- Status: `200 OK`
- Body:
```json
{
  "numberOfElements": 2,
  "alumnos": [
    {
      "alumnoId": 1,
      "rut": "12345678-9",
      "nombre": "Juan Perez",
      "direccion": "Calle 123",
      "materias": [
        "Matematicas",
        "Fisica"
      ]
    },
    {
      "alumnoId": 2,
      "rut": "98765432-1",
      "nombre": "Maria Lopez",
      "direccion": "Avenida Siempre Viva",
      "materias": [
        "Quimica"
      ]
    }
  ],
  "numberPage": 0
}
```
---

#### `GET` /api/alumnos/{id}

>A esta ruta puden acceder los usuarios con los roles `ROLE_ADMIN` y `ROLE_CLIENT`

**Descripción:** Recupera un alumno por id.

**Response:**
- Status: `200 OK`
- Body:
```json
{
  "alumnoId": 1,
  "rut": "12345678-9",
  "nombre": "Juan Perez",
  "direccion": "Calle 123",
  "materias": [
    "Matematicas",
    "Fisica"
  ]
}
```
---

#### `POST` /api/alumnos

>A esta ruta solo pueden acceder los usuarios con el rol `ROLE_ADMIN`

**Descripción:** Crea un nuevo alumno

**Request Body:**
```json
{
  "rut": "12345678-9",
  "nombre": "Juan Pérez",
  "direccion": "Calle Falsa 123"
}
```

**Response:**
- Status: `200 OK`

---

#### Materia Endpoints

#### `GET` /api/materias

>A esta ruta puden acceder los usuarios con los roles `ROLE_ADMIN` y `ROLE_CLIENT`

**Descripción:** Recupera una lista paginada de materias.

**Request Parameters:**
- `page` (optional, default: `0`): Número de página.
- `size` (optional, default: `5`): Tamaño de la página.

**Response:**
- Status: `200 OK`
- Body:
```json
{
  "numberOfElements": 2,
  "numberPage": 0,
  "materias": [
    {
      "materiaId": 1,
      "materiaNombre": "Matematicas",
      "alumno": {
        "alumnoId": 1,
        "rut": "12345678-9",
        "nombre": "Juan Perez",
        "direccion": "Calle 123",
        "materias": [
          "Matematicas",
          "Fisica"
        ]
      }
    },
    {
      "materiaId": 2,
      "materiaNombre": "Fisica",
      "alumno": {
        "alumnoId": 1,
        "rut": "12345678-9",
        "nombre": "Juan Perez",
        "direccion": "Calle 123",
        "materias": [
          "Matematicas",
          "Fisica"
        ]
      }
    }
  ]
}
```
---
#### `GET` /api/materias/{id}

>A esta ruta puden acceder los usuarios con los roles `ROLE_ADMIN` y `ROLE_CLIENT`

**Descripción:** Recupera una materia por id.

**Response:**
- Status: `200 OK`
- Body:
```json
{
  "materiaId": 1,
  "materiaNombre": "Matematicas",
  "alumno": {
    "alumnoId": 1,
    "rut": "12345678-9",
    "nombre": "Juan Perez",
    "direccion": "Calle 123",
    "materias": [
      "Matematicas",
      "Fisica"
    ]
  }
}
```
---
#### `POST` /api/materias

>A esta ruta solo pueden acceder los usuarios con el rol `ROLE_ADMIN`

**Descripción:** Crea una nueva materia.

**Request Body:**
```json
{
  "nombre": "materia prueba1",
  "alumnoId": 11
}
```

**Response:**
- Status: `200 OK`
- Body:


---
