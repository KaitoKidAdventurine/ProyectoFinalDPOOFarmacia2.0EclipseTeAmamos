package Interfaces_Enum;

import java.sql.Date;

import Logica.Factura;

public interface Facturar 
{
	Factura generarFactura(String nombreDelMed, String codigoDelMed, int cantMedVendidos, Date fechaDeLaCompra);
	double calcularTotal();
}


