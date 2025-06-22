package Logica;
import java.util.ArrayList;

import LogicaUtiles.Validaciones;

public class PacienteControlado extends Paciente 
{
	private String patologia;
	private ArrayList<Tarjeton> tarjetones;
	
	public String getPatologia() 
	{
		return patologia;
	}

	public void setPatologia(String patologia) 
	{
		if(Validaciones.noEstaVacio(patologia))
			if(Validaciones.noTieneNumeros(patologia))
				if(Validaciones.noTieneCaracteresEsp(patologia))
					this.patologia = patologia;
				else
					throw new IllegalArgumentException("El campo: patología, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: patología, presenta números");
		else
			throw new IllegalArgumentException("El campo: patología, se encuentra vacío");
	}

	public ArrayList<Tarjeton> getTarjetones() 
	{
		return tarjetones;
	}
	
	
	public void setTarjetones(ArrayList<Tarjeton> tarjetones) 
	{
		if(Validaciones.noEstanVacios(tarjetones))
			this.tarjetones = tarjetones;
		else
			throw new IllegalArgumentException("El campo: los , se encuentra vacío");
	}

	public void agregarPatologia()
	{
		
	}
	
	public ArrayList<Tarjeton> obtenerTarjetones()
	{
		return getTarjetones();	
	}
	
	public void agregarTarjeton()
	{
		
	}
}
