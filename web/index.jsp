<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ejercicio fotograma perdido ajax</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.0.0.js"></script>
<script src="https://code.jquery.com/jquery-migrate-3.0.0.js"></script>
</head>
<style type="text/css">
.progress-bar {
    color: #333;
} 
* {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
    outline: none;
}
    .form-control {
      position: relative;
      font-size: 16px;
      height: auto;
      padding: 10px;
        @include box-sizing(border-box);
        &:focus {
          z-index: 2;
        }
    }
      .navbar-default .navbar-fixed-top {
    background-color: #000000;
}
body {
    color: black;
    background: url(http://i.imgur.com/GHr12sH.jpg) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
.login-form {
    margin-top: 60px;
}
form[role=login] {
    color: #5d5d5d;
    background: #f2f2f2;
    padding: 26px;
    border-radius: 10px;
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
}
    form[role=login] img {
        display: block;
        margin: 0 auto;
        margin-bottom: 35px;
    }
    form[role=login] input,
    form[role=login] button {
        font-size: 18px;
        margin: 16px 0;
    }
    form[role=login] > div {
        text-align: center;
    }
    
.form-links {
    text-align: center;
    margin-top: 1em;
    margin-bottom: 50px;
}
    .form-links a {
        color: #fff;
    }
</style>
<body>

  <div class="row">
    <div class="col-md-4 center-block">
      <section class="login-form">
        <form method="post" action="" role="login">
          <input type="text" name="archivo" placeholder="archivo.jpg" required class="form-control input-lg archivo"archivo"" value="" required="" />
		  <input type="text" class="form-control input-lg titPelicula" placeholder="titulo" name="titPelicula" required="" />
		  <label>Elija el año:</label>
		  <select class="groupanio"></select>
		  <br>
		  <label>Elija el genero:</label>
		  <select class="groupgene"></select>
		  <br>
		  <label>Elija el director:</label>
          <select class="groupdirec"></select>
          <button type="button" name="registrar" class="btn btn-lg btn-primary btn-block registro">Registrar pelicula</button>
        </form>
      </section>  
	</div>
	<div class="col-md-4 center-block">
      <section class="login-form">
        <form method="post" action="" role="directores">
          <label>Buscar pelicula por director:</label>
		  <select class="groupdirecb"></select>
          <button type="button" name="direct" class="btn btn-lg btn-primary btn-block direct">Buscar</button>
                    <button type="button" name="fotog" class="btn btn-lg btn-primary btn-block fotogramas">Ver todas las peliculas</button>
        </form>
      </section>  
	</div>
  </div>

<div class="container listfotog">
  <div class="row listfotog">
    <h2>Fotogramas</h2>
      <div class="col-xs-12 col-sm-9">Lista fotogramas:</div>
  </div>
    <table class="table" id="table-foto">
      <thead>
        <tr>
          <th>Archivo</th>
          <th>Pelicula</th>
          <th>Año de estreno</th>
          <th>Director</th>
          <th>Genero</th>
        </tr>
      </thead>
      <tbody id="fotograma"></tbody>
	</table>
</div>

</body>   
    
<script>
   
$(document).ready(function() {
	$('.listfotog').hide();
	cargaInicial();
	
	
	
    function cargaInicial(){
    
       for(i = 1930; i < 2018; i++){		
		$('.groupanio').append(new Option(i, i));
        }
        cargarGeneros();
        cargarDirectores();
    
    }
        
    // PETICION DE REGISTRO FOTOGRAMA
    
    $('.registro').click(function(){
        var arch = $('.archivo').val();
        var tit = $('.titPelicula').val();
        var aestre= $('.groupanio').prop('value');
        var directo= $('.groupdirec').prop('value');
        var gener= $('.groupgene').prop('value');
        $.ajax({
            type: "POST",
            url: "controlador?accion=registrar",
            dataType: "json",
            data: { "archivo": arch, "titPelicula": tit, "anyoEstreno": aestre, "directores": directo, "generos": gener },
            success: function(data) {
                
                if (data.error!=null) {

					alert(data.error);
				} else {
					alert("Fotograma registrado");

				}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }           
        });
    });

		// ZOON IMAGEN
        $(".table").mouseenter(function() {
        $(this).find('img').animate({width:250,height:250,left:-25,top:-25});
        $(this).find('img').rotate({animateTo:8});
    	}).mouseleave(function() {
        $(this).find('img').animate({width:50,height:50,left:0,top:0});
        $(this).find('img').rotate({animateTo:0});
    	});

	//PETICION FOTOGRAMAS
	
	   $('.fotogramas').click(function(){
	   		var url;
	   		$('.listfotog').hide();
              $.ajax({
            type: "GET",
            url: "controlador?accion=fotogramas",
            dataType: "json",
            success: function(data) {
                      var htmlNuevaFila="";
					$('#fotograma').html(htmlNuevaFila);
                for(i = 0; i < data.length; i++){
                        url="img/"; 		
                		url +=data[i].archivo;
               			
                          htmlNuevaFila+= '<tr><td><img class="imgp" src='+url+' height="42" width="42"></img></td><td>'+data[i].titPelicula+'</td><td>'+data[i].anyoEstreno+'</td><td>'+data[i].directores+'</td><td>'+data[i].generos+'</td></tr>';
						//$('.imgp').attr('src', url); 
                      }
                      $('#fotograma').html(htmlNuevaFila);
            }, 
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });
        
        $('.listfotog').show();
      });
      
      
      //PETICION FOTOGRAMAS DE UN DIRECTOR
      
	   $('.direct').click(function(){
	   		var director= $('.groupdirecb').prop('value');
	   		var url;
	   		$('.listfotog').hide();
              $.ajax({
            type: "GET",
            url: "controlador?accion=bfotogramas",
            dataType: "json",
            data: { "directores": director},
            success: function(data) {
                      var htmlNuevaFila="";
					$('#fotograma').html(htmlNuevaFila);
                for(i = 0; i < data.length; i++){
                		  url="img/";
                		  url +=data[i].archivo;
                          htmlNuevaFila+= '<tr><td><img class="imgp" src='+url+' height="42" width="42"></img></td><td>'+data[i].titPelicula+'</td><td>'+data[i].anyoEstreno+'</td><td>'+data[i].directores+'</td><td>'+data[i].generos+'</td></tr>';
						//$('.imgp').attr('src', url); 
                      }
                      $('#fotograma').html(htmlNuevaFila);
            }, 
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });
        
        $('.listfotog').show();
      });
		
	

	//PETICION directores
	   function cargarDirectores(){
              $.ajax({
            type: "GET",
            url: "controlador?accion=directores",
            dataType: "json",
            success: function(data) {
                     

                for(i = 0; i < data.length; i++){
                          $('.groupdirec').append(new Option( data[i].nombre, data[i].id));
                          $('.groupdirecb').append(new Option(data[i].nombre, data[i].id));

                }
                      
            }, 
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });
        
      }
      
      //PETICION generos
      	   function cargarGeneros(){
              $.ajax({
            type: "GET",
            url: "controlador?accion=generos",
            dataType: "json",
            success: function(data) {
                     
                for(i = 0; i < data.length; i++){
                          $('.groupgene').append(new Option(data[i].nombre, data[i].id));

                }
                      
            }, 
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });
        
      }


});
</script>
     
</html>