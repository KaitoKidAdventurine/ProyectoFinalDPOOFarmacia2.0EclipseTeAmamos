package Logica;

import java.awt.List;
import java.sql.Date;
import java.util.ArrayList;

public class RecetaMedica {
	private Date fechaDeExpedi;
	private String nombreMed;
	private String idMed;
	private ArrayList<Medicamento> medicamentos;

	public Date getFechaDeExpedi() 
	{
		return fechaDeExpedi;
	}

	public String getIdMed() 
	{
		return idMed;
	}

	public String getNombreMed() 
	{
		return nombreMed;
	}

	public ArrayList<Medicamento> getMedicamentos() 
	{
		return medicamentos;
	}



	public void setFechaDeExpedi(Date fechaDeExpedi) 
	{
		if(Validaciones.noEstaVacio(fechaDeExpedi))
			if(Validaciones.sobrepasaDeLaFechaDeHoy(fechaDeExpedi))
				this.fechaDeExpedi = fechaDeExpedi;
			else
				throw new IllegalArgumentException("El campo: fecha de Expiracion de la receta medica, la fecha sobrepasa de la fecha de hoy");
		else
			throw new IllegalArgumentException("El campo: fecha de Expiracion de la receta medica, se encuentra vacío");
	}



	public void setNombreMed(String nombreMed) 
	{
		if(Validaciones.noEstaVacio(nombreMed))
			if(Validaciones.noTieneNumeros(nombreMed))
				if(Validaciones.noTieneCaracteresEsp(nombreMed))
					this.nombreMed = nombreMed;
				else
					throw new IllegalArgumentException("El campo: nombre del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre del medicamento, se encuentra vacío");
	}	



	public void setIdMed(String idMed) 
	{
		// el id del medicamento si puede tener numeros y letras mayusculas
		// pero no caracteres especiales, ni letras minusculas

		if(Validaciones.noEstaVacio(idMed))
			if(Validaciones.noTieneCaracteresEsp(idMed))
				if(Validaciones.noTieneMinusculas(idMed))
					this.idMed = idMed;
				else
					throw new IllegalArgumentException("El campo: id del medicamento, tiene letras minúsculas");
			else
				throw new IllegalArgumentException("El campo: id del medicamento, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: id del medicamento, se encuentra vacío");

	}



	public void setMedicamentos(ArrayList<Medicamento> medicamentos) 
	{
		if(medicamentos != null)
			this.medicamentos = medicamentos;
		else
			throw new IllegalArgumentException("El campo: lista de medicamentos, se encuentra vacío");
	}


}
