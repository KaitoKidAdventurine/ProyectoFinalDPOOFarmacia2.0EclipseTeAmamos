package Logica;
import Interfaces_Enum.Facturar;
import LogicaUtiles.Validaciones;

import java.util.Date;

public abstract class Venta
{
	protected Date fechaVenta;
	protected double importeTotal;
	protected String nombreDelMed;
	protected String codigoDelMed;
	protected int cantMedVendidos;
	
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
		if(Validaciones.noEstaVacio(fechaVenta))
			if(!Validaciones.noSePasaDeLaFechaDeHoy(fechaVenta))
				this.fechaVenta = fechaVenta;
			else
				throw new IllegalArgumentException("El campo: fecha de Vencimiento del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de la venta, se encuentra vacío");
	}
	
	
	public void setImporteTotal(double importeTotal) 
	{
		if(Validaciones.noEstaVacio(importeTotal))
			if(Validaciones.noTieneCaracteresEsp(importeTotal))
				if(!Validaciones.tieneNumeros(importeTotal))
					this.importeTotal = importeTotal;
				else
					throw new IllegalArgumentException("El campo: importe total de las almohadillas, presenta letras");
			else
				throw new IllegalArgumentException("El campo: importe total de las almohadillas, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: importe total de las almohadillas, se encuentra vacío");

		
	}
	
	
	public Venta(Date fechaVenta2, double importeTotal)
	{
		setFechaVenta(fechaVenta2);
		setImporteTotal(importeTotal);
	}	

}
