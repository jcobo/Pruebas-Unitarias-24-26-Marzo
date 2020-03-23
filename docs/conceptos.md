Conceptos
=========

Tests de caja negra
-------------------

Son aquellos tests que programamos sin mirar el código fuente del elemento que vamos a testear. Esto puede ser o bien porque no tenemos acceso a dicho código o bien porque no queremos mirarlo.

Para programar este tipo de tests nos tienen que decir cuál es el comportamiento esperado del elemento que hay que testear.

Tests de caja blanca
--------------------

Son aquellos tests que programamos mirando el código fuente. Con este tipo de tests nos ayudamos del propio código fuente para asegurarnos que estamos cubriendo todos los caminos posibles que puede seguir el código: ramificaciones por sentencias if-else, límites/fronteras en los bucles, cóndiciones lógicas...

Query function
--------------

Son funciones cuyo objetivo es conseguir/calcular/procesar información y devolvérnosla en el return.


Command function
----------------

Son funciones cuyo objetivo es ejecutar alguna instrucción que altere el entorno (escribir/modificar en base de datos, enviar un correo, escribir en un fichero, cambiar el estado de un objeto en memoria...


Tests de estado
---------------

Testear el estado implica comprobar que una función nos devuelve el resultado esperado según unas condiciones de partida o argumentos de entrada.

Este tipo de testeo se puede realizar tanto con la técnica de caja negra como con la técnica de caja blanca.

Tests de implementación
-----------------------

En este tipo de tests se pretende verficar que el elemento bajo test invoca (o no invoca) algún método concreto con unos argumentos concretos.


Propiedades de un buen test unitario
------------------------------------

Un buen test unitario debe tener las siguientes propiedades:


- Fáciles de escribir

En los tests de una aplicación hay tanto o más código como en la propia aplicación. Centenares o miles de tests para cubrir los diferentes casos y aspectos del comportamiento de la aplicación. Debemos ser capaces de escribir tests unitarios sin que suponga un gran esfuerzo.

- Legibles

La intención de un test debe ser clara. Un buen test unitario cuenta una historia sobre algún aspecto/comportamiento de la aplicación, por lo que debe ser fácil de entender cuál es ese aspecto que se está testeando. Y cuando el test falle, debe ser fácil localizar el problema para arreglar el error ¡incluso sin necesidad de ponerse a hacer debug!


- Fiables

Los tests unitarios deben fallar solamente si hay un bug en el sistema que se está testeando. Esto parece obvio, pero un mal diseño de nuestros tests unitarios puede provocar que algunos tests fallen dependiendo del orden en el que se ejecutan, o dependiendo si algún sistema externo (por ejemplo, la base de datos) está mal configurado, o fuera de servicio...

Los tests unitarios deben ser completamente independientes de factores externos como el entorno en el que se ejecutan o el orden de ejecución

 
- Rápidos

Los tests se programan con la intención de lanzarlos continuamente, una y otra vez. Si los tests son lentos, es muy probable que dejemos de ejecutarlos con frecuencia.

- Que sean realmente unitarios.


