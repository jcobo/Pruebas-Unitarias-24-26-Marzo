Anatomía de un test unitario
============================

Un tests unitario se compone de tres partes que deben quedar bien diferenciadas y se conocen como AAA:

- Arrange (Preparar)
- Act (Ejecutar)
- Assert (Comprobar)

Arrange
-------

En esta parte se prepara todo lo necesario para la ejecución del test.

Act
---

En esta parte se realiza la acción que estamos testeando. Normalmente será llamar a la función que estamos testeando pasándole como argumentos los datos conseguidos en la fase de preparación.

Assert
------

En esta parte comprobamos si la acción se ha ejecutado como era de esperar. Normalmente será comprobar que la función nos ha devuelto los datos que esperábamos o bien que la función ha hecho internamente llámadas a otros métodos.

Ejemplo
-------

```java
public void GetMinimum_UnsortedIntegerArray_ReturnsSmallestValue()
{
  integer[] unsortedArray = new integer[] {7,4,9,2,5}; // Arrange

  integer minimum = Statistics.GetMinimum(unsortedArray); // Act

  AssertEqual(2, minimum); // Assert
}
```
