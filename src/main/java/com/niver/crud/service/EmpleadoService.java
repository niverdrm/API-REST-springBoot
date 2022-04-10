package com.niver.crud.service;


import com.niver.crud.model.Empleado;

public interface EmpleadoService {
	

	public Empleado crearEmpelado(Empleado empleado);
	public Iterable<Empleado> listarEmpelados();
	public boolean eliminarEmpleado(long id);
	public Empleado actualizarEmpleado(long id, Empleado empleado);

}
