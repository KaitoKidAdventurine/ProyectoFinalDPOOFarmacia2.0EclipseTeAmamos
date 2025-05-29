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
	
	public long cantDeAlmohadillasNecesarias(){
		//implementar
	}
	
	public double comparacionDeVentasMensuales(){
		//implementar
	}
	
	public ArrayList<Tarjeton> registroDeIncumplimiento(){
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
