package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    private IRepositorioResultados repo;
    private Estadisticas           estadisticas;

    @BeforeEach
    void setUp() {
        repo         = new RepositorioEnMemoria();
        estadisticas = new Estadisticas(repo);
    }

    @Test
    @DisplayName("Estadísticas calculan correctamente racha y tipo más jugado")
    void estadisticasCalculanRachaYTipoMasJugado() {
        repo.agregar(new Resultado(7,  new ApuestaRojo(100),  true,  600));
        repo.agregar(new Resultado(3,  new ApuestaRojo(100),  true,  700));
        repo.agregar(new Resultado(14, new ApuestaNegro(100), false, 600));
        repo.agregar(new Resultado(2,  new ApuestaRojo(100),  true,  700));
        repo.agregar(new Resultado(4,  new ApuestaPar(100),   false, 600));

        assertAll(
                () -> assertEquals(5,      estadisticas.getTotalJugadas()),
                () -> assertEquals(3,      estadisticas.getVictorias()),
                () -> assertEquals(2,      estadisticas.getRachaMaxima()),
                () -> assertEquals(60.0,   estadisticas.getPorcentajeVictorias(), 0.01),
                () -> assertEquals("ROJO", estadisticas.getTipoMasJugado())
        );
    }
}