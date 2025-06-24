package LogicaUtiles;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.time.ZoneId;
import java.util.ArrayList;

import Logica.Paciente;
import Logica.Tarjeton;

public class Validaciones 
{
	public static boolean noEstaVacio(String texto)
	{
		return texto != null && !texto.trim().isEmpty(); 
	}

	// falta hacer una para las direcciones que dara bateo si se pone #

	public static boolean noTieneNumeros(String texto)
	{
		// Funcionalidad: Convierto el primer caracter del String en un char
		// despues verifico si ese caracter es un numero o no y retorno lo que me de.


		boolean todoBien = true;
		for(int i = 0; i< texto.length() && todoBien == true; i++)
		{
			char verificarCaracter = texto.charAt(i);
			if(Character.isDigit(verificarCaracter))
			{
				todoBien = false;
			}
		}
		return todoBien;
	}



	public static boolean noEstaVacio(int num)
	{
		// Funcionamiento: Cojo el valor int que me da y lo 
		// convierto en un valor Interger y ahi empiezo a revisar si es null


		Integer numero = Integer.valueOf(num);
		return numero != null; 
	}

	public static boolean noEstaVacio(double num)
	{
		// Funcionamiento: Cojo el valor double que me dan y lo 
		// convierto en un valor Double y ahi empiezo a revisar si es null


		Double numero = Double.valueOf(num);
		return numero != null; 
	}

	public static boolean noEstaVacio(long num)
	{
		// Funcionamiento: Cojo el valor long que me da y lo 
		// convierto en un valor Long y ahi empiezo a revisar si es null


		Long numero = Long.valueOf(num);
		return numero != null; 
	}



	public static boolean esUnaLetra(String texto)
	{
		// Funcionamiento: Devuelve true sino encuentra
		// ningun caracter que no se encuentre en la secuencia
		// de matches


		return texto.matches("[a-zA-Z]+");
	}

	public static boolean esUnaLetra(double numero)
	{
		// Funcionamiento: Devuelve true sino encuentra
		// ningun caracter que no se encuentre en la secuencia
		// de matches

		String texto = String.valueOf(numero);
		return texto.matches("[a-zA-Z]+");
	}

	public static boolean direccion(String texto)
	{
		// Funcionamiento: Devuelve true sino encuentra
		// ningun caracter que no se encuentre en la secuencia
		// de matches


		return texto.matches("^[a-zA-Z0-9\\s#°ºª.,-]+$");
	}



	public static boolean tieneNumeros(long num)
	{
		// Funcionalidad: Convierto la variable Long en un String 
		// despues verifico si cada caracter de la cadena del String 
		// es un numero o no y retorno true o false.


		boolean todoBien = true;
		String numTexto = Long.toString(num);
		for(int i = 0; i< numTexto.length() && todoBien == true; i++)
		{
			char verificarCaracter = numTexto.charAt(i);
			if(!Character.isDigit(verificarCaracter))
			{
				todoBien = false;
			}
		}
		return todoBien;
	}

	public static boolean tieneNumeros(double num)
	{
		// Funcionalidad: Convierto la variable double en un String 
		// despues verifico si cada caracter de la cadena del String 
		// es un numero o no y retorno true o false.


		boolean todoBien = true;
		String numTexto = Double.toString(num);
		for(int i = 0; i< numTexto.length() && todoBien == true; i++)
		{
			char verificarCaracter = numTexto.charAt(i);
			if(!Character.isDigit(verificarCaracter))
			{
				todoBien = false;
			}
		}
		return todoBien;
	}

