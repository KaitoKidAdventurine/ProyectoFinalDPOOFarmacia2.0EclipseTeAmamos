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
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getDireccion() 
	{
		return direccion;
	}
	
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	public ArrayList<Paciente> getMujeres() 
	{
		return mujeres;
	}
	
	public void setMujeres(ArrayList<Paciente> mujeres) 
	{
		this.mujeres = mujeres;
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
