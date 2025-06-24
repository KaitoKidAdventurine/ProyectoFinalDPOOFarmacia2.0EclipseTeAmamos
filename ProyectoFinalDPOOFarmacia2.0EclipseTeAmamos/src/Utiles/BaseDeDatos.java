package Utiles;

import java.util.*;
import java.time.*;
import java.sql.Date;
import Interfaces_Enum.Presentacion;
import Logica.*;
import LogicaUtiles.Factura;

public class BaseDeDatos {
    private static BaseDeDatos instancia;
    private final List<Venta> ventas;
    private final List<Factura> facturas;
    private final List<Tarjeton> tarjetones;
    private final Map<String, Paciente> pacientes;
    private final Map<String, Medicamento> medicamentos;
    private static final LocalDate FECHA_LIMITE = LocalDate.of(2025, 6, 23);

    private BaseDeDatos() {
        this.pacientes = new HashMap<String, Paciente>();
        this.medicamentos = new HashMap<String, Medicamento>();
        this.ventas = new ArrayList<Venta>();
        this.facturas = new ArrayList<Factura>();
        this.tarjetones = new ArrayList<Tarjeton>();
        inicializarDatosPrueba();
    }

    public static synchronized BaseDeDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    private void inicializarDatosPrueba() 
    {
        inicializarPacientes();
        inicializarMedicamentos();
        inicializarTarjetones();
        inicializarVentasYFacturas();
    }

