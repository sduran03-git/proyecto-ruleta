package Controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    private SessionController session;

    @BeforeEach
    void setUp() {
        session = new SessionController();
    }

    @Test
    @DisplayName("Login con usuario no registrado es rechazado")
    void loginConUsuarioNoRegistradoEsRechazado() {
        boolean resultado = session.iniciarSesion("usuarioInexistente", "clave123");
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Login con username nulo es rechazado")
    void loginConUsernameNuloEsRechazado() {
        boolean resultado = session.iniciarSesion(null, "clave123");
        assertFalse(resultado);
    }
}