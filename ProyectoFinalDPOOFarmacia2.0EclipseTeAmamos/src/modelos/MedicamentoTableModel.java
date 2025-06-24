package modelos;

import Logica.Medicamento;
import Utiles.BaseDeDatos;

import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class MedicamentoTableModel extends ModeloPrincipalTableModel<Medicamento> 
{
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public MedicamentoTableModel() {
        super(new String[] {
            "Nombre Común", 
            "Nombre Científico", 
            "Presentación",
            "Precio", 
            "Tipo", 
            "Fortaleza", 
            "Stock",
            "Temperatura Almacén",
            "Fecha Producción", 
            "Fecha Vencimiento"
        });
        
        // Cargar datos iniciales
        cargar(BaseDeDatos.obtenerInstancia().obtenerMedicamentos());
    }

    public void adicionar(Medicamento med) {
        Object[] fila = {
            med.getNomComun(),
            med.getNomCientifico(),
            med.getPresentacion(),
            String.format("%.2f", med.getPrecio()),
            med.getTipo(),
            med.getFortalezaDelMed(),
            String.format("%.1f°C", med.getTempDeAlmac()),
            med.getCantExis(),
            med.getFechaDeProd(),
            med.getFechaDeVenc()
        };
        this.addRow(fila);
    }
    
    public void cargar(List<Medicamento> listaMedicamentos) 
    {
        // Limpio las filas anteriores si ya había datos
        setRowCount(0);

        for (Medicamento med : listaMedicamentos) {
            adicionar(med); 
        }

    
    }
}