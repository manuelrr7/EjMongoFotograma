package proceso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import pojos.Directores;
import pojos.Fotograma;
import pojos.Generos;

public class Dao {
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private String cDirec="directores";
	private String cGe="generos";
	private String cFoto="fotogramas";
	
	
	public Dao(){
		
		this.mongoClient = new MongoClient();
		this.database= mongoClient.getDatabase("bdfotogramas");
		
	}
	
	
	//	insertar un nuevo fotograma
	
	public Fotograma crearFotograma(Fotograma fotograma){
		
		MongoCollection<Document> mongoCollection = database.getCollection(cFoto);
		Document document= new Document();

		document.append("archivo", fotograma.getArchivo());
		document.append("titPelicula", fotograma.getTitPelicula());
		document.append("anyoEstreno", fotograma.getAnyoEstreno());
		document.append("directores", fotograma.getDirectores());
		document.append("generos", fotograma.getGeneros());

		mongoCollection.insertOne(document);
		
		return fotograma;
	}

	
	// lista de fotogramas de un director
	
	public List<Fotograma> darFotogramasDirector(String director){
		
		List<Fotograma> data = new ArrayList<Fotograma>();
		Document document= new Document("directores", director);
		MongoCollection<Document> col=database.getCollection(cFoto);
		MongoCursor<Document> cursor = col.find(document).iterator();
		Fotograma f = new Fotograma();
		document= new Document();
		
		while (cursor.hasNext()) {
			
			document = cursor.next();
			
			f.setArchivo(document.getString("archivo"));
			f.setTitPelicula(document.getString("titPelicula"));
			f.setAnyoEstreno(document.getString("anyoEstreno"));
			
			for (int i = 0; i < darDirectores().size(); i++) {
				if (document.getString("directores").equals(darDirectores().get(i).getId())) {
					f.setDirectores(darDirectores().get(i).getNombre());
				}
			}
			
			for (int i = 0; i < darGereneros().size(); i++) {
				if (document.getString("generos").equals(darGereneros().get(i).getId())) {
					f.setGeneros(darGereneros().get(i).getNombre());
				}
			}
			
			data.add(f);
			System.out.println(f.toString());
			f = new Fotograma();
		}
		
		return data;
	}
	
	//lista de todos los fotogramas
	
	public List<Fotograma> darFotogramas(){
		
		List<Fotograma> data = new ArrayList<Fotograma>();
		Document document= new Document();
		MongoCollection<Document> col=database.getCollection(cFoto);
		MongoCursor<Document> cursor = col.find().iterator();
		Fotograma f = new Fotograma();
		while (cursor.hasNext()) {
			
			document = cursor.next();
			
			f.setArchivo(document.getString("archivo"));
			f.setTitPelicula(document.getString("titPelicula"));
			f.setAnyoEstreno(document.getString("anyoEstreno"));
			
			for (int i = 0; i < darDirectores().size(); i++) {
				if (document.getString("directores").equals(darDirectores().get(i).getId())) {
					f.setDirectores(darDirectores().get(i).getNombre());
				}
			}
			
			for (int i = 0; i < darGereneros().size(); i++) {
				if (document.getString("generos").equals(darGereneros().get(i).getId())) {
					f.setGeneros(darGereneros().get(i).getNombre());
				}
			}
			
			data.add(f);
			System.out.println(f.toString());
			f = new Fotograma();
		}
		System.out.println("ok");
		return data;
	}
	
	// lista de todos los directores
	
	public List<Directores> darDirectores(){
		
		List<Directores> data = new ArrayList<Directores>();
		Document document= new Document();
		MongoCollection<Document> col=database.getCollection(cDirec);
		MongoCursor<Document> cursor = col.find().iterator();
		Directores d = new Directores();
		
		while (cursor.hasNext()) {
			
			document = cursor.next();
			
			d.setId(document.getObjectId("_id").toString());
			d.setNombre(document.getString("nombre"));
			d.setNacionalidad(document.getString("nacionalidad"));
			
			data.add(d);
			d = new Directores();
		}
		
		return data;
	}
	
	
	
	// lista de todos los generos
	
	public List<Generos> darGereneros(){
		
		List<Generos> data = new ArrayList<Generos>();
		Document document= new Document();
		MongoCollection<Document> col=database.getCollection(cGe);
		MongoCursor<Document> cursor = col.find().iterator();
		Generos g = new Generos();
		
		while (cursor.hasNext()) {
			
			document = cursor.next();
						
			g.setId(document.getObjectId("_id").toString());
			g.setNombre(document.getString("nombre"));
			
			data.add(g);
			g = new Generos();
		}
		
		
		return data;
				
	}
	

}
