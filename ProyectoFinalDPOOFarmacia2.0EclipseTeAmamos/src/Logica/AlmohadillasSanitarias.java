package Logica;


import java.util.Date;
import Interfaces_Enum.Facturar;
import LogicaUtiles.Validaciones;

public class AlmohadillasSanitarias extends Venta 
{
	private double precioUnit;
	private int cant;
	
	public AlmohadillasSanitarias(double precioUnit, int cant,Date fechaVenta)
	{
		super(fechaVenta, cant * precioUnit);
		setPrecioUnit(precioUnit);
		setCant(cant);
	}
	
	
	public double getPrecioUnit() 
	{
		return precioUnit;
	}


	public void setPrecioUnit (double precioUnit) throws IllegalArgumentException
	{
		
		if(Validaciones.noEstaVacio(precioUnit))
			if(!Validaciones.esUnaLetra(precioUnit))
				if(Validaciones.noTieneCaracteresEsp(precioUnit))
					this.precioUnit = precioUnit;
				else
					throw new IllegalArgumentException("El campo: precio, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: precio, presenta letras");
		else
			throw new IllegalArgumentException("El campo: precio, se encuentra vacío");
		
	}


	public int getCant() 
	{
		return cant;
	}


	public void setCant(int cant) 
	{
		
		this.cant =  cant;
	}


	public boolean verificarCompraMensual(NucleoFamiliar nucleo)
	{
		
		return true;
	}
	
	
	
	
	
}
