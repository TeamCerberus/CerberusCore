package teamcerberus.cerberuscore.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CerberusXMLParser {
	HashMap<String, Node>	xmlData;

	public CerberusXMLParser(InputStream XMLFile) throws SAXException,
			IOException, ParserConfigurationException {
		xmlData = new HashMap<String, Node>();
		final DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        final Document doc = docBuilder.parse(XMLFile);
        xmlData = new HashMap<String, Node>();
        parse("", doc.getDocumentElement());
    }

    private void parse(String prefix, final Element e) {
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
            	xmlData.put(prefix + n.getNodeName(), n);
                parse(prefix + n.getNodeName() + ".", (Element) n);
            }
        }
    }

	public boolean containsKey(String key) {
		return xmlData.containsKey(key);
	}

	public boolean containsValue(String value) {
		return xmlData.containsKey(value);
	}

	public Node get(String key) {
		return xmlData.get(key);
	}
	
	public HashMap<String, Node> getAll() {
		return xmlData;
	}
}
