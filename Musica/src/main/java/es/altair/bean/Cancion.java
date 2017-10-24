package es.altair.bean;

public class Cancion {
	
	private int id; 
	private String nombre; 
	private float duracion; 
	private int artistaId;
	public Cancion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cancion(int id, String nombre, float duracion, int artistaId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.artistaId = artistaId;
	}
	
	
	
	public Cancion(int id, String nombre, float duracion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getDuracion() {
		return duracion;
	}
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	public int getArtistaId() {
		return artistaId;
	}
	public void setArtistaId(int artistaId) {
		this.artistaId = artistaId;
	}
	@Override
	public String toString() {
		return "Cancion [idCancion=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", artistaId="
				+ artistaId + "]";
	}
	
	
}
