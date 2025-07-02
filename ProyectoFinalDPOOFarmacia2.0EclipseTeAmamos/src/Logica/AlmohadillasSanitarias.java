package Logica;


import java.util.Date;


public class AlmohadillasSanitarias extends Venta 
{
	private double precioUnit;
	private int cant;
	
	public AlmohadillasSanitarias(double precioUnit, int cant,Date fechaVenta)
	{
	    super(fechaVenta, precioUnit * cant);
	    if (precioUnit <= 0 || cant <= 0) {
	        throw new IllegalArgumentException("Precio y cantidad deben ser mayores que cero.");
	    }

	    this.precioUnit = precioUnit;
	    this.cant = cant;


	}
	
	
	public double getPrecioUnit() 
	{
		return precioUnit;
	}


	public void setPrecioUnit(double precioUnit) {
	    if (precioUnit <= 0) {
	        throw new IllegalArgumentException("El precio debe ser mayor que cero.");
	    }
	    this.precioUnit = precioUnit;
	}


	public int getCant() 
	{
		return cant;
	}


	public void setCant(int cant) {
	    if (cant <= 0) {
	        throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
	    }
	    this.cant = cant;
	}


	public boolean verificarCompraMensual(NucleoFamiliar nucleo)
	{
		
		return true;
	}
	
	
	
	
	
}
