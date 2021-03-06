package acciones;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import pojos.*;
import proceso.*;
import acciones.*;
import controlador.Accion;

/**
 * Acción validar. Valida un usuario
 * @author  Eduardo A. Ponce
 * @version  Ajax-MVC2
 */
public class AccionRegistrar implements Accion {
	/**
	 * Datasource que se empleará para acceder a la base de datos.
	 * @uml.property  name="dS"
	 */
	private DataSource DS;
	/**
	 * Bean de error para situaciones en los que el método ejecutar() devuelve false.
	 * @uml.property  name="error"
	 * @uml.associationEnd  
	 */
	private BeanError error;
	/**
	 * Objeto que encapsula el modelo que procesará la vista.
	 * @uml.property  name="modelo"
	 */
	private Object modelo;
	/**
	 * Página JSP que se devuelve como "vista" del procesamiento de la acción.
	 * @uml.property  name="vista"
	 */
	private String vista = null;
	/**
	 * Contexto de aplicación.
	 */
	private ServletContext Sc;

	@Override
	/**
	 * Ejecuta la acción de validar. Recupera el datasource y comprueba 
	 * si existe el usuario, en cuyo caso se devuelve true, en caso
	 * contrario, se devuelve false. Se trata de una respuesta ante una petición
	 * AJAX.
	 */
	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean estado = true;
		Dao procesaBD = new Dao();
		ModeloAjax modelo = new ModeloAjax();
		
		String json, archi, tit, anio, dire, gen;
		
		archi= request.getParameter("archivo");
		tit= request.getParameter("titPelicula");
		anio= request.getParameter("anyoEstreno");
		dire= request.getParameter("directores");
		gen= request.getParameter("generos");
		
		modelo.setContentType("application/json; charset=UTF-8");
		Fotograma fotograma = new Fotograma();
		
		if ((archi!=null && !archi.equals("")) && (tit!=null && !tit.equals("")))
		{
		
		fotograma.setArchivo(archi);
		fotograma.setTitPelicula(tit);
		fotograma.setAnyoEstreno(anio);
		fotograma.setDirectores(dire);
		fotograma.setGeneros(gen);
		
		if (procesaBD.crearFotograma(fotograma) != null){		
			
		
			
		}else{
			fotograma.setError("Fotograma invalido");
		}
		}else{
			fotograma.setError("Debe rellenar todos los campos");
		}
		json = new Gson().toJson(fotograma);
		modelo.setRespuesta(json);
		this.setModelo(modelo);
		return estado;
	}

	/**
	 * @return
	 * @uml.property  name="error"
	 */
	@Override
	/**
	 * Devuelve el error asociado a la acción, si lo hubiera.
	 */
	public Exception getError() {
		return error;
	}

	/**
	 * @return
	 * @uml.property  name="modelo"
	 */
	@Override
	/**
	 * Devuelve el objeto modelo
	 */
	public Object getModelo() {
		return modelo;
	}
	/**
	 * Método setter para la propiedad modelo.
	 * @param modelo  El modelo a establecer.
	 * @uml.property  name="modelo"
	 */
	private void setModelo(Object modelo) {
		this.modelo = modelo;
	}	

	/**
	 * @return
	 * @uml.property  name="vista"
	 */
	@Override
	/**
	 * Devuelve la vista que debe procesar el modelo. En caso de ser
	 * una petición AJAX, la vista deberá ser null.
	 */
	public String getVista() {
		// La vista devuelta por una petición AJAX es null
		return vista;
	}
	/**
	 * Método setter para la propiedad vista.
	 * @param vista  La vista a establecer.
	 * @uml.property  name="vista"
	 */
	private void setVista(String vista) {
		this.vista = vista;
	}
	/**
	 * Método getter para la propiedad DS (datasource).
	 * @return  El datasource DS.
	 * @uml.property  name="dS"
	 */
	private DataSource getDS() {
		return DS;
	}	
	/**
	 * @param ds
	 * @uml.property  name="dS"
	 */
	@Override
	/**
	 * Establece el valor del datasource
	 */
	public void setDS(DataSource ds) {
		this.DS = ds;
	}

	/**
	 * @param sc
	 * @uml.property  name="sc"
	 */
	@Override
	/**
	 * Establece el contexto de aplicación
	 */
	public void setSc(ServletContext sc) {
		this.Sc = sc;
	}

}
