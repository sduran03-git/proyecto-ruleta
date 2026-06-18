package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    private IRepositorioResultados repo;

    @BeforeEach
    void setUp() {
        repo = new RepositorioEnMemoria();
    }

    @Test
    @DisplayName("Constructor rechaza saldo inicial negativo")
    void constructorRechazaSaldoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Ruleta(-1, repo),
                "Saldo inicial inválido");
    }

    @Test
    @DisplayName("Depósito válido incrementa el saldo")
    void depositoValidoIncrementaSaldo() {
        Ruleta ruleta = new Ruleta(500, repo);
        ruleta.depositar(200);
        assertEquals(700, ruleta.getSaldo());
    }

    @Test
    @DisplayName("Apuesta nula es rechazada")
    void apuestaNulaEsRechazada() {
        Ruleta ruleta = new Ruleta(500, repo);
        assertThrows(IllegalArgumentException.class,
                () -> ruleta.jugar(null),
                "Apuesta requerida");
    }

    @Test
    @DisplayName("Apuesta con monto mayor al saldo es rechazada")
    void apuestaConMontoMayorAlSaldoEsRechazada() {
        Ruleta ruleta = new Ruleta(100, repo);
        ApuestaBase apuesta = new ApuestaRojo(500);
        assertThrows(IllegalArgumentException.class,
                () -> ruleta.jugar(apuesta),
                "Saldo insuficiente");
    }
}