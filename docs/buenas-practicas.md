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

- Mantén el número de asserts al mínimo en cada test. Un test unitario debe testear una única cosa. Tener más de un assert en un test es válido, pero si se pueden separar de forma lógica, es mejor hacerlo.

- Evita test sin ningún assert. A veces se usan para testear que no ocurre ninguna excepción. Pero normalmente lo lógico es testear que ocurran cosas, no que no ocurran.

- No pongan asserts para comprobar funcionalidades que ya estén testeadas en otros tests.

- Separa claramente los códigos de cada parte del test (arrange, act y assert). A veces basta con una línea de separación, otras veces necesitarán algo más informativo. A veces se pone en una sola línea el act y el assert. Normalmente resulta más legible hacerlo en líneas separadas.

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

En este caso, nos hubiera bastado un único test, que compruebe que nuestro método está efectivamente utilizando el operador '+' con los dos argumentos de entrada. El resto de métodos están testeando si el operador '+' funciona correctamete. Pero ese operador no es nuestro, y además está sobradamente testeado en las librerías de java.

Lo mismo aplica si un método utilizar una librería para escribir/leer de un fichero, enviar un correo, o acceder a la base de datos. En los tests unitarios se comprueba si nuestros métodos hacen las llamadas correspondientes a las librerías, con los argumentos correctos. No se testea si la escritura en el fichero ha ido bien o si el correo se ha llegado a enviar.

Otro ejemplo: Si queremos testear el siguiente trozo de código que usa Hibernate para hacer un insert:

```java
Session session = HibernateUtil.getSessionFactory().openSession();
session.beginTransaction();
        
EmployeeEntity emp = new EmployeeEntity();
emp.setEmail("demo-user@mail.com");
emp.setFirstName("demo");
emp.setLastName("user");
 
session.save(emp);
session.getTransaction().commit();
HibernateUtil.shutdown();
```

Lo que hay que testear no es si el insert se ha hecho o no en la base de datos. Eso es muy costoso de testear. Y no es unitario. Si acaso es un test de integración. Lo que hay que testear es que hemos llamado al método save() con el objeto correcto como argumento de entrada.

```java
stmt = con.prepareStatement("INSERT INTO libros VALUES (?,?,?,?,?)");
String sISBN = "84-9815-212-7";
String sTitulo = "Yo, Claudio";
String sDescripcion= "Supuesta autobiografía de Claudio...";
String sCategoria = "novela histórica";
int idAutor = 3;
   
stmt.setString(1,sISBN);
stmt.setInt(2,idAutor);
stmt.setString(3,sTitulo);
stmt.setString(4,sDescripcion);
stmt.setString(5,sCategoria);

stmt.executeUpdate();
```

O con consultas SQL "a mano". Habría que testear que hemos llamado a cada uno de los métodos prepareStatement, setString, setInt, executeUpdate... las veces necesarias y con los argumentos necesarios.






