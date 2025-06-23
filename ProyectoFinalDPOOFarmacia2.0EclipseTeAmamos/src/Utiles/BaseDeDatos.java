package Utiles;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

import Interfaces_Enum.Presentacion;
import Logica.*;

public class BaseDeDatos {
    // Instancia única (Singleton)
    private final Random random;
<<<<<<< HEAD
    private static volatile BaseDeDatos instancia;
    
=======
    private final List<Venta> ventas;
	private final List<Factura> facturas;
	
	private BaseDeDatos() 
	{
        this.random = new Random();
        this.pacientes = new HashMap<String, Paciente>();
        this.medicamentos = new HashMap<String, Medicamento>();
        this.ventas = new ArrayList<Venta>();
        this.facturas = new ArrayList<Factura>();
        inicializarDatosPrueba();
    }
	
>>>>>>> 6e770434d6cac210f7f67205144dce4cb70fb04d
    // Datos de prueba
    private static final List<String> NOMBRES_MASCULINOS = Arrays.asList(
        "Alejandro", "Benjamin", "Carlos", "Daniel", "Emilio", 
        "Fernando", "Gabriel", "Hector", "Ignacio", "Javier",
        "Kevin", "Luis", "Manuel", "Nicolas", "Oscar",
        "Pablo", "Raúl", "Sergio", "Tomas", "Victor");
    
    private static final List<String> NOMBRES_FEMENINOS = Arrays.asList(
        "Adriana", "Beatriz", "Camila", "Diana", "Elena",
        "Fernanda", "Gabriela", "Helena", "Isabel", "Jimena",
        "Karina", "Laura", "Mariana", "Natalia", "Olivia",
        "Patricia", "Raquel", "Sofía", "Tatiana", "Valeria");
    
    private static final List<String> APELLIDOS = Arrays.asList(
        "Aguilar", "Bermúdez", "Cervantes", "Delgado", "Espinoza",
        "Fuentes", "Gutiérrez", "Hernández", "Ibarra", "Juárez",
        "Katz", "López", "Méndez", "Núñez", "Ortiz",
        "Pérez", "Quintero", "Rojas", "Sánchez", "Torres");

