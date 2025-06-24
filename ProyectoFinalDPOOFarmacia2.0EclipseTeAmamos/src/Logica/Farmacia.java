package Logica;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import LogicaUtiles.Porcentaje;
import LogicaUtiles.Factura;
import Logica.Medicamento;
import LogicaUtiles.VentaDeMedicamentos;
import Interfaces_Enum.Facturar;
import Interfaces_Enum.GestionarStockAlmohadillasSanitarias;
import Interfaces_Enum.Reportes;

public class Farmacia implements Reportes,Facturar,GestionarStockAlmohadillasSanitarias 
{
	//Instancia única (Singleton)
    private static Farmacia instancia;
	
	private ArrayList<Medicamento> medicamentos;
	private ArrayList <Venta> historialVentas;
	private ArrayList <NucleoFamiliar> nucleos;
	private ArrayList<Tarjeton> tarjetones;
	private ArrayList<Factura> facturas;
	private long cantidadDeAlmohadillasSanitarias;


    // Constructor PRIVADO
    private Farmacia() {
        // Inicializar todas las listas
        this.medicamentos = new ArrayList<Medicamento>();
        this.historialVentas = new ArrayList<Venta>();
        this.nucleos = new ArrayList<NucleoFamiliar>();
        this.tarjetones = new ArrayList<Tarjeton>();
        this.facturas = new ArrayList<Factura>();
        this.cantidadDeAlmohadillasSanitarias = 0;
        
        // Aquí puedes cargar datos iniciales si es necesario
    }
    
    // Método para obtener la instancia (Singleton)
    public static Farmacia obtenerInstancia() {
        if (instancia == null) {
            instancia = new Farmacia();
        }
        return instancia;
    }

	public void registrarMedicamento()
	{
		//implementar
	}

	public void procesarVenta()
	{
		//implementar
	}

