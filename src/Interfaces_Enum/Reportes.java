package Interfaces_Enum;
import java.util.ArrayList;
import Logica.Tarjeton;

public interface Reportes {
	public ArrayList<String> medicamentoMasVendido();
	public long cantDeAlmohadillasNecesarias();
	public double comparacionDeVentasMensuales();
	public ArrayList<Tarjeton> registroDeIncumplimiento();

}
