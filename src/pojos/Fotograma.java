package pojos;

import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class Fotograma {



private String id;

private String archivo;

private String titPelicula;

private String anyoEstreno;


private String directores;

private String generos;

private String error;

/**
* No args constructor for use in serialization
*
*/
public Fotograma() {
}

/**
*
* @param id
* @param archivo
* @param generos
* @param titPelicula
* @param directores
* @param anyoEstreno
*/
public Fotograma( String archivo, String titPelicula, String anyoEstreno, String directores, String generos) {
super();

this.archivo = archivo;
this.titPelicula = titPelicula;
this.anyoEstreno = anyoEstreno;
this.directores = directores;
this.generos = generos;
}

public Fotograma(String id, String archivo, String titPelicula, String anyoEstreno, String directores,
		String generos, String error) {
	super();
	this.id = id;
	this.archivo = archivo;
	this.titPelicula = titPelicula;
	this.anyoEstreno = anyoEstreno;
	this.directores = directores;
	this.generos = generos;
	this.error = error;
}


public String getArchivo() {
return archivo;
}

public void setArchivo(String archivo) {
this.archivo = archivo;
}

public Fotograma withArchivo(String archivo) {
this.archivo = archivo;
return this;
}

public String getTitPelicula() {
return titPelicula;
}

public void setTitPelicula(String titPelicula) {
this.titPelicula = titPelicula;
}

public Fotograma withTitPelicula(String titPelicula) {
this.titPelicula = titPelicula;
return this;
}

public String getAnyoEstreno() {
return anyoEstreno;
}

public void setAnyoEstreno(String anyoEstreno) {
this.anyoEstreno = anyoEstreno;
}

public Fotograma withAnyoEstreno(String anyoEstreno) {
this.anyoEstreno = anyoEstreno;
return this;
}

public String getDirectores() {
	return directores;
}

public void setDirectores(String directores) {
	this.directores = directores;
}

public String getGeneros() {
	return generos;
}

public void setGeneros(String generos) {
	this.generos = generos;
}

public String getError() {
	return error;
}

public void setError(String error) {
	this.error = error;
}

@Override
public String toString() {
	return "Fotograma [archivo=" + archivo + ", titPelicula=" + titPelicula + ", anyoEstreno=" + anyoEstreno
			+ ", directores=" + directores + ", generos=" + generos + "]";
}


	
	
}
