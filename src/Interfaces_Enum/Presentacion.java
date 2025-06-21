package Interfaces_Enum;

public enum Presentacion {
	TABLETAS("Tabletas"),
    CAPSULAS("C�psulas"),
    JARABE("Jarabe"),
    CREMA("Crema"),
    POMADA("Pomada"),
    GEL("Gel"),
    INYECTABLE("Inyectable"),
    SUSPENSION("Suspensi�n"),
    POLVO("Polvo"),
    GRAGEA("Gragea"),
    SUPOSITORIO("Supositorio"),
    SOLUCION("Soluci�n"),
    UNGUENTO("Ung�ento"),
    AEROSOL("Aerosol"),
    PARCHE("Parche"),
    GOTAS("Gotas"),
    COLIRIO("Colirio"),
    SHAMPOO("Shampoo medicinal"),
    LOCOION("Loci�n"),
    INHALADOR("Inhalador");

    private final String descripcion;

    Presentacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
