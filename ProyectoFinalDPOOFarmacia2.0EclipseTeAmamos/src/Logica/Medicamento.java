package Logica;
import java.util.Date;

import LogicaUtiles.Validaciones;

public class Medicamento 
{
	protected String nomComun;
	protected String nomCientifico;
	protected String presentacion ;
	protected double precio;
	protected String tipo;
	protected String fortalezaDelMed; 
	protected double tempDeAlmac;
	protected long  cantExis;
	protected Date fechaDeProd;
	protected Date fechaDeVenc;

	public String getNomComun() 
	{
		return nomComun;
	}

	public String getNomCientifico() 
	{
		return nomCientifico;
	}

	public String getPresentacion() 
	{
		return presentacion;
	}

	public double getPrecio() 
	{
		return precio;
	}
	
	public String getTipo() 
	{
		return tipo;
	}

	public String getFortalezaDelMed() 
	{
		return fortalezaDelMed;
	}

	public double getTempDeAlmac() 
	{
		return tempDeAlmac;
	}

	public long getCantExis() 
	{
		return cantExis;
	}

	public Date getFechaDeProd() 
	{
		return fechaDeProd;
	}

	public Date getFechaDeVenc() 
	{
		return fechaDeVenc;
	}

	public void setNomComun(String nomComun) 
	{
		if(Validaciones.noEstaVacio(nomComun))
			if(Validaciones.noTieneNumeros(nomComun))
				if(Validaciones.noTieneCaracteresEsp(nomComun))
					this.nomComun = nomComun;
				else
					throw new IllegalArgumentException("El campo: nombre comun del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre comun del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre comun del medicamento, se encuentra vacío");
	}

	public void setNomCientifico(String nomCientifico) 
	{
		if(Validaciones.noEstaVacio(nomCientifico))
			if(Validaciones.noTieneCaracteresEsp(nomCientifico))
				if(Validaciones.noTieneNumeros(nomCientifico))
					this.nomCientifico = nomCientifico;
				else
					throw new IllegalArgumentException("El campo: nombre científico del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre científico del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre científico del medicamento, se encuentra vacío");
	}


	public void setPresentacion(String presentacion) 
	{
		if(Validaciones.noEstaVacio(presentacion))
			if(Validaciones.noTieneCaracteresEsp(presentacion))
				if(Validaciones.noTieneNumeros(presentacion))
					this.presentacion = presentacion;
				else
					throw new IllegalArgumentException("El campo: presentación del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: presentación del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: presentación del medicamento, se encuentra vacío");
	}

	public void setPrecio(double precio) 
	{
		if(Validaciones.noEstaVacio(precio))
			if(!Validaciones.esUnaLetra(precio))
				if(Validaciones.noTieneCaracteresEsp(precio))
					this.precio = precio;
				else
					throw new IllegalArgumentException("El campo: precio del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: precio del medicamento, presenta letras");
		else
			throw new IllegalArgumentException("El campo: precio del medicamento, se encuentra vacío");
	}


	public void setTipo(String tipo) 
	{
		if(Validaciones.noEstaVacio(tipo))
			if(Validaciones.noTieneCaracteresEsp(tipo))
				if(Validaciones.noTieneNumeros(tipo))
					this.tipo = tipo;
				else
					throw new IllegalArgumentException("El campo: tipo de medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: tipo de medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: tipo de medicamento, se encuentra vacío");


	}

	public void setFortalezaDelMed(String fortalezaDelMed) 
	{
		if(Validaciones.noEstaVacio(fortalezaDelMed))
			if(Validaciones.noTieneCaracteresEsp(fortalezaDelMed))
				this.fortalezaDelMed = fortalezaDelMed;
			else
				throw new IllegalArgumentException("El campo: fortaleza de medicamento, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: fortaleza de medicamento, se encuentra vacío");


	}

	public void setTempDeAlmac(double tempDeAlmac) 
	{
		if(Validaciones.noEstaVacio(tempDeAlmac))
			if(Validaciones.noTieneCaracteresEsp(tempDeAlmac))
				this.tempDeAlmac = tempDeAlmac;	
			else
				throw new IllegalArgumentException("El campo: temperatura de almacenamiento del medicamento, presenta caracteres especiales");

		else
			throw new IllegalArgumentException("El campo: temperatura de almacenamiento del medicamento, se encuentra vacío");

	}

	public void setCantExis(long cantExis) 
	{
		if(Validaciones.noEstaVacio(cantExis))
			if(Validaciones.noTieneCaracteresEsp(cantExis))
				if(Validaciones.tieneNumeros(cantExis))
					this.cantExis = cantExis;
				else
					throw new IllegalArgumentException("El campo: cantidad de medicamentos existentes, presenta letras");
			else
				throw new IllegalArgumentException("El campo: cantidad de medicamentos existentes, presenta caracteres especiales");

		else
			throw new IllegalArgumentException("El campo: cantidad de medicamentos existentes, se encuentra vacío");
	}

	

	public void setFechaDeProd(Date fechaDeProd) 
	{
		if(Validaciones.noEstaVacio(fechaDeProd))
			if(!Validaciones.sobrepasaDeLaFechaDeHoy(fechaDeProd))
				if(getFechaDeVenc() != null && fechaDeProd.after(getFechaDeVenc())) 
					this.fechaDeProd = fechaDeProd;
				else
					throw new IllegalArgumentException("La fecha de producción no puede ser posterior a la fecha de vencimiento");
			else
				throw new IllegalArgumentException("El campo: fecha de Producción del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de Producción del medicamento, se encuentra vacío");
	}

	// revisar 
	public void setFechaDeVenc(Date fechaDeVenc) 
	{
		if(Validaciones.noEstaVacio(fechaDeVenc))
			if(!Validaciones.sobrepasaDeLaFechaDeHoy(fechaDeVenc))
				if(getFechaDeProd() != null && fechaDeVenc.before(getFechaDeProd())) 
					this.fechaDeVenc = fechaDeVenc;
				else
					throw new IllegalArgumentException("La fecha de vencimiento no puede ser antes de la fecha de produción");
			else
				throw new IllegalArgumentException("El campo: fecha de Vencimiento del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de Vencimiento del medicamento, se encuentra vacío");

	}
}
