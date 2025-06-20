package Logica;

public class VentaDeMedicamentos
{
	private String  nombre;
	private int cantidadVendida;

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		if(Validaciones.noEstaVacio(nombre))
			if(Validaciones.noTieneNumeros(nombre))
				if(Validaciones.noTieneCaracteresEsp(nombre))
					this.nombre = nombre;
				else
					throw new IllegalArgumentException("El campo: nombre del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre del medicamento, se encuentra vacío");
	}

	public int getCantidadVendida() 
	{
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) 
	{
		
		this.cantidadVendida = cantidadVendida;
	}
}
