package modelos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
		// TODO Auto-generated method stub
		
	}

 
}