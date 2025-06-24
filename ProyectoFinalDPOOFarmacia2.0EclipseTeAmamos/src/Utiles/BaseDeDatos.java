package Utiles;

import java.util.*;
import java.time.*;
import java.sql.Date;

import Interfaces_Enum.Presentacion;
import Logica.*;
import LogicaUtiles.Factura;

public class BaseDeDatos {
    // Instancia única (Singleton)
    private static BaseDeDatos instancia;
    private final List<Venta> ventas;
    private final List<Factura> facturas;
    
    // Estructuras de almacenamiento
    private final Map<String, Paciente> pacientes;
    private final Map<String, Medicamento> medicamentos;

    private BaseDeDatos() {
        this.pacientes = new HashMap<String, Paciente>();
        this.medicamentos = new HashMap<String, Medicamento>();
        this.ventas = new ArrayList<Venta>();
        this.facturas = new ArrayList<Factura>();
        inicializarDatosPrueba();
    }

    public static synchronized BaseDeDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new BaseDeDatos();
        }
        return instancia;
    }

    private void inicializarDatosPrueba() {
        inicializarPacientes();
        inicializarMedicamentos();
        inicializarVentasYFacturas();
    }

    private void inicializarPacientes() {
        // 45 pacientes completamente definidos
        
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

    private void agregarPaciente(String nombre, String ci, char genero, LocalDate fechaNac, String direccion) {
        Paciente p = new Paciente();
        p.setNombre(nombre);
        p.setCi(ci);
        p.setGenero(genero);
        p.setFechaNacimiento(Date.from(fechaNac.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        p.setDireccion(direccion);
        pacientes.put(ci, p);
    }

    private void inicializarMedicamentos() {
        // 30 medicamentos completamente definidos
        
        // Venta libre (10)
        agregarMedicamento("Paracetamol", "Acetaminophen", "Tabletas", 5.99, "Venta libre", "500 mg", 25.0, 1000, 
                          LocalDate.of(2023, 1, 15), LocalDate.of(2025, 1, 15));
        agregarMedicamento("Ibuprofeno", "Ibuprofen", "Tabletas", 7.50, "Venta libre", "400 mg", 25.0, 800, 
                          LocalDate.of(2023, 2, 10), LocalDate.of(2025, 2, 10));
        agregarMedicamento("Aspirina", "Acetylsalicylic Acid", "Tabletas", 4.25, "Venta libre", "500 mg", 25.0, 1200, 
                          LocalDate.of(2023, 3, 5), LocalDate.of(2025, 3, 5));
        agregarMedicamento("Cetirizina", "Cetirizine", "Tabletas", 8.75, "Venta libre", "10 mg", 25.0, 600, 
                          LocalDate.of(2023, 4, 20), LocalDate.of(2025, 4, 20));
        agregarMedicamento("Loratadina", "Loratadine", "Tabletas", 9.99, "Venta libre", "10 mg", 25.0, 700, 
                          LocalDate.of(2023, 5, 15), LocalDate.of(2025, 5, 15));
        agregarMedicamento("Omeprazol", "Omeprazole", "Cápsulas", 12.50, "Venta libre", "20 mg", 25.0, 500, 
                          LocalDate.of(2023, 6, 10), LocalDate.of(2025, 6, 10));
        agregarMedicamento("Ranitidina", "Ranitidine", "Tabletas", 6.80, "Venta libre", "150 mg", 25.0, 900, 
                          LocalDate.of(2023, 7, 5), LocalDate.of(2025, 7, 5));
        agregarMedicamento("Diclofenaco", "Diclofenac", "Gel", 15.25, "Venta libre", "1%", 25.0, 400, 
                          LocalDate.of(2023, 8, 1), LocalDate.of(2025, 8, 1));
        agregarMedicamento("Naproxeno", "Naproxen", "Tabletas", 10.50, "Venta libre", "250 mg", 25.0, 750, 
                          LocalDate.of(2023, 9, 15), LocalDate.of(2025, 9, 15));
        agregarMedicamento("Salbutamol", "Salbutamol", "Inhalador", 18.99, "Venta libre", "100 mcg", 25.0, 300, 
                          LocalDate.of(2023, 10, 10), LocalDate.of(2025, 10, 10));

        // Con prescripción (10)
        agregarMedicamento("Amoxicilina", "Amoxicillin", "Cápsulas", 12.75, "Con prescripción", "250 mg", 20.0, 500, 
                          LocalDate.of(2023, 1, 20), LocalDate.of(2024, 1, 20));
        agregarMedicamento("Ciprofloxacino", "Ciprofloxacin", "Tabletas", 22.50, "Con prescripción", "500 mg", 20.0, 400, 
                          LocalDate.of(2023, 2, 15), LocalDate.of(2024, 2, 15));
        agregarMedicamento("Azitromicina", "Azithromycin", "Tabletas", 28.99, "Con prescripción", "250 mg", 20.0, 350, 
                          LocalDate.of(2023, 3, 10), LocalDate.of(2024, 3, 10));
        agregarMedicamento("Atorvastatina", "Atorvastatin", "Tabletas", 35.75, "Con prescripción", "20 mg", 20.0, 300, 
                          LocalDate.of(2023, 4, 5), LocalDate.of(2024, 4, 5));
        agregarMedicamento("Metformina", "Metformin", "Tabletas", 14.25, "Con prescripción", "500 mg", 20.0, 600, 
                          LocalDate.of(2023, 5, 1), LocalDate.of(2024, 5, 1));
        agregarMedicamento("Losartán", "Losartan", "Tabletas", 18.50, "Con prescripción", "50 mg", 20.0, 450, 
                          LocalDate.of(2023, 6, 20), LocalDate.of(2024, 6, 20));
        agregarMedicamento("Levotiroxina", "Levothyroxine", "Tabletas", 16.80, "Con prescripción", "50 mcg", 20.0, 500, 
                          LocalDate.of(2023, 7, 15), LocalDate.of(2024, 7, 15));
        agregarMedicamento("Sertralina", "Sertraline", "Tabletas", 42.99, "Con prescripción", "50 mg", 20.0, 250, 
                          LocalDate.of(2023, 8, 10), LocalDate.of(2024, 8, 10));
        agregarMedicamento("Tramadol", "Tramadol Hydrochloride", "Cápsulas", 30.50, "Con prescripción", "50 mg", 20.0, 200, 
                          LocalDate.of(2023, 9, 5), LocalDate.of(2024, 9, 5));
        agregarMedicamento("Dexametasona", "Dexamethasone", "Tabletas", 25.25, "Con prescripción", "4 mg", 20.0, 350, 
                          LocalDate.of(2023, 10, 1), LocalDate.of(2024, 10, 1));

        // Controlados (10)
        agregarMedicamentoControlado("Clonazepam", "Clonazepam", "Tabletas", 45.99, "Medicamento controlado", "2 mg", 20.0, 150, 
                                   "Ansiedad", LocalDate.of(2023, 1, 10), LocalDate.of(2024, 1, 10));
        agregarMedicamentoControlado("Alprazolam", "Alprazolam", "Tabletas", 52.50, "Medicamento controlado", "1 mg", 20.0, 120, 
                                   "Trastorno de pánico", LocalDate.of(2023, 2, 5), LocalDate.of(2024, 2, 5));
        agregarMedicamentoControlado("Metadona", "Methadone", "Tabletas", 65.75, "Medicamento controlado", "10 mg", 20.0, 80, 
                                   "Dependencia opioide", LocalDate.of(2023, 3, 1), LocalDate.of(2024, 3, 1));
        agregarMedicamentoControlado("Oxicodona", "Oxycodone", "Cápsulas", 78.99, "Medicamento controlado", "5 mg", 20.0, 60, 
                                   "Dolor crónico", LocalDate.of(2023, 4, 15), LocalDate.of(2024, 4, 15));
        agregarMedicamentoControlado("Diazepam", "Diazepam", "Tabletas", 38.50, "Medicamento controlado", "5 mg", 20.0, 100, 
                                   "Ansiedad", LocalDate.of(2023, 5, 10), LocalDate.of(2024, 5, 10));
        agregarMedicamentoControlado("Zolpidem", "Zolpidem", "Tabletas", 55.25, "Medicamento controlado", "10 mg", 20.0, 90, 
                                   "Insomnio", LocalDate.of(2023, 6, 5), LocalDate.of(2024, 6, 5));
        agregarMedicamentoControlado("Fentanilo", "Fentanyl", "Parche", 120.99, "Medicamento controlado", "25 mcg/h", 20.0, 40, 
                                   "Dolor severo", LocalDate.of(2023, 7, 1), LocalDate.of(2024, 7, 1));
        agregarMedicamentoControlado("Metilfenidato", "Methylphenidate", "Tabletas", 68.75, "Medicamento controlado", "10 mg", 20.0, 70, 
                                   "TDAH", LocalDate.of(2023, 8, 20), LocalDate.of(2024, 8, 20));
        agregarMedicamentoControlado("Codeína", "Codeine", "Jarabe", 42.50, "Medicamento controlado", "15 mg/5ml", 20.0, 110, 
                                   "Tos y dolor", LocalDate.of(2023, 9, 15), LocalDate.of(2024, 9, 15));
        agregarMedicamentoControlado("Morfina", "Morphine", "Inyección", 95.99, "Medicamento controlado", "10 mg/ml", 20.0, 50, 
                                   "Dolor severo", LocalDate.of(2023, 10, 10), LocalDate.of(2024, 10, 10));
    }

    private void agregarMedicamento(String nombre, String cientifico, String presentacion, 
                                  double precio, String tipo, String fortaleza, 
                                  double temp, long cantidad, LocalDate fechaProd, LocalDate fechaVenc) {
        Medicamento m = new Medicamento();
        m.setNomComun(nombre);
        m.setNomCientifico(cientifico);
        m.setPresentacion(presentacion);
        m.setPrecio(precio);
        m.setTipo(tipo);
        m.setFortalezaDelMed(fortaleza);
        m.setTempDeAlmac(temp);
        m.setCantExis(cantidad);
        m.setFechaDeProd(Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        m.setFechaDeVenc(Date.from(fechaVenc.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        medicamentos.put(nombre, m);
    }

    private void agregarMedicamentoControlado(String nombre, String cientifico, String presentacion, 
                                            double precio, String tipo, String fortaleza, 
                                            double temp, long cantidad, String patologia,
                                            LocalDate fechaProd, LocalDate fechaVenc) {
        MedicamentoControlado mc = new MedicamentoControlado();
        mc.setNomComun(nombre);
        mc.setNomCientifico(cientifico);
        mc.setPresentacion(presentacion);
        mc.setPrecio(precio);
        mc.setTipo(tipo);
        mc.setFortalezaDelMed(fortaleza);
        mc.setTempDeAlmac(temp);
        mc.setCantExis(cantidad);
        mc.setFechaDeProd(Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        mc.setFechaDeVenc(Date.from(fechaVenc.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        mc.setPatologia(patologia);
        mc.setCantAsigMensual(cantidad / 2);
        mc.setCantDispensadaMensual(0);
        medicamentos.put(nombre, mc);
    }

    private void inicializarVentasYFacturas() {
        // Fechas predefinidas para las ventas
        LocalDate fecha1 = LocalDate.of(2023, 10, 1);
        LocalDate fecha2 = LocalDate.of(2023, 10, 5);
        LocalDate fecha3 = LocalDate.of(2023, 10, 10);
        LocalDate fecha4 = LocalDate.of(2023, 10, 15);
        LocalDate fecha5 = LocalDate.of(2023, 10, 20);
        
        // 150 facturas distribuidas en los 3 tipos de ventas
        
        // Venta Libre (90 facturas)
        for (int i = 1; i <= 90; i++) {
            LocalDate fechaVenta;
            if (i <= 18) fechaVenta = fecha1;
            else if (i <= 36) fechaVenta = fecha2;
            else if (i <= 54) fechaVenta = fecha3;
            else if (i <= 72) fechaVenta = fecha4;
            else fechaVenta = fecha5;
            
            String medNombre;
            String medCientifico;
            double precio;
            int cantidad = (i % 5) + 1; // 1-5 unidades
            
            if (i % 10 == 1) {
                medNombre = "Paracetamol";
                medCientifico = "Acetaminophen";
                precio = 5.99;
            } else if (i % 10 == 2) {
                medNombre = "Ibuprofeno";
                medCientifico = "Ibuprofen";
                precio = 7.50;
            } 
            // ... continuar con los otros 8 medicamentos de venta libre
            else {
                medNombre = "Salbutamol";
                medCientifico = "Salbutamol";
                precio = 18.99;
            }
            
            // Crear venta
            VentaLibre vl = new VentaLibre(Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant()), 
                       precio * cantidad);
            ArrayList<Medicamento> carrito = new ArrayList<Medicamento>();
            carrito.add(medicamentos.get(medNombre));
            vl.setInventario(carrito);
            ventas.add(vl);
            
            // Crear factura
            facturas.add(new Factura(medNombre, medCientifico, cantidad, 
                                  Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        
        // Venta con Prescripción (30 facturas)
        for (int i = 1; i <= 30; i++) {
            LocalDate fechaVenta;
            if (i <= 6) fechaVenta = fecha1;
            else if (i <= 12) fechaVenta = fecha2;
            else if (i <= 18) fechaVenta = fecha3;
            else if (i <= 24) fechaVenta = fecha4;
            else fechaVenta = fecha5;
            
            String medNombre;
            String medCientifico;
            double precio;
            int cantidad = 1; // Siempre 1 unidad
            
            if (i % 10 == 1) {
                medNombre = "Amoxicilina";
                medCientifico = "Amoxicillin";
                precio = 12.75;
            } else if (i % 10 == 2) {
                medNombre = "Ciprofloxacino";
                medCientifico = "Ciprofloxacin";
                precio = 22.50;
            } 
            // ... continuar con los otros 8 medicamentos con prescripción
            else {
                medNombre = "Dexametasona";
                medCientifico = "Dexamethasone";
                precio = 25.25;
            }
            
            // Crear venta
            VentaConPrescripcion vcp = new VentaConPrescripcion(
                (Date) Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant()), 
                precio * cantidad);
            vcp.setFechaDeCompra((Date) Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ventas.add(vcp);
            
            // Crear factura
            facturas.add(new Factura(medNombre, medCientifico, cantidad, 
                                  Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        
        // Venta Controlada (20 facturas)
        for (int i = 1; i <= 20; i++) {
            LocalDate fechaVenta;
            if (i <= 4) fechaVenta = fecha1;
            else if (i <= 8) fechaVenta = fecha2;
            else if (i <= 12) fechaVenta = fecha3;
            else if (i <= 16) fechaVenta = fecha4;
            else fechaVenta = fecha5;
            
            String medNombre;
            String medCientifico;
            double precio;
            int cantidad = 1; // Siempre 1 unidad
            
            if (i % 10 == 1) {
                medNombre = "Clonazepam";
                medCientifico = "Clonazepam";
                precio = 45.99;
            } else if (i % 10 == 2) {
                medNombre = "Alprazolam";
                medCientifico = "Alprazolam";
                precio = 52.50;
            } 
            // ... continuar con los otros 8 medicamentos controlados
            else {
                medNombre = "Morfina";
                medCientifico = "Morphine";
                precio = 95.99;
            }
            
            // Crear venta
            VentaControlada vc = new VentaControlada(
                Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant()), 
                precio * cantidad);
            ventas.add(vc);
            
            // Crear factura
            facturas.add(new Factura(medNombre, medCientifico, cantidad, 
                                  Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
        
        // Almohadillas Sanitarias (10 facturas)
        for (int i = 1; i <= 10; i++) {
            LocalDate fechaVenta;
            if (i <= 2) fechaVenta = fecha1;
            else if (i <= 4) fechaVenta = fecha2;
            else if (i <= 6) fechaVenta = fecha3;
            else if (i <= 8) fechaVenta = fecha4;
            else fechaVenta = fecha5;
            
            double precioUnit = 8.50;
            int cantidad = (i % 5) + 1; // 1-5 paquetes
            
            // Crear venta
            AlmohadillasSanitarias as = new AlmohadillasSanitarias(
                precioUnit, cantidad, 
                Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            ventas.add(as);
            
            // Crear factura
            facturas.add(new Factura(
                "Almohadillas Sanitarias", 
                "ALM-001", 
                cantidad, 
                Date.from(fechaVenta.atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }
    }

    // Métodos de consulta
    public List<Paciente> obtenerPacientes() 
    {
        return new ArrayList<Paciente>(pacientes.values());
    }

    public List<Medicamento> obtenerMedicamentos() 
    {
        return new ArrayList<Medicamento>(medicamentos.values());
    }

    public List<Venta> obtenerVentas() 
    {
        return new ArrayList<Venta>(ventas);
    }

    public List<Factura> obtenerFacturas() 
    {
        return new ArrayList<Factura>(facturas);
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.put(paciente.getCi(), paciente);
    }

    public void registrarMedicamento(Medicamento medicamento) {
        medicamentos.put(medicamento.getNomComun(), medicamento);
    }

    public void registrarVenta(Venta venta) {
        ventas.add(venta);
    }
}