package Logica;

import java.util.ArrayList;
import java.util.Date;

import LogicaUtiles.Validaciones;

public class Paciente {
	protected String nombre;
	protected String ci;
	protected String direccion;
	protected Date fechaNacimiento;
	protected char genero;
	protected NucleoFamiliar nucleo;
	protected ArrayList<Tarjeton> tarjetones;
	protected boolean esControlado;
	private String direccion2;


	public Paciente(String nombre, String ci, String direccion, Date fechaNacimiento, char genero) 
	{
		setNombre(nombre);
		setCi(ci);
		setDireccion(direccion);
		setFechaNacimiento(fechaNacimiento);
		setGenero(genero);
		this.tarjetones = new ArrayList<Tarjeton>();
		this.esControlado = false;
	}

	public Paciente() 
	{
		this.tarjetones = new ArrayList<Tarjeton>();
		this.esControlado = false;
		setNucleo(nucleo);
	}


	public void setFechaNacimiento(Date fechaNacimiento) 
	{
		if (fechaNacimiento == null) 
		{
			throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
		}
		this.fechaNacimiento = fechaNacimiento;
	}




	public void agregarTarjeton(Tarjeton tarjeton) 
	{
		if (tarjeton != null) 
		{
			if (tarjetones.size() <= 3) 
			{
				this.tarjetones.add(tarjeton);
				this.esControlado = true;
			}
			else
				throw new IllegalStateException("No se pueden agregar más tarjetones. Límite alcanzado.");
		}
		else        
			throw new IllegalArgumentException("El tarjetón no puede ser nulo");
	}


	public ArrayList<Tarjeton> obtenerTarjetones() 
	{
		return new ArrayList<Tarjeton>(tarjetones);
	}

	public boolean removerTarjeton(Tarjeton tarjeton) 
	{
		return this.tarjetones.remove(tarjeton);
	}


	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		if(Validaciones.noEstaVacio(nombre))
			if(Validaciones.noTieneNumeros(nombre))
				if(Validaciones.noTieneCaracteresEsp(nombre))
					if(Validaciones.esMayusculaLaPrimeraLetra(nombre))
						this.nombre = nombre;
					else
						throw new IllegalArgumentException("El campo: nombre del paciente, presenta minúscula en el primer caracter");
				else
					throw new IllegalArgumentException("El campo: nombre del paciente, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre del paciente, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre del paciente, se encuentra vacío");
	}

	public String getCi() 
	{
		return ci;
	}

	public void setCi(String ci) 
	{
		if(Validaciones.validarCI(ci))
			{
				this.ci = ci;
			}
		else
			throw new IllegalArgumentException("El campo: carnet de indentidad, se encuentra vacío" + ci);
	}
	

	

	public void setDireccion(String direccion) 
	{
		direccion2 = direccion;
		if (direccion2 != null || direccion2.trim().isEmpty()) 
			if(Validaciones.direccion(direccion2))
				this.direccion = direccion2;
			else
				throw new IllegalArgumentException("La dirección presenta carcteres especiales");
		else 
			throw new IllegalArgumentException("La dirección del paciente no puede estar vacía");
	}

	public Date getFechaNacimiento() 
	{
		return fechaNacimiento;
	}



	public char getGenero() 
	{
		return genero;
	}

	public void setGenero(char genero) 
	{
		if (genero == 'M' || genero == 'm' || genero == 'F' || genero == 'f') 
			this.genero = Character.toUpperCase(genero);
		else
			throw new IllegalArgumentException("Género inválido: debe ser M o F");
	}

	public NucleoFamiliar getNucleo() 
	{
		return nucleo;
	}
	public String getDireccion() 
	{
		return direccion;
	}
	
	public void setNucleo(NucleoFamiliar nucleo) 
	{
		//if(nucleo != null)
		//{
		//	nucleo.setCompraron(nucleo.getCompraron());
		//	nucleo.setDireccion(nucleo.getDireccion());
		//	nucleo.setHombres(nucleo.getHombres());
		//	nucleo.setMujeres(nucleo.getMujeres());
		//	nucleo.setId(nucleo.getId());
		//	nucleo.setJefe(nucleo.getJefe());
		this.nucleo = nucleo;
		//}
		//else 
		//	throw new IllegalArgumentException("El campo: núcleo del paciente se encuentra vacío");
	}

	public boolean esControlado() 
	{
		return esControlado;
	}

	public boolean perteneceANucleo() 
	{
		return nucleo != null;
	}

	@Override
	public String toString() 
	{
		return "Paciente{" +
				"nombre='" + nombre + '\'' +
				", ci='" + ci + '\'' +
				", genero=" + genero +
				'}';
	}
}