    // Estructuras de almacenamiento
    private final Map<String, Paciente> pacientes;
    private final Map<String, Medicamento> medicamentos;

<<<<<<< HEAD
    private BaseDeDatos() {
        this.random = new Random();
        this.pacientes = new HashMap<String, Paciente>();
        this.medicamentos = new HashMap<String, Medicamento>();
        inicializarDatosPrueba();
    }
=======
    

>>>>>>> 6e770434d6cac210f7f67205144dce4cb70fb04d
    public static synchronized BaseDeDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }
    
    private void inicializarDatosPrueba() {
        // Inicialización con datos de prueba
        for (int i = 0; i < 50; i++) {
            char genero = random.nextBoolean() ? 'M' : 'F';
            registrarPaciente(crearPaciente(genero));
        }
        
        for (int i = 0; i < 30; i++) {
            registrarMedicamento(crearMedicamento());
        }
    }

    public Paciente crearPaciente(char genero) {
        LocalDate fechaNacimiento = generarFechaNacimiento();
        Date fechaNac = Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        Paciente paciente = new Paciente();
        paciente.setNombre(generarNombreCompleto(genero));
        paciente.setCi(generarCarnetIdentidad(genero, fechaNacimiento));
        paciente.setFechaNacimiento(fechaNac);
        paciente.setDireccion(generarDireccion());
        
        return paciente;
    }

    private LocalDate generarFechaNacimiento() {
        LocalDate hoy = LocalDate.now();
        return hoy.minusYears(10 + random.nextInt(80)); // Entre 10 y 90 años
    }

    private String generarNombreCompleto(char genero) {
        String nombre = genero == 'M' ? 
            NOMBRES_MASCULINOS.get(random.nextInt(NOMBRES_MASCULINOS.size())) :
            NOMBRES_FEMENINOS.get(random.nextInt(NOMBRES_FEMENINOS.size()));
        
        List<String> apellidos = new ArrayList<String>(APELLIDOS);
        String apellido1 = apellidos.remove(random.nextInt(apellidos.size()));
        String apellido2 = apellidos.get(random.nextInt(apellidos.size()));
        
        return nombre + " " + apellido1 + " " + apellido2;
    }

    private String generarCarnetIdentidad(char genero, LocalDate fechaNacimiento) {
        StringBuilder ci = new StringBuilder();
        
        // Año (2 últimos dígitos)
        ci.append(String.format("%02d", fechaNacimiento.getYear() % 100));
        // Mes
        ci.append(String.format("%02d", fechaNacimiento.getMonthValue()));
        // Día
        ci.append(String.format("%02d", fechaNacimiento.getDayOfMonth()));
        // 3 dígitos aleatorios
        ci.append(String.format("%03d", random.nextInt(1000)));
        // Dígito de género (par=M, impar=F)
        ci.append(genero == 'M' ? random.nextInt(5) * 2 : random.nextInt(5) * 2 + 1);
        
        return ci.toString();
    }

    private String generarDireccion() {
        return "Calle " + (1 + random.nextInt(100)) + ", #" + (1 + random.nextInt(500));
    }

    public Medicamento crearMedicamento() {
        Medicamento med = new Medicamento();

        // Nombres comunes y científicos ficticios
        String[] nombresComunes = {
            "Paracetamol", "Ibuprofeno", "Amoxicilina", "Loratadina", "Omeprazol",
            "Metformina", "Simvastatina", "Aspirina", "Dipirona", "Clonazepam",
            "Salbutamol", "Levotiroxina", "Diclofenaco", "Ranitidina", "Cetirizina",
            "Naproxeno", "Prednisona", "Tramadol", "Fluoxetina", "Venlafaxina"
        };

        String[] nombresCientificos = {
            "Acetaminophen", "Ibuprofen", "Amoxicillin", "Loratadine", "Omeprazole",
            "Metformin", "Simvastatin", "Acetylsalicylic Acid", "Metamizole", "Clonazepam",
            "Salbutamol", "Levothyroxine Sodium", "Diclofenac", "Ranitidine", "Cetirizine",
            "Naproxen", "Prednisone", "Tramadol Hydrochloride", "Fluoxetine", "Venlafaxine"
        };

        Presentacion[] presentaciones = Presentacion.values();
        String[] tipos = {"Venta libre", "Con prescripción", "Medicamento controlado"};

        // Seleccionar valores aleatorios
        String nomComun = nombresComunes[random.nextInt(nombresComunes.length)];
        String nomCientifico = nombresCientificos[random.nextInt(nombresCientificos.length)];
        Presentacion presentacionEnum = presentaciones[random.nextInt(presentaciones.length)];
        String tipo = tipos[random.nextInt(tipos.length)];

        // Atributos numéricos y fechas
        double precio = 5 + random.nextDouble() * 100;
        String fortalezaDelMed = (50 + random.nextInt(950)) + " mg";
        double tempDeAlmac = 2 + random.nextDouble() * 28;
        long cantExis = random.nextInt(1000);
        LocalDate fechaProd = LocalDate.now().minusMonths(random.nextInt(36));
        Date fechaProduccion = Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaVencimiento = Date.from(fechaProd.plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Asignar al objeto usando tus atributos exactos
        med.setNomComun(nomComun);
        med.setNomCientifico(nomCientifico);
        med.setPresentacion(presentacionEnum.getDescripcion()); // Usamos getDescripcion()
        med.setPrecio(precio);
        med.setTipo(tipo);
        med.setFortalezaDelMed(fortalezaDelMed);
        med.setTempDeAlmac(tempDeAlmac);
        med.setCantExis(cantExis);
        med.setFechaDeProd(fechaProduccion);
        med.setFechaDeVenc(fechaVencimiento);

        return med;
    }

    // Métodos de registro
    public void registrarPaciente(Paciente paciente) {
        pacientes.put(paciente.getCi(), paciente);
    }

    public void registrarMedicamento(Medicamento medicamento) {
        medicamentos.put(medicamento.getNomComun(), medicamento);
    }

    // Métodos de consulta
    public List<Paciente> obtenerPacientes() {
        return new ArrayList<Paciente>(pacientes.values());
    }

    public List<Medicamento> obtenerMedicamentos() {
        return new ArrayList<Medicamento>(medicamentos.values());
    }

    // Método de validación
    public boolean validarCarnetIdentidad(String ci, char genero, LocalDate fechaNacimiento) {
        if (ci == null || ci.length() != 11) return false;
        
        try {
            int añoCI = Integer.parseInt(ci.substring(0, 2));
            int mesCI = Integer.parseInt(ci.substring(2, 4));
            int diaCI = Integer.parseInt(ci.substring(4, 6));
            
            int añoCompleto = (añoCI <= LocalDate.now().getYear() % 100) ? 
                2000 + añoCI : 1900 + añoCI;
            
            boolean fechaValida = LocalDate.of(añoCompleto, mesCI, diaCI).equals(fechaNacimiento);
            boolean generoValido = (Integer.parseInt(ci.substring(10)) % 2 == 0) == (genero == 'M');
            
            return fechaValida && generoValido;
        } catch (Exception e) {
            return false;
        }
    }
    public void registrarVenta(Venta venta) 
	{
	    ventas.add(venta);
	}


	public List<Venta> obtenerVentas() 
	{
	    return new ArrayList<Venta>(ventas);
	}





	private void generarVentasPrueba() 
	{
	    List<Medicamento> listaMedicamentos = new ArrayList<Medicamento>(medicamentos.values());

	    if (listaMedicamentos.isEmpty()) 
	    {
	        System.out.println("No hay medicamentos disponibles para generar ventas.");
	        return;
	    }

	    for (int i = 0; i < 50; i++) 
	    {
	        // VentaConPrescripcion
	        Date fechaVenta1 = generarFechaAleatoria();
	        double importeTotal1 = 20 + random.nextDouble() * 150;
	        VentaConPrescripcion ventaPrescripcion = new VentaConPrescripcion(fechaVenta1, importeTotal1);
	        try 
	        {	
	            ventaPrescripcion.setFechaDeCompra(generarFechaAleatoria());
	        } 
	        
	        catch (Exception e) 
	        {
	            System.err.println("Error al setear fecha de compra: " + e.getMessage());
	        }
	        ventas.add(ventaPrescripcion);
	        
	        if (!listaMedicamentos.isEmpty()) 
	        {
	        	Medicamento med = listaMedicamentos.get(random.nextInt(listaMedicamentos.size()));

	        	Factura factura1 = new Factura(
	        	    med.getNomComun(),          // Nombre del medicamento
	        	    med.getNomCientifico(),     // Codigo del medicamento
	        	    1,                          // Cantidad vendida (ejemplo)
	        	    fechaVenta1                 // Fecha de la compra
	        	);

	        	facturas.add(factura1);
	        }
	        
	        
	        // VentaControlada
	        Date fechaVenta2 = generarFechaAleatoria();
	        double importeTotal2 = 10 + random.nextDouble() * 200;
	        ventas.add(new VentaControlada(fechaVenta2, importeTotal2));
	        
	        if (!listaMedicamentos.isEmpty()) 
	        {
	        	Medicamento med = listaMedicamentos.get(random.nextInt(listaMedicamentos.size()));
	        	facturas.add(new Factura(
	        		    med.getNomComun(),          // nombreDelMed
	        		    med.getNomCientifico(),     // codigoDelMed
	        		    1,                          // cantMedVendidos
	        		    fechaVenta2                 // fechaDeLaCompra
	        		));
	        }


	        // VentaLibre
	        Date fechaVenta3 = generarFechaAleatoria();
	        double importeTotal3 = 10 + random.nextDouble() * 100;

	        ArrayList<Medicamento> carrito = new ArrayList<Medicamento>();
	        for (int j = 0; j < 2 + random.nextInt(4); j++) 
	        {
	            if (!listaMedicamentos.isEmpty()) 
	            {
	                carrito.add(listaMedicamentos.get(random.nextInt(listaMedicamentos.size())));
	            }
	        }

	        VentaLibre ventaLibre = new VentaLibre(fechaVenta3, importeTotal3);
	        
	        try 
	        {
	            ventaLibre.setInventario(carrito);
	        } 
	        
	        catch (Exception e) 
	        {
	            System.err.println("Error al setear inventario: " + e.getMessage());
	        }
	        ventas.add(ventaLibre);
	        
	        if (!carrito.isEmpty()) 
	        {
	            for (Medicamento med : carrito) 
	            {
	            	facturas.add(new Factura(
	            		    med.getNomComun(), 
	            		    med.getNomCientifico(), 
	            		    1, 
	            		    fechaVenta3
	            		));
	            }
	        }

	        // AlmohadillasSanitarias
	        Date fechaVenta4 = generarFechaAleatoria();
	        double precioUnit = 5 + random.nextDouble() * 10;
	        int cantidad = 5 + random.nextInt(10);

	        try 
	        {
	        	ventas.add(new AlmohadillasSanitarias(precioUnit, cantidad, fechaVenta4));
	        } 
	        
	        catch (Exception e) 
	        {
	            System.err.println("Error al crear AlmohadillasSanitarias: " + e.getMessage());
	        }
	        
	        Factura factura4 = new Factura("Almohadillas Sanitarias", "ALM-001", cantidad, fechaVenta4);
	        facturas.add(factura4);
	        
	    }

	    
	}
	
	private Date generarFechaAleatoria() 
	{
	    Calendar calendario = Calendar.getInstance();
	    int anioActual = calendario.get(Calendar.YEAR);

	    // Establecer al 1 de enero del anno actual
	    Calendar inicioAnio = Calendar.getInstance();
	    inicioAnio.set(anioActual, Calendar.JANUARY, 1);

	    // Calcular los dias transcurridos desde el 1 de enero hasta hoy
	    long milisegundosDesdeInicio = calendario.getTimeInMillis() - inicioAnio.getTimeInMillis();
	    int diasTranscurridos = (int) (milisegundosDesdeInicio / (24 * 60 * 60 * 1000));

	    // Genera una cantidad aleatoria de dias entre 0 y los dias transcurridos
	    int diasAleatorios = random.nextInt(diasTranscurridos + 1);

	    // Avanzar desde el 1ero de enero y pone dias aleatorios
	    inicioAnio.add(Calendar.DAY_OF_YEAR, diasAleatorios);

	    return inicioAnio.getTime();
	}
	
	public List<Factura> obtenerFacturas() 
	{
	    return new ArrayList<Factura>(facturas);
	}
}