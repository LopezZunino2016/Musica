package es.altair.main;

import java.util.List;
import java.util.Scanner;

import es.altair.bean.Artista;
import es.altair.bean.Cancion;
import es.altair.bean.Estilo;
import es.altair.dao.ArtistaDAO;
import es.altair.dao.ArtistaDAOImplJDBC;
import es.altair.dao.CancionDAO;
import es.altair.dao.CancionImplJDBC;
import es.altair.dao.EstiloDAO;
import es.altair.dao.EstiloDAOImplJDBC;


public class Principal {

	private static Scanner sc = new Scanner(System.in);
	
	private static ArtistaDAO artistaDAO = new ArtistaDAOImplJDBC();
	private static EstiloDAO estiloDAO = new EstiloDAOImplJDBC();
	private static CancionDAO cancionDAO = new CancionImplJDBC();
	
	public static List<Artista> listaArtista = null; 
	public static List<Estilo> listaEstilo = null;
	public static List<Cancion> listaCancion = null; 
	
	public static void main(String[] args) {
		ArtistaDAO aDAO = new ArtistaDAOImplJDBC(); 
		List<Artista> artistas = aDAO.listarArtista();
		listaEstilo = estiloDAO.listarEstilo(); 
		
				
		
		int opcion = 0;
		
		do {
			System.out.println("\t\t\t\t╔═════════════════════════════════════════════════╗");
	        System.out.println("\t\t\t\t║                         MENU                    ║");
	        System.out.println("\t\t\t\t╠═════════════════════════════════════════════════╣");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    1.- Añadir Artista      	                  ║");
	        System.out.println("\t\t\t\t║                      		                  ║");
	        System.out.println("\t\t\t\t║    2.- Añadir Cancion                           ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    3.- Añadir Estilo	                          ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    4.- Listar Artista Segun Estilo              ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    5.- Lista Canciones de un Artista            ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    6.- Actualizar edad de un Artista            ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    7.- Aumentar duracion por Pais               ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    8.- Borrar Todas las canciones de un Artista ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║    9.- Mostrar Artista y Canciones              ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t║                  0) Salir       	          ║");
	        System.out.println("\t\t\t\t║                                                 ║");
	        System.out.println("\t\t\t\t╚═════════════════════════════════════════════════╝");
	           
	        System.out.print("\t\t\t\tEscribe una de las opciones: ");
	        opcion = sc.nextInt();
	        
	        switch (opcion) {
			case 0:
				sc.close();
				break;

			case 1://Añadir Artista
				listaArtista = artistaDAO.listarArtista();
				listaEstilo = estiloDAO.listarEstilo(); 
				
				System.out.print("Nombre: ");
				String nombre = sc.next();
				
				System.out.print("Apellidos: ");
				String apellidos = sc.next();
				
				mostrarEstilo();
				
				System.out.print("Estilo [ID]: ");
				int estilo = sc.nextInt();
				
				System.out.print("Edad: ");
				int edad = sc.nextInt();
				
				System.out.print("Pais: ");
				String pais = sc.next();
				
				Artista art1 = new Artista(0, nombre, apellidos, edad, pais, estilo);
				
				if (artistaDAO.insertar(art1))
					System.out.println("Artista Insertado");
				else
					System.out.println("Artista No Insertado");
				
				System.out.println();
				
				
				break;
			case 2 : //añadir Cancion
				listaArtista = artistaDAO.listarArtista();
				
				System.out.print("Nombre: ");
				String nombreC = sc.next();
				
				System.out.print("Duracion: ");
				float duracion = sc.nextFloat();
				
				mostrarArtista();
				
				System.out.print("Artista (ID): ");
				int artistaC = sc.nextInt();
				
				Cancion can1 = new Cancion(0,nombreC,duracion,artistaC);
				if(cancionDAO.insertar(can1))
					System.out.println("Cancion Insertado");
				else
					System.out.println("Cancion No Insertado");
				
				System.out.println();
				break;
			case 3://Añadir Estilo
				System.out.print("Nombre: ");
				String nombreE = sc.next();
				
				Estilo est1 = new Estilo(0,nombreE);
				if(estiloDAO.insertar(est1))
					System.out.println("Estilo Insertado");
				else
					System.out.println("Estilo No Insertado");
				break;
			case 4: //Listar Artista Segun Estilo
				mostrarEstilo();
				System.out.println("Seleccione un estilo[id]");
				estilo = sc.nextInt();
				
				List<Artista> artistas2 = artistaDAO.listarArtistaPorEstilo(estilo);
				
				for (Artista artista : artistas2) {
					System.out.println(artista);
				}
				break;
			case 5://Lista Canciones de un Artista
				listaArtista = artistaDAO.listarArtista();
				mostrarArtista();

				System.out.print("Seleccione un Artista[id]");
				int idArtista = sc.nextInt(); 
				
				List<Cancion> canciones = cancionDAO.mostrarCancionPorArtista(idArtista);
				
				for (Cancion cancion : canciones) {
					System.out.println(cancion);
				}

				System.out.println();

				break;
			case 6: //Actualizar edad de un Artista
				listaArtista = artistaDAO.listarArtista();
				mostrarArtista();
				System.out.print("Seleccione un Artista[id]: ");
				idArtista = sc.nextInt();
				System.out.println("Indique nueva edad: ");
				edad = sc.nextInt();
				
				Artista a = artistaDAO.coger(idArtista);
				
				if(a !=null) {
					a.setEdad(edad);
					if(artistaDAO.actualizarEdad(a))
						System.out.println("Edad Actualizada");
					else
						System.out.println("Edad No Actualizad");
				}
				break;
			case 7://Aumentar duracion por Pais
				break;
			case 8://Borrar Todas las canciones de un Artista  
				listaArtista = artistaDAO.listarArtista();
				mostrarArtista();
				
				System.out.print("Seleccione un Artista[id]: ");
				idArtista = sc.nextInt();
				
				if(cancionDAO.borrar(idArtista))
					System.out.println("Canciones Borradas");
				else 
					System.out.println("No se han borrado las cancions");
				break;
			case 9: //Mostrar Artista y Canciones
				listaArtista = artistaDAO.listarArtista();
				
				for (Artista artista : listaArtista) {
					System.out.println("╔═══════════════════════════════════════╗");
					if((artista.getNombre().length() + artista.getApellidos().length()) <10)
						System.out.println("║\t" +artista.getId() +" - "+ artista.getNombre() + " " + artista.getApellidos()+ "\t\t        ║");
					else if((artista.getNombre().length() + artista.getApellidos().length()) > 10)
						System.out.println("║\t" +artista.getId() +" - "+ artista.getNombre() + " " + artista.getApellidos()+ "\t\t║");
					else
						System.out.println("║\t" +artista.getId() +" - "+ artista.getNombre() + " " + artista.getApellidos()+ "\t\t    ║");
					List<Cancion> listaCancion = cancionDAO.mostrarCancionPorArtista(artista.getId());
					for (Cancion cancion : listaCancion) {
						if(cancion.getNombre().length() <=10)
							System.out.println("║\t     " + cancion.getNombre() + "\t\t        ║");
						else if (cancion.getNombre().length() <=15)
							System.out.println("║\t     " + cancion.getNombre() + "\t\t║");
						else if(cancion.getNombre().length() <=20)
							System.out.println("║\t     " + cancion.getNombre() + "\t║");
						else
							System.out.println("║\t     " + cancion.getNombre() + "║");
					}	
					System.out.println("╚═══════════════════════════════════════╝");
				}
				break;
			}
		} while (opcion != 0);
		
	}

	private static void mostrarEstilo() {
		
		for (Estilo estilo : listaEstilo) {
			System.out.println("\t" + estilo.getId() + " - " + estilo.getNombre());
		}
		
	}
	private static void mostrarArtista(){
		System.out.println("\t" + "Nombre" +" " + "Apellidos" + "6, " + "edad" );
		for (Artista artista : listaArtista) {
			System.out.println("\t" +artista.getId() +" - "+ artista.getNombre() + " " + artista.getApellidos() + " , "+ artista.getEdad());
		}
	}

}
