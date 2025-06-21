import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paciente {
    protected String nombre;
    protected String ci;
    protected String direccion;
    protected Date fechaNacimiento;
    protected char genero;
    protected NucleoFamiliar nucleo;
    protected List<Tarjeton> tarjetones;
    protected boolean esControlado;

    // Constructor básico
    public Paciente() {
        this.tarjetones = new ArrayList<Tarjeton>();
    }

    // Constructor completo
    public Paciente(String nombre, String ci, String direccion, Date fechaNacimiento, char genero) {
        this.nombre = nombre;
        this.ci = ci;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.tarjetones = new ArrayList<Tarjeton>();
        this.esControlado = false;
    }

    // Métodos para manejar tarjetones
    public void agregarTarjeton(Tarjeton tarjeton) {
        if (tarjeton == null) {
            throw new IllegalArgumentException("El tarjetón no puede ser nulo");
        }
        if (tarjetones.size() >= 3) { // Límite de 3 tarjetones por paciente
            throw new IllegalStateException("No se pueden agregar más tarjetones. Límite alcanzado");
        }
        this.tarjetones.add(tarjeton);
        this.esControlado = true;
    }

    public List<Tarjeton> obtenerTarjetones() {
        return new ArrayList<>(tarjetones); // Devuelve copia para proteger encapsulamiento
    }

    public boolean removerTarjeton(Tarjeton tarjeton) {
        return tarjetones.remove(tarjeton);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        if (ci == null || ci.length() != 11) {
            throw new IllegalArgumentException("El CI debe tener 11 dígitos");
        }
        this.ci = ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        if (genero != 'M' && genero != 'F') {
            throw new IllegalArgumentException("Género debe ser 'M' o 'F'");
        }
        this.genero = genero;
    }

    public NucleoFamiliar getNucleo() {
        return nucleo;
    }

    public void setNucleo(NucleoFamiliar nucleo) {
        this.nucleo = nucleo;
    }

    public boolean esControlado() {
        return esControlado;
    }

    // Método para verificar si el paciente pertenece a un núcleo familiar
    public boolean perteneceANucleo() {
        return nucleo != null;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", ci='" + ci + '\'' +
                ", genero=" + genero +
                ", esControlado=" + esControlado +
                '}';
    }
}