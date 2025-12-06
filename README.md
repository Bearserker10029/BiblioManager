# BiblioManager

Una librería desea que sus empleados puedan visualizar su catálogo de libros mediante una página web, agregar libros y, además, realizar búsquedas.

Se ha definido la base de datos Librería. Importe el script `libreria.sql`, el cual contiene el modelo de base de datos y los datos de ejemplo necesarios para el desarrollo de la evaluación.

## Requerimientos

### 1. Generación del DAO Base

Usted debe generar un DAO Base que contenga la conexión hacia la base de datos. Una vez realizado esto:

- Todos los desarrollos de DAO posteriores deben heredar de este para realizar la conexión a la base de datos.

> **Nota:** Si no se utiliza el DAO Base, se descontarán puntos.

### 2. Implementar vista de libro

Usted debe implementar el método de visualización considerando lo siguiente:

- Crear un DAO para los libros llamado `LibroDao`.
- Crear un método para listar los libros.
- Crear la vista y tabla para visualizar el listado con sus respectivos botones y filtros que posteriormente serán implementados.
- Mostrar en la vista el listado de los libros que se obtienen desde la base de datos (todos los campos, incluyendo el género y editorial).

### 3. Implementar creación y borrado de libro

Usted debe implementar los métodos de creación y borrado considerando lo siguiente:

- Generar dos métodos abstractos en el DAO Base: uno para la creación y otro para el borrado de cualquier entidad.
- En el `LibroDao`, implementar ambos métodos heredados del DAO Base.
- Utilizar estos métodos en los botones de la vista principal.
- Considerar que, para la creación, al seleccionar género y editorial, se debe seleccionar mediante un ComboBox los datos que se encuentran en la base de datos.
- Considerar que, para que un libro pueda ser borrado, no debe tener ningún premio; es decir, número de premios = 0.

### 4. Uso de ComboBox para filtrar por género

En la vista/lista principal de libros, generar un filtro a modo de ComboBox. Debe poder seleccionarse un valor de la lista de géneros y solo se deben mostrar los libros del género seleccionado.

### 5. Uso de ComboBox para filtrar por editorial

En la vista/lista principal de libros, generar un filtro a modo de ComboBox. Debe poder seleccionarse un valor de la lista de editoriales y solo se deben mostrar los libros de la editorial seleccionada.

> **Nota:** Los filtros se pueden aplicar simultáneamente y pueden ser limpiados cuando se requiera.


