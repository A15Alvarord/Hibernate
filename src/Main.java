import biblioteca2.*;
import org.hibernate.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		
		OperacionsUsuarios op = new OperacionsUsuarios();
		op.abrirConexion();
		Scanner sc = new Scanner(System.in);
		//Listamos Usuarios
		for(Usuario us : op.listaUsuarios()) {
			System.out.println(us);
		}
		//Añadimos el usuario
				
		//Usuario user = new Usuario("44444444L","Jezus","jesus@gmail.com",new HashSet());
		//op.engadirUsuario(user);
		
		//Modificamos usuario
		System.out.println("Id del usuario que quieres modificar: ");
		int idmod = sc.nextInt();
		op.modificarUsuario(idmod);
		
		
		//Consultar un usuario
		System.out.println("Id del usuario a consultar: ");
		int idcon = sc.nextInt();
		System.out.println(op.consultaUsuario(idcon));
		
		
		//Eliminar un usuario
		System.out.println("Id del usuario que quieres eliminar: ");
		int idEliminar = sc.nextInt();
		op.borraUsuario(idEliminar);
		
		
		//Listado de usuarios finales
		for(Usuario us : op.listaUsuarios()) {
			System.out.println(us);
		}
			
		op.cerrarConexion();

	}

}
