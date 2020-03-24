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

**Test Class**: Una clase que contiene al menos un Test Method.

Las *Test Class* no pueden ser abstractas y solamente pueden tener un constructor.

**Test Method**: Cualquier método que tenga una de las siguientes anotaciones: @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, or @TestTemplate.

**Lifecycle Method**: Cualquier método con una de las siguientes anotaciones @BeforeAll, @AfterAll, @BeforeEach, or @AfterEach.

Los *test methods* y los *lifecycle methods* no pueden ser abstractos y no pueden devolver ningún valor con return.

Ni las *Test Class* ni los *Test Method* ni los *Lifecycle Method* pueden ser privados.

Ejemplo básico con los 3 elementos:

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

Ya hemos visto las siguientes anotaciones:

- @Test
- @BeforeAll 
- @AfterAll
- @BeforeEach 
- @AfterEach

Vamos a echar un vistazo a algunas más a lo largo de este tema. La lista completa está publicada en la guía oficial de JUnit.

https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations

También es posible crear anotaciones personalizadas.


### Deshabilitar Tests


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


Creación de anotaciones
-----------------------

La anotación @interface, permite crear anotaciones 

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

Temp Directory (experimental)
----------------------------

La anotación @TempDir sirve para declarar un path como directorio temporal para la escritura/lectura de ficheros en los tests.

```java
class SharedTempDirectoryDemo {

    @TempDir
    static Path sharedTempDir;

    @Test
    void writeItemsToFile() throws IOException {
        Path file = sharedTempDir.resolve("test.txt");

        new ListWriter(file).write("a", "b", "c");

        assertEquals(singletonList("a,b,c"), Files.readAllLines(file));
    }

    @Test
    void anotherTestThatUsesTheSameTempDir() {
        // use sharedTempDir
    }
}
```

Diferencias con JUnit v4
------------------------

- El mensaje de test fallido es el primer parámetro en JUnit 4

```java
assertEquals(expected, actual);
assertEquals("failure - strings are not equal", expected, actual);
```

- Aserciones de excepciones

El testeo de excepciones en JUnit 4 se realizaba a través de la anotación @Test

```java
@Test(expected = NullPointerException.class)
public void exceptionTesting() {
    String test = null;
    test.length();
}
```

En JUnit 5 existen assertions para ello:

```java
@Test
void exceptionTesting() {
    assertThrows(NullPointerException.class, () -> {
    		String test = null;
    		test.length();
    });
}
```

