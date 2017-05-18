package pojos;

import com.mongodb.BasicDBObject;

public class Generos {

private String id;
private String nombre;
private String error;



public Generos() {
}


public Generos(String nombre) {
	super();
	this.nombre = nombre;
}

public Generos(String id, String nombre) {
	super();
	this.id= id;
	this.nombre = nombre;
}

public Generos(String id, String nombre, String error) {
	super();
	this.id= id;
	this.nombre = nombre;
	this.error = error;
}


/**
*
* @param nombre
*/


public String getNombre() {
return nombre;
}

public void setNombre(String nombre) {
this.nombre = nombre;
}

public Generos withNombre(String nombre) {
this.nombre = nombre;
return this;
}


public String getError() {
	return error;
}



public void setError(String error) {
	this.error = error;
}





public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


@Override
public String toString() {
	return "Generos [nombre=" + nombre + "]";
}


}