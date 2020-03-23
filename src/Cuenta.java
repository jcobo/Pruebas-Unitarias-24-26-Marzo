
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
        if(esValida){ 
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
    
    public void retirada(double cantidad){
    	boolean esValida = this.validarCantidadRetirada(cantidad);
        if(esValida){ 
            this.saldo -= cantidad;
        }
    }
    
    private boolean validarCantidadRetirada(double cantidad){
    	double roundOff = Math.round(cantidad * 100.0) / 100.0;
        if(roundOff != cantidad) {
            return false;
        }
        
        if(cantidad < 0) {
            return false;
        }
        
        if(cantidad > this.saldo) {
            return false;
        }
        
        if(cantidad > 6000.00){
            return false;
        } 
        
        return true;
    }
    
    public void transferencia(Cuenta cuenta_destino, double cantidad){
    	boolean esValida = this.validarCantidadTransferencia(cantidad);
        if(esValida){ 
            this.retirada(cantidad);
            cuenta_destino.ingreso(cantidad);
        }
   
    }
    
    private boolean validarCantidadTransferencia(double cantidad){        
        if(cantidad < 0) {
            return false;
        }
        
        if(cantidad > this.saldo) {
            return false;
        }
        
        if(cantidad > 3000){
            return false;
        } 
        
        return true;
    }
	
}
