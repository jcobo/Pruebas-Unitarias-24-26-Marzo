Seguimos.

Haremos los 3 tests que quedan rápidamente. No aportan nada didáctico nuevo.

- **Si ingreso 100.457 en una cuenta vacía, el saldo es de 0**
- **Si ingreso 6000.00 en una cuenta vacía, el saldo es de 6000.00**
- **Si ingreso 6000.01 en una cuenta vacía, el saldo es de 0**

Tests

```java
    @Test
    public void testIngresoCantidadMasDe2DecimalesNoEsValido(){
        Cuenta c = new Cuenta();
        c.ingreso(100.457);
        assertEquals(0, c.getSaldo());
    }
    
	@Test
    public void testIngresoMaximoEsDe6000(){
        Cuenta c = new Cuenta();
        c.ingreso(6000);
        assertEquals(6000, c.getSaldo());
    }
    
	@Test
    public void testIngresoMasDe6000NoEsValido(){
        Cuenta c = new Cuenta();
        c.ingreso(6000.01);
        assertEquals(0, c.getSaldo());
    }
```


Código final


``` [php]
public class Cuenta {
	private double saldo;

	public Cuenta() {
		this.saldo = 0.0;
	}
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public void ingreso(double cantidad){
        double roundOff = Math.round(cantidad * 100.0) / 100.0;
        if(roundOff != cantidad) {
            this.saldo = 0;
        }
        
        if(cantidad < 0) {
            this.saldo = 0;
        }
        
        if(cantidad > 6000.00){
            this.saldo = 0;
        } 
        if(esValida){ 
            this.saldo += cantidad;
        }
    }
}
```

Paso 2, los tests pasan.

Paso 3. Refactorizar. La función ingreso() empieza a tener más líneas de control que 
de lo que realmente hace. Me pide refactorizarla. Voy a crear un método privado de validación

``` [php]
public class Cuenta {
	private double saldo;

	public Cuenta() {
		this.saldo = 0.0;
	}
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public void ingreso(double cantidad){
        boolean esValida = this.validarCantidadIngresada(cantidad);
        if(!esValida){ 
        	   this.saldo = 0;
        } else {
        	   this.saldo += cantidad;
        }
    }
    
    private boolean validarCantidadIngresada(double cantidad){
    	    double roundOff = Math.round(cantidad * 100.0) / 100.0;
        if(roundOff != cantidad) {
            return false;
        }
        
        if(cantidad < 0) {
            return false;
        }
        
        if(cantidad > 6000.00){
            return false;
        } 
        
        return true;
    }
```

Vuelvo a ejecutar los tests. ¡¡¡Y pasan!!! Mi código hace exactamente lo mismo que antes.
TDD da una seguridad muy grande a la hora de afrontar cambios o refactorizaciones.