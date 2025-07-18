
package LogicaUtiles;


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
				throw new IllegalArgumentException("El campo: nombre del medicamento, presenta n�meros");
		else
			throw new IllegalArgumentException("El campo: nombre del medicamento, se encuentra vac�o");
	}

	public int getCantidadVendida() 
	{
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) 
	{
		if(Validaciones.noEstaVacio(cantidadVendida))
			this.cantidadVendida = cantidadVendida;
		else
			throw new IllegalArgumentException("El campo: cantidad de medicamentos, se encuentra vac�o");
	}
}

