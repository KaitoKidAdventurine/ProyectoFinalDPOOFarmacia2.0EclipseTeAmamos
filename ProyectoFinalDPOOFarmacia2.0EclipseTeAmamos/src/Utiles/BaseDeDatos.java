package Utiles;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

import Interfaces_Enum.Presentacion;
import Logica.*;

public class BaseDeDatos {
    // Instancia única (Singleton)
    private final Random random;
    private static volatile BaseDeDatos instancia;
    
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

    private BaseDeDatos() {
        this.random = new Random();
        this.pacientes = new HashMap<String, Paciente>();
        this.medicamentos = new HashMap<String, Medicamento>();
        inicializarDatosPrueba();
    }
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
}