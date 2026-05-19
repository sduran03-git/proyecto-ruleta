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
        char tipo  = leerTipoApuesta(in);
        int monto  = leerMonto(in);
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);
        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    public static char leerTipoApuesta(Scanner in) {
        System.out.println("Tipo de apuesta: (R)ojo / (N)egro / (P)ar / (I)mpar");
        System.out.print("Tu apuesta: ");
        String linea = in.nextLine().trim().toUpperCase();
        if (!linea.isEmpty() && "RNPI".indexOf(linea.charAt(0)) >= 0) {
            return linea.charAt(0);
        }
        System.out.println("Opción inválida, se usará (P)ar por defecto.");
        return 'P';
    }

    public static int leerMonto(Scanner in) {
        System.out.print("Monto a apostar: $");
        try {
            int monto = Integer.parseInt(in.nextLine().trim());
            return Math.max(monto, 1);
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido, se usará $1.");
            return 1;
        }
    }

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        if (tipo == 'R') {
            return esRojo(numero);
        } else if (tipo == 'N') {
            return !esRojo(numero) && numero != 0;
        } else if (tipo == 'P') {
            return numero != 0 && numero % 2 == 0;
        } else if (tipo == 'I') {
            return numero % 2 != 0;
        } else {
            return false;
        }
    }

    public static boolean esRojo(int n) {
        for (int rojo : NUMEROS_ROJOS) {
            if (n == rojo) return true;
        }
        return false;
    }
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize >= MAX_HISTORIAL) return;
        historialNumeros [historialSize] = numero;
        historialApuestas[historialSize] = apuesta;
        historialAciertos[historialSize] = acierto;
        historialSize++;
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
