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
				throw new IllegalArgumentException("El campo: nombre del medicamento, presenta n�meros");
		else
			throw new IllegalArgumentException("El campo: nombre del medicamento, se encuentra vac�o");
		
	}
	
	public void setDireccion(String direccion) 
	{
		if(Validaciones.noEstaVacio(direccion))
			if(Validaciones.noTieneCaracteresEsp(direccion))
				this.direccion = direccion;
			else
				throw new IllegalArgumentException("El campo: direcci�n, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: direcci�n, se encuentra vac�o");
		
	}
	
	
	public void setNucleo(NucleoFamiliar nucleo) 
	{
		if(nucleo != null)
			this.nucleo = nucleo;
		else
			throw new IllegalArgumentException("El campo: n�cleo del paciente, se encuentra vac�o");
	}
	// implementar
	public void setCi(String ci) 
	{
		this.ci = ci;
	}
	
	
}
