```mermaid
erDiagram
    USUARIO {
        int id PK
        string nombre
        string apellido
        string email
        string contrasena
        string rol
        date fecha_registro
        string perfil
    }

    CURSO {
        int id PK
        string titulo
        string descripcion
        int instructor_id FK
        int categoria_id FK
        float precio
        string nivel
        date fecha_creacion
        string estado
    }

    CATEGORIA {
        int id PK
        string nombre
        string descripcion
    }

    LECCION {
        int id PK
        string titulo
        string contenido
        int curso_id FK
        int orden
    }

    INSCRIPCION {
        int id PK
        int usuario_id FK
        int curso_id FK
        date fecha_inscripcion
    }

    RESENA {
        int id PK
        int usuario_id FK
        int curso_id FK
        int calificacion
        string comentario
        date fecha
    }

    PREGUNTA {
        int id PK
        int curso_id FK
        int usuario_id FK
        string contenido
        date fecha
    }

    RESPUESTA {
        int id PK
        int pregunta_id FK
        int usuario_id FK
        string contenido
        date fecha
    }

    USUARIO ||--o{ CURSO : crea
    USUARIO ||--o{ INSCRIPCION : inscribe
    CURSO ||--o{ INSCRIPCION : tiene
    CURSO ||--o{ LECCION : contiene
    CURSO ||--o{ RESENA : tiene
    CURSO ||--o{ PREGUNTA : contiene
    PREGUNTA ||--o{ RESPUESTA : tiene
    USUARIO ||--o{ RESENA : escribe
    USUARIO ||--o{ PREGUNTA : pregunta
    USUARIO ||--o{ RESPUESTA : responde
    CURSO }o--|| CATEGORIA : pertenece