package inicializacion;

import java.util.Date;
import java.util.List;

import Logica.*;
import LogicaUtiles.*;
import Utiles.BaseDeDatos;


public final class InicializadoraBaseDatos {
    private InicializadoraBaseDatos() {}
    
    public static void inicializarTodo() {
        BaseDeDatos bd = BaseDeDatos.obtenerInstancia();
        inicializarPacientes(bd);
        inicializarMedicamentos(bd);
        inicializarVentas(bd);
        inicializarFacturas(bd);
    }

    private static void inicializarPacientes(BaseDeDatos bd) {
        for(int i = 0; i < 50; i++) { // 50 pacientes de prueba
            char genero = i % 2 == 0 ? 'M' : 'F';
            Paciente paciente = new Paciente();
            
            paciente.setNombre(BaseDeDatos.generarNombreCompleto(genero));
            paciente.setCi(BaseDeDatos.generarCarnetIdentidad(genero, new Date()));
            paciente.setFechaNacimiento(new Date()); // Fecha actual como ejemplo
            paciente.setDireccion("Calle " + (i+1) + ", #" + (i*10));
            
            bd.registrarPaciente(paciente);
        }
    }

    private static void inicializarMedicamentos(BaseDeDatos bd) {
        String[] tipos = {"Venta libre", "Con prescripción", "Controlado"};
        
        for(int i = 0; i < 30; i++) { // 30 medicamentos de prueba
            Medicamento med = new Medicamento();
            
            med.setNomComun("Medicamento " + (i+1));
            med.setNomCientifico("SciName-" + (i+1));
            med.setPrecio(5 + (i * 3));
            med.setTipo(tipos[i % tipos.length]);
            med.setCantExis(100 - i);
            
            bd.registrarMedicamento(med);
        }
    }

    private static void inicializarVentas(BaseDeDatos bd) {
        for(int i = 0; i < 100; i++) { // 100 ventas de prueba
            Venta venta;
            
            if(i % 3 == 0) {
                venta = new VentaLibre(new Date(), 50 + i);
            } else if(i % 3 == 1) {
                venta = new VentaConPrescripcion(new Date(), 75 + i);
            } else {
                venta = new VentaControlada(new Date(), 60 + i);
            }
            
            bd.registrarVenta(venta);
        }
    }

    private static void inicializarFacturas(BaseDeDatos bd) {
        List<Medicamento> medicamentos = bd.obtenerMedicamentos();
        
        for(int i = 0; i < 50; i++) { // 50 facturas de prueba
            Medicamento med = medicamentos.get(i % medicamentos.size());
            Factura factura = new Factura(
                med.getNomComun(),
                med.getNomCientifico(),
                1 + (i % 5), // Cantidad entre 1-5
                new Date()
            );
            
            bd.obtenerFacturas().add(factura);
        }
    }
}
