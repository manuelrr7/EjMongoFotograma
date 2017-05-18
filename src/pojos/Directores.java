package pojos;

import com.mongodb.BasicDBObject;

public class Directores {

	private String id;
	private String nombre;
	private String nacionalidad;
	
	private String error;


	public Directores() {
	}


	public Directores( String nombre, String nacionalidad) {
		super();
		
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	
	public Directores(String id, String nombre, String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
}
