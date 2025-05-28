package Logica;
import java.util.ArrayList;
import Interfaces_Enum.Facturar;
import Interfaces_Enum.Reportes;

public class Farmacia implements Reportes,Facturar{
	private ArrayList <Medicamento> inventario;
	private ArrayList <Venta> historialVentas;
	private ArrayList <NucleoFamiliar> nucleos;
	private ArrayList<Tarjeton> tarjetones;
	
	public void registrarMedicamento(){
		//implementar
	}
	
	public void procesarVenta(){
		//implementar
	}
	
	public void generarReporte(){
		//implementar
	}
	
	public ArrayList<String> medicamentosMasVendidos(){
		//implementar
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
	
}
