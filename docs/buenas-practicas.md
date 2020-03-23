Buenas prácticas
================

Sobre el código fuente
----------------------

- Principios solid
  - Single Responsability Principle
  - Open-Closed Principle
  - Liskov Substitution Principle
  - Interface Segregation Principle
  - Dependecy Inversion Principle
  
El primero y el último de los principios SOLID son esenciales para generar código que sea fácil de testear.

- Query & Command functions

Hay que intentar evitar por todos los medios que nuestras funciones sean al mismo tiempo query y command, ya que aumentarán la complejidad de los tests.

Además, si una función es a la vez de tipo query y de tipo command, está incumpliendo el principio de Single Responsability.

- Hacer return de más de un tipo de dato

Una función que devuelva distintas cosas dependiendo de una condición interna complica el testeo. Por ejemplo, que devuelva un array de objetos si todo va bien, pero devuela un string con un mensaje de error si hay algún problema.

El return debe ser homegéneo para todos los casos. Si se producen errores, se deben tratar con excepciones.
 

Sobre los tests
---------------

- Los nombres de los tests deben ser lo más descriptivos posible

- Keep the number of assertions per unit test to a minimum. A single unit test is testing one thing. Multiple assertions in a single test are fine, but if it’s logical to split the assertions into separate tests, then it’s best to do so.

- Avoid assertion-less tests. These are tests that don’t contain any assertions (or verifications in the case of implementation tests), and are used to test that something works without throwing an exception. I like my unit tests to always check that something worked.

- Don’t repeat assertions that have been covered in existing tests. If a unit test asserts that a result is not null, or that a collection has exactly one item, then subsequent unit tests don’t need to repeat such assertions before asserting additional state.

- Assertions should be situated in the unit tests proper, rather than pulled out into helper methods. If checking the state is a bit complicated and common across many tests, it’s good to write a helper method to check the state. It’s easy to then put the assertions within that helper method, though I find unit tests more readable if they contain the assertions which could check a boolean returned by the helper method.

- The code for arrange, act and assert should be on their own lines, ideally with a new line between each. If you’re asserting that a method returns true, it can be tempting to perform that method call right inside the assert statement. I find unit tests clearer when these are kept separate.

- Testea únicamente el elemento bajo prueba

```java
class Adder {
    public int add(int a, int b) {
        return a + b;
    }
}
```

```java
import static org.junit.Assert.*;

import org.junit.Test;

public class TestAdder {

    @Test
    public void testSumPositiveNumbersOneAndOne() {
        Adder adder = new AdderImpl();
        assert(adder.add(1, 1) == 2);
    }

    // can it add the positive numbers 1 and 2?
    @Test
    public void testSumPositiveNumbersOneAndTwo() {
        Adder adder = new AdderImpl();
        assert(adder.add(1, 2) == 3);
    }

    // can it add the positive numbers 2 and 2?
    @Test
    public void testSumPositiveNumbersTwoAndTwo() {
        Adder adder = new AdderImpl();
        assert(adder.add(2, 2) == 4);
    }

    // is zero neutral?
    @Test
    public void testSumZeroNeutral() {
        Adder adder = new AdderImpl();
        assert(adder.add(0, 0) == 0);
    }

    // can it add the negative numbers -1 and -2?
    @Test
    public void testSumNegativeNumbers() {
        Adder adder = new AdderImpl();
        assert(adder.add(-1, -2) == -3);
    }

    // can it add a positive and a negative?
    @Test
    public void testSumPositiveAndNegative() {
        Adder adder = new AdderImpl();
        assert(adder.add(-1, 1) == 0);
    }

    // how about larger numbers?
    @Test
    public void testSumLargeNumbers() {
        Adder adder = new AdderImpl();
        assert(adder.add(1234, 988) == 2222);
    }

}
```

En este caso, nos hubiera bastado un único test, que compruebe que nuestro método está efectivamente utilizando el operador '+'. El resto de métodos están testeando si el operador '+' funciona correctamete. Pero ese operador no es nuestro, y además está sobradamente testeado en las librerías de java.


