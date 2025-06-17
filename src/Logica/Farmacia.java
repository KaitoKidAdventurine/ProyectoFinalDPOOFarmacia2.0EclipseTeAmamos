package Logica;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import proyecto.Factura;
import proyecto.Medicamento;
import proyecto.VentaDeMedicamentos;
import Interfaces_Enum.Facturar;
import Interfaces_Enum.GestionarStockAlmohadillasSanitarias;
import Interfaces_Enum.Reportes;
import LogicaUtiles.Porcentaje;

public class Farmacia implements Reportes,Facturar,GestionarStockAlmohadillasSanitarias 
{
	private ArrayList<Medicamento> medicamentos;
	private ArrayList <Venta> historialVentas;
	private ArrayList <NucleoFamiliar> nucleos;
	private ArrayList<Tarjeton> tarjetones;
	private ArrayList<Factura> facturas;
	private long cantidadDeAlmohadillasSanitarias;

	

	public void registrarMedicamento(){
		//implementar
	}

	public void procesarVenta(){
		//implementar
	}

	public void generarReporte(){
		//implementar
	}

	// Primer reporte
	public ArrayList<VentaDeMedicamentos> medicamentosMasVendidos()
	{
		ArrayList<VentaDeMedicamentos> medMasVendidos = buscarOrdenDeAccion();
		return medMasVendidos;
	}

	// Segundo reporte
	public long cantDeAlmohadillasNecesarias()
	{
		long cantidad = calcularCantidad();
		return cantidad;
		
	}

	
	
	
	// Tercer reporte
	
	public Porcentaje comparacionDeVentasMensuales()
	{
		Porcentaje resultado = calcularValor();
		return resultado ;
	}

	public ArrayList<Tarjeton> registroDeIncumplimiento()
	{
		//implementar
	}






	@Override
	public Factura generarFactura(String nombreDelMed, String codigoDelMed, int cantMedVendidos, Date fechaDeLaCompra) 
	{
		Factura facturacion = new Factura(nombreDelMed, codigoDelMed, cantMedVendidos, fechaDeLaCompra);
		return facturacion;
	}

	@Override
	public double calcularTotal() 
	{

		return 0;
	}

	

	// Metodos del primer reporte 
	public ArrayList<VentaDeMedicamentos> buscarOrdenDeAccion()
	{
		VentaDeMedicamentos venta;
		ArrayList<VentaDeMedicamentos> ventas = new ArrayList<VentaDeMedicamentos>();
		for(Medicamento m: medicamentos)
		{
			int totalVendidoDelMedicamento = 0;
			for(Factura f: facturas)
				if(m.nomComun.equals(f.getNombreDelMed()))
					totalVendidoDelMedicamento += f.getCantMedVendidos();

			if(totalVendidoDelMedicamento > 0)
			{
				venta.setCantidadVendida(totalVendidoDelMedicamento);
				venta.setNombre(m.nomComun);
				ventas.add(venta);
			}
		}
		// es como tener un merge sort
		
	    
	    Collections.sort(ventas, comparador);
		return ventas;
	}
	
	Comparator<VentaDeMedicamentos> comparador = new Comparator<VentaDeMedicamentos>() 
	{
		public int compare(VentaDeMedicamentos a, VentaDeMedicamentos b) 
		{
		     int cantidadB = b.getCantidadVendida();
		     int cantidadA = a.getCantidadVendida();
		     return cantidadB - cantidadA; 
		}
	};
	


	// metodos del segundo reporte
	
	public long calcularCantidad()
	{
		long cantidad = 0;
		
		for(NucleoFamiliar n: nucleos)
		{
			if(n.getCompraron() == false)
				cantidad += n.getMujeres().size();
		}
		
		cantidadDeAlmohadillasSanitarias -= cantidadDeAlmohadillas();
		
		// DANGER: realizar validacion de mayor que stock 
		cantidad -= cantidadDeAlmohadillasSanitarias;
		
		return cantidad;
	}
	
	
	
	
	
	

