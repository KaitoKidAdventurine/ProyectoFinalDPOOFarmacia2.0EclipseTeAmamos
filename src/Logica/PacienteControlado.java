package Logica;
import java.util.ArrayList;

public class PacienteControlado extends Paciente {
	private String patologia;
	private ArrayList<Tarjeton> tarjetones;
	
	public String getPatologia() 
	{
		return patologia;
	}

	public void setPatologia(String patologia) 
	{
		this.patologia = patologia;
	}

	public ArrayList<Tarjeton> getTarjetones() 
	{
		return tarjetones;
	}

	public void setTarjetones(ArrayList<Tarjeton> tarjetones) 
	{
		this.tarjetones = tarjetones;
	}

	public void agregarPatologia()
	{
		
	}
}
