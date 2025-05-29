package Logica;

public class Porcentaje 
{
	private double porcentaje;
	private String nombre;
	public double getPorcentaje() 
	{
		return porcentaje;
	}
	
	public void setPorcentaje(double porcentaje) 
	{
		this.porcentaje = porcentaje;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public Porcentaje(double porcentaje, String nombre)
	{
		setNombre(nombre);
		setPorcentaje(porcentaje);
	}
	
}