	// metodos del tercer reporte
	public Porcentaje calcularValor()
	{
		double importeTotalDeAlmohadillas = 0;
		double importeTotalDeMedPrescripcion = 0;
		double importeTotalDeMedControlados = 0;
		double importeTotalDeMedVentaLibre = 0;
		double total = 0;

		for(Venta h: historialVentas)
		{
			if(h instanceof AlmohadillasSanitarias)
			{
				AlmohadillasSanitarias almohadilla = (AlmohadillasSanitarias)h;
				importeTotalDeAlmohadillas += almohadilla.getImporteTotal();
			}

			else if(h instanceof VentaConPrescripcion)
			{
				VentaConPrescripcion prescripcion = (VentaConPrescripcion)h;
				importeTotalDeMedPrescripcion += prescripcion.getImporteTotal();
			}

			else if(h instanceof VentaControlada)
			{
				VentaControlada control = (VentaControlada)h;
				importeTotalDeMedControlados += control.getImporteTotal();
			}

			else if(h instanceof VentaLibre)
			{
				VentaLibre ventLib = (VentaLibre)h;
				importeTotalDeMedVentaLibre += ventLib.getImporteTotal();
			}
		}

		total = importeTotalDeAlmohadillas + importeTotalDeMedPrescripcion 
				+ importeTotalDeMedControlados + importeTotalDeMedVentaLibre;

		if(total != 0)
		{
			Porcentaje resultado = calcularPorcentaje(total, importeTotalDeAlmohadillas,
			importeTotalDeMedPrescripcion, importeTotalDeMedControlados, 
			importeTotalDeMedVentaLibre);
		}

		else
		{
			Porcentaje resultado = new Porcentaje(0, "No hubo ventas en la Farmacia");
		}

		return resultado;
	}
	
	
	

	public Porcentaje calcularPorcentaje(double total, double importeTotalDeAlmohadillas,
			double importeTotalDeMedPrescripcion, double importeTotalDeMedControlados, 
			double importeTotalDeMedVentaLibre)
	{
		Porcentaje resultado;
		double porcentajeAlmohadillas = (importeTotalDeAlmohadillas * 100) / total;
		double porcentajePrescripcion = (importeTotalDeMedPrescripcion * 100) / total;
		double porcentajeControlados = (importeTotalDeMedControlados * 100) / total;
		double porcentajeVentaLibre = (importeTotalDeMedVentaLibre * 100) / total;

		resultado = comparacion(porcentajeAlmohadillas, porcentajePrescripcion,
				porcentajeControlados, porcentajeVentaLibre);

		return resultado;
	}

	public Porcentaje comparacion(double porcentajeAlmohadillas, double porcentajePrescripcion,
			double porcentajeControlados, double porcentajeVentaLibre)
	{
		if (porcentajeAlmohadillas == porcentajePrescripcion &&
				porcentajePrescripcion == porcentajeControlados &&
				porcentajeControlados == porcentajeVentaLibre) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Todos son iguales");
		}
		
