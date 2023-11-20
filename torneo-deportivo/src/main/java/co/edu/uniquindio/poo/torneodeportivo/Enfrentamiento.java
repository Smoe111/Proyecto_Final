package co.edu.uniquindio.poo.torneodeportivo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    private EstadoEnfrentamiento estado;
    private Collection<Equipo> listaEquipos = new ArrayList<>();
   

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
        this.estado= EstadoEnfrentamiento.PENDIENTE;

        ASSERTION.assertion( nombreLugar != null && !nombreLugar.isBlank() , "El nombre del lugar es requerido");
        ASSERTION.assertion( ubicacionLugar!= null && !ubicacionLugar.isBlank() , "El nombre es requerido");
        ASSERTION.assertion( fechaHoraEnfrentamiento != null , "La fecha es requerida");
        ASSERTION.assertion( equipoVisitante != null );
        ASSERTION.assertion( jueces != null );
    }


    public Enfrentamiento (String nombreLugar, String ubicacionLugar, LocalDateTime fechaHoraEnfrentamiento, Collection<Juez> jueces,
            Equipo equipoLocal, Equipo equipoVisitante){

                ASSERTION.assertion( nombreLugar != null && !nombreLugar.isBlank() , "El nombre del lugar es requerido");
        ASSERTION.assertion( ubicacionLugar!= null && !ubicacionLugar.isBlank() , "El nombre es requerido");
        ASSERTION.assertion( fechaHoraEnfrentamiento != null , "La fecha es requerida");
        ASSERTION.assertion( equipoVisitante != null );
        ASSERTION.assertion( jueces != null );

        this.nombreLugar = nombreLugar;
        this.ubicacionLugar = ubicacionLugar;
        this.fechaHoraEnfrentamiento = fechaHoraEnfrentamiento;
        this.jueces = jueces;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;

            }

    

    public Enfrentamiento(String nombreLugar, String ubicacionLugar, Equipo equipoLocal, Equipo equipoVisitante) {
        this.nombreLugar = nombreLugar;
        this.ubicacionLugar = ubicacionLugar;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    

    public Enfrentamiento(String nombreLugar, String ubicacionLugar, Equipo equipoLocal, Equipo equipoVisitante,
            int puntosEquipoLocal, int puntosEquipoVisitante) {
        this.nombreLugar = nombreLugar;
        this.ubicacionLugar = ubicacionLugar;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.puntosEquipoLocal = puntosEquipoLocal;
        this.puntosEquipoVisitante = puntosEquipoVisitante;
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

    public Collection<Equipo> getListaEquipos() {
        return listaEquipos;
    }


    public String estadoEnfrentamiento(LocalDateTime fechaHoraActual) {
        String estado = "";
    
        if (fechaHoraActual == null) {
            throw new IllegalArgumentException("La fechaHoraActual no puede ser nula");
        }
    
        if (fechaHoraActual.isBefore(fechaHoraEnfrentamiento)) {
            estado = EstadoEnfrentamiento.PENDIENTE.name();
        } else if (fechaHoraActual.isBefore(fechaHoraEnfrentamiento.plusMinutes(15))) {
            estado = EstadoEnfrentamiento.EN_JUEGO.name();
        } else if (calcularResultado() != null && fechaHoraActual.isAfter(fechaHoraEnfrentamiento.plusMinutes(120))) {
            estado = EstadoEnfrentamiento.FINALIZADO.name();
        }
    
        return estado;
    }
    

    public String definirEstadoEnfretamientonAplazado(){
        return EstadoEnfrentamiento.APLAZADO.name();
    }


    public Map<Equipo, String> calcularResultado() {
        Map<Equipo, String> resultados = new HashMap<>();

        if (puntosEquipoLocal > puntosEquipoVisitante) {
            resultados.put(equipoLocal, "Victoria");
            resultados.put(equipoVisitante, "Derrota");
        } else if (puntosEquipoLocal < puntosEquipoVisitante) {
            resultados.put(equipoLocal, "Derrota");
            resultados.put(equipoVisitante, "Victoria");
        } else {
            resultados.put(equipoLocal, "Empate");
            resultados.put(equipoVisitante, "Empate");
        }

        return resultados;
    }

    
}



