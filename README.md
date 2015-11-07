#Proyecto OpenApi 1.0.2
##por Juan Ignacio Chávez 24/Octubre/2015

----
##JDBC
> Se integran las funcionalidades básicas para las transacciones a bases de datos, pudiendo ejecutar comandos SQL. De igual forma se agrega funcionalidad para la administración de la conexión a la Base de datos.

----
##JSON
> Se genera utileria para la manipulacion de objetos en Notacion JSON, obteniendo valores de un objeto JSON, convertir arreglos JSON a Listas genericas de Java y crear objetos JSON con atributos y arreglos.

*En el programa **javax.openAPI.pruebas.PruebaJSON**, se realizó una prueba sencilla para la ejecución de los metodos integrados*

----
##Archivos de Configuracion
> Se agrega la funcionalidad para leer archivos de configuracion, como tambien el acceso a cada uno de sus elementos.

*En el programa **javax.openAPI.pruebas.PruebaPropiedades**, se realizó una prueba sencilla para la ejecución de los metodos integrados*

----
##Servicios
> Se implementan metodos y funciones para la ejecucion de servicios a partir de una direccion URL, puede ser para las diferentes peticiones HTTP (GET, POST, PUT y DELETE), incusive para servicios que retornen objetos con notacion JSON.

*En el programa **javax.openAPI.pruebas.PruebaServicios**, se realizó una prueba sencilla para la ejecución de los metodos integrados*

####Nota:
**Para la compilación y generacion de archivo JAR, es necesario que se tenga instalado la herramienta ANT Apache**
