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
		this.fechaDeExpedi = fechaDeExpedi;
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
				throw new IllegalArgumentException("El campo: nombre del medicamento, presenta n�meros");
		else
			throw new IllegalArgumentException("El campo: nombre del medicamento, se encuentra vac�o");
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
					throw new IllegalArgumentException("El campo: id del medicamento, tiene letras min�sculas");
			else
				throw new IllegalArgumentException("El campo: id del medicamento, presenta caracteres especiales");
		else
			throw new IllegalArgumentException("El campo: id del medicamento, se encuentra vac�o");

	}



	public void setMedicamentos(ArrayList<Medicamento> medicamentos) 
	{
		this.medicamentos = medicamentos;
	}


}
