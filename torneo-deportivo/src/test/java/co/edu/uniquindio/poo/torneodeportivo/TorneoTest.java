/**
 * Clase para probar el funcionamiento del Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.util.AssertionUtil;

public class TorneoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());
    
    /**
     * Verificar que la clase Torneo almacene y recupere los datos 
     * 
     */
    @Test
    public void datosCompletos() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL|GRUPAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1), LocalDate.of(2023, 9, 15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL , GeneroTorneo.MASCULINO);

        // Recuperación y verificación de datos
        assertEquals("Copa Mundo",torneo.getNombre());
        assertEquals(LocalDate.of(2023, 10, 1),torneo.getFechaInicio());
        assertEquals(LocalDate.of(2023, 8, 1),torneo.getFechaInicioInscripciones());
        assertEquals(LocalDate.of(2023, 9, 15),torneo.getFechaCierreInscripciones());
        assertEquals((byte)24,torneo.getNumeroParticipantes());
        assertEquals((byte)0,torneo.getLimiteEdad());
        assertEquals(0,torneo.getValorInscripcion());
        assertEquals(TipoTorneo.LOCAL,torneo.getTipoTorneo());
        assertEquals(CaracterTorneo.GRUPAL, torneo.getCaracter());
        assertEquals(GeneroTorneo.MASCULINO, torneo.getGenero());
        LOG.info("Fin de prueba datos completos...");
    }

    /**
     * Verificar que la clase Torneo valide que se ingrese los datos
     * 
     */
    @Test
    public void datosNulos() {
        LOG.info("Inicio de prueba datos nulos...");
        // Almacenar los datos de prueba null|null|null|null|24|0|0|null|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo(null, null, null, null, (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO));
        
        
        LOG.info("Fin de prueba datos nulos...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de número de participantes negativo 
     * 
     */
    @Test
    public void participantesNegativos() {
        LOG.info("Inicio de prueba número de participantes negativo...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|-24|0|0|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)-24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO));
        
        LOG.info("Fin de prueba  número de participantes negativo...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de limites de edades negativo 
     * 
     */
    @Test
    public void limiteEdadesNegativo() {
        LOG.info("Inicio de prueba limites de edades negativo...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|-1|0|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)24, (byte)-1, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL , GeneroTorneo.MIXTO));
        
        LOG.info("Fin de prueba  limites de edades negativo...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de valor de inscripción negativa
     * 
     */
    @Test
    public void inscripcionNegativa() {
        LOG.info("Inicio de prueba inscripción negativa...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|-1|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)24, (byte)0, -1,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL , GeneroTorneo.MASCULINO));
        
        LOG.info("Fin de prueba inscripción negativa...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de inscripciones posteriores a la 
     * fecha de inicio del torneo
     * 
     */
    @Test
    public void inscripcionTardia() {
        LOG.info("Inicio de prueba inscripción tardia...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 01), LocalDate.of(2023, 11, 15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO));
        
        LOG.info("Fin de prueba inscripción tardia...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de inicio inscripciones posteriores a 
     * la fecha de cierre de inscripciones
     * 
     */
    @Test
    public void cierreInscripcionAnteriorInicio() {
        LOG.info("Inicio de prueba Cierre inscripción anterior al inicio...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 1), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO));
        
        LOG.info("Fin de prueba Cierre inscripción anterior al inicio...");
    }

    /**
     * Verificar el registro exitoso de un participante y la existencia de este en el torneo
     */
    @Test
    public void verificarExistenciaYRegistroParticipanteExitoso() {
        LOG.info("Iniciando prueba para verificar registro de participate exitoso");
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        Participante participante = new Equipo("Tigres", representante);
         
        torneo.registrarParticipante(participante);
        assertTrue(torneo.getParticipantes().contains(participante));
    


        LOG.info("Fin prueba para verificar registro de participate exitoso");
    }

    /**
     * Verificar que el registro de un participante no se haga si ya existe uno con su mismo nombre
     */
    
    @Test
    public void registrarParticipanteRepetido() {

        LOG.info("Iniciando prueba para verificar registro de participate existente");
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        Participante participante1 = new Equipo("Tigres", representante);
        Participante participante2 = new Equipo("Tigres", representante);
         
        torneo.registrarParticipante(participante1);
        assertThrows(RuntimeException.class, ()-> torneo.registrarParticipante(participante2));
    
        LOG.info("Fin prueba para verificar registro de participate existente");
    }


    /**
     * Verificar que el registro de un participe¿ante no se realice si las fechas de inscripciones no están abiertas para realizar la acción 
     */
    @Test
    public void registrarParticipanteFechaInicioInscripcionNoValida() {
        LOG.info("Iniciando prueba para verificar registro de participate con fecha de inicio de inscripción no valida");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 3, 12), LocalDate.of(2024, 4, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        Participante participante = new Equipo("Tigres", representante);
        
        // Intentar registrar participante con fecha de inscripción no válida
        assertThrows(RuntimeException.class, () -> torneo.registrarParticipante(participante));

        // Verificar que el participante no se haya registrado
        assertFalse(torneo.getParticipantes().contains(participante));

        LOG.info("Fin prueba para verificar registro de participate con fecha de inicio de inscripción no valida");
    }

    /**
     * Verificar que se encuentre un participante existente en el torneo dado su nombre
     */
    @Test
    public void buscarParticipanteExistentePorNombre(){
        LOG.info("Inicio prueba buscar participante por su nombre");
        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO);

        var representante = new Persona ("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        var equipo1 = new Equipo ("Tigres" , representante);
        var equipo2 = new Equipo ("Panteras" , representante);
        
        torneo.registrarParticipante(equipo1);
        torneo.registrarParticipante(equipo2);

        Optional<Participante> participanteEncontrado = torneo.buscarParticipantePorNombre("Tigres");

        assertTrue(participanteEncontrado.isPresent()); // Verificar que el participante fue encontrado
        assertEquals("Tigres", participanteEncontrado.get().getNombreCompleto()); // Verificar el nombre del participante

        LOG.info("Fin prueba buscar participante por su nombre");

    }

    /**
     *ayuda a verificar que el Optional devuelto por buscarParticipantePorNombre está vacío cuando el participante no  está presente en el torneo.
     */
    @Test
    public void buscarParticipanteNoExistentePorNombre(){
        LOG.info("Inicio prueba buscar participante no existente por su nombre");
        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        var equipo = new Equipo("Tigres", representante);
        
        torneo.registrarParticipante(equipo);
        Optional<Participante> participanteBuscado = torneo.buscarParticipantePorNombre("Panteras");
        assertFalse(participanteBuscado.isPresent());

        LOG.info("Fin prueba buscar participante no existente por su nombre");
        
    }
    /**
     * verificar que un jugador quede registrado correctamente en un equipo dentro de un torneo
     */
    @Test
    public void verificarRegistroJugadorAEquipo(){
        LOG.info("Inicio prueba para verificar que un jugador quede registrado en un equipo");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO);

        var representante = new Persona ("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        var equipo = new Equipo("Tigres", representante);

        torneo.registrarParticipante(equipo);

        var jugador = new Jugador("Sara", "Acosta", "sVaB@gamil.com", "3145290574", LocalDate.of(2005, 9, 1), GeneroTorneo.FEMENINO);

        torneo.registrarJugador("Tigres", jugador);
        assertTrue(equipo.jugadores().contains(jugador));
        
        LOG.info("Fin prueba para verificar que un jugador quede registrado en un equipo");
    }
    /**
     * verificar que al momento de registrar un jugador a equipo no exista ya un jugador registrado con el mismo nombre y apellido y si lo hay no lo registre en el equipo
     */
    @Test
    public void verificarRegistroJugadorConMismoNombreAEquipo(){

        LOG.info("Inicio prueba para verificar que al momento de registrar un jugador a equipo no exista ya un jugador registrado con el mismo nombre y apellido");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte)24, (byte)0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300"); 

        var equipo = new Equipo ("Tigres", representante);

        torneo.registrarParticipante(equipo);

        var jugador = new Jugador("Sara", "Echeverry", "sEuQ@gamil.com", "3145290574", LocalDate.of(2006, 6, 5), GeneroTorneo.FEMENINO);
        var jugador2 = new Jugador("Sara", "Echeverry", "sEuQ@gamil.com", "3145290574", LocalDate.of(2006, 6, 5), GeneroTorneo.FEMENINO);

        torneo.registrarJugador("Tigres", jugador);

        assertThrows(RuntimeException.class, ()-> torneo.registrarJugador("Tigres", jugador2));
        assertFalse(equipo.jugadores().contains(jugador2));

        LOG.info("Fin prueba para verificar que al momento de registrar un jugador a equipo no exista ya un jugador registrado con el mismo nombre y apellido");

    }

    

   


}
