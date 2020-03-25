Aprovecho la ocasión para introducir el concepto del patrón AAA en los tests.

NO es un patrón de TDD. Es un patrón de construcción de tests, independiente de la 
técnica que se esté utilizando en el desarrollo del software.


Según este patrón, un buen debe tener 3 partes diferenciadas:

- Arrange
- Act
- Assert

Arrange (set up). Antes de empezar nuestro último test, necesitábamos la existencia 
previa de una cuenta con saldo 100. La misión de este test no es testear si se crea 
correctamente una cuenta con saldo 100. La misión es otra. Pero necesita una cuenta 
con saldo 100 para testear lo que tiene que testear. Por lo tanto nuestra primera parte 
del test estará dedicada a conseguir dicha cuenta con saldo 100.

Act. En esta parte se ejecuta la acción (o acciones) que se desea(n) poner a prueba. En nuestro caso
ingresar 3000 en la cuenta previamente preparada con saldo 100.

Assert. En esta parte, se realiza la comprobación (o comprobaciones) pertinentes para verificar
que la parte Act funciona como debe. 



```java
	@Test
    public void testAlIngresar3000EnCuentaCon100ElSaldoEs3100()
    {
    		//Arrange (Set up)
        Cuenta c = new Cuenta();
        c.ingreso(100);
        
        //Act
        c.ingreso(3000);
        
        //Assert
        assertEquals(3100, c.getSaldo());
    }
```

