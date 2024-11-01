import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class ConversorDeMonedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monedaPrincipal;

        System.out.println("Hola, bienvenido al sistema de conversión de Monedas");
        System.out.print("¿Cuál es tu moneda principal? (ejemplo: USD): ");
        monedaPrincipal = scanner.nextLine().toUpperCase();

        boolean salir = false;

        while (!salir) {
            System.out.println("\nElige una opción:");
            System.out.println("1. Convertir de " + monedaPrincipal + " a USD");
            System.out.println("2. Convertir de " + monedaPrincipal + " a CLP");
            System.out.println("3. Convertir de " + monedaPrincipal + " a MXN");
            System.out.println("4. Convertir de " + monedaPrincipal + " a ARS");
            System.out.println("5. Elegir otra moneda");
            System.out.println("6. Salir del sistema");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    convertirMoneda(monedaPrincipal, "USD", scanner);
                    break;
                case 2:
                    convertirMoneda(monedaPrincipal, "CLP", scanner);
                    break;
                case 3:
                    convertirMoneda(monedaPrincipal, "MXN", scanner);
                    break;
                case 4:
                    convertirMoneda(monedaPrincipal, "ARS", scanner);
                    break;
                case 5:
                    System.out.print("¿Cuál es tu nueva moneda principal? (ejemplo: USD): ");
                    scanner.nextLine(); // Limpiar el buffer
                    monedaPrincipal = scanner.nextLine().toUpperCase();
                    break;
                case 6:
                    salir = true;
                    System.out.println("Gracias por usar el sistema de conversión de monedas.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    public static void convertirMoneda(String monedaOrigen, String monedaDestino, Scanner scanner) {
        System.out.print("Ingrese el monto a convertir: ");
        double monto = scanner.nextDouble();
        double tasaCambio = obtenerTasaCambio(monedaOrigen, monedaDestino);

        if (tasaCambio > 0) {
            double resultado = monto * tasaCambio;
            System.out.printf("Monto en %s: %.2f%n", monedaDestino, resultado);
        } else {
            System.out.println("No se pudo obtener la tasa de cambio.");
        }
    }

    public static double obtenerTasaCambio(String monedaOrigen, String monedaDestino) {
        String apiKey = "59b53b1a79174b7d24448e4a";
        String urlStr = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKey, monedaOrigen, monedaDestino);

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parsear el JSON usando GSON y retornar la tasa de cambio
            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            return jsonObject.get("conversion_rate").getAsDouble();

        } catch (Exception e) {
            return 0.0; // Retorna 0.0 si hay un error
        }
    }
}
