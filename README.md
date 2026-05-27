# Programación III - UTN - Backend en Java

Repositorio del Trabajo Práctico N°8 de la asignatura Programación III de la Tecnicatura Universitaria en Programación (UTN), enfocado en el diseño e implementación de un modelo de dominio persistente utilizando JPA (Java Persistence API).

## Funcionalidades Implementadas

* **TP8 - JPA (Java Persistence API):**
  * Mapeo Objeto-Relacional (ORM) mediante anotaciones (`@Entity`, `@Id`, `@OneToMany`, `@ManyToOne`, `@Enumerated`).
  * Configuración de la unidad de persistencia conectada a una base de datos embebida (H2) a través de `persistence.xml`.
  * Operaciones CRUD utilizando `EntityManager`:
    * Persistencia de múltiples entidades (Usuarios, Productos, Categorías, Pedidos y Detalles) aprovechando `CascadeType`.
    * Actualización de registros (`merge`).
    * Búsqueda de entidades por Clave Primaria (`find`).
    * Consultas personalizadas mediante **JPQL** (ej: búsqueda por email).
    * Eliminación física de registros (`remove`).

## Objetivo

Diseñar e implementar un modelo de dominio persistente utilizando JPA, comprendiendo el ciclo de vida de las entidades y la ejecución de operaciones CRUD sobre una base de datos relacional sin escribir sentencias SQL nativas.

## Estructura del Proyecto

El proyecto sigue el paradigma de **POO**, con herencia desde una clase abstracta `Base` (`@MappedSuperclass`).

* `org.jcr.entidades/` — Clases del modelo (`Base`, `Usuario`, `Pedido`, `Producto`, `Categoria`, `DetallePedido`) e interfaz `Calculable`.
* `org.jcr.enums/` — Enumeraciones mapeadas como texto para garantizar la integridad de los datos (`Estado`, `FormaPago`, `Rol`).
* `META-INF/` — Contiene el archivo `persistence.xml` con la configuración del motor ORM.
* `Main.java` — Clase principal con la inicialización de `EntityManagerFactory` y la ejecución estructurada de las operaciones requeridas.

## Tecnologías Utilizadas

* **Java** (JDK 17)
* **Gradle** (Gestión de dependencias)
* **JPA / Hibernate** (Motor ORM)
* **H2 Database** (Base de datos embebida)
* **Lombok** (Generación automática de código y patrón Builder)
* **Apache NetBeans IDE**

## Autor

**Nicolás A. Pannunzio** – Full Stack Developer & QA Specialist
🔗 [Perfil de LinkedIn](https://www.linkedin.com/in/nicolas-a-pannunzio-/)
