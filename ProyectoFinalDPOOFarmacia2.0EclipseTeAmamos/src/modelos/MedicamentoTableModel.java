package modelos;

import Logica.Medicamento;
import Utiles.BaseDeDatos;

import java.text.SimpleDateFormat;
import java.util.List;

public class MedicamentoTableModel extends ModeloPrincipalTableModel<Medicamento> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public MedicamentoTableModel() {
        super(new String[]{"Nombre Com�n", "Nombre Cient�fico", "Presentaci�n", "Precio", 
                         "Tipo", "Fortaleza", "Cantidad", "Temperatura Almac�n", 
                         "Fecha Producci�n", "Fecha Vencimiento"});

    }

    @Override
    public void adicionar(Medicamento med) {
        addRow(new Object[]{
            med.getNomComun(),
            med.getNomCientifico(),
            med.getPresentacion(),
            String.format("%.2f", med.getPrecio()),
            med.getTipo(),
            med.getFortalezaDelMed(),
            med.getCantExis(),
            String.format("%.1f�C", med.getTempDeAlmac()),
            med.getFechaDeProd() != null ? dateFormat.format(med.getFechaDeProd()) : "",
            med.getFechaDeVenc() != null ? dateFormat.format(med.getFechaDeVenc()) : ""
        });
    }
}