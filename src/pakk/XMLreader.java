package pakk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XMLreader {


    public static List<News> readXML(String rssurl) throws IOException, SAXException, ParserConfigurationException {

        URL u = new URL(rssurl);
        InputStream inputFile = u.openStream();


        //See leht oli abiks XML parseri kirjutamisel
        //https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm

        DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("item");

        List<News> newsList = new ArrayList<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                News news = new News();

                if (eElement.getElementsByTagName("title").getLength() != 0) {
                    news.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                }
                if (eElement.getElementsByTagName("description").getLength() != 0) {
                    news.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                }
                if (eElement.getElementsByTagName("author").getLength() != 0) {
                    news.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                }

                if (eElement.getElementsByTagName("link").getLength() != 0) {
                    news.setLink(eElement.getElementsByTagName("link").item(0).getTextContent());
                }

                newsList.add(news);

            }
        }
        //System.out.println(Arrays.toString(newsList.toArray()));
        return newsList;
    }
}


