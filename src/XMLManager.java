import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLManager {

    public static void writeMachinesToXML(List<WashingMachine> machines, String filePath) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("WashingMachines");
        doc.appendChild(rootElement);

        for (WashingMachine machine : machines) {
            Element machineElement = doc.createElement("WashingMachine");

            machineElement.appendChild(createElement(doc, "id", String.valueOf(machine.getId())));
            machineElement.appendChild(createElement(doc, "type", machine.getType()));
            machineElement.appendChild(createElement(doc, "model", machine.getModel()));
            machineElement.appendChild(createElement(doc, "power", String.valueOf(machine.getPower())));
            machineElement.appendChild(createElement(doc, "maxSpeed", String.valueOf(machine.getMaxSpeed())));
            machineElement.appendChild(createElement(doc, "releaseDate", machine.getReleaseDate().toString()));
            machineElement.appendChild(createElement(doc, "price", String.valueOf(machine.getPrice())));

            rootElement.appendChild(machineElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(filePath));
        transformer.transform(domSource, streamResult);
    }

    private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    public static List<WashingMachine> readMachinesFromXML(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readMachinesFromXML'");
    }
}
