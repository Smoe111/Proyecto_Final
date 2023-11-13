package co.edu.uniquindio.poo.torneodeportivo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;


public class Enfrentamiento {

    private String nombreLugar;
    private String ubicacionLugar;
    private LocalDateTime fechaHoraEnfrentamiento;
    private Collection<Juez> jueces;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int puntosEquipoLocal;
    private int puntosEquipoVisitante;


public Enfrentamiento(String nombreLugar, String ubicacionLugar, LocalDateTime fechaHoraEnfrentamiento, Collection<Juez> jueces,
            Equipo equipoLocal, Equipo equipoVisitante, int puntosEquipoLocal, int puntosEquipoVisitante) {
        this.nombreLugar = nombreLugar;
        this.ubicacionLugar = ubicacionLugar;
        this.fechaHoraEnfrentamiento = fechaHoraEnfrentamiento;
        this.jueces = jueces;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.puntosEquipoLocal = puntosEquipoLocal;
        this.puntosEquipoVisitante = puntosEquipoVisitante;

        ASSERTION.assertion( nombreLugar != null && !nombreLugar.isBlank() , "El nombre del lugar es requerido");
        ASSERTION.assertion( ubicacionLugar!= null && !ubicacionLugar.isBlank() , "El nombre es requerido");
        ASSERTION.assertion( fechaHoraEnfrentamiento != null , "La fecha es requerida");
        ASSERTION.assertion( equipoVisitante != null );
        ASSERTION.assertion( jueces != null );
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getUbicacionLugar() {
        return ubicacionLugar;
    }

    public Collection<Juez> getJueces() {
        return jueces;
    }

    public void setJueces(Collection<Juez> jueces) {
        this.jueces = new ArrayList<>(jueces);
    }

    public void setUbicacionLugar(String ubicacionLugar) {
        this.ubicacionLugar = ubicacionLugar;
    }

    public LocalDateTime getFechaHoraEnfrentamiento() {
        return fechaHoraEnfrentamiento;
    }

    public void setFechaHoraEnfrentamiento(LocalDateTime fechaHoraEnfrentamiento) {
        this.fechaHoraEnfrentamiento = fechaHoraEnfrentamiento;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }
    
    public int getPuntosEquipoLocal() {
        return puntosEquipoLocal;
    }

    public void setPuntosEquipoLocal(int puntosEquipoLocal) {
        this.puntosEquipoLocal = puntosEquipoLocal;
    }

    public int getPuntosEquipoVisitante() {
        return puntosEquipoVisitante;
    }

    public void setPuntosEquipoVisitante(int puntosEquipoVisitante) {
        this.puntosEquipoVisitante = puntosEquipoVisitante;
    }


    public String estadoEnfrentamiento() {
        LocalDateTime fechaHoraActualesEnfrentamiento = LocalDateTime.now();
        String estado;
    
        if (fechaHoraActualesEnfrentamiento.isBefore(fechaHoraEnfrentamiento)) {
            estado = "PENDIENTE";
        } else if (fechaHoraActualesEnfrentamiento.isEqual(fechaHoraEnfrentamiento)) {
            estado = "EN JUEGO";
        } else if (calcularResultado() != null) {
            estado = "FINALIZADO";
        } else {
            estado = "APLAZADO";
        }
    
        return estado;
    }
    
    public String calcularResultado() {
        if (puntosEquipoLocal > puntosEquipoVisitante) {
            return "victoria";
        } else if (puntosEquipoLocal == puntosEquipoVisitante) {
            return "empate";
        } else {
            return "derrota";
        }
    }
}


