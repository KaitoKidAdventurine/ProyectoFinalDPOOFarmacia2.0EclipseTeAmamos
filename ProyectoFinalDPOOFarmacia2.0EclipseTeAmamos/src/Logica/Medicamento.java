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
	protected String codigo;
	
	public Medicamento(String nomComun, String nomCientifico, String presentacion , 
			double precio, String tipo, String fortalezaDelMed, double tempDeAlmac, 
			long  cantExis, Date fechaDeProd, Date fechaDeVenc, String codigo)
	{
		setNomComun(nomComun);
		setNomCientifico(nomCientifico);
		setPresentacion(presentacion);
		setPrecio(precio);
		setTipo(tipo);
		setFortalezaDelMed(fortalezaDelMed);
		setTempDeAlmac(tempDeAlmac);
		setCantExis(cantExis);
		setFechaDeProd(fechaDeProd);
		setFechaDeVenc(fechaDeVenc);
		setCodigo(codigo);
	}
	
	
	public String getCodigo() 
	{
		return codigo;
	}


	public void setCodigo(String codigo) 
	{
		if(Validaciones.noEstaVacio(codigo))
			
			this.codigo = codigo;
		else
			throw new IllegalArgumentException("El campo: codigo del medicamento, se encuentra vacío");
	}


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
			if(Validaciones.noTieneNumeros(nomCientifico))
				if(Validaciones.noTieneCaracteresEsp(nomCientifico))
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
					if(tipo == "Venta libre" ||  tipo == "Con prescripción" || tipo == "Medicamento controlado")
						this.tipo = tipo;
					else
						throw new IllegalArgumentException("El campo: tipo de medicamento, debe ser: Venta libre o Con prescripción o  Medicamento controlado");
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
					if(cantExis >= 0)
						this.cantExis = cantExis;
					else 
						throw new IllegalArgumentException("La cantidad existente de medicamentos en la farmacia no puede ser negativa");
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
			if(Validaciones.sobrepasaDeLaFechaDeHoy(fechaDeProd))
				this.fechaDeProd = fechaDeProd;
			else
				throw new IllegalArgumentException("El campo: fecha de Producción del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de Producción del medicamento, se encuentra vacío");
	}


	public void setFechaDeVenc(Date fechaDeVenc) 
	{
		if(Validaciones.noEstaVacio(fechaDeVenc))
			if(Validaciones.esAntesDeLaFechaDeHoy(fechaDeVenc))
				this.fechaDeVenc = fechaDeVenc;
			else
				throw new IllegalArgumentException("El campo: fecha de Vencimiento del medicamento, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de Vencimiento del medicamento, se encuentra vacío");
	}
}
