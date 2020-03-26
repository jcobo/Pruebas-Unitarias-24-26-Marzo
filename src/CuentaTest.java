import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class CuentaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// System.out.println("BeforeAll");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// System.out.println("AfterAll");
	}

	@BeforeEach
	void setUp() throws Exception {
		// System.out.println("BeforeEach");
	}

	@AfterEach
	void tearDown() throws Exception {
		// System.out.println("AfterEach");
	}

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
    
	@Test
    public void testAlIngresar3000EnCuentaVaciaElSaldoEs3000()
    {
        Cuenta c = new Cuenta();
        c.ingreso(3000);
        assertEquals(3000, c.getSaldo());
    }
    
	@Test
    public void testAlIngresar3000EnCuentaCon100ElSaldoEs3100()
    {
        Cuenta c = new Cuenta();
        c.ingreso(100);
        c.ingreso(3000);
        assertEquals(3100, c.getSaldo());
    }
    
	@Test
    public void testNoSePuedeIngresarCantidadNegativa(){
        Cuenta c = new Cuenta();
        c.ingreso(-100);
        assertEquals(0, c.getSaldo());
    }
    
	@Test
    public void testIngresoCantidad2Decimales(){
        Cuenta c = new Cuenta();
        c.ingreso(100.45);
        assertEquals(100.45, c.getSaldo());
    }
    
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
    
    
	@Test
    public void testAlRetirar100EnCuentaCon500ElSaldoEs400()
    {
        Cuenta c = new Cuenta();
        c.ingreso(500);
        assertEquals(500, c.getSaldo());
        c.retirada(100);
        assertEquals(400, c.getSaldo());
    }
    
	@Test
    public void testAlRetirar200EnCuentaCon900ElSaldoEs700()
    {
        Cuenta c = new Cuenta();
        c.ingreso(900);
        c.retirada(200);
        assertEquals(700, c.getSaldo());
    }
    
	@Test
    public void testAlRetirar200EnCuentaCon1200ElSaldoEs1000YAlRetirarOtros150ElSaldoEs850()
    {
        Cuenta c = new Cuenta();
        c.ingreso(1200);
        c.retirada(200);
        assertEquals(1000, c.getSaldo());
        c.retirada(150);
        assertEquals(850, c.getSaldo());
    }
    
	@Test
    public void testNoSePuedeRetirarMasSaldoDelDisponible()
    {
        Cuenta c = new Cuenta();
        c.ingreso(200);

        c.retirada(500);
        assertEquals(200, c.getSaldo());
    }
    
	@Test
    public void testRetiradaCantidad2Decimales(){
        Cuenta c = new Cuenta();
        c.ingreso(1000);
        
        c.retirada(100.45);
        assertEquals(899.55, c.getSaldo());
    }
    
	@Test
    public void testNoSePuedeRetirarCantidadNegativa(){
        Cuenta c = new Cuenta();
        c.ingreso(500);
        
        c.retirada(-100);
        assertEquals(500, c.getSaldo());
    }
    
	@Test
    public void testRetiradaCantidadDe2DecimalesEsValida(){
        Cuenta c = new Cuenta();
        c.ingreso(500);
        
        c.retirada(100.45);
        assertEquals(399.55, c.getSaldo());
    }
    
	@Test
    public void testRetiradaCantidadMasDe2DecimalesNoEsValida(){
        Cuenta c = new Cuenta();
        c.ingreso(500);
        
        c.retirada(100.457);
        assertEquals(500, c.getSaldo());
    }
    
	@Test
    public void testRetiradaMaximoEsDe6000(){
        Cuenta c = new Cuenta();
        c.ingreso(3000);
        c.ingreso(4000);
        
        c.retirada(6000);
        assertEquals(1000, c.getSaldo());
    }
    
	@Test
    public void testRetiradaMasDe6000NoEsValido(){
        Cuenta c = new Cuenta();
        c.ingreso(3000);
        c.ingreso(4000);
        
        c.retirada(6000.01);
        assertEquals(7000, c.getSaldo());
    }
    

	@Test
    public void testTransferencia()
    {
        //Set up (Arrange)
        Cuenta cuenta1 = new Cuenta();
        cuenta1.ingreso(500);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.ingreso(50);
        
        //Act
        cuenta1.transferencia(cuenta2,100);
        
        //Assert
        assertEquals(400, cuenta1.getSaldo());
        assertEquals(150, cuenta2.getSaldo());
    }
    
	@Test
    public void testTransferenciaNegativaNoEsValida()
    {
        //Set up
        Cuenta cuenta1 = new Cuenta();
        cuenta1.ingreso(500);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.ingreso(50);
        
        //Act
        cuenta1.transferencia(cuenta2,-100);
        
        //Assert
        assertEquals(500, cuenta1.getSaldo());
        assertEquals(50, cuenta2.getSaldo());
    }
    
	@Test
    public void testTransferenciaLimiteCantidad3000()
    {
        //Set up
        Cuenta cuenta1 = new Cuenta();
        cuenta1.ingreso(3500);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.ingreso(50);
        
        Cuenta cuenta3 = new Cuenta();
        cuenta3.ingreso(3500);
        
        Cuenta cuenta4 = new Cuenta();
        cuenta4.ingreso(50);
        
        //Act
        //Esta transferencia es válida
        cuenta1.transferencia(cuenta2,3000);
        //Esta transferencia no es válida porque supera los 3000
        cuenta3.transferencia(cuenta4,3000.01);
        
        //Assert
        assertEquals(500, cuenta1.getSaldo());
        assertEquals(3050, cuenta2.getSaldo());
        assertEquals(3500, cuenta3.getSaldo());
        assertEquals(50, cuenta4.getSaldo());
    }
    
    
    
	@Test
    public void testNoSePuedeTransferirMasSaldoDelDisponible(){
        Cuenta cuenta1 = new Cuenta();
        cuenta1.ingreso(2350);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.ingreso(300);
        
        cuenta1.transferencia(cuenta2,2500);

        assertEquals(2350, cuenta1.getSaldo());
        assertEquals(300, cuenta2.getSaldo());
    }
}
