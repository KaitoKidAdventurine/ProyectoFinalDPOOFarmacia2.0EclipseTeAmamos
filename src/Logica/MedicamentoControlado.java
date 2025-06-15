package Logica;

public class MedicamentoControlado extends Medicamento
{
	private String patologia;
	private long cantAsigMensual;
	private long cantDispensadaMensual;

	public String getPatologia() 
	{
		return patologia;
	}

	public long getCantAsigMensual() 
	{
		return cantAsigMensual;
	}

	public long getCantDispensadaMensual() 
	{
		return cantDispensadaMensual;
	}


	
	public void setPatologia(String patologia) 
	{

		if(Validaciones.noEstaVacio(patologia))
			if(Validaciones.tieneNumeros(patologia))
				this.patologia = patologia;
			else
				throw new IllegalArgumentException("Presenta numeros la patologia");
		else
			throw new IllegalArgumentException("La patologia se encuentra vacía");
	}


	
	public void setCantAsigMensual(long cantAsigMensual) 
	{
		if(Validaciones.nuloOVacioNum(cantAsigMensual))
			if(Validaciones.tieneNumeros(cantAsigMensual))
				this.cantAsigMensual = cantAsigMensual;
			else
				throw new IllegalArgumentException("El campo: cantidad asignada mensual, presenta letras");
		else
			throw new IllegalArgumentException("El campo: cantidad asignada mensual se encuentra vacío");
	}



	public void setCantDispensadaMensual(long cantDispensadaMensual) 
	{
		if(Validaciones.nuloOVacioNum(cantDispensadaMensual))
			if(Validaciones.tieneNumeros(cantDispensadaMensual))
				this.cantDispensadaMensual = cantDispensadaMensual;
			else
				throw new IllegalArgumentException("El campo: cantidad dispensada mensual, Presenta letras");
		else
			throw new IllegalArgumentException("El campo: cantidad asignada mensual, se encuentra vacío");
	}



}