    private void inicializarPacientes() {
        // Hombres (25)
        agregarPaciente("Alejandro Bermúdez Cervantes", "88041568485", 'M', LocalDate.of(1988, 4, 15), "Calle 23, #45");
        agregarPaciente("Benjamín Delgado Espinoza", "90062012345", 'M', LocalDate.of(1990, 6, 20), "Calle 12, #34");
        agregarPaciente("Carlos Fuentes Gutiérrez", "78031049284", 'M', LocalDate.of(1978, 3, 10), "Calle 34, #56");
        agregarPaciente("Daniel Hernández López", "85071234567", 'M', LocalDate.of(1985, 7, 12), "Calle 45, #78");
        agregarPaciente("Emilio Juárez Méndez", "92081567890", 'M', LocalDate.of(1992, 8, 15), "Calle 56, #90");
        agregarPaciente("Fernando Katz Núñez", "83092123456", 'M', LocalDate.of(1983, 9, 21), "Calle 67, #12");
        agregarPaciente("Gabriel López Ortiz", "89103078901", 'M', LocalDate.of(1989, 10, 30), "Calle 78, #34");
        agregarPaciente("Héctor Méndez Pérez", "77020545678", 'M', LocalDate.of(1977, 2, 5), "Calle 89, #56");
        agregarPaciente("Ignacio Núñez Quintero", "94040890123", 'M', LocalDate.of(1994, 4, 8), "Calle 90, #78");
        agregarPaciente("Javier Ortiz Rojas", "81061156789", 'M', LocalDate.of(1981, 6, 11), "Calle 11, #90");
        agregarPaciente("Kevin Pérez Sánchez", "99121401234", 'M', LocalDate.of(1999, 12, 14), "Calle 22, #13");
        agregarPaciente("Luis Quintero Torres", "87101767890", 'M', LocalDate.of(1987, 10, 17), "Calle 33, #24");
        agregarPaciente("Manuel Rojas Aguilar", "93022012345", 'M', LocalDate.of(1993, 2, 20), "Calle 44, #35");
        agregarPaciente("Nicolás Sánchez Bermúdez", "80032378901", 'M', LocalDate.of(1980, 3, 23), "Calle 55, #46");
        agregarPaciente("Oscar Torres Cervantes", "95062623456", 'M', LocalDate.of(1995, 6, 26), "Calle 66, #57");
        agregarPaciente("Pablo Aguilar Delgado", "85012989012", 'M', LocalDate.of(1985, 1, 29), "Calle 77, #68");
        agregarPaciente("Raúl Bermúdez Espinoza", "74040234567", 'M', LocalDate.of(1974, 4, 2), "Calle 88, #79");
        agregarPaciente("Sergio Cervantes Fuentes", "98070590123", 'M', LocalDate.of(1998, 7, 5), "Calle 99, #80");
        agregarPaciente("Tomás Delgado Gutiérrez", "82080845678", 'M', LocalDate.of(1982, 8, 8), "Calle 10, #91");
        agregarPaciente("Víctor Espinoza Hernández", "91091101234", 'M', LocalDate.of(1991, 9, 11), "Calle 21, #02");
        agregarPaciente("Adrián Fuentes Ibarra", "75011456789", 'M', LocalDate.of(1975, 1, 14), "Calle 32, #13");
        agregarPaciente("Bruno Gutiérrez Juárez", "97021712345", 'M', LocalDate.of(1997, 2, 17), "Calle 43, #24");
        agregarPaciente("Diego Hernández Katz", "87032067890", 'M', LocalDate.of(1987, 3, 20), "Calle 54, #35");
        agregarPaciente("Eduardo Ibarra López", "92042323456", 'M', LocalDate.of(1992, 4, 23), "Calle 65, #46");
        agregarPaciente("Felipe Juárez Méndez", "81052678901", 'M', LocalDate.of(1981, 5, 26), "Calle 76, #57");

        // Mujeres (20)
        agregarPaciente("Adriana Méndez Núñez", "89062934567", 'F', LocalDate.of(1989, 6, 29), "Calle 87, #68");
        agregarPaciente("Beatriz Núñez Ortiz", "94080190123", 'F', LocalDate.of(1994, 8, 1), "Calle 98, #79");
        agregarPaciente("Camila Ortiz Pérez", "85090445678", 'F', LocalDate.of(1985, 9, 4), "Calle 09, #80");
        agregarPaciente("Diana Pérez Quintero", "99100701234", 'F', LocalDate.of(1999, 10, 7), "Calle 20, #91");
        agregarPaciente("Elena Quintero Rojas", "77111056789", 'F', LocalDate.of(1977, 11, 10), "Calle 31, #02");
        agregarPaciente("Fernanda Rojas Sánchez", "93021312345", 'F', LocalDate.of(1993, 2, 13), "Calle 42, #13");
        agregarPaciente("Gabriela Sánchez Torres", "83031667890", 'F', LocalDate.of(1983, 3, 16), "Calle 53, #24");
        agregarPaciente("Helena Torres Aguilar", "97041923456", 'F', LocalDate.of(1997, 4, 19), "Calle 64, #35");
        agregarPaciente("Isabel Aguilar Bermúdez", "87052278901", 'F', LocalDate.of(1987, 5, 22), "Calle 75, #46");
        agregarPaciente("Jimena Bermúdez Cervantes", "92062534567", 'F', LocalDate.of(1992, 6, 25), "Calle 86, #57");
        agregarPaciente("Karina Cervantes Delgado", "82072890123", 'F', LocalDate.of(1982, 7, 28), "Calle 97, #68");
        agregarPaciente("Laura Delgado Espinoza", "98083145678", 'F', LocalDate.of(1998, 8, 31), "Calle 08, #79");
        agregarPaciente("Mariana Espinoza Fuentes", "74090301234", 'F', LocalDate.of(1974, 9, 3), "Calle 19, #80");
        agregarPaciente("Natalia Fuentes Gutiérrez", "95100656789", 'F', LocalDate.of(1995, 10, 6), "Calle 30, #91");
        agregarPaciente("Olivia Gutiérrez Hernández", "85110912345", 'F', LocalDate.of(1985, 11, 9), "Calle 41, #02");
        agregarPaciente("Patricia Hernández Ibarra", "99121267890", 'F', LocalDate.of(1999, 12, 12), "Calle 52, #13");
        agregarPaciente("Raquel Ibarra Juárez", "81031523456", 'F', LocalDate.of(1981, 3, 15), "Calle 63, #24");
        agregarPaciente("Sofía Juárez Katz", "97041878901", 'F', LocalDate.of(1997, 4, 18), "Calle 74, #35");
        agregarPaciente("Tatiana Katz López", "83052134567", 'F', LocalDate.of(1983, 5, 21), "Calle 85, #46");
        agregarPaciente("Valeria López Méndez", "93062490123", 'F', LocalDate.of(1993, 6, 24), "Calle 96, #57");
    }
    
    
    
    
    private void inicializarMedicamentos() {
        // Venta libre (10)
        agregarMedicamento("Paracetamol", "Acetaminophen", "Tabletas", 5.99, "Venta libre", "500 mg", 25.0, 1000, 
                         LocalDate.of(2023, 1, 15), LocalDate.of(2024, 1, 15));
        agregarMedicamento("Ibuprofeno", "Ibuprofen", "Tabletas", 7.50, "Venta libre", "400 mg", 25.0, 800, 
                         LocalDate.of(2023, 2, 10), LocalDate.of(2024, 2, 10));
        agregarMedicamento("Aspirina", "Acetylsalicylic Acid", "Tabletas", 4.25, "Venta libre", "500 mg", 25.0, 1200, 
                         LocalDate.of(2023, 3, 5), LocalDate.of(2024, 3, 5));
        agregarMedicamento("Cetirizina", "Cetirizine", "Tabletas", 8.75, "Venta libre", "10 mg", 25.0, 600, 
                         LocalDate.of(2023, 4, 20), LocalDate.of(2024, 4, 20));
        agregarMedicamento("Loratadina", "Loratadine", "Tabletas", 9.99, "Venta libre", "10 mg", 25.0, 700, 
                         LocalDate.of(2023, 5, 15), LocalDate.of(2024, 5, 15));
        agregarMedicamento("Omeprazol", "Omeprazole", "Cápsulas", 12.50, "Venta libre", "20 mg", 25.0, 500, 
                         LocalDate.of(2023, 6, 10), LocalDate.of(2024, 6, 10));
        agregarMedicamento("Ranitidina", "Ranitidine", "Tabletas", 6.80, "Venta libre", "150 mg", 25.0, 900, 
                         LocalDate.of(2023, 7, 5), LocalDate.of(2024, 7, 5));
        agregarMedicamento("Diclofenaco", "Diclofenac", "Gel", 15.25, "Venta libre", "1%", 25.0, 400, 
                         LocalDate.of(2023, 8, 1), LocalDate.of(2024, 8, 1));
        agregarMedicamento("Naproxeno", "Naproxen", "Tabletas", 10.50, "Venta libre", "250 mg", 25.0, 750, 
                         LocalDate.of(2023, 9, 15), LocalDate.of(2024, 9, 15));
        agregarMedicamento("Salbutamol", "Salbutamol", "Inhalador", 18.99, "Venta libre", "100 mcg", 25.0, 300, 
                         LocalDate.of(2023, 10, 10), LocalDate.of(2024, 10, 10));

        // Con prescripción (10)
        agregarMedicamento("Amoxicilina", "Amoxicillin", "Cápsulas", 12.75, "Con prescripción", "250 mg", 20.0, 500, 
                         LocalDate.of(2023, 1, 20), LocalDate.of(2024, 1, 20));
        agregarMedicamento("Ciprofloxacino", "Ciprofloxacin", "Tabletas", 22.50, "Con prescripción", "500 mg", 20.0, 400, 
                         LocalDate.of(2023, 2, 15), LocalDate.of(2024, 2, 15));
        agregarMedicamento("Azitromicina", "Azithromycin", "Tabletas", 28.99, "Con prescripción", "250 mg", 20.0, 350, 
                         LocalDate.of(2023, 3, 10), LocalDate.of(2024, 3, 10));
        agregarMedicamento("Atorvastatina", "Atorvastatin", "Tabletas", 35.75, "Con prescripción", "20 mg", 20.0, 300, 
                         LocalDate.of(2023, 4, 5), LocalDate.of(2024, 4, 5));
        agregarMedicamento("Metformina", "Metformin", "Tabletas", 18.50, "Con prescripción", "500 mg", 20.0, 450, 
                         LocalDate.of(2023, 5, 1), LocalDate.of(2024, 5, 1));
        agregarMedicamento("Losartán", "Losartan", "Tabletas", 24.99, "Con prescripción", "50 mg", 20.0, 380, 
                         LocalDate.of(2023, 6, 15), LocalDate.of(2024, 6, 15));
        agregarMedicamento("Simvastatina", "Simvastatin", "Tabletas", 30.25, "Con prescripción", "20 mg", 20.0, 320, 
                         LocalDate.of(2023, 7, 10), LocalDate.of(2024, 7, 10));
        agregarMedicamento("Levotiroxina", "Levothyroxine", "Tabletas", 19.80, "Con prescripción", "50 mcg", 20.0, 420, 
                         LocalDate.of(2023, 8, 5), LocalDate.of(2024, 8, 5));
        agregarMedicamento("Clonazepam", "Clonazepam", "Tabletas", 42.50, "Con prescripción", "2 mg", 20.0, 280, 
                         LocalDate.of(2023, 9, 1), LocalDate.of(2024, 9, 1));
        agregarMedicamento("Fluoxetina", "Fluoxetine", "Cápsulas", 38.75, "Con prescripción", "20 mg", 20.0, 310, 
                         LocalDate.of(2023, 10, 15), LocalDate.of(2024, 10, 15));

        // Controlados (10)
        agregarMedicamentoControlado("Morfina", "Morphine", "Inyección", 125.99, "Medicamento controlado", "10 mg/ml", 15.0, 150, 
                                   LocalDate.of(2023, 1, 10), LocalDate.of(2024, 1, 10), "Dolor crónico", 5, 0);
        agregarMedicamentoControlado("Oxicodona", "Oxycodone", "Tabletas", 95.50, "Medicamento controlado", "5 mg", 15.0, 180, 
                                   LocalDate.of(2023, 2, 5), LocalDate.of(2024, 2, 5), "Dolor postoperatorio", 4, 0);
        agregarMedicamentoControlado("Metadona", "Methadone", "Tabletas", 88.25, "Medicamento controlado", "10 mg", 15.0, 200, 
                                   LocalDate.of(2023, 3, 1), LocalDate.of(2024, 3, 1), "Dependencia opioides", 3, 0);
        agregarMedicamentoControlado("Fentanilo", "Fentanyl", "Parche", 210.75, "Medicamento controlado", "25 mcg/h", 15.0, 120, 
                                   LocalDate.of(2023, 4, 15), LocalDate.of(2024, 4, 15), "Dolor oncológico", 2, 0);
        agregarMedicamentoControlado("Codeína", "Codeine", "Jarabe", 65.99, "Medicamento controlado", "10 mg/5ml", 15.0, 250, 
                                   LocalDate.of(2023, 5, 10), LocalDate.of(2024, 5, 10), "Tos persistente", 6, 0);
        agregarMedicamentoControlado("Diazepam", "Diazepam", "Tabletas", 55.50, "Medicamento controlado", "5 mg", 15.0, 300, 
                                   LocalDate.of(2023, 6, 5), LocalDate.of(2024, 6, 5), "Ansiedad", 4, 0);
        agregarMedicamentoControlado("Alprazolam", "Alprazolam", "Tabletas", 60.25, "Medicamento controlado", "1 mg", 15.0, 280, 
                                   LocalDate.of(2023, 7, 1), LocalDate.of(2024, 7, 1), "Trastorno de pánico", 3, 0);
        agregarMedicamentoControlado("Metilfenidato", "Methylphenidate", "Tabletas", 75.80, "Medicamento controlado", "10 mg", 15.0, 220, 
                                   LocalDate.of(2023, 8, 15), LocalDate.of(2024, 8, 15), "TDAH", 5, 0);
        agregarMedicamentoControlado("Zolpidem", "Zolpidem", "Tabletas", 68.50, "Medicamento controlado", "10 mg", 15.0, 240, 
                                   LocalDate.of(2023, 9, 10), LocalDate.of(2024, 9, 10), "Insomnio", 4, 0);
        agregarMedicamentoControlado("Tramadol", "Tramadol", "Cápsulas", 72.99, "Medicamento controlado", "50 mg", 15.0, 260, 
                                   LocalDate.of(2023, 10, 5), LocalDate.of(2024, 10, 5), "Dolor moderado", 5, 0);
    }

