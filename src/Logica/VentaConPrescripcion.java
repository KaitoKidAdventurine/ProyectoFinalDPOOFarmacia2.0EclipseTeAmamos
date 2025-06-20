package Logica;
import java.sql.Date;

import Interfaces_Enum.Validacion;

public class VentaConPrescripcion extends Venta implements Validacion{
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

	public boolean validarReceta()
	{
		
	}
}
