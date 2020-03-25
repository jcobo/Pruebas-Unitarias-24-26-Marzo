Seguimos

- Ingresos. 
    (...)
    - Los ingresos admiten un máximo de 2 decimales de precisión
        - **Si ingreso 100.45 en una cuenta vacía, el saldo es de 100.45**
        - **Si ingreso 100.457 en una cuenta vacía, el saldo es de 0**
    - La cantidad máxima que se puede ingresar es de 6000
        - **Si ingreso 6000.00 en una cuenta vacía, el saldo es de 6000.00**
        - **Si ingreso 6000.01 en una cuenta vacía, el saldo es de 0**


Tests

```java
	@Test
    public void testIngresoCantidad2Decimales(){
        Cuenta c = new Cuenta();
        c.ingreso(100.45);
        assertEquals(100.45, c.getSaldo());
    }
```

Este test nos obliga a cambiar los datos a tipo double. O quizás a sobrecargar el método *ingreso* para que acepte ambos tipos, pero "ley del mínimo esfuerzo": si cambiando a double, los tests pasan, pues listo.

```java
public class Cuenta {

    private double saldo;

	public Cuenta() {
		this.saldo = 0;
	}
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public void ingreso(double cantidad){
        this.saldo += cantidad;
    }
}
```