    private void inicializarVentasYFacturas() {
        // Obtener listas de medicamentos por tipo
        List<Medicamento> ventaLibre = new ArrayList<Medicamento>();
        List<Medicamento> conPrescripcion = new ArrayList<Medicamento>();
        List<Medicamento> controlados = new ArrayList<Medicamento>();
        for (Medicamento m : medicamentos.values()) {
            if (m.getTipo().equals("Venta libre")) {
                ventaLibre.add(m);
            } else if (m.getTipo().equals("Con prescripción")) {
                conPrescripcion.add(m);
            } else if (m.getTipo().equals("Medicamento controlado")) {
                controlados.add(m);
            }
        }

        // Crear 120 ventas distribuidas
        crearVentasLibres(ventaLibre, 40);
        crearVentasConPrescripcion(conPrescripcion, 40);
        crearVentasControladas(controlados, 30);
        crearVentasAlmohadillas(10);
    }

    private void agregarPaciente(String nombre, String ci, char genero, LocalDate fechaNac, String direccion) {
        Paciente p = new Paciente();
        p.setNombre(nombre);
        p.setCi(ci);
        p.setGenero(genero);
        p.setFechaNacimiento(java.sql.Date.valueOf(fechaNac)); 
        p.setDireccion(direccion);
        pacientes.put(ci, p);
    }

