En este caso no vemos nada que refactorizar ni en el código ni en los tests. 

Los tests:


```java
class CuentaTest {

	@Test
	public void testAlCrearCuentaElSaldoEsCero()
    {
        Cuenta c = new Cuenta();
        assertEquals(0, c.getSaldo());
    }
    
	@Test
    public void testAlIngresar100EnCuentaVaciaElSaldoEs100()
    {
        Cuenta c = new Cuenta();
        c.ingreso(100);
        assertEquals(100, c.getSaldo());
    }
```



El código:

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
        this.saldo = 100;
    }
}
```


Sigamos con el siguiente test.