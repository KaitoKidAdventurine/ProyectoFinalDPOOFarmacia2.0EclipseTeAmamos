package Interfaces_Enum;
import java.util.ArrayList;

import proyecto.VentaDeMedicamentos;
import Logica.Tarjeton;

public interface Reportes {
	public ArrayList<Logica.VentaDeMedicamentos> medicamentosMasVendidos();
	public long cantDeAlmohadillasNecesarias();
	public double comparacionDeVentasMensuales();
	public ArrayList<Tarjeton> registroDeIncumplimiento();

}
