package com.puerta18.enclase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.puerta18.model.Socio;

@Controller
public class SociosController {

	@Autowired
	private Environment env;

	// landing page, muestra un formulario de busqueda 
	// y tambien muestra los resultados con un parametro no requerido 
	@GetMapping("/")
	public String landing() {
		return "index";
	}
	
	@GetMapping("/socios/busqueda")
	public String buscarSocio(@RequestParam(required=false) String buscar,Model template) throws SQLException {
		
		if(buscar==null || buscar.equals(""))
		{
			return "index";
		}
		//buscar= buscar.toLowerCase();
		
		Connection connection;
		
		connection= DriverManager.getConnection(
				env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
	
		
		PreparedStatement consulta = connection.prepareStatement("SELECT * FROM socios WHERE LOWER(unaccent(nombre)) LIKE LOWER(unaccent(?)) OR  LOWER(unaccent(apellido)) LIKE  LOWER(unaccent(?)) OR  LOWER(email) LIKE  LOWER(?) OR  dni=?");
		
		consulta.setString(1,"%"+buscar +"%");
		consulta.setString(2,"%"+buscar +"%");
		consulta.setString(3,"%"+buscar +"%");
		consulta.setString(4,"%"+buscar +"%");
		
		
		ResultSet resultados= consulta.executeQuery();
	
		ArrayList<Socio> losSocios= new ArrayList<Socio>();  
		

		
		while(resultados.next()) 
		{
			int id= resultados.getInt("id");
			String nombre= resultados.getString("nombre");
			String apellido= resultados.getString("apellido");
			String email= resultados.getString("email");
			String dni= resultados.getString("dni");
			String celular= resultados.getString("celular");
			String telefono= resultados.getString("telefono");
			String telefono2= resultados.getString("telefono2");
			String direccion= resultados.getString("direccion");
			String genero= resultados.getString("genero");
			String localidad= resultados.getString("localidad");
			Date fecha_de_nacimiento= resultados.getDate("fecha_de_nacimiento");
			boolean presente= resultados.getBoolean("presente");
			
			
			Socio elSocio= new Socio(id,nombre,apellido,email,dni,celular,telefono,telefono2,direccion,genero,localidad,fecha_de_nacimiento,presente);
			losSocios.add(elSocio);
			System.out.println("hola");

		}
		template.addAttribute("socios",losSocios);
		
		connection.close();
		
		
		
		return "busqueda";
	}
	
	@GetMapping("/socios/nuevo") // formulario de alta vacio
	public String nuevo() {
		return "nuevosocio";
	}
	
	@GetMapping("/socios/nuevo/procesar") // inserta nuevos socios
	public String insertarNuevo(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String email, @RequestParam String dni) throws SQLException {
		

		Connection connection;
		
		connection= DriverManager.getConnection(
				env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = connection.prepareStatement("INSERT INTO socios(nombre,apellido,email,dni) VALUES(?,?,?,?);");
		
		consulta.setString(1,nombre);
		consulta.setString(2,apellido);
		consulta.setString(3,email);
		consulta.setString(4,dni);
		consulta.execute();
		connection.close();
		
		return "redirect:/";
	}
	
	@GetMapping("/socios/checkin/{id}") // 
	public String checkIn(@PathVariable int id, Model template) throws SQLException {
		
		Connection connection;
		
		connection= DriverManager.getConnection(
				env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = connection.prepareStatement("INSERT INTO checks(id_socio,momento,tipo) VALUES (?,NOW(),'IN') ");
		PreparedStatement consulta2 = connection.prepareStatement("UPDATE socios SET presente=true WHERE id=? ");
		
		
		consulta.setInt(1,id);
		consulta2.setInt(1,id);
		consulta.execute();consulta2.execute();
		connection.close();
		return "redirect:/";
	}
	
	@GetMapping("/socios/checkout/{id}") // 
	public String checkOut(@PathVariable int id, Model template) throws SQLException {
	Connection connection;
		
		connection= DriverManager.getConnection(
				env.getProperty("spring.datasource.url"),
				env.getProperty("spring.datasource.username"),
				env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = connection.prepareStatement("INSERT INTO checks(id_socio,momento,tipo) VALUES (?,NOW(),'OUT')");
		PreparedStatement consulta2 = connection.prepareStatement("UPDATE socios SET presente=false WHERE id=? ");
		
		
		
		consulta.setInt(1,id);
		consulta2.setInt(1,id);
		consulta.execute();consulta2.execute();
		connection.close();
		return "redirect:/";
	}
	
	// estas rutas mas adelante vamos a protegerlas con usuario y contraseña
	// @GetMapping("/socios/mostrar/{id}") // muestra el detalle completo de un socio
	// @GetMapping("/socios/listado")      // muestra el listado completo sin paginacion, por ahora
	
	// @GetMapping("/socios/modificar/{id}")
	// @GetMapping("/socios/modificar/procesar/{id}")
}