    private void agregarMedicamento(String nomComun, String nomCientifico, String presentacion,
                                    double precio, String tipo, String fortaleza, double tempAlmac,
                                    long cantExis, LocalDate fechaProd, LocalDate fechaVenc) {
        Medicamento m = new Medicamento();
        m.setNomComun(nomComun);
        m.setNomCientifico(nomCientifico);
        m.setPresentacion(presentacion);
        m.setPrecio(precio);
        m.setTipo(tipo);
        m.setFortalezaDelMed(fortaleza);
        m.setTempDeAlmac(tempAlmac);
        m.setCantExis(cantExis);
        m.setFechaDeProd(java.sql.Date.valueOf(fechaProd)); 
        m.setFechaDeVenc(java.sql.Date.valueOf(fechaVenc)); 
        medicamentos.put(nomComun, m);
    }

    private void agregarMedicamentoControlado(String nomComun, String nomCientifico, String presentacion,
                                            double precio, String tipo, String fortaleza, double tempAlmac,
                                            long cantExis, LocalDate fechaProd, LocalDate fechaVenc,
                                            String patologia, long cantAsig, long cantDisp) {
        MedicamentoControlado mc = new MedicamentoControlado();
        mc.setNomComun(nomComun);
        mc.setNomCientifico(nomCientifico);
        mc.setPresentacion(presentacion);
        mc.setPrecio(precio);
        mc.setTipo(tipo);
        mc.setFortalezaDelMed(fortaleza);
        mc.setTempDeAlmac(tempAlmac);
        mc.setCantExis(cantExis);
        mc.setFechaDeProd(java.sql.Date.valueOf(fechaProd)); 
        mc.setFechaDeVenc(java.sql.Date.valueOf(fechaVenc)); 
        mc.setPatologia(patologia);
        mc.setCantAsigMensual(cantAsig);
        mc.setCantDispensadaMensual(cantDisp);
        medicamentos.put(nomComun, mc);
    }

