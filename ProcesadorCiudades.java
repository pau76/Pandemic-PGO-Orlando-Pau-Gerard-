import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ProcesadorCiudades {
    public static void main(String[] args) {
        // Ruta del archivo de entrada y salida
        String archivoEntrada = "ciudades.txt";
        String archivoSalida = "ciudadesRedactadas.txt";

        // Mapa para almacenar las coordenadas de las ciudades
        Map<String, Ciudad> ciudadesMap = new HashMap<>();

        try {
            // Leer el archivo de entrada
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Dividir la línea en partes usando el punto y coma como separador
                String[] partes = linea.split(";");
                String nombreCiudad = partes[0];
                int tipo = Integer.parseInt(partes[1]);
                String[] coordenadasStr = partes[2].split(",");
                double[] coordenadas = { Double.parseDouble(coordenadasStr[0]), Double.parseDouble(coordenadasStr[1]) };
                String[] ciudadesColindantes = partes[3].split(",");
                ciudadesMap.put(nombreCiudad, new Ciudad(nombreCiudad, tipo, coordenadas, ciudadesColindantes));
            }
            lector.close();

            // Escribir en el archivo de salida
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida));
            for (Ciudad ciudad : ciudadesMap.values()) {
                // Construir la frase para la ciudad
                StringBuilder frase = new StringBuilder();
                frase.append("La ciudad ").append(ciudad.nombre).append(" está en las coordenadas (")
                        .append(ciudad.coordenadas[0]).append(",").append(ciudad.coordenadas[1]).append(") sus ciudades colindantes son:\n");

                for (String colindante : ciudad.ciudadesColindantes) {
                    // Agregar la ciudad colindante a la frase
                    frase.append("· ").append(colindante).append(", que está a una distancia de ")
                            .append(calcularDistancia(ciudadesMap.get(ciudad.nombre), ciudadesMap.get(colindante)))
                            .append("\n");
                }

                // Escribir la frase en el archivo de salida
                escritor.write(frase.toString());
                escritor.newLine();
            }
            escritor.close();

            System.out.println("Se ha generado el archivo ciudadesRedactadas.txt exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al leer/escribir el archivo: " + e.getMessage());
        }
    }

    // Método para calcular la distancia entre dos ciudades
    private static double calcularDistancia(Ciudad ciudad1, Ciudad ciudad2) {
        double distanciaX = ciudad1.coordenadas[0] - ciudad2.coordenadas[0];
        double distanciaY = ciudad1.coordenadas[1] - ciudad2.coordenadas[1];
        return Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
    }

    // Clase para representar una ciudad
    static class Ciudad {
        private String nombre;
        private int tipo;
        private double[] coordenadas;
        private String[] ciudadesColindantes;

        public Ciudad(String nombre, int tipo, double[] coordenadas, String[] ciudadesColindantes) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.coordenadas = coordenadas;
            this.ciudadesColindantes = ciudadesColindantes;
        }
    }
}
