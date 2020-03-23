JUnit
=====

Para escribir tests en Junit necesitamos al menos lo siguiente:

- Importar las Assertions necesarias (org.junit.jupiter.api.Assertions.*)
- Importar el decorador @Test (org.junit.jupiter.api.Test)
- Una **Test Class**
- Un **Test Method**

Ejemplo:

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HelloWorldTest {

	@Test
	void testSumPostive() {
		HelloWorld myclass = new HelloWorld();
		int output = myclass.sum(7, 15);
		assertEquals(22, output, "Sum positive values as expected");
	}

}
```

Test Class y Test Methods
-------------------------

Test Class: any top-level class, static member class, or @Nested class that contains at least one test method.

Test classes must not be abstract and must have a single constructor.

Test Method: any instance method that is directly annotated or meta-annotated with @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, or @TestTemplate.

Lifecycle Method: any method that is directly annotated or meta-annotated with @BeforeAll, @AfterAll, @BeforeEach, or @AfterEach.

Test methods and lifecycle methods may be declared locally within the current test class, inherited from superclasses, or inherited from interfaces (see Test Interfaces and Default Methods). In addition, test methods and lifecycle methods must not be abstract and must not return a value.

Test classes, test methods, and lifecycle methods are not required to be public, but they must not be private.
The following test class demonstrates the use of @Test methods and all supported lifecycle methods. For further information on runtime semantics, see Test Execution Order and Wrapping Behavior of Callbacks.

A standard test class
```java
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StandardTests {

    @BeforeAll
    static void initAll() {
    		// Se ejecuta una única vez
    }

    @BeforeEach
    void init() {
    		// Se ejecuta antes de CADA test
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @AfterEach
    void tearDown() {
    		// Se ejecuta después de CADA test
    }

    @AfterAll
    static void tearDownAll() {
    		// Se ejecuta una única vez
    }

}
```


Anotaciones
-----------

https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations


Deshabilitar Tests
------------------

Se puede deshabilitar un test concreto con el decorador @Disabled:


```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DisabledTestsDemo {

    @Disabled("Disabled until bug #42 has been resolved")
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }

}
```

Y también se puede deshabilitar una clase entera

```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until bug #99 has been fixed")
class DisabledClassDemo {

    @Test
    void testWillBeSkipped() {
    }

}
```

Tests asociados a Sistemas Operativos
-------------------------------------

Los decoradores @EnabledOnOs y @DisabledOnOs nos permiten ejecutar tests únicamente en sistemas operativos concretos

```java
@Test
@EnabledOnOs(MAC)
void onlyOnMacOs() {
    // ...
}

@Test
@EnabledOnOs({ LINUX, MAC })
void onLinuxOrMac() {
    // ...
}

@Test
@DisabledOnOs(WINDOWS)
void notOnWindows() {
    // ...
}
```

Java Runtime Environment Conditions
-----------------------------------

@EnabledOnJre and @DisabledOnJre y @EnabledForJreRange and @DisabledForJreRange


```java
@Test
@EnabledOnJre(JAVA_8)
void onlyOnJava8() {
    // ...
}

@Test
@EnabledOnJre({ JAVA_9, JAVA_10 })
void onJava9Or10() {
    // ...
}

@Test
@EnabledForJreRange(min = JAVA_9, max = JAVA_11)
void fromJava9to11() {
    // ...
}

@Test
@EnabledForJreRange(min = JAVA_9)
void fromJava9toCurrentJavaFeatureNumber() {
    // ...
}

@Test
@EnabledForJreRange(max = JAVA_11)
void fromJava8To11() {
    // ...
}

@Test
@DisabledOnJre(JAVA_9)
void notOnJava9() {
    // ...
}

@Test
@DisabledForJreRange(min = JAVA_9, max = JAVA_11)
void notFromJava9to11() {
    // ...
}

@Test
@DisabledForJreRange(min = JAVA_9)
void notFromJava9toCurrentJavaFeatureNumber() {
    // ...
}

