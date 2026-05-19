import java.util.Random;
import java.util.Scanner;

public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros  = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static final int[] NUMEROS_ROJOS =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);
        in.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== CASINO BLACK CAT - RULETA =====");
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadísticas");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    public static int leerOpcion(Scanner in) {
        try {
            return Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 1) {
            iniciarRonda(in);
        } else if (opcion == 2) {
            mostrarEstadisticas();
        } else if (opcion == 3) {
            System.out.println("¡Hasta pronto!");
        } else {
            System.out.println("Opción inválida. Intenta de nuevo.");
        }
    }

    public static void iniciarRonda(Scanner in) {
    }

    /**
     * Permite al usuario seleccionar el tipo de apuesta (R/N/P/I).
     *
     * @param in Scanner para entrada por consola.
     *           3
     * @return el tipo de apuesta elegido.
     */
    public static char leerTipoApuesta(Scanner in) {
        return ' ';
    }

    /**
     * Simula el giro de la ruleta generando un número aleatorio de 0 a 36.
     *
     * @return número de la ruleta.
     */
    public static int girarRuleta() {
        return 0;
    }

    /**
     * Evalúa si la apuesta realizada por el jugador fue acertada.
     *
     * @param numero número obtenido en la ruleta.
     * @param tipo   tipo de apuesta elegida.
     * @return true si acertó, false si perdió.
     */
    public static boolean evaluarResultado(int numero, char tipo) {
        return false;
    }

    /**
     * Determina si un número corresponde a color rojo.
     *
     * @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    public static boolean esRojo(int n) {
        return false;
    }
/**
 * Registra los resultados de la ronda en los arreglos de historial.
 * @param numero número obtenido en la ruleta.
 * @param apuesta monto apostado.
 * @param acierto si el jugador acertó o no.
 */

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
    }

    /**
     * Muestra en consola el resultado de la ronda.
     *
     * @param numero  número obtenido en la ruleta.
     * @param tipo    tipo de apuesta realizada.
     * @param monto   monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {
    }

    /**
     * Muestra estadísticas generales de todas las rondas jugadas.
     */
    public static void mostrarEstadisticas() {
    }
}
