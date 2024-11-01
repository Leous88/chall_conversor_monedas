import java.util.Scanner;

public class ConversorDeMonedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Conversor de Monedas");
        System.out.print("Ingrese la moneda de origen (ejemplo: USD): ");
        String monedaOrigen = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda de destino (ejemplo: EUR): ");
        String monedaDestino = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese el monto a convertir: ");
        double monto = scanner.nextDouble();

        // Llamar al metodo para obtener la tasa de cambio y calcular el resultado
        double tasaCambio = obtenerTasaCambio(monedaOrigen, monedaDestino);
        double resultado = monto * tasaCambio;

        System.out.printf("Monto en %s: %.2f%n", monedaDestino, resultado);
        scanner.close();
    }

    public static double obtenerTasaCambio(String monedaOrigen, String monedaDestino) {
        // Este metodo hará la solicitud a la API y devolverá la tasa de cambio
        return 0.0; // Placeholder
    }
}