@Test
@DisabledForJreRange(max = JAVA_11)
void notFromJava8to11() {
    // ...
}
```

System Property Conditions
--------------------------

@EnabledIfSystemProperty and @DisabledIfSystemProperty

```java
@Test
@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
void onlyOn64BitArchitectures() {
    // ...
}

@Test
@DisabledIfSystemProperty(named = "ci-server", matches = "true")
void notOnCiServer() {
    // ...
}
```

Environment Variable Conditions
-------------------------------

@EnabledIfEnvironmentVariable and @DisabledIfEnvironmentVariable

```java
@Test
@EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
void onlyOnStagingServer() {
    // ...
}

@Test
@DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
void notOnDeveloperWorkstation() {
    // ...
}
```

Parametrización de tests (experimental)
---------------------------------------

Se utiliza la etiqueta @ParameterizedTest junto con @ValueSource en vez de @Test

```java
@ParameterizedTest
@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
void palindromes(String candidate) {
    assertTrue(StringUtils.isPalindrome(candidate));
}
```

@ValueSource soporta arrays de los siguientes tipos de datos:

- short

- byte

- int

- long

- float

- double

- char

- boolean

- java.lang.String

- java.lang.Class


Hay otros sources, que se pueden consultar en la documentación, pero hay uno merece una mención especial: @CsvSource

@CsvSource, permite pasar varios argumentos en cada test, siguiendo notación csv.

```java
@CsvSource({
    "apple,         1",
    "banana,        2",
    "'lemon, lime', 5"
})
void testWithCsvSource(String fruit, int rank) {
    assertNotNull(fruit);
    assertNotEquals(0, rank);
}
```

Se puede aprovechar @CsvSource para pasar al método entradas y salidas del método que queremos testear

```java
@CsvSource({
    "apple,         1",
    "banana,        2",
    "'lemon, lime', 5"
})
void testWithCsvSource(String fruit, int expectedRank) {
    int actualRank = FruitsRank.getRank(fruit);
    assertEquals(expectedRank, actualRank);
}
```

En estos casos conviene SIEMPRE escribir el mensaje de fallo para poder discriminar cuál de los "juegos de datos" es el que ha fallado.

Ejercicio: Reescribir el test de calcularLetraDNI con @CsvSource.

https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests

Timeouts
--------

```java
@BeforeEach
@Timeout(5)
void setUp() {
    // fails if execution time exceeds 5 seconds
}

@Test
@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
void failsIfExecutionTimeExceeds100Milliseconds() {
    // fails if execution time exceeds 100 milliseconds
}
```

https://junit.org/junit5/docs/current/user-guide/#writing-tests-declarative-timeouts


Creación de decoradores
-----------------------

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@EnabledOnOs(MAC)
@interface TestOnMac {
}
```


```java
@TestOnMac
void testOnMac() {
    // ...
}
```

Ejecución en paralelo
---------------------

Desde la versión 5.3 de JUnit es posible indicar la ejecución en paralelo de los tests.

Configuration para ejecutar TODOS los tests en paralelo.

```
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = concurrent
```

Configuration para ejecutar las clases en paralelo, pero los métodos secuencialmente.

```
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = same_thread
junit.jupiter.execution.parallel.mode.classes.default = concurrent
```

Configuración para ejecutar las clases secuencialmente pero los métodos en paralelo.

```
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = concurrent
junit.jupiter.execution.parallel.mode.classes.default = same_thread
```

Gráfico comparativo al final de esta sección: https://junit.org/junit5/docs/current/user-guide/#writing-tests-parallel-execution

TempDirectory (experimental)
----------------------------



Diferencias con JUnit v4
------------------------

- El mensaje de test fallido es el primer parámetro en JUnit 4

```java
assertEquals(expected, actual);
assertEquals("failure - strings are not equal", expected, actual);
```

- Aserciones de excepciones


