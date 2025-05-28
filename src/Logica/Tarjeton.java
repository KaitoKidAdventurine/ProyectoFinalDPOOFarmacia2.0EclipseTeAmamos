package Logica;

import java.awt.List;
import java.sql.Date;
import java.util.ArrayList;

public class Tarjeton{
	private String nombre;
	private String direccionPaciente;
	private Date fechaExpedicion;
	private Date fechaVencimiento;
	private ArrayList<MedicamentoControlado> medicamentosConts;
	private boolean activo;

	public String getNombre() 
	{
		return nombre;
	}
	public String getDireccionPaciente() 
	{
		return direccionPaciente;
	}
	
	public Date getFechaExpedicion() 
	{
		return fechaExpedicion;
	}
	public Date getFechaVencimiento() 
	{
		return fechaVencimiento;
	}
	public List<MedicamentoControlado> getMedicamentosConts() 
	{
		return medicamentosConts;
	}


	public void setNombre(String nombre) 
	{
		if(Validaciones.noEstaVacio(nombre))
			if(Validaciones.noTieneNumeros(nombre))
				if(Validaciones.noTieneCaracteresEsp(nombre))
					this.nombre = nombre;
				else
					throw new IllegalArgumentException("El campo: nombre, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre, se encuentra vacío");

	}

	public void setDireccionPaciente(String direccionPaciente) 
	{
		if(Validaciones.noEstaVacio(direccionPaciente))
			if(Validaciones.noTieneCaracteresEsp(direccionPaciente))
				this.direccionPaciente = direccionPaciente;
			else
				throw new IllegalArgumentException("El campo: nombre, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: nombre, se encuentra vacío");
	}	


	public void setFechaExpedicion(Date fechaExpedicion) 
	{
		this.fechaExpedicion = fechaExpedicion;
	}

	

	public void setFechaVencimiento(Date fechaVencimiento) 
	{
		this.fechaVencimiento = fechaVencimiento;
	}


	public void setMedicamentosConts(List<MedicamentoControlado> medicamentosConts) 
	{
		this.medicamentosConts = medicamentosConts;
	}
}