	public void generarReporte()
	{
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

	// Tercer Reporte
	
	public Porcentaje comparacionDeVentasMensuales()
	{
		Porcentaje resultado = calcularValor();
		return resultado;
	}
	
	// Cuarto Reporte
	public ArrayList<Tarjeton> registroDeIncumplimiento()
	{
		ArrayList<Tarjeton> tarjetonesNoActivos = tarjetonesDesactivados();
		return tarjetonesNoActivos;
	}
	
	public Factura generarFactura(String nombreDelMed, String codigoDelMed, int cantMedVendidos, Date fechaDeLaCompra) 
	{
		Factura facturacion = new Factura(nombreDelMed, codigoDelMed, cantMedVendidos, fechaDeLaCompra);
		return facturacion;
	}
	
	// Metodos del primer reporte 
	public ArrayList<VentaDeMedicamentos> buscarOrdenDeAccion()
	{
		VentaDeMedicamentos venta = null;
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
		
		//  es como tener un merge sort
	    
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
		double importeTotalDeAlmohadillas = cantDeDineroObtenidoPorAlmohadillas();;
		double importeTotalDeMedPrescripcion = cantDeDineroObtenidoPorMedPrescripcion();
		double importeTotalDeMedControlados = cantDeDineroObtenidoPorMedControlados();
		double importeTotalDeMedVentaLibre = cantDeDineroObtenidoPorMedVentaLibre();
		double total = 0;
		Porcentaje resultado;
		total = calcularTotal();

		if(total != 0)
		{
			resultado = calcularPorcentaje(total, importeTotalDeAlmohadillas,
			importeTotalDeMedPrescripcion, importeTotalDeMedControlados, 
			importeTotalDeMedVentaLibre);
		}

		else
		{
			resultado = new Porcentaje(0, "No hubo ventas en la Farmacia");
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
		// busco el valor máximo los porcentajes
		
		double maximo = valorMaximo(porcentajeAlmohadillas, porcentajePrescripcion
				,porcentajeControlados, porcentajeVentaLibre);
		
		// busco cuantos valores tienen ese valor
		
		int contador = repeticiones(maximo, porcentajeAlmohadillas, porcentajePrescripcion,
				porcentajeControlados, porcentajeVentaLibre);
		
		// reviso si se repite ese contador entonces se revisa cuales
		// son los valores que hay que introducir al String
		
		String nombres = revisionDeRepeticiones(contador ,maximo, porcentajeAlmohadillas, porcentajePrescripcion,
				porcentajeControlados, porcentajeVentaLibre);
		
		Porcentaje valores = new Porcentaje(maximo,nombres);
		
		return valores;
		
	}

	public double valorMaximo(double almo, double pres, double cont, double libr)
	{
		return Math.max(Math.max(almo, pres), Math.max(cont, libr));
	}
	
	public int repeticiones(double max,double alm, double pres,
			double contro, double libr)
	{
		int contador = 0;
		
		if(max == alm)
			contador++;
		
		if(max == pres)
			contador++;
		
		if(max == contro)
			contador++;
		
		if(max == libr)
			contador++;
		
		return contador;
	}
	
	public String revisionDeRepeticiones(int cont,double max,double alm, double pres,
			double contro, double libr)
	{
		String nombres = null;
		switch(cont)
		{
			case 4:
			{
				nombres = "Almohadillas, Medicamento por Prescripción, Controlado y Libre";
				break;				
			}
			
			case 3:
			
				{
					if(max != alm)
						nombres = "Medicamento por Prescripción, Controlado y Libre";
					else if(max != pres)
						nombres = "Almohadillas, Medicamento Controlado y Libre";
					else if(max != contro)
						nombres = "Almohadillas, Medicamento por Prescripción y Libre";
					else if(max != libr)
						nombres = "Almohadillas, Medicamento por Prescripción y Controlado";
					break;
				}
			case 2:
			{
				if (alm == max && pres == max)  
					nombres = "Almohadillas y Prescripción";
				
				else if (alm == max && contro == max)  
					nombres = "Almohadillas y Controlados";
				
				else if (alm == max && libr == max)  
					nombres = "Almohadillas y Venta Libre";
				
				else if (pres == max && contro == max)  
					nombres = "Prescripción y Controlados ";
				
				else if (pres == max && libr == max)  
					nombres = "Prescripción y Venta Libre";
				break;
			}
			
			case 1:
			{
				if(max == alm)
					nombres = "Almohadillas";
				else if(max == pres)
					nombres = "Medicamento con Prescripción";
				else if(max == contro)
					nombres = "Medicamento Controlado";
				else if(max == libr)
					nombres = "Medicamento Libre";
				break;
			}
			
			default:
			{
				nombres = "Error encontrado en el contador, tercer reporte, segundo metodo.";
				break;
			}
			
		}
		return nombres;
	}
	
	
	
	// metodos del cuarto reporte
	
	public ArrayList<Tarjeton> tarjetonesDesactivados()
	{
		ArrayList<Tarjeton> tarjetonesInactivos = new ArrayList<Tarjeton>();
		for(Tarjeton t: tarjetones)
			if(!t.validacion(t.getFechaExpedicion(), t.getFechaVencimiento()))
				tarjetonesInactivos.add(t);
		
		return tarjetonesInactivos;
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

	
	
	public double calcularTotal() 
	{
		double total = 0;
		total += cantDeDineroObtenidoPorAlmohadillas();
		total += cantDeDineroObtenidoPorMedPrescripcion();
		total += cantDeDineroObtenidoPorMedControlados();
		total +=cantDeDineroObtenidoPorMedVentaLibre();
		return total;
	}

	public double cantDeDineroObtenidoPorAlmohadillas()
	{
		double importeTotalDeAlmohadillas = 0;
		for(Venta h: historialVentas)
		{
			if(h instanceof AlmohadillasSanitarias)
			{
				AlmohadillasSanitarias almohadilla = (AlmohadillasSanitarias)h;
					importeTotalDeAlmohadillas += almohadilla.getImporteTotal();
			}
		}
		return importeTotalDeAlmohadillas;
	}
	
	public double cantDeDineroObtenidoPorMedPrescripcion()
	{
		double importeTotalDeMedPrescripcion = 0;
		for(Venta h: historialVentas)
		{
			if(h instanceof VentaConPrescripcion)
			{
				VentaConPrescripcion prescripcion = (VentaConPrescripcion)h;
					importeTotalDeMedPrescripcion += prescripcion.getImporteTotal();
			}
		}
		return importeTotalDeMedPrescripcion ;
	}
	
	public double cantDeDineroObtenidoPorMedControlados()
	{
		double importeTotalDeMedControlados = 0;
		for(Venta h: historialVentas)
		{
			
			if(h instanceof VentaControlada)
			{
				VentaControlada control = (VentaControlada)h;
				importeTotalDeMedControlados += control.getImporteTotal();
			}
		}
		return importeTotalDeMedControlados;
	}
	
	public double cantDeDineroObtenidoPorMedVentaLibre()
	{
		double importeTotalDeMedVentaLibre = 0;
		for(Venta h: historialVentas)
		{
			if(h instanceof VentaLibre)
			{
				VentaLibre ventLib = (VentaLibre)h;
				importeTotalDeMedVentaLibre += ventLib.getImporteTotal();
			}
		}
		return importeTotalDeMedVentaLibre;
	}
	
	
	public ArrayList<Venta> getHistorialVentas() {
		return historialVentas;
	}

	public void setHistorialVentas(ArrayList<Venta> historialVentas) {
		this.historialVentas = historialVentas;
	}

	public ArrayList<NucleoFamiliar> getNucleos() {
		return nucleos;
	}

	public void setNucleos(ArrayList<NucleoFamiliar> nucleos) {
		this.nucleos = nucleos;
	}

	public ArrayList<Tarjeton> getTarjetones() {
		return tarjetones;
	}

	public void setTarjetones(ArrayList<Tarjeton> tarjetones) {
		this.tarjetones = tarjetones;
	}

	public Comparator<VentaDeMedicamentos> getComparador() {
		return comparador;
	}

	public void setComparador(Comparator<VentaDeMedicamentos> comparador) {
		this.comparador = comparador;
	}
}