    private void inicializarTarjetones() {
        List<Paciente> listaPacientes = new ArrayList<Paciente>(pacientes.values());
        Collections.shuffle(listaPacientes);
        for (int i = 0; i < 15; i++) {
            Paciente p = listaPacientes.get(i);
            int numTarjetones = 1 + (i % 3);
            for (int j = 0; j < numTarjetones; j++) {
                LocalDate fechaExp = LocalDate.of(2024, 1 + (i % 12), 1 + (j * 10));
                LocalDate fechaVenc = fechaExp.plusMonths(6);
                List<Medicamento> medicamentosControlados = obtenerMedicamentos();
                Collections.shuffle(medicamentosControlados);
                int numMedicamentos = 1 + (j % 3);
                ArrayList<MedicamentoControlado> medsControlados = new ArrayList<MedicamentoControlado>();
                for (int k = 0; k < numMedicamentos && k < medicamentosControlados.size(); k++) {
                    medsControlados.add((MedicamentoControlado) medicamentosControlados.get(k));
                }
                Tarjeton t = new Tarjeton(
                    p.getNombre(),
                    p.getDireccion(),
                    java.sql.Date.valueOf(fechaExp), 
                    java.sql.Date.valueOf(fechaVenc), 
                    medsControlados
                );
                tarjetones.add(t);
                p.agregarTarjeton(t);
            }
        }
    }

