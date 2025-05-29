package Logica;
import java.sql.Date;

import Interfaces_Enum.Validacion;

public class VentaControlada extends Venta implements Validacion
{
	
	public VentaControlada(Date fechaVenta, double importeTotal)
	{
		super(fechaVenta, importeTotal);
	}
	
	public boolean validarTarjeton()
	{
		
	}

	public Factura generarFactura(String nombreDelMed, String codigoDelMed,
			int cantMedVendidos, Date fechaDeLaCompra) {
		// TODO Auto-generated method stub
		return null;
	}

	public double calcularTotal(int cant, double precio) {
		// TODO Auto-generated method stub
		return 0;
	}
}
