package Logica;
import java.util.Date;
import java.util.ArrayList;

public class VentaLibre extends Venta 
{
	private ArrayList <Medicamento> inventario;
	
	public VentaLibre(Date fechaVenta, double importeTotal, ArrayList <Medicamento> inventario) 
	{
		super((java.sql.Date) fechaVenta, importeTotal);
		setInventario(inventario);
	}
	
	public ArrayList<Medicamento> getInventario() 
	{
		return inventario;
	}



	public void setInventario(ArrayList<Medicamento> inventario) 
	{
		if(inventario != null)
			this.inventario = inventario;
		else
			throw new IllegalArgumentException("El campo: nombre del medicamento, se encuentra vacío");
	}
}
