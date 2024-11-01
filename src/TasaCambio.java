import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



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

        // Imprime la respuesta para verificar que sea correcta
        System.out.println("Respuesta de la API: " + response.toString());

        // Parsear el JSON usando GSON
        JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
        return jsonObject.get("conversion_rate").getAsDouble();

    } catch (Exception e) {
        System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
        return 0.0;
    }
}
