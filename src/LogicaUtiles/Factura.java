package LogicaUtiles;

import java.sql.Date;

public class Factura 
{
	private String nombreDelMed;
	private String codigoDelMed;
	private int cantMedVendidos;
	private Date fechaDeLaCompra;
	
	
	
	public String getNombreDelMed() 
	{
		return nombreDelMed;
	}
	
	public void setNombreDelMed(String nombreDelMed) 
	{
		if(Validaciones.noEstaVacio(nombreDelMed))
			if(Validaciones.noTieneNumeros(nombreDelMed))
				if(Validaciones.noTieneCaracteresEsp(nombreDelMed))
					this.nombreDelMed = nombreDelMed;
				else
					throw new IllegalArgumentException("El campo: nombre comun del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre comun del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre comun del medicamento, se encuentra vacío");
	
		
	}
	
	public String getCodigoDelMed() 
	{
		return codigoDelMed;
	}
	
	public void setCodigoDelMed(String codigoDelMed) 
	{
		
		
		
		this.codigoDelMed = codigoDelMed;
	}
	
	public int getCantMedVendidos() 
	{
		return cantMedVendidos;
	}
	
	public void setCantMedVendidos(int cantMedVendidos) 
	{
		this.cantMedVendidos = cantMedVendidos;
	}
	
	public Date getFechaDeLaCompra() 
	{
		return fechaDeLaCompra;
	}
	public void setFechaDeLaCompra(Date fechaDeLaCompra) {
		this.fechaDeLaCompra = fechaDeLaCompra;
	}

	public Factura(String nombreDelMed, String codigoDelMed, int cantMedVendidos, Date fechaDeLaCompra) 
	{
        setNombreDelMed(nombreDelMed);
        setCodigoDelMed(codigoDelMed);
        setCantMedVendidos(cantMedVendidos);
        setFechaDeLaCompra(fechaDeLaCompra);
    }
	
	
	
}

