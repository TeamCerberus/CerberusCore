package teamcerberus.cerberuscore.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

public class CerberusXMLParser {
	public HashMap<String, String>				xmlData;
	public HashMap<String, Iterator<Attribute>>	xmlAttributes;

	public CerberusXMLParser(InputStream XMLFile, Iterable<String> properties)
			throws XMLStreamException {
		xmlData = new HashMap<String, String>();
		parse(XMLFile, properties);
	}

	private void parse(InputStream XMLFile, Iterable<String> properties)
			throws XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory.createXMLEventReader(XMLFile);

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				String name = event.asStartElement().getName().getLocalPart();
				//xmlAttributes.put(name, event.asStartElement().getAttributes());
				xmlData.put(name, event.asCharacters().getData());
			}
		}
	}
}
