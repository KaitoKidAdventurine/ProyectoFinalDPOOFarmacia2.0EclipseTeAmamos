package modelos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import Logica.Farmacia;
import Logica.MedicamentoControlado;
import Logica.Tarjeton;

public class TarjetonesIncumplidosTableModel extends ModeloPrincipalTableModel<Tarjeton> {

    private static final long serialVersionUID = 1L;

    public TarjetonesIncumplidosTableModel() 
    {
        super(new String[] 
        {
            "Nombre Paciente",
            "Dirección",
            "Fecha Expedición", 
            "Fecha Vencimiento",
            "Medicamentos Asociados"
        });
    }

    @Override
    public void adicionar(Tarjeton t) {
        // Creamos una fila con los datos del Tarjeton
        Object[] fila = new Object[getColumnCount()]; // 5 columnas

        // Llenamos cada celda según la posición
        fila[0] = t.getNombre(); // Nombre del paciente
        fila[1] = t.getDireccionPaciente(); // Dirección del paciente
        fila[2] = formatDate(t.getFechaExpedicion()); // Fecha de expedición
        fila[3] = formatDate(t.getFechaVencimiento()); // Fecha de vencimiento
        
        // Mostrar nombres de medicamentos separados por coma
        if (t.getMedicamentosConts() != null && !t.getMedicamentosConts().isEmpty()) 
        {
            StringBuilder meds = new StringBuilder();
            for (MedicamentoControlado med : t.getMedicamentosConts()) 
            {
                meds.append(med.getNomComun()).append(", ");
            }
            fila[4] = meds.toString().replaceAll(", $", ""); 
        } 
        
        else 
        {
            fila[4] = "Ninguno";
        }

        // Añadir fila al modelo de tabla
        addRow(fila);
    }

    // Método auxiliar para formatear fechas
    private String formatDate(java.util.Date date) 
    {
        if (date != null) 
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }
        return "N/A";
    }

 
}