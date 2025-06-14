package Logica;

package Logica;

import java.sql.Date;

import Interfaces_Enum.Facturar;

public class AlmohadillasSanitarias extends Venta implements Facturar 
{
	private double precioUnit;
	private int cant;
	
	public AlmohadillasSanitarias(double precioUnit, int cant,Date fechaVenta, double importeTotal)
	{
		super(fechaVenta, importeTotal);
		setPrecioUnit(precioUnit);
		setCant( cant);
	}
	
	
	public double getPrecioUnit() 
	{
		return precioUnit;
	}


	public void setPrecioUnit(double precioUnit) 
	{
		this.precioUnit = precioUnit;
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
	
	
	
	@Override
	public double calcularTotal(long  cant, double precio)
	{
		
		return 0.0;
	}

	@Override
	public Factura generarFactura(String nombreDelMed, String codigoDelMed,
			int cantMedVendidos, Date fechaDeLaCompra) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
