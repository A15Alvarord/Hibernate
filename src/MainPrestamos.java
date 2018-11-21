import biblioteca2.*;
import java.util.Scanner;
import java.util.Calendar;
import java.util.List;
public class MainPrestamos {

	public static void main(String[] args) throws Exception {
		OperacionsPrestamos op = new OperacionsPrestamos();
		Scanner sc = new Scanner(System.in);
		while(true) {
			op.abrirConexion();
			System.out.println("Escoge lo que quieres realizar: ");
			System.out.println("1. Mostrar los prestamos de libros non devoltos.");
			System.out.println("2. Realizar prestamo de Libro.");
			System.out.println("3. Realizar devolución.");
			System.out.println("4. Salir do programa.");
			switch(sc.nextInt()) {
				case 1:
					
					List<Prestamo> lisp= op.listadoPrestamos(); 
					if(lisp.size()==0) {
						System.out.println("Cero prestamos de libro non devoltos. ");
					}else {
						for(Prestamo p: lisp)
							System.out.println(p);
					}
					op.cerrarConexion();
					break;
				case 2:
					System.out.println("ID del libro a prestar:");
					Libro lib = new Libro();
					lib.setIdLibro(sc.nextInt());
					System.out.println("ID del Usuario:");
					Usuario us = new Usuario();
					us.setIdUsuario(sc.nextInt());
					Prestamo pres = new Prestamo(lib,us,Calendar.getInstance().getTime(), false);
					if(op.engadirPrestamo(pres)==1) 
						System.out.println("\nPrestamo engadido");
					else
						System.out.println("Prestamo non engadido");
					op.cerrarConexion();
					break;
				case 3:
					op.abrirConexion();
					System.out.println("ID do libro a devolver:");
					if(op.realizarDevolucion(sc.nextInt())>=1)
						System.out.println("Libro devolto");
					else
						System.out.println("Libro non devolto porque estaba devolto ou non existe ID de libro");
					op.cerrarConexion();
					break;
				default:
					System.exit(0);
			}
		}


	}

}
