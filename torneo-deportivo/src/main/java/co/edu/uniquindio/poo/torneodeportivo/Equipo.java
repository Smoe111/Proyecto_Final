/**
 * Registro que agrupa los datos de un Equipo
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Equipo implements Participante {
    private String nombre;
    private Persona representante;
    private Collection<Jugador> jugadores;
    private int victorias;
    private int empates;
    private int derrotas;
    private List<Enfrentamiento> enfrentamientos;
     
    public Equipo(String nombre, Persona representante, Collection<Jugador> jugadores, int victorias, int empates,
            int derrotas, List<Enfrentamiento> enfrentamientos) {

        ASSERTION.assertion( nombre != null && !nombre.isBlank() , "El nombre es requerido");
        ASSERTION.assertion( representante != null , "El representante es requerido");
        this.nombre = nombre;
        this.representante = representante;
        this.jugadores = jugadores;
        this.victorias = victorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.enfrentamientos = new ArrayList<>();
    }

    // Es un constructor adicional que se utiliza para crear un nuevo objeto Equipo con valores iniciales cuando no se especifican los jugadores ni las estadísticas de victorias, empates y derrotas.
    public Equipo(String nombre,Persona representante){
        this(nombre,representante,new LinkedList<>(), 0, 0, 0, new LinkedList<>());
        this.enfrentamientos = new ArrayList<>();
    }

    public Equipo(String nombre2, Persona representante2, List<Enfrentamiento> listaEnfrentamientos) {

        this.nombre= nombre2;
        this.representante= representante2;
        this.enfrentamientos= new ArrayList<>();
    }

    
    /**
     * Permite registrar un jugador en un equipo siempre y cuando no exista ya un jugador registrado en el equipo con el mismo nombre y apellido
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Jugador jugador) {
        validarJugadorExiste(jugador);
        jugadores.add(jugador);
    }

    
    public Collection<Jugador> jugadores() {
        return jugadores;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public Persona getRepresentante() {
        return representante;
    }
    
    public String getNombreEquipo(){
        return nombre;
    }

    @Override
    public String getNombreCompleto() {
        return nombre;
    }
    
    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento) {
        enfrentamientos.add(enfrentamiento);
    }

    public Collection<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }
    /**
     * Permite agregar un enfrentamiento a la lista de enfrentamientos del equipo.
     * @param enfrentamiento Enfrentamiento a ser agregado.
     */
    

    /**
     * Permite buscar un jugador en el equipo basado en su nombre y apellido.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador que coincida con el nombre y apellido del jugador buscado, 
     * o Optional vacío en caso de no encontrar un jugador en el equipo con dicho nombre y apellido.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        Predicate<Jugador> nombreIgual = j->j.getNombre().equals(jugador.getNombre());
        Predicate<Jugador> apellidoIgual = j->j.getApellido().equals(jugador.getApellido());
        return jugadores.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya está registrado");
    }

    public Equipo registrarResultadoEquipo(Enfrentamiento resultado, Equipo equipo) {
        int newVictorias = victorias;
        int newEmpates = empates;
        int newDerrotas = derrotas;
        Map<Equipo, String> resultadoEnfrentamiento = resultado.calcularResultado();

        if (resultadoEnfrentamiento.get(equipo).equals("Victoria")) {
            newVictorias++;
        } else if (resultadoEnfrentamiento.get(equipo).equals("Empate")) {
            newEmpates++;
        } else if (resultadoEnfrentamiento.get(equipo).equals("Derrota")) {
            newDerrotas++;
        }

        List<Enfrentamiento> nuevosEnfrentamientosEquipo = new LinkedList<>(enfrentamientos);
        nuevosEnfrentamientosEquipo.add(resultado);

        victorias = newVictorias;
        empates = newEmpates;
        derrotas = newDerrotas;
        enfrentamientos = nuevosEnfrentamientosEquipo;

        // Devolver la instancia actualizada
        return new Equipo(nombre, representante, jugadores, newVictorias, newEmpates, newDerrotas, nuevosEnfrentamientosEquipo);
    }
    
   
}
