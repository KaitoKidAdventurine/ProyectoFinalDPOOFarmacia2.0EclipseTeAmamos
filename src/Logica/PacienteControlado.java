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
					throw new IllegalArgumentException("El campo: patolog�a, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: patolog�a, presenta n�meros");
		else
			throw new IllegalArgumentException("El campo: patolog�a, se encuentra vac�o");
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
			throw new IllegalArgumentException("El campo: los , se encuentra vac�o");
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
