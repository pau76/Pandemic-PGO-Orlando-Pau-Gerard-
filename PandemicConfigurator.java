import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PandemicConfigurator {
    private static final String FILE_NAME = "parametros.xml";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Mostrar datos del archivo parametros.xml");
            System.out.println("2. Cambiar y guardar datos en parametros.xml");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (option) {
                case 1:
                    leerParametros();
                    break;
                case 2:
                    cambiarParametros(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void leerParametros() {
        try {
            File file = new File(FILE_NAME);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Datos actuales del archivo parametros.xml:");
            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println(element.getTagName() + ": " + element.getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error al leer el archivo parametros.xml: " + e.getMessage());
        }
    }

    private static void cambiarParametros(Scanner scanner) {
        try {
            File file = new File(FILE_NAME);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.print("Nuevo valor para " + element.getTagName() + ": ");
                    String newValue = scanner.nextLine();
                    element.setTextContent(newValue);
                }
            }

            // Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Datos actualizados en el archivo parametros.xml.");
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.out.println("Error al cambiar y guardar los datos en parametros.xml: " + e.getMessage());
        }
    }
}
