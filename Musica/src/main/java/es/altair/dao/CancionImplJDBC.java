package es.altair.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.altair.bean.Cancion;

public class CancionImplJDBC implements CancionDAO {

	public boolean insertar(Cancion c) {
		int num_filas = 0; 
		
		ConexionDAO.abrirConexion();
		
		String query = "INSERT INTO CANCIONES VALUES(null,?,?,?)";
		
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setString(1, c.getNombre());
			sentencia.setFloat(2, c.getDuracion());
			sentencia.setInt(3, c.getArtistaId());
			
			num_filas = sentencia.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionDAO.cerrarConexion();
		if(num_filas == 0)
			return false;
		else
			return true; 
	}

	public List<Cancion> mostrarCancionPorArtista(int idArtista) {
		List<Cancion> cancionPorArtista = new ArrayList<Cancion>();
		
		ConexionDAO.abrirConexion();
		
		String query = "SELECT * FROM CANCIONES WHERE idArtista = ?";
		
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setInt(1, idArtista);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				Cancion c = new Cancion(resultado.getInt("id"),
						resultado.getString("nombre"),
						resultado.getFloat("duracion"));
				cancionPorArtista.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexionDAO.cerrarConexion();
		
		return cancionPorArtista;
	}

	public Cancion coger(String Pais) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean aumentarDuracion(Cancion c) {
		int num_filas = 0; 
		ConexionDAO.abrirConexion();
		
		
		
		ConexionDAO.cerrarConexion();
		if(num_filas == 0)
			return false;
		else
			return true;
	}

	public boolean borrar(int id) {
		int num_filas = 0; 
		ConexionDAO.abrirConexion();
		
		String query = "DELETE FROM CANCIONES WHERE idArtista = ?";
		
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setInt(1, id);
			num_filas = sentencia.executeUpdate();
			
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionDAO.cerrarConexion();
		if(num_filas == 0)
			return false;
		else
			return true;
	}

	public boolean actualizarDuracion(List<Integer> idArtista) {
		int num_filas = 0; 
		ConexionDAO.abrirConexion();
		
		for (int i = 0; i < idArtista.size(); i++) {
			String query = "UPDATE canciones SET duracion = duracion +1 WHERE idArtista = ?";
			
			PreparedStatement sentencia;
			try {
				sentencia = ConexionDAO.getConexion().prepareStatement(query);
				sentencia.setInt(1, idArtista.get(i));
				num_filas = sentencia.executeUpdate(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ConexionDAO.cerrarConexion();
		if(num_filas == 0)
			return false;
		else
			return true;
	}

}
