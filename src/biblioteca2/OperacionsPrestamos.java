package biblioteca2;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class OperacionsPrestamos {

	private SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
	private Session sesion;
	public void abrirConexion() throws Exception{
		sesion = sessionFactory.openSession();
	}
	public void cerrarConexion() throws Exception{
		sesion.close();
	}
	//Metodo comprobar si un libro está devolto ou non, devuelve true o false
	public boolean comprobarDevolto(int idPrestamo)throws Exception{
		boolean tipo = false;
		Prestamo busca;
		Query consulta = sesion.createQuery("from Prestamo where idPrestamo=?");
		consulta.setInteger(0, idPrestamo);
		busca = (Prestamo) consulta.uniqueResult();
		tipo = busca.getDevolto();
		return tipo;
	}
	//Metodo para comprobar si existe un libro en la bd
	public boolean existeLibro(int idLibro)throws Exception{
		boolean existe = false;
		Query consulta = sesion.createQuery("from Libro where idLibro=?");
		consulta.setInteger(0,idLibro);
		if(consulta.uniqueResult()!= null) {
			existe = true;
		}
		return existe;
	}
	
	
	//Metodo para añadir un prestamo
	public int engadirPrestamo(Prestamo nuevoPrestamo) throws Exception{
		int resultado = 0;
		//Comprobamos si existe ese libro
		if(existeLibro(nuevoPrestamo.getLibro().getIdLibro())) {
			Transaction tr = sesion.beginTransaction();
			Query consulta = sesion.createQuery("From Prestamo where idLibro=? order by data");
			consulta.setInteger(0, nuevoPrestamo.getLibro().getIdLibro());
			
			List<Prestamo> listaPrestamo = consulta.list();
			Prestamo p = listaPrestamo.get(listaPrestamo.size() -1);
			//Comprobamos si el libro está devuelto o no
			if(comprobarDevolto(p.getIdPrestamo())) {
				sesion.save(nuevoPrestamo);
				tr.commit();
				resultado = 1;
			}

		}
		return resultado;
	}
	
	//Metodo para realizar unha devolución
	public int realizarDevolucion(int idLibro) throws Exception{
		int resultado = 0;
		if(existeLibro(idLibro)) {
			Transaction tr = sesion.beginTransaction();
			Query consulta = sesion.createQuery("From Prestamo where idLibro=? order by data");
			consulta.setInteger(0, idLibro);
			
			List<Prestamo> listaPrestamo = consulta.list();
			Prestamo p = listaPrestamo.get(listaPrestamo.size() -1);
			//Comprobamos si el libro está devuelto o no
			if(comprobarDevolto(p.getIdPrestamo()) == false) {
				Query modificar = sesion.createQuery("update Prestamo set devolto=? where idPrestamo=?");
				modificar.setBoolean(0, true);
				modificar.setInteger(1, p.getIdPrestamo());
				resultado = modificar.executeUpdate();
				tr.commit();
			}
		}
		return resultado;
	}
	
	//Metodo que devolve o listado de Prestamos
	public List<Prestamo> listadoPrestamos() throws Exception{
		
		List<Prestamo> lista = new ArrayList<>();
		Query consulta = sesion.createQuery("from Prestamo where devolto = 0"); //prestamos non devoltos
		lista = consulta.list();
		return lista;
		
	}
	
}
