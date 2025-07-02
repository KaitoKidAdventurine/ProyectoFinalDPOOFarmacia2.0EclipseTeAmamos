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

	
	public NucleoFamiliar(String id, String direccion, ArrayList <Paciente> hombres,
			ArrayList <Paciente> mujeres, Paciente jefe, boolean compraron)
	{
		setId(id); 
		setDireccion(direccion);
		setHombres(hombres);
		setMujeres(mujeres); 
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
			this.id = id;
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
			if(Validaciones.direccion(direccion))
				this.direccion = direccion;
			else
				throw new IllegalArgumentException("El campo: dirección, tiene caracteres especiales: "+ direccion );
		else
			throw new IllegalArgumentException("El campo: dirección, se encuentra vacío: " + direccion);
	}
	
	public ArrayList<Paciente> getHombres() 
	{
		return hombres;
	}

	


	public ArrayList<Paciente> getMujeres() 
	{
		return mujeres;
	}

	
	public void setHombres(ArrayList<Paciente> hombres) 
	{
		if(Validaciones.sonHombres(hombres))
			this.hombres = hombres;
		else
			throw new IllegalArgumentException("El campo: lista de hombres, se encuentra con mujeres");
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
		jefe.setCi(jefe.getCi());
		jefe.setDireccion(jefe.getDireccion());
		jefe.setFechaNacimiento(jefe.getFechaNacimiento());
		jefe.setGenero(jefe.getGenero());
		jefe.setNombre(jefe.getNombre());
		jefe.setNucleo(jefe.getNucleo());
		this.jefe = jefe;
	}




}
