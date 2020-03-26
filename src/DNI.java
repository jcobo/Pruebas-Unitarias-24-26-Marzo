
public class DNI {
	
//	public static void main(String[] args) {
//		char letra = DNI.calcularLetra(23467345);
//		System.out.println(letra);
//	}
	
//	public char calcularLetra(int dni) {
//		 
//		char salida = ' ';
//		int resto = dni%23;
//		 
//		switch ( resto ) { 
//			case 0: salida = 'T'; break;
//			case 1: salida = 'R'; break; 
//			case 2: salida = 'W'; break; 
//			case 3: salida = 'A'; break; 
//			case 4: salida = 'G'; break; 
//			case 5: salida = 'M'; break; 
//			case 6: salida = 'Y'; break; 
//			case 7: salida = 'F'; break; 
//			case 8: salida = 'P'; break; 
//			case 9: salida = 'D'; break; 
//			case 10: salida = 'X'; break; 
//			case 11: salida = 'B'; break; 
//			case 12: salida = 'N'; break; 
//			case 13: salida = 'J'; break; 
//			case 14: salida = 'Z'; break; 
//			case 15: salida = 'S'; break; 
//			case 16: salida = 'Q'; break; 
//			case 17: salida = 'V'; break; 
//			case 18: salida = 'H'; break; 
//			case 19: salida = 'L'; break; 
//			case 20: salida = 'C'; break; 
//			case 21: salida = 'K'; break; 
//			case 22: salida = 'E'; break;
//		} 
//		return salida; 
//
//	} //fin del calcularLetra
//	
	
	public char calcularLetra(int dni){
        String caracteres="TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = dni%23;
        return caracteres.charAt(resto);
   }
}
