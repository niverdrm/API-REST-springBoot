package com.niver.crud.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niver.crud.model.Empleado;
import com.niver.crud.repository.EmpleadoRepository;
import com.niver.crud.service.EmpleadoService;

@Service
public class EmpleadoSeviceImp implements EmpleadoService{
	
	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public Empleado crearEmpelado(Empleado empleado) {
		
		String cedula = empleado.getCedula();
		
	Optional<Empleado>  empleadoAux = 	empleadoRepository.findByCedula(cedula);
	    
	if(empleadoAux.isPresent()) {
		
		return null;
	}
	   
      return  empleadoRepository.save(empleado);
		
	}

	@Override
	public Iterable<Empleado> listarEmpelados() {
		
		return empleadoRepository.findAll();
	}

	@Override
	public boolean eliminarEmpleado(long id) {
		
		 
        Optional<Empleado>  empleadoDb = empleadoRepository.findById(id);
		
		if(empleadoDb.isPresent()) {

		empleadoRepository.deleteById(id);
		return true;  
			
		}
		
		return false;
	}
	

	@Override
	public Empleado actualizarEmpleado(long id, Empleado empleado) {
		
		Optional<Empleado>  empleadoDb = empleadoRepository.findById(id);
		
		if(empleadoDb.isPresent()) {
			   
			Empleado auxEmpleado = empleadoDb.get();
			  
			auxEmpleado.setPrimerNombre(empleado.getPrimerNombre()); 
			auxEmpleado.setPrimerApellido(empleado.getPrimerApellido()); 
			auxEmpleado.setCedula(empleado.getCedula()); 
			auxEmpleado.setPais(empleado.getPais()); 
			
			Empleado actEmpelado = empleadoRepository.save(auxEmpleado);
			return  actEmpelado;
		}
		
		
		return null;

	}
	
	

}
