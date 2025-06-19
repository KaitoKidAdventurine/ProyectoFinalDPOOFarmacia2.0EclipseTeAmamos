package Logica;

import java.util.ArrayList;

public class NucleoFamiliar 
{
	private String id;
	private String direccion;
	private ArrayList<Paciente> mujeres;
	private Paciente jefe;
	private boolean compraron;
	
	public boolean getCompraron() 
	{
		return compraron;
	}

	public void setCompraron(boolean compraron) 
	{
		this.compraron = compraron;
	}

	public String getId() 
	{
		
		return id;
	}
	// implementar
	public void setId(String id) 
	{
		if(Validaciones.noEstaVacio(id))
			if(Validaciones.noTieneCaracteresEsp(id))
				this.id = id;
			else
				throw new IllegalArgumentException("El campo: id, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: id, se encuentra vacío");
	}
	
	public String getDireccion() 
	{
		
		return direccion;
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
	
	public ArrayList<Paciente> getMujeres() 
	{
		return mujeres;
	}
	
	public void setMujeres(ArrayList<Paciente> mujeres) 
	{
		if(Validaciones.sonMujeres(mujeres))
			this.mujeres = mujeres;
		else
			throw new IllegalArgumentException("El campo: lista de mujeres, tiene hombres");
	}
	
	public Paciente getJefe() 
	{
		return jefe;
	}
	
	public void setJefe(Paciente jefe) 
	{
		this.jefe = jefe;
	}

	
	
	
}
