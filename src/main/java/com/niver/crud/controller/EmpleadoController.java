package com.niver.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niver.crud.model.Empleado;
import com.niver.crud.service.EmpleadoService;


@CrossOrigin(origins="http://localhost:4200", maxAge= 3600)
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleandoService;
	
	@PostMapping("/guardar")
	public ResponseEntity<Map<String, Object>>   guardarEmpleado (@RequestBody Empleado nuevoEmpleado) {

		Empleado empleado = empleandoService.crearEmpelado(nuevoEmpleado);
		
		Map<String, Object> response = new HashMap<>();
		if(empleado != null) {
			response.put("ok", true);
			response.put("msg", "Empleado registrado");
		}else {
			response.put("ok", false);
			response.put("msg", "El numero de cedula ya existe");
		}
		response.put("data", empleado);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	@GetMapping("empleados")
	public ResponseEntity<Map<String, Object>>   listarEmpleados () {

		
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleandoService.listarEmpelados().forEach(empleados:: add);
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("ok", true);
		response.put("data", empleados);
		
		if(empleados.isEmpty()) {
			response.put("msg", "No existen empleado");
		}else {
			response.put("msg", "Lista de empleados obtenida");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public  ResponseEntity<Map<String, Object>>  eliminarEmpleado(@PathVariable long id) {
		
		
		boolean  borrado = empleandoService.eliminarEmpleado(id);
		
		Map<String, Object> response = new HashMap<>();
		response.put("ok", borrado);
		if(borrado) {
			response.put("msg", "Eliminado con exito");
		}else {
			response.put("msg", "NO existe el empleado");	
		}
		
	
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("actualizar/{id}")
	public  ResponseEntity<Map<String, Object>>  actualizarEmpleado(@PathVariable long id, @RequestBody Empleado empleado) {

		 Empleado empleadoRes =empleandoService.actualizarEmpleado(id, empleado );
		
		Map<String, Object> response = new HashMap<>();
		if(empleadoRes != null) {
			response.put("ok", true);
			response.put("msg", "Empleado Actualizado");
		}else {
			response.put("ok", true);
			response.put("msg", "No existe ese empleado");
		}
		response.put("data", empleadoRes);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
