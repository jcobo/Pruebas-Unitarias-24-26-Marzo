El siguiente ejemplo de la lista ATDD es:

**Al ingresar 3000 en cuenta vacía el saldo es 3000**



Convertimos el ejemplo en un test:

```java
	@Test
    public void testAlIngresar3000EnCuentaVaciaElSaldoEs3000()
    {
        Cuenta c = new Cuenta();
        c.ingreso(3000);
        assertEquals(3000, c.getSaldo());
    }
```

A primera vista, es lo mismo que el test anterior, pero con otra cantidad, parece 
que no aporta funcionalidad. Pero si ejecutamos el test, falla, así que sí, algo
de funcionalidad nueva debe aportar.


Mirando este test y el anterior vemos que el método ingreso NO devuelve siempre 
lo mismo. Con este ejemplo se ha **eliminado ambigüedad** al método ingreso (aunque todavía 
no se ha eliminado el 100% de ambigüedad, como veremos).

Volviendo al tema, tenemos un método getSaldo() que devuelve 100 cuando al método ingreso()
le pasamos 100 como parámetro y devuelve 3000 cuando al método ingreso le pasamos 3000
como parámetro. Y devuelve 0 si NO se llama al método ingreso(). Pues esta clarísimo.

```java
public class Cuenta {

    private int saldo;

	public Cuenta() {
		this.saldo = 0;
	}
    
    public int getSaldo() {
        return this.saldo;
    }
    
    public void ingreso(int cantidad){
        this.saldo = cantidad;
    }
}
```

Ejecutamos los tests, y pasan. Somos conscientes de que está mucho mejor que hace un test,
pero que todavía no es la solución final, que ingreso tiene que sumar cantidad a lo que 
hubiera, pero hasta ahora no nos hemos encontrado ningún tests que nos lo exija. 

Si hay más de 1 solución funcionalmente distinta que hace pasar los tests, es síntoma de 
que necesitamos más tests. Y los hay, por supuesto, así que
lo dejamos así, y pasamos a la fase 3 del algoritmo: refactorizar.

No observamos nada que refactorizar, pasamos al siguiente test.