	public static boolean noTieneCaracteresEsp(String texto) 
	{
		// Funcionalidad: Revisa el String y verifica
		// que no haya ningun caracter especial


		return texto.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñüÑÜ ]+");
	}

	public static boolean noTieneCaracteresEsp(double numero) 
	{
		// Funcionalidad: Convierte el double en String
		// y revisa que el String no tenga ningun caracter especial

		String texto = String.valueOf(numero);
		return texto.matches("[0-9]+(\\.[0-9]+)?");
	}

	public static boolean noTieneCaracteresEsp(long numero) 
	{
		// Funcionalidad: Convierte el double en String
		// y revisa que el String no tenga ningun caracter especial

		String texto = String.valueOf(numero);
		return texto.matches("[0-9]+");
	}


	public static boolean noTieneMinusculas(String texto)
	{
		// Funcionalidad: Revisa que el String no tenga 
		// ninguna letra minuscula


		return texto.matches("[A-Z0-9]+");
	}

	public static boolean noEstaVacio(Date fecha)
	{
		//  Funcionalidad: Revisa que la fecha no sea null


		return fecha != null;
	}

	public static boolean noSePasaDeLaFechaDeHoy(Date fecha)
	{
		//  Funcionaliodad: Revisa que la fecha no se pase
		//  de la fecha del sistema.


		Date fechaDeHoy = new Date(0);
		return fecha.after(fechaDeHoy);
	}

	public static boolean sobrepasaDeLaFechaDeHoy (Date fecha)
	{
		// Sino quieres leer todo esto te recomiendo el resumen...

		//  Funcionalidad: Coge el dato: Date y lo transforma en una
		// instancia de tiempo ( traducido que ahora el coge la hora
		// exacta del sistema ejemplo la hora que tienes en la laptop),
		// despues adquiere a traves del sistema en que zona horaria 
		// se encuentra (Ejemplo: La hora de un sistema americano
		// no tiene el mismo horario con un servidor de china), y para
		// concluir convierte el dato en un tipo de dato LocalDate.

		// Resumen: Retorna true si la fecha no sobrepasa de la
		// fecha de hoy, mientras que si hace lo contrario decuelve false.

		// Obtener la fecha actual del sistema
	    Date hoy = new Date();

	    // Comparar si la fecha pasada es posterior a hoy
	    return fecha.after(hoy);
	}

	public static boolean estaEntreLasDosFechas(Date inicio, Date fecha ,Date fin)
	{
		// Funcionalidad: Revisa que la fecha este entre la fecha 
		// de inicio y la fecha de fin o sea devolvera true si 
		// esta entre: Inicio y Fin sino sera false;


		return !fecha.before(inicio) && !fecha.after(fin);
	}

	public static boolean noEstanVacios(ArrayList<Tarjeton> tarjetones) 
	{
		// Funcionalidad: Revisa que no esten vacios los tarjetones
		// si estan vacios returna false y si estan llenos returna true


		boolean salida = true;
		for(Tarjeton t: tarjetones)
			if(t == null)
				salida = false;
		return salida;
	}

	public static boolean sonMujeres(ArrayList<Paciente> mujeres)
	{
		boolean salida = true;

		for(int i = 0; i < mujeres.size() && salida == true; i++)
		{
			Paciente pacienteX = mujeres.get(i);
			String carnet = pacienteX.getNombre();
			char penultimoDigito = carnet.charAt(10);
			int numero = Character.getNumericValue(penultimoDigito);
			if(numero % 2 == 0)
				salida = false;

		}

		return salida;
	}


	public static boolean validarCI(String ci) 
	{
		// Funcionalidad: 
		validarCantDeCaracteresYQueNoEsteVacio(ci);
		LocalDate fechaNacimiento = validarFechaNacimiento(ci);
		validarEdad(fechaNacimiento);
		validarDigitoSexo(ci.charAt(9));
		return true;
	}

	// Funcionalidad: Valido que el carnet no se pueda ser distinto de 11 digitos,
	// ademas reviso que no este vacio el carnet
	private static void validarCantDeCaracteresYQueNoEsteVacio(String ci) 
	{
		if (ci.length() != 11 || !ci.matches("\\d+")) 
		{
			throw new IllegalArgumentException("El CI debe tener 11 dígitos numéricos");
		}
		
		if(ci == null)
		{
			throw new IllegalArgumentException("El CI no puede estar vacío");
		}
	}

	// 
	private static LocalDate validarFechaNacimiento(String ci) 
	{
		String fechaStr = ci.substring(0, 6);
		char digitoSiglo = ci.charAt(6);
		// resumem extraigo todos los datos de anno, siglo, mes, dia.
		int anno = extraerAnno(fechaStr);
		int mes = extraerMes(fechaStr);
		int dia = extraerDia(fechaStr);
		int siglo = determinarSiglo(digitoSiglo);

		return crearFechaValidada(anno, mes, dia, siglo);
	}

	// Estrae el anno del carnet
	private static int extraerAnno(String fechaStr) 
	{
		return Integer.parseInt(fechaStr.substring(0, 2));
	}
	
	// Estrae el mes del carnet 
	private static int extraerMes(String fechaStr) {
		return Integer.parseInt(fechaStr.substring(2, 4));
	}
	
	// Estrae el dia del carnet
	private static int extraerDia(String fechaStr) 
	{
		return Integer.parseInt(fechaStr.substring(4, 6));
	}

	// Determinación del siglo
	private static int determinarSiglo(char digitoSiglo) 
	{
		switch (digitoSiglo) 
		{
		case '0': case '1': case '2': case '3': case '4': case '5':
			return 1900;
		case '6': case '7': case '8':
			return 2000;
		case '9':
			throw new IllegalArgumentException("No se permiten fechas anteriores a 1905");
		default:
			throw new IllegalArgumentException("Dígito de siglo inválido (debe ser 0-8)");
		}
	}

	// Creación y validación de fecha
	private static LocalDate crearFechaValidada(int anno, int mes, int dia, int siglo) 
	{
		int annoCompleto = siglo + anno;
		validarAnnoMinimo(annoCompleto);

		try 
		{
			LocalDate fecha = LocalDate.of(annoCompleto, mes, dia);
			validarFechaNoFutura(fecha);
			return fecha;
		} 
		
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Fecha inválida: " + dia + "/" + mes + "/" + annoCompleto);
		}
	}

	private static void validarAnnoMinimo(int annoCompleto) 
	{
		if (annoCompleto < 1905) 
		{
			throw new IllegalArgumentException("No se acepta un año menor de 1905");
		}
	}

	private static void validarFechaNoFutura(LocalDate fecha) 
	{
		if (fecha.isAfter(LocalDate.now())) 
		{
			throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior al día de hoy");
		}
	}

	// Validación de edad
	private static void validarEdad(LocalDate fechaNacimiento) 
	{
		Period periodo = Period.between(fechaNacimiento, LocalDate.now());
		if (periodo.getYears() > 120) 
		{
			throw new IllegalArgumentException("Edad inválida: más de 120 años");
		}
	}

	// Validación de dígito de sexo
	private static void validarDigitoSexo(char digitoSexo) 
	{
		if (!Character.isDigit(digitoSexo)) 
		{
			throw new IllegalArgumentException("Dígito de sexo debe ser un número");
		}
	}


}
