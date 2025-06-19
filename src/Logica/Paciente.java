package Logica;

import java.util.ArrayList;

public class Paciente 
{
	protected String nombre;
	protected String ci;
	protected String direccion;
	protected NucleoFamiliar nucleo;
	
	
	
	public String getNombre() 
	{
		return nombre;
	}

	

	public String getCi() 
	{
		return ci;
	}

	

	public String getDireccion() 
	{
		return direccion;
	}

	

	public NucleoFamiliar getNucleo() 
	{
		return nucleo;
	}

	

	public void setNombre(String nombre) 
	{
		if(Validaciones.noEstaVacio(nombre))
			if(Validaciones.noTieneNumeros(nombre))
				if(Validaciones.noTieneCaracteresEsp(nombre))
					this.nombre = nombre;
				else
					throw new IllegalArgumentException("El campo: nombre del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre del medicamento, se encuentra vacío");
		
	}
	
	public void setDireccion(String direccion) 
	{
		if(Validaciones.noEstaVacio(direccion))
			if(Validaciones.noTieneCaracteresEsp(direccion))
				this.direccion = direccion;
			else
				throw new IllegalArgumentException("El campo: dirección, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: dirección, se encuentra vacío");
		
	}
	
	
	public void setNucleo(NucleoFamiliar nucleo) 
	{
		if(nucleo != null)
			this.nucleo = nucleo;
		else
			throw new IllegalArgumentException("El campo: núcleo del paciente, se encuentra vacío");
	}
	// implementar
	public void setCi(String ci) 
	{
		this.ci = ci;
	}
	
	
}