    private void crearVentasLibres(List<Medicamento> medicamentos, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            LocalDate fechaVenta = LocalDate.of(2025, 1, 1).plusDays(i % 175);
            ArrayList<Medicamento> carrito = new ArrayList<Medicamento>();
            int numMedicamentos = 1 + (i % 4);
            Collections.shuffle(medicamentos);
            double importeTotal = 0;
            for (int j = 0; j < numMedicamentos && j < medicamentos.size(); j++) {
                Medicamento m = medicamentos.get(j);
                carrito.add(m);
                importeTotal += m.getPrecio();
                facturas.add(new Factura(
                    m.getNomComun(),
                    m.getNomCientifico(),
                    1,
                    java.sql.Date.valueOf(fechaVenta) 
                ));
            }
            VentaLibre venta = new VentaLibre(
                java.sql.Date.valueOf(fechaVenta),
                importeTotal
            );
            try {
                venta.setInventario(carrito);
            } catch (Exception e) {
                System.err.println("Error al crear venta libre: " + e.getMessage());
            }
            ventas.add(venta);
        }
    }

    private void crearVentasConPrescripcion(List<Medicamento> medicamentos, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            LocalDate fechaVenta = LocalDate.of(2025, 1, 1).plusDays(i % 175);
            LocalDate fechaCompra = fechaVenta.minusDays(1 + (i % 7));
            Medicamento m = medicamentos.get(i % medicamentos.size());
            double importeTotal = m.getPrecio() * (1 + (i % 3));
            VentaConPrescripcion venta = new VentaConPrescripcion(
                java.sql.Date.valueOf(fechaVenta), 
                importeTotal
            );
            try {
                venta.setFechaDeCompra(java.sql.Date.valueOf(fechaCompra)); 
            } catch (Exception e) {
                System.err.println("Error al crear venta con prescripción: " + e.getMessage());
            }
            ventas.add(venta);
            facturas.add(new Factura(
                m.getNomComun(),
                m.getNomCientifico(),
                1 + (i % 3),
                java.sql.Date.valueOf(fechaVenta) 
            ));
        }
    }

    private void crearVentasControladas(List<Medicamento> medicamentos, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            LocalDate fechaVenta = LocalDate.of(2025, 1, 1).plusDays(i % 175);
            MedicamentoControlado mc = (MedicamentoControlado) medicamentos.get(i % medicamentos.size());
            double importeTotal = mc.getPrecio() * (1 + (i % 2));
            VentaControlada venta = new VentaControlada(
                java.sql.Date.valueOf(fechaVenta), 
                importeTotal
            );
            ventas.add(venta);
            mc.setCantDispensadaMensual(mc.getCantDispensadaMensual() + (1 + (i % 2)));
            facturas.add(new Factura(
                mc.getNomComun(),
                mc.getNomCientifico(),
                1 + (i % 2),
                java.sql.Date.valueOf(fechaVenta) 
            ));
        }
    }

    private void crearVentasAlmohadillas(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            LocalDate fechaVenta = LocalDate.of(2025, 1, 1).plusDays(i % 175);
            double precioUnit = 8.50;
            int cant = 5 + (i % 6);
            double importeTotal = precioUnit * cant;
            AlmohadillasSanitarias venta = new AlmohadillasSanitarias(
                precioUnit,
                cant,
                java.sql.Date.valueOf(fechaVenta) 
            );
            ventas.add(venta);
            facturas.add(new Factura(
                "Almohadillas Sanitarias",
                "ALM-001",
                cant,
                java.sql.Date.valueOf(fechaVenta) 
            ));
        }
    }

    // Métodos de acceso
    public List<Paciente> obtenerPacientes() {
        return new ArrayList<Paciente>(pacientes.values());
    }

    public List<Medicamento> obtenerMedicamentos() {
        return new ArrayList<Medicamento>(medicamentos.values());
    }

    public List<Venta> obtenerVentas() {
        return new ArrayList<Venta>(ventas);
    }

    public List<Factura> obtenerFacturas() {
        return new ArrayList<Factura>(facturas);
    }

    public List<Tarjeton> obtenerTarjetones() {
        return new ArrayList<Tarjeton>(tarjetones);
    }

    // Métodos de registro
    public void registrarPaciente(Paciente paciente) {
        pacientes.put(paciente.getCi(), paciente);
    }

    public void registrarMedicamento(Medicamento medicamento) {
        medicamentos.put(medicamento.getNomComun(), medicamento);
    }

    public void registrarVenta(Venta venta) {
        ventas.add(venta);
    }

    public void registrarFactura(Factura factura) {
        facturas.add(factura);
    }

    public void registrarTarjeton(Tarjeton tarjeton) {
        tarjetones.add(tarjeton);
    }
}