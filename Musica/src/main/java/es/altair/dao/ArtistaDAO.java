package es.altair.dao;

import java.util.List;

import es.altair.bean.Artista;

public interface ArtistaDAO {

		public List<Artista> listarArtista();
		public boolean insertar(Artista a);
		public List<Artista> listarArtistaPorEstilo(int idEstilo);
		public Artista coger(int idArtista);
		public boolean actualizarEdad(Artista a);
		public List<Artista> listaPaises();
		public List<Integer> obtener(String pais);

}
