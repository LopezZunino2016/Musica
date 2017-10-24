package es.altair.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.altair.bean.Artista;

public class ArtistaDAOImplJDBC implements ArtistaDAO {

	public List<Artista> listarArtista() {
		List<Artista> artista = new ArrayList<Artista>();
		
		ConexionDAO.abrirConexion();
		
		String query ="SELECT * FROM ARTISTAS";
		try {
			Statement sentencia = ConexionDAO.getConexion().createStatement();
			ResultSet resultado = sentencia.executeQuery(query);
			while (resultado.next()) {
				Artista a = new Artista(resultado.getInt("id"),
						resultado.getString("nombre"),
						resultado.getString("apellidos"),
						resultado.getInt("edad"),
						resultado.getString("pais"),
						resultado.getInt("idEstilo"));
				artista.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexionDAO.cerrarConexion();
		return artista;
	}

	public boolean insertar(Artista a) {
		int num_filas = 0;
		
		ConexionDAO.abrirConexion();
		
		String query ="INSERT INTO ARTISTAS VALUES(null, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setString(1, a.getNombre());
			sentencia.setString(2, a.getApellidos());
			sentencia.setInt(3, a.getEdad());
			sentencia.setString(4, a.getPais());
			sentencia.setInt(5, a.getIdEstilo());
			
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

	public List<Artista> listarArtistaPorEstilo(int idEstilo) {
		List<Artista> artistasPorEstilo = new ArrayList<Artista>();
		
		ConexionDAO.abrirConexion();
		
		String query ="SELECT * FROM ARTISTAS WHERE idEstilo = ?";
		 
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setInt(1, idEstilo);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				Artista a = new Artista(resultado.getInt("id"),
						resultado.getString("nombre"),
						resultado.getString("apellidos"),
						resultado.getInt("edad"),
						resultado.getString("pais"));
				artistasPorEstilo.add(a);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		ConexionDAO.cerrarConexion();
		
		
		
		return artistasPorEstilo;
	}
	
	public Artista coger(int idArtista) {
		Artista a = null;
		
		ConexionDAO.abrirConexion();
		
		String query ="SELECT * FROM ARTISTAS WHERE id = ?";
		
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setInt(1, idArtista);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				a = new Artista(resultado.getInt("id"),
						resultado.getString("nombre"),
						resultado.getString("apellidos"),
						resultado.getInt("edad"),
						resultado.getString("pais"),
						resultado.getInt("idEstilo"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConexionDAO.cerrarConexion();
		return a;
	}
	public boolean actualizarEdad(Artista a) {
		int num_filas = 0; 
		
		ConexionDAO.abrirConexion();
		
		String query ="UPDATE ARTISTAS SET edad = ? WHERE id= ?";
		
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setInt(1, a.getEdad());
			sentencia.setInt(2, a.getId());
			
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

	

}
