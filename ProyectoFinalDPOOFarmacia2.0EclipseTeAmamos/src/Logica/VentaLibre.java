package Logica;
import java.util.Date;
import java.util.ArrayList;

public class VentaLibre extends Venta 
{
	private String nombreDelMed;
	private String codigoDelMed;
	private int cantMedVendidos;
	public VentaLibre(Date fechaVenta, double importeTotal,String nombreDelMed,String codigoDelMed, int cantMedVendidos) 
	{
		super((java.util.Date) fechaVenta, importeTotal);
		setCantMedVendidos(cantMedVendidos);
		setCodigoDelMed(codigoDelMed) ;
		setNombreDelMed(nombreDelMed);
	}
	public String getNombreDelMed() 
	{
		return nombreDelMed;
	}
	public void setNombreDelMed(String nombreDelMed) 
	{
		this.nombreDelMed = nombreDelMed;
	}
	public String getCodigoDelMed() 
	{
		return codigoDelMed;
	}
	public void setCodigoDelMed(String codigoDelMed) 
	{
		this.codigoDelMed = codigoDelMed;
	}
	public int getCantMedVendidos() 
	{
		return cantMedVendidos;
	}
	public void setCantMedVendidos(int cantMedVendidos) 
	{
		this.cantMedVendidos = cantMedVendidos;
	}
	
}
