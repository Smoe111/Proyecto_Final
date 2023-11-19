/**
 * Clase para probar el registro de los participantes
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class ParticipanteTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(ParticipanteTest.class.getName());

    /**
     * Verificar que sea posible registrar un participante en un torneo 
     * 
     */
    @Test
    public void registrarParticipanteTorneo() {
        LOG.info("Inicio de prueba registrarParticipanteTorneo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|INDIVIDUAL}   Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL,CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);

        
        
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(15),GeneroTorneo.MASCULINO);

        torneo.registrarParticipante(jugador);
        

        // Recuperación y verificación de datos
        assertTrue(torneo.getParticipantes().contains(jugador));
        assertEquals(1, torneo.getParticipantes().size());
        LOG.info("Fin de prueba registrarParticipanteTorneo...");
    }

    /**
     * Verificar que no sea posible registrar dos participantes con el mismo nombre completo en un mismo  torneo 
     * 
     */
    @Test
    public void registrarParticipanteRepetidosTorneo() {
        LOG.info("Inicio de prueba registrarParticipanteRepetidosTorneo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|INDIVIDUAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300},  Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Equipo{Quindío}

        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL,CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);

        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(15), GeneroTorneo.MASCULINO);
        var jugador2 = new Jugador("Christian", "Candela", "ccandela@email.com", "6067431235",LocalDate.now().minusYears(15),GeneroTorneo.MASCULINO);
                
        torneo.registrarParticipante(jugador);
        assertThrows(Throwable.class,()->torneo.registrarParticipante(jugador2));

        // Recuperación y verificación de datos
        
        LOG.info("Fin de prueba registrarParticipanteRepetidosTorneo...");
    }

    /**
     * Verificar que no sea posible registrar un jugador como participante en un torneo grupal. 
     * 
     */
    @Test
    public void registrarParticipanteIndividualTorneoGrupal() {
        LOG.info("Inicio de prueba registrarParticipanteIndividualTorneoGrupal...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|GRUPAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300},  Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Equipo{Quindío}

        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);

        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(15), GeneroTorneo.MIXTO);
                
        
        assertThrows(Throwable.class,()->torneo.registrarParticipante(jugador));
        
        LOG.info("Fin de prueba registrarParticipanteIndividualTorneoGrupal...");
    }

    /**
     * Verificar que no sea posible registrar un equipo en un torneo de carácter individual
     * 
     */
    @Test
    public void registrarEquipoTorneoIndividual() {
        LOG.info("Inicio de prueba registrarEquipoTorneoIndividual...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\| fechaActual - 15 días\|fechaActual+15 días\|24\|18\|0\|LOCAL|INDIVIDUAL}  Equipo{Uniquindio} Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300},  Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Jugador {Christian,Candela,chrcandela@email.com,6067431234, fechaActual - 15 años}, Equipo{Quindío}

        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL,CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
                
        
        assertThrows(Throwable.class,()->torneo.registrarParticipante(equipo));
        
        LOG.info("Fin de prueba registrarEquipoTorneoIndividual...");
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
    


}