		else if (porcentajeAlmohadillas == porcentajePrescripcion &&
				porcentajePrescripcion == porcentajeControlados &&
				porcentajeControlados > porcentajeVentaLibre) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas, Prescripci�n y Controlados (iguales)");
		}

		else if (porcentajeAlmohadillas == porcentajePrescripcion &&
				porcentajePrescripcion == porcentajeVentaLibre &&
				porcentajeVentaLibre > porcentajeControlados) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas, Prescripci�n y Venta Libre (iguales)");
		}

		else if (porcentajeAlmohadillas == porcentajeControlados &&
				porcentajeControlados == porcentajeVentaLibre &&
				porcentajeVentaLibre > porcentajePrescripcion) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas, Controlados y Venta Libre (iguales)");
		}

		else if (porcentajePrescripcion == porcentajeControlados &&
				porcentajeControlados == porcentajeVentaLibre &&
				porcentajeVentaLibre > porcentajeAlmohadillas) 
		{
			Porcentaje resultado = new Porcentaje(porcentajePrescripcion, "Prescripci�n, Controlados y Venta Libre (iguales)");
		}

		else if (porcentajeAlmohadillas == porcentajePrescripcion &&
				porcentajeAlmohadillas > porcentajeControlados &&
				porcentajeAlmohadillas > porcentajeVentaLibre) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas y Prescripci�n (iguales)");
		}

		else if (porcentajeAlmohadillas == porcentajeControlados &&
				porcentajeAlmohadillas > porcentajePrescripcion &&
				porcentajeAlmohadillas > porcentajeVentaLibre) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas y Controlados (iguales)");
		}

		else if (porcentajeAlmohadillas == porcentajeVentaLibre &&
				porcentajeAlmohadillas > porcentajePrescripcion &&
				porcentajeAlmohadillas > porcentajeControlados) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas y Venta Libre (iguales)");
		}

		else if (porcentajePrescripcion == porcentajeControlados &&
				porcentajePrescripcion > porcentajeAlmohadillas &&
				porcentajePrescripcion > porcentajeVentaLibre) 
		{
			Porcentaje resultado = new Porcentaje(porcentajePrescripcion, "Prescripci�n y Controlados (iguales)");
		}

		else if (porcentajePrescripcion == porcentajeVentaLibre &&
				porcentajePrescripcion > porcentajeAlmohadillas &&
				porcentajePrescripcion > porcentajeControlados) 
		{
			Porcentaje resultado = new Porcentaje(porcentajePrescripcion, "Prescripci�n y Venta Libre (iguales)");
		}

		else if (porcentajeControlados == porcentajeVentaLibre &&
				porcentajeControlados > porcentajeAlmohadillas &&
				porcentajeControlados > porcentajePrescripcion) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeControlados, "Controlados y Venta Libre (iguales)");
		}
		
		else if(porcentajeAlmohadillas > porcentajePrescripcion &&
				porcentajeAlmohadillas >porcentajeControlados &&
				porcentajeAlmohadillas > porcentajeVentaLibre)
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas");			


		else if(porcentajePrescripcion > porcentajeAlmohadillas &&
				porcentajePrescripcion > porcentajeControlados &&
				porcentajePrescripcion > porcentajeVentaLibre)
			Porcentaje resultado = new Porcentaje(porcentajePrescripcion, "Medicamento por Prescripcion");

		else if(porcentajeControlados > porcentajeAlmohadillas && 
				porcentajeControlados > porcentajePrescripcion && 
				porcentajeControlados > porcentajeVentaLibre)
			Porcentaje resultado = new Porcentaje(porcentajeControlados, "Medicamento Controlado");

		else if(porcentajeVentaLibre > porcentajeAlmohadillas && 
				porcentajeVentaLibre > porcentajePrescripcion && 
				porcentajeVentaLibre >  porcentajeControlados porcentajeVentaLibre)
			Porcentaje resultado = new Porcentaje(porcentajeControlados, "Medicamento Controlado");

		return resultado;
		
	}

	public ArrayList<Factura> getFacturas() 
	{
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) 
	{
		this.facturas = facturas;
	}

	public ArrayList<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	
	public long getCantidadDeAlmohadillasSanitarias() 
	{
		return cantidadDeAlmohadillasSanitarias;
	}

	public void setCantidadDeAlmohadillasSanitarias(long cantidadDeAlmohadillasSanitarias) 
	{
		this.cantidadDeAlmohadillasSanitarias = cantidadDeAlmohadillasSanitarias;
	}
	
	public long cantidadDeAlmohadillas()
	{
		long cantidad = 0;
		for(Venta v: historialVentas)
			if(v instanceof AlmohadillasSanitarias)
				cantidad += ((AlmohadillasSanitarias) v).getCant();
		
		return cantidad;
			
	}

}
