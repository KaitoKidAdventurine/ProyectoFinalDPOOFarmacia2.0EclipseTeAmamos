package Logica;
import java.util.Date;

import Interfaces_Enum.ValidacionBool;
import LogicaUtiles.Validaciones;
import Interfaces_Enum.ValidacionBool;
import LogicaUtiles.Validaciones;

public class VentaConPrescripcion extends Venta
{
	private Date fechaDeCompra;



	public VentaConPrescripcion(Date fechaVenta, double importeTotal, Date fechaDeCompraDos)
	{ 
		super((java.util.Date)fechaVenta, importeTotal);
		setFechaDeCompra((java.util.Date)fechaDeCompraDos);
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
