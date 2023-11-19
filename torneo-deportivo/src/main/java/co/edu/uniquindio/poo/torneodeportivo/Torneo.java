/**
 * Clase que agrupa los datos de un Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Torneo {
    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    private final Collection<Participante> participantes;
    private final CaracterTorneo caracterTorneo;
    private final Collection<Equipo> listaEquipos;
    private final GeneroTorneo genero;
    private final Collection<Juez> jueces;
    private List<Enfrentamiento> listaEnfretamientos;

    public Torneo(String nombre, LocalDate fechaInicio,
            LocalDate fechaInicioInscripciones,
            LocalDate fechaCierreInscripciones, byte numeroParticipantes,
            byte limiteEdad, int valorInscripcion,TipoTorneo tipoTorneo, CaracterTorneo caracter,  GeneroTorneo genero) {
        
                
        ASSERTION.assertion( nombre != null , "El nombre es requerido");
        ASSERTION.assertion( numeroParticipantes >= 0, "El número de participantes no puede ser negativo");
        ASSERTION.assertion( limiteEdad >= 0,"El límite de edad no puede ser negativo");
        ASSERTION.assertion( valorInscripcion >= 0,"El valor de la inscripción no puede ser negativo");
        
        
        this.nombre = nombre;
        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones); 
        setFechaInicio(fechaInicio);
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.participantes = new LinkedList<>();
        this.caracterTorneo = Objects.requireNonNull(caracter,"El carácter del torneo es requerido");
        this.genero = Objects.requireNonNull(genero, "El género del torneo es requerido");
        this.jueces = new LinkedList<>();
        this.listaEquipos= new ArrayList<>();
        this.listaEnfretamientos= new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public CaracterTorneo getCaracter() {
        return caracterTorneo;
    }

    public GeneroTorneo getGenero() {
        return genero;
    }

    public Collection<Enfrentamiento> getEnfretamientos() {
        return listaEnfretamientos;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        ASSERTION.assertion( fechaInicio != null , "La fecha de inicio es requerida");
        ASSERTION.assertion( ( fechaInicioInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones) ) &&
                ( fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones) ),"La fecha de inicio no es válida" );
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        ASSERTION.assertion( fechaInicioInscripciones != null , "La fecha de inicio de inscripciones es requerida");
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }


    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        ASSERTION.assertion( fechaCierreInscripciones != null , "La fecha de cierre es requerida");
        ASSERTION.assertion( fechaCierreInscripciones.isAfter(fechaInicioInscripciones),"La fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones" );
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }
    
    /**
     * Permite registrar un participante en el torneo
     * @param participante Participante a ser registrado
     * @throws Se genera un error si ya existe un equipo registrado con el mismo nombre, o en caso de que las inscripciones del torneo no estén abiertas.
     */
    public void registrarParticipante(Participante participante) {
        validarParticipanteExiste(participante); 

         validarCaracter(participante);
         validarInscripcionesAbiertas(); 

        participantes.add(participante);
    }

    /**
     * Valida que el participante sea acorde con el carácter del torneo.
     * @param participante Participante a ser registrado
     */
    private void validarCaracter(Participante participante) {
        ASSERTION.assertion( caracterTorneo.esValido(participante),"Las inscripciones no están abiertas");
    }

    /**
     * Valida que las inscripciones del torneo esten abiertas, en caso de no estarlo genera un assertion error.
     */
    private void validarInscripcionesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now()) && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion( inscripcionAbierta,"Las inscripciones no están abiertas");
    }
    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de haberlo genera un assertion error.
     */
    private void validarParticipanteExiste(Participante participante) {
        boolean existeEquipo = buscarParticipantePorNombre(participante.getNombreCompleto()).isPresent();
        ASSERTION.assertion( !existeEquipo,"El equipo ya esta registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los participantes registrados.
     * @return Collection<Participante> no modificable de los participantes registrados en el torneo.
     */
    public Collection<Participante> getParticipantes() {
        return Collections.unmodifiableCollection(participantes);
    }
    
    /**
     * Permite buscar un participante por su nombre entre los participantes registrados en el torneo
     * @param nombre Nombre del participante que se está buscando
     * @return Un Optional<Participante> con el participante cuyo nombre sea igual al nombre buscado, o un Optional vacío en caso de no encontrar un participante con nombre igual al dado.
     */
    public Optional<Participante> buscarParticipantePorNombre(String nombre){
        Predicate<Participante> condicion = participante->participante.getNombreCompleto().equals(nombre);
        return participantes.stream().filter(condicion).findAny();
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     *  
     * @param nombre Nombre del equipo en que se desea registrar el jugador
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(String nombre, Jugador jugador) {
        var participante = buscarParticipantePorNombre(nombre);
        
        participante.ifPresent( (e)->{
            if( e instanceof Equipo equipo){
                registrarJugador(equipo, jugador);
            }
        } );
    }

    public List<Equipo> obtenerEquiposConResultados() {
        // Filtrar los equipos que tienen al menos un enfrentamiento registrado
        List<Equipo> equiposConResultados = listaEquipos.stream()
                .filter(equipo -> !equipo.getEnfrentamientos().isEmpty())
                .toList();

        return equiposConResultados;
    }
    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     * 
     * @param equipo Equipo en el que se desea registrar el jugador.
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Equipo equipo, Jugador jugador) {
        ASSERTION.assertion( !LocalDate.now().isAfter(fechaCierreInscripciones) , "No se pueden registrar jugadores después del a fecha de cierre de inscripciones");
        validarLimiteEdadJugador(jugador); 
        validarJugadorExiste(jugador);
        equipo.registrarJugador(jugador);
    }

    /**
     * Permite buscar un jugador basado en su nombre y apellido en todos los equipos registrados en el torneo.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador encontrado o un optional vacío en caso de no haber encontrado un jugador con el nombre y apellido del jugador buscado.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        return participantes.stream()
            .filter(p->p instanceof Equipo)
            .map(p->(Equipo)p)
            .map(equipo->equipo.buscarJugador(jugador))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }

    /**
     * Valida que no exista se puedan registrar jugadores que al momento del inicio del torneo excedan el limite de edad.
     */
    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadAlInicioTorneo = jugador.calcularEdad(fechaInicio);
        ASSERTION.assertion( limiteEdad == 0 || limiteEdad >= edadAlInicioTorneo , "No se pueden registrar jugadores que excedan el limite de edad del torneo"); 
    }

    public void registrarEnfrentamiento(Enfrentamiento enfrentamiento) {
        ASSERTION.assertion(enfrentamiento != null, "El enfrentamiento no puede ser nulo");
        listaEnfretamientos.add(enfrentamiento);
    }

    public List<Enfrentamiento> obtenerEnfrentamientosDeEquipo(Equipo equipo) {
        return listaEnfretamientos.stream()
            .filter(enfrentamiento -> enfrentamiento.getListaEquiposConPuntos().entrySet().stream()
                .anyMatch(entry -> entry.getKey().equals(equipo)))
            .collect(Collectors.toList());
    }  

    public List<Enfrentamiento> obtenerEnfrentamientosPorLicenciaJuez(String licenciaJuez) {
        // Filtrar los enfrentamientos por la licencia del juez
        return listaEnfretamientos.stream()
            .filter(enfrentamiento -> enfrentamiento.getJueces().stream()
                .anyMatch(juez -> juez.getLicencia().equals(licenciaJuez)))
            .collect(Collectors.toList());
    }

    public List<Enfrentamiento> obteneEnfrentamientos(){
        return listaEnfretamientos;

    }

    public Map<String, String> listadoEstadisticasEquipos() {
    Map<String, String> listaResultados = new HashMap<>();

    // Recorrer todos los equipos en el torneo
    for (Equipo equipo : listaEquipos) {
        int victorias = equipo.getVictorias();
        int derrotas = equipo.getDerrotas();
        int empates = equipo.getEmpates();

        // Calcular el total de enfrentamientos
        int totalEnfrentamientos = victorias + derrotas + empates;

        // Agregar estadísticas al mapa
        listaResultados.put(equipo.getNombreEquipo() + " - Victorias", String.valueOf(victorias));
        listaResultados.put(equipo.getNombreEquipo() + " - Derrotas", String.valueOf(derrotas));
        listaResultados.put(equipo.getNombreEquipo() + " - Empates", String.valueOf(empates));
        listaResultados.put(equipo.getNombreEquipo() + " - Total Enfrentamientos", String.valueOf(totalEnfrentamientos));
    }

    // Ordenar el mapa por número de victorias, empates y derrotas (en ese orden)
    listaResultados = listaResultados.entrySet().stream()
            .sorted(Map.Entry.<String, String>comparingByValue().reversed())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    return listaResultados;
}







    
    


    
}
