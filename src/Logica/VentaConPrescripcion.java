package Logica;
import java.sql.Date;

import Interfaces_Enum.ValidacionBool;
import LogicaUtiles.Validaciones;

public class VentaConPrescripcion extends Venta implements ValidacionBool
{
	private Date fechaDeCompra;

	public Date getFechaDeCompra() 
	{
		return fechaDeCompra;
	}

	public void setFechaDeCompra(Date fechaDeCompra) 
	{
		
		if(Validaciones.noEstaVacio(fechaDeCompra))
			if(Validaciones.noSePasaDeLaFechaDeHoy(fechaDeCompra))
				this.fechaDeCompra = fechaDeCompra;
			else
				throw new IllegalArgumentException("El campo: fecha de compra del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de la compra, se encuentra vacío");
		
	}

	public VentaConPrescripcion(Date fechaVenta, double importeTotal)
	{
		super(fechaVenta, importeTotal);
		setFechaDeCompra(fechaDeCompra);
	}
	
	
	public boolean validacion(Date fechaExpedicion, Date fechaVencimiento) {
		// TODO Auto-generated method stub
		return false;
	}
}
