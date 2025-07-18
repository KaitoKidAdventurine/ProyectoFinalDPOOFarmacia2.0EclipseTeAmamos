package Logica;

import java.util.ArrayList;

import LogicaUtiles.Validaciones;

public class NucleoFamiliar 
{
	private String id;
	private String direccion;
	private ArrayList <Paciente> mujeres;
	private ArrayList <Paciente> hombres;
	private Paciente jefe;
	private boolean compraron;
	
	public ArrayList<Paciente> getHombres() 
	{
		return hombres;
	}

	public void setHombres(ArrayList<Paciente> hombres) 
	{
		if(Validaciones.sonHombres(hombres))
			this.hombres = hombres;
		else
			throw new IllegalArgumentException("El campo: lista de hombres, se encuentra con mujeres");
	}

	public NucleoFamiliar(String id, String direccion, ArrayList <Paciente> mujeres, 
			ArrayList <Paciente> hombres, Paciente jefe, boolean compraron)
	{
		setId(id); 
		setDireccion(direccion);
		setMujeres(mujeres); 
		setHombres(hombres);
		setJefe(jefe);
		setCompraron(compraron);
	}
	
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
		if(Validaciones.noEstaVacio(id))
			if(Validaciones.noTieneCaracteresEsp(id))
				this.id = id;
			else
				throw new IllegalArgumentException("El campo: id, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: id, se encuentra vac�o");
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
				throw new IllegalArgumentException("El campo: direcci�n, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: direcci�n, se encuentra vac�o");
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
