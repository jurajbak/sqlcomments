package sk.vracon.sqlcomments.maven;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MappingFileParser {

    public static void loadMappingFile(final Map<String, Properties> mappings, InputStream input) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(input, new DefaultHandler() {
            private StringBuilder content;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                content = new StringBuilder();
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                content.append(ch, start, length);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if ("sqlcomments".equals(localName)) {
                    return;
                }

                Properties properties = mappings.get(localName);
                if (properties == null) {
                    properties = new Properties();
                    mappings.put(localName, properties);
                }

                try {
                    properties.load(new ByteArrayInputStream(content.toString().getBytes()));
                }
                catch (IOException e) {
                    throw new SAXException(e);
                }
            }
        });
    }
}
