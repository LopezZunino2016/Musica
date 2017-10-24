package es.altair.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.altair.bean.Estilo;

public class EstiloDAOImplJDBC implements EstiloDAO {

	public List<Estilo> listarEstilo() {
		
		List<Estilo> estilo = new ArrayList<Estilo>();
		
		ConexionDAO.abrirConexion();
		
		String query = "SELECT * FROM ESTILOS"; 
		
		try {
			
			Statement sentencia = ConexionDAO.getConexion().createStatement();
			ResultSet resultado = sentencia.executeQuery(query);
			
			while(resultado.next()){
				Estilo e = new Estilo(resultado.getInt("id"),
						resultado.getString("nombre"));
				estilo.add(e);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		ConexionDAO.cerrarConexion();
		return estilo;
	}

	public boolean insertar(Estilo e) {
		int num_filas = 0; 
		
		ConexionDAO.abrirConexion();
		
		String query= "INSERT INTO ESTILOS VALUES(null,?)";
		
		try {
			PreparedStatement sentencia = ConexionDAO.getConexion().prepareStatement(query);
			sentencia.setString(1, e.getNombre());
			
			num_filas = sentencia.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ConexionDAO.cerrarConexion();
		if(num_filas == 0)
			return false;
		else
			return true;
	}

}
