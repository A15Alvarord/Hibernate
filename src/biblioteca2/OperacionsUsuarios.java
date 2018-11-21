package biblioteca2;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class OperacionsUsuarios {
	
	private SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
	private Session sesion;
	public void abrirConexion() throws Exception{
		sesion = sessionFactory.openSession();
	}
	public void cerrarConexion() throws Exception{
		sesion.close();
	}
	
	public Usuario consultaUsuario(int id) throws Exception {
		Usuario nuevoUser;
		Query consulta = sesion.createQuery("from Usuario where idUsuario=?");
		consulta.setInteger(0, id);
		nuevoUser = (Usuario)consulta.uniqueResult();
		return nuevoUser;
	}
	public void engadirUsuario(Usuario nuevoUser) throws Exception{
		Transaction tr = sesion.beginTransaction();
		sesion.save(nuevoUser);
		tr.commit();
	}
	public List<Usuario> listaUsuarios() throws Exception{
		List<Usuario> lista = new ArrayList<>();
		Query consulta = sesion.createQuery("from Usuario");
		lista = consulta.list();
		return lista;
	}
	public void modificarUsuario(int id)throws Exception{
		Transaction tr = sesion.beginTransaction();
		Query modificar = sesion.createQuery("update Usuario set dni = ?, nome = ?, correoe = ? where idUsuario = ?");
		modificar.setString(0, "3");
		modificar.setString(1, "proba");
		modificar.setString(2, "correo");
		modificar.setInteger(3, id);
		modificar.executeUpdate();
		tr.commit();
		
	}
	public int borraUsuario(int id) throws Exception{
		Transaction tr = sesion.beginTransaction();
		Query borrar = sesion.createQuery("delete from Usuario where idUsuario=?");
		borrar.setInteger(0,  id);
		Usuario comprobarUsuario = consultaUsuario(id);
		int resultado = 0;
		//Comprobamos si tiene prestamos ese Usuario
		if(comprobarUsuario.getPrestamos().isEmpty()) {
			resultado = borrar.executeUpdate();
			tr.commit();
		}
		return resultado;
		
	}
	
	
	
}
