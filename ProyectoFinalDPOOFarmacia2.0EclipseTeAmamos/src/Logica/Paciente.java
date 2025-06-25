package Logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import LogicaUtiles.Validaciones;

public class Paciente {
    protected String nombre;
    protected String ci;
    protected String direccion;
    protected Date fechaNacimiento;
    protected char genero;
    protected NucleoFamiliar nucleo;
    protected List<Tarjeton> tarjetones;
    protected boolean esControlado;

    // Constructor por defecto
    public Paciente() 
    {
        this.tarjetones = new ArrayList<Tarjeton>();
        this.esControlado = false;
    }

    // Constructor completo
    public Paciente(String nombre, String ci, String direccion, Date fechaNacimiento, char genero) {
        setNombre(nombre);
        setCi(ci);
        setDireccion(direccion);
        setFechaNacimiento(fechaNacimiento);
        setGenero(genero);
        this.tarjetones = new ArrayList<Tarjeton>();
        this.esControlado = false;
    }

    // Método corregido para agregar tarjetones
    public void agregarTarjeton(Tarjeton tarjeton) {
        if (tarjeton == null) {
            throw new IllegalArgumentException("El tarjetón no puede ser nulo");
        }
        if (this.tarjetones.size() >= 3) {
            throw new IllegalStateException("No se pueden agregar más tarjetones. Límite alcanzado.");
        }
        this.tarjetones.add(tarjeton);
        this.esControlado = true;
    }

    // Obtener tarjetones
    public List<Tarjeton> obtenerTarjetones() 
    {
        return new ArrayList<Tarjeton>(tarjetones);
    }

    // Remover tarjeton
    public boolean removerTarjeton(Tarjeton tarjeton) {
        return this.tarjetones.remove(tarjeton);
    }

    // Getters y setters corregidos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        
    	if(Validaciones.noEstaVacio(nombre))
			if(Validaciones.noTieneNumeros(nombre))
				if(Validaciones.noTieneCaracteresEsp(nombre))
					this.nombre = nombre;
				else
					throw new IllegalArgumentException("El campo: nombre comun del medicamento, presenta caracteres especiales");
			else
				throw new IllegalArgumentException("El campo: nombre comun del medicamento, presenta números");
		else
			throw new IllegalArgumentException("El campo: nombre comun del medicamento, se encuentra vacío");
    
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) 
    {
        if (ci == null || ci.length() != 11) 
        {
            throw new IllegalArgumentException("El CI debe tener exactamente 11 dígitos");
        }
        this.ci = ci;
    }

    public String getDireccion() 
    {
        return direccion;
    }

    public void setDireccion(String direccion) 
    {
        if (direccion == null || direccion.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("La dirección no puede estar vacía");
        }
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() 
    {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) 
    {
        if (fechaNacimiento == null) 
        {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getGenero() 
    {
        return genero;
    }

    public void setGenero(char genero) 
    {
        if (genero != 'M' && genero != 'm' && genero != 'F' && genero != 'f') 
        {
            throw new IllegalArgumentException("Género inválido: debe ser M o F");
        }
        this.genero = Character.toUpperCase(genero);
    }

    public NucleoFamiliar getNucleo() 
    {
        return nucleo;
    }

    public void setNucleo(NucleoFamiliar nucleo) 
    {
        this.nucleo = nucleo;
    }

    public boolean esControlado() 
    {
        return esControlado;
    }

    public boolean perteneceANucleo() 
    {
        return nucleo != null;
    }

    @Override
    public String toString() 
    {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", ci='" + ci + '\'' +
                ", genero=" + genero +
                '}';
    }
}