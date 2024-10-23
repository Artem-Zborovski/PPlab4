import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLReader {

    public static List<WashingMachine> readMachinesFromXML(String filePath) throws Exception {
        List<WashingMachine> machines = new ArrayList<>();
        
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();
        
        NodeList nodeList = document.getElementsByTagName("machine");
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                String model = element.getElementsByTagName("model").item(0).getTextContent();
                int power = Integer.parseInt(element.getElementsByTagName("power").item(0).getTextContent());
                int maxRPM = Integer.parseInt(element.getElementsByTagName("maxRPM").item(0).getTextContent());
                Date releaseDate = java.sql.Date.valueOf(element.getElementsByTagName("releaseDate").item(0).getTextContent());
                double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());

                // Допустим, создаем DomesticWashingMachine
                machines.add(new DomesticWashingMachine(id, model, power, maxRPM, releaseDate, price));
            }
        }
        return machines;
    }
}
