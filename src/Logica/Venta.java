package Logica;
import Interfaces_Enum.Facturar;
import java.sql.Date;

public abstract class Venta implements Facturar{
	protected Date fechaVenta;
	protected double importeTotal;
	
	
	public String generarFactura(){
		
	}
	
	public double calcularTotal(){
		
	}
	
	public Date getFechaVenta() 
	{
		return fechaVenta;
	}
	
	public double getImporteTotal() 
	{
		return importeTotal;
	}
	
	public void setFechaVenta(Date fechaVenta) 
	{
		this.fechaVenta = fechaVenta;
	}
	
	
	public void setImporteTotal(double importeTotal) 
	{
		this.importeTotal = importeTotal;
	}
	
}
