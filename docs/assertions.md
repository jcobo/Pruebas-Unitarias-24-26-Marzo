# Assertions

## Lista completa de Assertions 


### Comprobaciones básicas


- assertEquals

Comprueba que dos datos son iguales. (Excepto para arrays).

- assertNotEquals

Comprueba que dos datos no son iguales. (Excepto para arrays).

- assertArrayEquals

Comprueba que dos arrays son iguales

- assertIterableEquals

Comprueba que dos Iterables son "deeply equal".

- assertLinesMatch

Comprueba que dos listas de strings son iguales

- assertTrue

Comprueba que un valor es false

- assertFalse

Comprueba que un valor es false

- assertNull

Comprueba que un valor es null

- assertNotNull

Comprueba que un valor no es null

- assertSame

Comprueba que las dos variables contienen la misma referencia.

- assertNotSame

Comprueba que las dos variables no contienen la misma referencia.



### Comprobaciones sobre excepciones

- assertThrows

Comprueba que el ejecutable proporcionado lanza una excepción del tipo especificado

- assertDoesNotThrow (desde la versión 5.2)

Comprueba que el ejecutable proporcionado NO lanza ninguna excepción.

NOTA: Si el método bajo prueba lanza una excepción no capturada, el test se contará como fallido.

- assertAll

Testea que todos los ejectubales proporcionados NO lanzan excepciones


### Comprobaciones temporales

- assertTimeout

Comprueba que un método se ejecuta en menos tiempo del indicado. Se ejecuta en el mismo hilo que el propio test. No aborta el proceso si se excede del tiempo.

- assertTimeoutPreemptively

Comprueba que un método se ejecuta en menos tiempo del indicado. Se ejecuta en un hilo distinto. Aborta el proceso si excede del tiempo.


### Utilidades

- fail()

Hace fallar un test.




## Más sobre assertions

### Mensajes en caso de fallo

```java
@Test
void standardAssertions() {
    assertEquals(15, calculator.multiply(3, 5));
    assertEquals(15, calculator.multiply(3, 5),
            "Desde la versión 5 de JUnit, el mensaje se pone como último parámetro");
    assertEquals(15, calculator.multiply(3, 5), () -> "Assertion messages can be lazily evaluated -- "
            + "to avoid constructing complex messages unnecessarily.");
}
```

### Agrupación de assertions

```java
@Test
void groupedAssertions() {
    // In a grouped assertion all assertions are executed, and all
    // failures will be reported together.
    assertAll("person",
        () -> assertEquals("Jane", person.getFirstName()),
        () -> assertEquals("Doe", person.getLastName())
    );
}
```

### Excepciones

assertThrows devuelve la excepción lanzada:

```java
@Test
void exceptionTesting() {
    Exception exception = assertThrows(ArithmeticException.class, () ->
        calculator.divide(1, 0));
    assertEquals("/ by zero", exception.getMessage());
}
```

### Timeouts

```java
@Test
void timeoutNotExceeded() {
    // The following assertion succeeds.
    assertTimeout(ofMinutes(2), () -> {
        // Perform task that takes less than 2 minutes.
    });
}
```

```java
@Test
void timeoutNotExceededWithResult() {
    // The following assertion succeeds, and returns the supplied object.
    String actualResult = assertTimeout(ofMillis(10), calculator.multiply(3, 5));
    assertEquals(15, actualResult);
}
```




