package Logica;

import java.sql.Date;
import java.util.ArrayList;

import Interfaces_Enum.ValidacionBool;
import LogicaUtiles.Validaciones;

public class Tarjeton implements ValidacionBool
{
	private String nombre;
	private String direccionPaciente;
	private Date fechaExpedicion;
	private Date fechaVencimiento;
	private ArrayList<MedicamentoControlado> medicamentosConts;

	public Tarjeton(String nombre, String direccionPaciente, Date fechaExpedicion,
			Date fechaVencimiento, ArrayList<MedicamentoControlado> medicamentosConts)
	{
		setNombre(nombre);
		setDireccionPaciente(direccionPaciente);
		setFechaExpedicion(fechaExpedicion);
		setFechaVencimiento(fechaVencimiento);
		setMedicamentosConts(medicamentosConts); 
	}
	
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
	public ArrayList<MedicamentoControlado> getMedicamentosConts() 
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
			if(Validaciones.direccion(direccionPaciente))
				this.direccionPaciente = direccionPaciente;
			else
				throw new IllegalArgumentException("El campo: dirección, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: dirección, se encuentra vacío");
	}	


	public void setFechaExpedicion(Date fechaExpedicion) 
	{
		if(Validaciones.noEstaVacio(fechaExpedicion))
			if(Validaciones.sobrepasaDeLaFechaDeHoy(fechaExpedicion))			
					this.fechaExpedicion = fechaExpedicion;
			else
				throw new IllegalArgumentException("El campo: fecha de Producción del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de Producción del medicamento, se encuentra vacío");
		
	}

	

	public void setFechaVencimiento(Date fechaVencimiento) 
	{
		if(Validaciones.noEstaVacio(fechaVencimiento))
			
				if(getFechaExpedicion() != null && fechaVencimiento.after(getFechaExpedicion())) 
					this.fechaVencimiento = fechaVencimiento;
				else
					throw new IllegalArgumentException("La fecha de vencimiento no puede ser antes de la fecha de produción");
			else
				throw new IllegalArgumentException("El campo: fecha de Vencimiento del medicamento, se encuentra vacío");
		
		
	}


	public void setMedicamentosConts(ArrayList<MedicamentoControlado> medicamentosConts) 
	{
		if(medicamentosConts != null)
			this.medicamentosConts = medicamentosConts;
		else
			throw new IllegalArgumentException("El campo:lista de medicamentos controlados, se encuentra vacío");
	}
	
	
	// Interfaz ValicacionBool
	
	public boolean validacion(Date fechaExpedicion, Date fechaVencimiento) 
	{
		Date fechaDeHoy = new Date(0);
		return !fechaDeHoy.before(fechaExpedicion) && !fechaDeHoy.after(fechaVencimiento);
	}
}
