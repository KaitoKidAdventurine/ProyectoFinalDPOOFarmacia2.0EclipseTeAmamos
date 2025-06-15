package Logica;
import java.sql.Date;
import java.util.ArrayList;

import proyecto.Factura;
import proyecto.Medicamento;
import proyecto.VentaDeMedicamentos;
import Interfaces_Enum.Facturar;
import Interfaces_Enum.Reportes;

public class Farmacia implements Reportes,Facturar{
	private ArrayList<Medicamento> medicamentos;
	private ArrayList <Venta> historialVentas;
	private ArrayList <NucleoFamiliar> nucleos;
	private ArrayList<Tarjeton> tarjetones;
	private ArrayList<Factura> facturas;


	public void registrarMedicamento(){
		//implementar
	}

	public void procesarVenta(){
		//implementar
	}

	public void generarReporte(){
		//implementar
	}

	public ArrayList<VentaDeMedicamentos> medicamentosMasVendidos()
	{
		ArrayList<VentaDeMedicamentos> medMasVendidos = buscarOrdenDeAccion();
		return medMasVendidos;
	}

	public long cantDeAlmohadillasNecesarias()
	{
		long cantidad = calcularCantidad();
		return cantidad;
		
	}

	
	
	
	// tercer reporte
	
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

	@Override 

	// metodos del primer reporte 
	public ArrayList<VentaDeMedicamentos> buscarOrdenDeAccion()
	{

		ArrayList<String> nombres = new ArrayList<String>();
		ArrayList<Integer> cantidad = new ArrayList<Integer>();
		for(Medicamento m: medicamentos)
		{
			int totalVendidoDelMedicamento = 0;
			for(Factura f: facturas)
				if(m.nomComun.equals(f.getNombreDelMed()))
					totalVendidoDelMedicamento += f.getCantMedVendidos();

			if(totalVendidoDelMedicamento > 0)
			{
				nombres.add(m.nomComun);
				cantidad.add(totalVendidoDelMedicamento);
			}
		}
		ArrayList<VentaDeMedicamentos> ventas =transformarYOrdenar(nombres, cantidad);
		return ventas;
	}

	public ArrayList<VentaDeMedicamentos> transformarYOrdenar(ArrayList<String> nombres, ArrayList<Integer> cantidad) 
	{
		ArrayList<VentaDeMedicamentos> ventasOrdenadas = new ArrayList<VentaDeMedicamentos>();

		for(int i = 0; i< nombres.size(); i++)
		{
			for(int j = 0; j < nombres.size() -1 ; j++)
			{
				if(cantidad.get(j) < cantidad.get(j+1) )
				{
					int temp = cantidad.get(j);
					cantidad.set(j, cantidad.get(j+1));
					cantidad.set(j+1, temp);

					String temporal = nombres.get(j);
					nombres.set(j, nombres.get(j+1));
					nombres.set(j+1, temporal);
				}
			}
		}

		for(int i = 0; i< nombres.size(); i++)
		{
			VentaDeMedicamentos ventas = new VentaDeMedicamentos();
			ventas.setCantidadVendida(cantidad.get(i));
			ventas.setNombre(nombres.get(i));
			ventasOrdenadas.add(ventas);
		}

		return ventasOrdenadas;
	}


	// metodos del segundo reporte
	
	public long calcularCantidad()
	{
		long cantidad = 0;
		
		for(NucleoFamiliar n: nucleos)
		{
			cantidad += n.getMujeres().size();
		}
		
		// arreglar el tipo de dato
		
		if(cantidad <= getCant())
			cantidad = getCant() - cantidad;
		else
			cantidad = getCant() - cantidad;
		
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
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas, Prescripción y Controlados (iguales)");
		}

		else if (porcentajeAlmohadillas == porcentajePrescripcion &&
				porcentajePrescripcion == porcentajeVentaLibre &&
				porcentajeVentaLibre > porcentajeControlados) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas, Prescripción y Venta Libre (iguales)");
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
			Porcentaje resultado = new Porcentaje(porcentajePrescripcion, "Prescripción, Controlados y Venta Libre (iguales)");
		}

		else if (porcentajeAlmohadillas == porcentajePrescripcion &&
				porcentajeAlmohadillas > porcentajeControlados &&
				porcentajeAlmohadillas > porcentajeVentaLibre) 
		{
			Porcentaje resultado = new Porcentaje(porcentajeAlmohadillas, "Almohadillas y Prescripción (iguales)");
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
			Porcentaje resultado = new Porcentaje(porcentajePrescripcion, "Prescripción y Controlados (iguales)");
		}

		else if (porcentajePrescripcion == porcentajeVentaLibre &&
				porcentajePrescripcion > porcentajeAlmohadillas &&
				porcentajePrescripcion > porcentajeControlados) 
		{
			Porcentaje resultado = new Porcentaje(porcentajePrescripcion, "Prescripción y Venta Libre (iguales)");
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



}
