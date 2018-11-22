import biblioteca2.*;
import org.hibernate.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		
		OperacionsUsuarios op = new OperacionsUsuarios();
		
		Scanner sc = new Scanner(System.in);
		//Listamos Usuarios
		
		//Añadimos el usuario
				
		Usuario user = new Usuario("44444444L","novo","novo@gmail.com",new HashSet());
		//op.engadirUsuario(user);
		op.abrirConexion();
		op.listaUsuarios();
		op.cerrarConexion();
		
		/*
		//Modificamos usuario
		System.out.println("Id del usuario que quieres modificar: ");
		int idmod = sc.nextInt();
		op.abrirConexion();
		op.modificarUsuario(idmod);
		op.cerrarConexion();
		op.abrirConexion();
		for(Usuario us : op.listaUsuarios()) {
			System.out.println(us);
		}
		op.cerrarConexion();
		
		//Consultar un usuario
		System.out.println("Id del usuario a consultar: ");
		int idcon = sc.nextInt();
		op.abrirConexion();
		System.out.println(op.consultaUsuario(idcon));
		op.cerrarConexion();
		
		//Eliminar un usuario
		System.out.println("Id del usuario que quieres eliminar: ");
		int idEliminar = sc.nextInt();
		op.abrirConexion();
		op.borraUsuario(idEliminar);
		op.cerrarConexion();
		
		//Listado de usuarios finales
		op.abrirConexion();

		for(Usuario us : op.listaUsuarios()) {
			System.out.println(us);
		}
			
		op.cerrarConexion();
		*/

	}

}
