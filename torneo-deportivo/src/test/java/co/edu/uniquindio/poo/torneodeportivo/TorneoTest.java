/**
 * Clase para probar el funcionamiento del Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



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
    public void NumeroParticipantesNegativos() {
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

    @Test
    public void testObtenerEnfrentamientosDeEquipo() {
    
        LOG.info("Inicio de prueba obtener enfrentamiento de equipo");
        
        List<Enfrentamiento> listaEnfrentamientos = new ArrayList<>();
        
        var representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var representante2 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300");

        Equipo equipo1 = new Equipo("Tigres", representante1, listaEnfrentamientos);
        Equipo equipo2 = new Equipo("Panteras", representante2, listaEnfrentamientos);
        Juez juez = new Juez("Sara", "Acosta", "SvAb@gmail.com", "3145290574", "123456");

        Enfrentamiento enfrentamiento = new Enfrentamiento("estadio central", "centro armenia",
                    LocalDateTime.of(2024, 5, 28, 18, 0), List.of(juez), equipo1, equipo2);
        Enfrentamiento enfrentamiento2 = new Enfrentamiento("estadio central", "centro armenia",
                    LocalDateTime.of(2024, 5, 28, 18, 0), List.of(juez), equipo1, equipo2);
        Enfrentamiento enfrentamiento3 = new Enfrentamiento("estadio central", "centro armenia",
                    LocalDateTime.of(2024, 5, 28, 18, 0), List.of(juez), equipo1, equipo2);
        listaEnfrentamientos.add(enfrentamiento);
        listaEnfrentamientos.add(enfrentamiento2);
        listaEnfrentamientos.add(enfrentamiento3);

        
            Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO, listaEnfrentamientos);

        
        Collection<Enfrentamiento> enfrentamientosEquipo1 = torneo.obtenerEnfrentamientosDeEquipo(equipo1);

        
        assertEquals(2, enfrentamientosEquipo1.size());  // El equipo1 participa en dos enfrentamientos

        LOG.info("Fin de prueba obtener enfrentamiento de equipo");
        
}


    @Test
    public void testListadoEstadisticasEquipos() {
        
        LOG.info("Inicio de prueba obtener listado de estadisticas de los equipos");
        // Crear un torneo y equipos de prueba
        Collection<Equipo> listaEquipos= new ArrayList<>();// Crear un torneo y equipos de prueba
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 2, 1), LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 5), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.FEMENINO);

        Persona representante1 = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        Persona representante2 = new Persona("William", "Pulgarin", "rpulgarin@email.com", "6067359300");

        Equipo equipo1 = new Equipo("Tigres", representante1);
        Equipo equipo2 = new Equipo("Panteras", representante2);

        torneo.registrarParticipante(equipo1);
        torneo.registrarParticipante(equipo2);
        listaEquipos.add(equipo1);
        listaEquipos.add(equipo2);

        // Simular algunos enfrentamientos y resultados
        Enfrentamiento enfrentamiento1 = new Enfrentamiento("estadio central", "centro armenia",  equipo1, equipo2, 5,2);
        enfrentamiento1.calcularResultado();

        Enfrentamiento enfrentamiento2 = new Enfrentamiento("estadio central", "centro armenia", equipo1, equipo2,2,1);
        enfrentamiento2.calcularResultado();

        torneo.registrarEnfrentamiento(enfrentamiento1);
        torneo.registrarEnfrentamiento(enfrentamiento2);

        // Ejecutar el método que se va a probar
        Map<String, String> estadisticasEquipos = torneo.listadoEstadisticasEquipos();

        // Verificar que las estadísticas son las esperadas
        Assertions.assertEquals("2", estadisticasEquipos.get("Tigres - Victorias"));
        Assertions.assertEquals("2", estadisticasEquipos.get("Panteras - Derrotas"));
        Assertions.assertEquals("0", estadisticasEquipos.get("Tigres - Derrotas")); // Debería ser 0
        Assertions.assertEquals("0", estadisticasEquipos.get("Panteras - Victorias")); // Debería ser 0
        Assertions.assertEquals("0", estadisticasEquipos.get("Tigres - Empates"));
        Assertions.assertEquals("0", estadisticasEquipos.get("Panteras - Empates"));
        Assertions.assertEquals("2", estadisticasEquipos.get("Tigres - Total Enfrentamientos"));
        Assertions.assertEquals("2", estadisticasEquipos.get("Panteras - Total Enfrentamientos"));

        LOG.info("Fin de prueba obtener listado de estadisticas de los equipos");
    }

}
