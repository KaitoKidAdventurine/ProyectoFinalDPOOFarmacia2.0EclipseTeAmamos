package LogicaUtiles;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.ArrayList;



import java.util.HashMap;
import java.util.HashSet;

import Logica.Farmacia;
import Logica.Medicamento;
import Logica.MedicamentoControlado;
import Logica.NucleoFamiliar;
import Logica.Paciente;
import Logica.Tarjeton;

import java.util.Iterator;



public class Validaciones 
{
	public static boolean noEstaVacio(String texto)
	{
		return texto != null && !texto.trim().isEmpty(); 
	}

	public static boolean esMayusculaLaPrimeraLetra(String texto)
	{
		char primLetra = texto.charAt(0);
		return Character.isUpperCase(primLetra);
	}

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


		return texto.matches("^[a-zA-Z0-9\\s#°ºª.,/-]+$");
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


		return texto.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñüÑÜ/ ]+");
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
		return fecha.before(fechaDeHoy);
	}

	public static boolean sobrepasaDeLaFechaDeHoy (Date fecha)
	{

		// Funcionalidad: Retorna true si la fecha  sobrepasa de la
		// fecha de hoy, mientras que si hace lo contrario decuelve true.

		// Obtener la fecha actual del sistema
		Date hoy = new Date();

		// Comparar si la fecha pasada es posterior a hoy
		return fecha.before(hoy);
	}

	public static boolean esAntesDeLaFechaDeHoy (Date fecha)
	{
		// Funcionalidad: Devolvera true si es despues  de la fecha de hoy y devolvera false
		// si es antes de la fecha de hoy 

		Date hoy = new Date();
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

			String carnet = pacienteX.getCi();
			int numero = Character.getNumericValue(carnet.charAt(9));

			// par es hombre "77020545628"
			if(numero % 2 == 0) 
				salida = false;
		}

		return salida;
	}




	public static boolean sonHombres(ArrayList<Paciente> hombres)
	{
		boolean salida = true;

		for(int i = 0; i < hombres.size() && salida == true; i++)
		{
			Paciente pacienteX = hombres.get(i);
			String carnet = pacienteX.getCi();
			int numero = Character.getNumericValue(carnet.charAt(9));

			// impar es mujer 
			if(numero % 2 != 0)
			{
				salida = false;
			}
		}
		return salida;
	}

	public static boolean seRepiteElCarnet(String ci, ArrayList<String> carnets)
	{
		boolean salida = false; 
		for(String c: carnets)
			if(c.trim().equals(ci))
			{
				salida = true;
			}
		System.out.println("CI: " + ci);
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

		// Extrae todos los datos de anno, siglo, mes, dia.

		int anno = extraerAnno(fechaStr);
		int mes = extraerMes(fechaStr);
		int dia = extraerDia(fechaStr);
		int siglo = determinarSiglo(digitoSiglo);


		// Con los datos obtenidos verifica si esos datos son validos.
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

	// Puse maximo 120 annos como el maximo de tiempo que una persona puede vivir.
	private static void validarAnnoMinimo(int annoCompleto) 
	{
		if (annoCompleto < 1905) 
		{
			throw new IllegalArgumentException("No se acepta un año menor de 1905");
		}
	}

	// Valida que la fecha no pueda ser posterior a hoy
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

	// remuebe los pacientes que esten repetidos
	/*
	public static void removerPacientesRepetidos() 
	{
		ArrayList<Paciente> pacientes = Farmacia.obtenerInstancia().getPacientes();
		ArrayList<Paciente> pacientesRep = new ArrayList<>(pacientes);
		ArrayList<Paciente> aEliminar = new ArrayList<>();

		int contador;

		for (Paciente p : pacientes) 
		{
			contador = 0;
			for (Paciente pc : pacientesRep) 
			{
				if (p.getCi().equals(pc.getCi())) 
				{
					contador++;
				}
			}

			if (contador > 1) 
			{
				aEliminar.add(p); // Marcar para eliminar
			}
		}
		//for(int i = 0; i < ( Farmacia.obtenerInstancia().getPacientes().size() - 1) /2; i++)
		//if(Farmacia.obtenerInstancia().getPacientes().get(i).equals(aEliminar.get(i)))

		int i = 0;
		ArrayList<Paciente> pas = Farmacia.obtenerInstancia().getPacientes();
		Iterator <Paciente> it = pas.iterator();
	    while (it.hasNext() && i < 45) 
	    {
	        Paciente actual = it.next();
	        if (i < 45 && actual.equals(aEliminar.get(i))) 
	        {
	            it.remove();
	        }
	        i++;
	    }
	    Farmacia.obtenerInstancia().getPacientes().clear();
	    Farmacia.obtenerInstancia().getPacientes().addAll(pas);
	}
	 */
	public static void removerPacientesRepetidos() 
	{
		ArrayList<Paciente> pacientes = Farmacia.obtenerInstancia().getPacientes();

		HashMap<String, Paciente> mapaPorCI = new HashMap<>();

		// Sobreescribe con el último paciente encontrado por CI
		for (Paciente p : pacientes) 
		{
			mapaPorCI.put(p.getCi(), p);
		}

		// Limpiar y rellenar con los valores únicos
		Farmacia.obtenerInstancia().getPacientes().clear();
		Farmacia.obtenerInstancia().getPacientes().addAll(mapaPorCI.values());
		System.out.println("Total de pacientes: "+ Farmacia.obtenerInstancia().getPacientes().size());
	}

	public static void removerMedicamentosRepetidos() 
	{
		ArrayList<Medicamento> medicamentos = Farmacia.obtenerInstancia().getMedicamentos();

		HashMap<String, Medicamento> mapaPorCodigo = new HashMap<>();

		// Sobreescribe con el último paciente encontrado por CI
		for (Medicamento m : medicamentos) 
		{
			mapaPorCodigo.put(m.getCodigo(), m);
		}

		// Limpiar y rellenar con los valores únicos
		Farmacia.obtenerInstancia().getMedicamentos().clear();
		Farmacia.obtenerInstancia().getMedicamentos().addAll(mapaPorCodigo.values());
		System.out.println("Total de medicamentos: "+ Farmacia.obtenerInstancia().getMedicamentos().size());
	}


	public static void removerMedicamentosControladosRepetidos() 
	{
		ArrayList<MedicamentoControlado> medicamentos = Farmacia.obtenerInstancia().getMedicamentoControlado();

		HashMap<String, MedicamentoControlado> mapaPorCodigo = new HashMap<>();

		// Sobreescribe con el último paciente encontrado por CI
		for (MedicamentoControlado m : medicamentos) 
		{
			mapaPorCodigo.put(m.getCodigo(), m);
		}

		// Limpiar y rellenar con los valores únicos
		Farmacia.obtenerInstancia().getMedicamentoControlado().clear();
		Farmacia.obtenerInstancia().getMedicamentoControlado().addAll(mapaPorCodigo.values());
		System.out.println("Total de medicamentos Controlados: "+ Farmacia.obtenerInstancia().getMedicamentos().size());
	}




	// Validacion para agregar CI 
	private static boolean carnetsDeIdentidadRepetidoEnAgregarPaciente(Paciente pac)
	{
		boolean salida = false;
		for(int i= 0; i < Farmacia.obtenerInstancia().getPacientes().size() && salida == false; i++ )
			if(Farmacia.obtenerInstancia().getPacientes().get(i).equals(pac))
				salida = true;

		return salida;
	}




	// Validación de dígito de sexo
	private static void validarDigitoSexo(char digitoSexo) 
	{
		if (!Character.isDigit(digitoSexo)) 
		{
			throw new IllegalArgumentException("Dígito de sexo debe ser un número");
		}
	}


	// Validacion para verificar que el carnet no se encuentra repetido
	public static void carnetsDeIdentidadRepetido()
	{
		int contador;
		ArrayList <String> carnes  = Farmacia.obtenerInstancia().getCarnets();


		for(String c: Farmacia.obtenerInstancia().getCarnets())
		{
			contador = 0;
			for(String co: carnes)
				if(c.equals(co))
					contador++;
			if(contador !=1)
				throw new IllegalArgumentException("El carnet de identidad"+ c + "se encuentra repetido");
		}	
	}


	public static boolean estaVencido(Date fechaVencimiento) 
	{
		// Funcionalidad: devuelve true si el vencimiento es antes de la fecha de hoy  
		Date fechaDeHoy = new Date(); 
		return fechaVencimiento.before(fechaDeHoy); 
	}


	public static void reajustarNucleosYDirecciones() 
	{
		for (Paciente p : Farmacia.obtenerInstancia().getPacientes()) 
		{
			boolean encontrado = false;

			// Buscar en todos los núcleos
			for(int i= 0; i< Farmacia.obtenerInstancia().getNucleos().size() && encontrado == false; i++)
			{
				if (Farmacia.obtenerInstancia().getNucleos().get(i).getHombres().contains(p) 
						|| Farmacia.obtenerInstancia().getNucleos().get(i).getMujeres().contains(p)) 
				{
					p.setDireccion(Farmacia.obtenerInstancia().getNucleos().get(i).getDireccion());
					p.setNucleo(Farmacia.obtenerInstancia().getNucleos().get(i));
					System.out.println("Direccion: " + p.getDireccion() + 
							" Paciente: " + p.getNombre() + 
							" Nucleo: " + Farmacia.obtenerInstancia().getNucleos().get(i).getDireccion());
					encontrado = true;
				}
			}
			
			for (NucleoFamiliar n : Farmacia.obtenerInstancia().getNucleos()) 
			{

				// Verificar si el paciente está en hombres o mujeres
				if (n.getHombres().contains(p) || n.getMujeres().contains(p)) 
				{
					p.setDireccion(n.getDireccion());
					p.setNucleo(n);
					encontrado = true;
					
				}
			}
		}
	}

	public static void seRepiteCodDelMed()
	{
		int contador;
		ArrayList <String>  med =  new ArrayList<String>();
		for(Medicamento m: Farmacia.obtenerInstancia().getMedicamentos())
		med.add(m.getCodigo());


		for(Medicamento c: Farmacia.obtenerInstancia().getMedicamentos())
		{
			contador = 0;
			for(String m: med)
				if(c.getCodigo().equals(m))
					contador++;
			if(contador !=1)
				throw new IllegalArgumentException("El medicamento "+ c.getNomComun() + " se encuentra repetido");
		}	
		
	}
	// Usar en validaciones
	public static void seRepiteCodDelMedAgregado(Medicamento medi)
	{
		for(Medicamento c: Farmacia.obtenerInstancia().getMedicamentos())
		{
			if(c.getCodigo().equals(medi.getCodigo()))
				throw new IllegalArgumentException("El medicamento "+ medi.getNomComun() + " ya se encuentra en la base de datos");
		}	
		
	}
	
	public static void seRepiteCodDelNucleo()
	{
		int contador;
		ArrayList <String>  med =  new ArrayList<String>();
		for(NucleoFamiliar n: Farmacia.obtenerInstancia().getNucleos())
		med.add(n.getId());


		for(NucleoFamiliar n: Farmacia.obtenerInstancia().getNucleos())
		{
			contador = 0;
			for(String m: med)
				if(n.getId().equals(m))
					contador++;
			
			if(contador !=1)
				throw new IllegalArgumentException("El nucleo "+ n.getId() + " se encuentra repetido");
		}	
	}
	// Usar en validaciones
	public static void seRepiteCodDelNucleoAgregado(NucleoFamiliar nuc)
	{
		for(NucleoFamiliar nf: Farmacia.obtenerInstancia().getNucleos())
		{
			if(nf.getId().equals(nuc.getId()))
				throw new IllegalArgumentException("El Nucleo Familiar: "+ nuc.getId() + " ya se encuentra en la base de datos");
		}	
		
	}
	
	
	public static void seRepiteCodDelMedCon()
	{
		int contador;
		ArrayList <String>  med =  new ArrayList<String>();
		for(MedicamentoControlado m: Farmacia.obtenerInstancia().getMedicamentoControlado())
		med.add(m.getCodigo());


		for(MedicamentoControlado c: Farmacia.obtenerInstancia().getMedicamentoControlado())
		{
			contador = 0;
			for(String m: med)
				if(c.getCodigo().equals(m))
					contador++;
			if(contador !=1)
				throw new IllegalArgumentException("El medicamento Controlado "+ c.getNomComun() + " se encuentra repetido");
		}	
		
	}
	
	
	// Usar en validaciones
		public static void seRepiteCodDelMedContAgregado(MedicamentoControlado medi)
		{
			for(Medicamento c: Farmacia.obtenerInstancia().getMedicamentos())
			{
				if(c.getCodigo().equals(medi.getCodigo()))
					throw new IllegalArgumentException("El medicamento Controlado: "+ medi.getNomComun() + " ya se encuentra en la base de datos");
			}	
			
		}
	
		public static void removerNucleosRepetidos() 
		{
			ArrayList<NucleoFamiliar> nucleos = Farmacia.obtenerInstancia().getNucleos();

			HashMap<String, NucleoFamiliar> mapaPorCodigo = new HashMap<>();

			// Sobreescribe con el último paciente encontrado por CI
			for (NucleoFamiliar n : nucleos) 
			{
				mapaPorCodigo.put(n.getId(), n);
			}

			// Limpiar y rellenar con los valores únicos
			Farmacia.obtenerInstancia().getNucleos().clear();
			Farmacia.obtenerInstancia().getNucleos().addAll(mapaPorCodigo.values());
			System.out.println("Total de Nucleos Familiares: "+ Farmacia.obtenerInstancia().getMedicamentos().size());
		}
}
