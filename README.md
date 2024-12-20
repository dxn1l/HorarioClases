
# Horario de Clases

LINK al repositorio: https://github.com/dxn1l/HorarioClases

## Descripción

**Horario de Clases** es una aplicación móvil que permite gestionar los horarios de clases de los usuarios. A través de esta aplicación, los usuarios pueden agregar, ver y consultar las clases actuales de forma sencilla y eficiente.

## Características

- **Agregar clase**: Los usuarios pueden registrar una nueva clase, incluyendo información como el nombre, horario, día, profesor y aula.
- **Consultar horario**: Los usuarios pueden ver todas las clases registradas para un día específico.
- **Clase actual**: Los usuarios pueden consultar la clase que está en curso en el momento actual.
- **Validación de datos**: La aplicación valida que los horarios ingresados estén en el formato correcto y dentro del rango permitido (00:00 - 23:59).

## Tecnologías utilizadas

- **Jetpack Compose**: Para construir la interfaz de usuario de forma declarativa.
- **Firebase**: Para el manejo de almacenamiento de datos en tiempo real.
- **Kotlin**: El lenguaje de programación utilizado para desarrollar la aplicación.
- **Navigation Compose**: Para la navegación entre pantallas de forma sencilla y eficiente.
- **Material 3**: Para el diseño y los componentes visuales de la aplicación.

## Estructura de la aplicación

1. **Pantallas**:
    - **HomeScreen**: Pantalla principal donde el usuario puede elegir entre agregar una clase, consultar el horario o ver la clase actual.
    - **AddClassScreen**: Pantalla para agregar una nueva clase.
    - **ViewClassScreen**: Pantalla para visualizar las clases guardadas.
    - **ActualClassScreen**: Pantalla para consultar la clase actual.

2. **Firebase**:
    - **Firebase Firestore**: Para almacenar las clases de los usuarios.