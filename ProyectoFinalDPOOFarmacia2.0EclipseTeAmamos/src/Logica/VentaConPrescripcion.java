package Logica;
import java.util.Date;

import Interfaces_Enum.ValidacionBool;
import LogicaUtiles.Validaciones;
import Interfaces_Enum.ValidacionBool;
import LogicaUtiles.Validaciones;

public class VentaConPrescripcion extends Venta
{
	private Date fechaDeCompra;
	private String nombreDelMed;
	private String codigoDelMed;
	private int cantMedVendidos;


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

	public VentaConPrescripcion(Date fechaVenta, double importeTotal, Date fechaDeCompraDos,String nombreDelMed,String codigoDelMed, int cantMedVendidos)
	{ 
		super((java.util.Date)fechaVenta, importeTotal);
		setFechaDeCompra((java.util.Date)fechaDeCompraDos);
		setCantMedVendidos(cantMedVendidos);
		setCodigoDelMed(codigoDelMed) ;
		setNombreDelMed(nombreDelMed);
	}

	public Date getFechaDeCompra() 
	{
		return fechaDeCompra;
	}
	
	public void setFechaDeCompra(Date fechaDeCompra) 
	{
		
		if(Validaciones.noEstaVacio(fechaDeCompra))
			if(!Validaciones.noSePasaDeLaFechaDeHoy(fechaDeCompra))
				this.fechaDeCompra = fechaDeCompra;
			else
				throw new IllegalArgumentException("El campo: fecha de compra del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de la compra, se encuentra vacío");
		
	}
}
