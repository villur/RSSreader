package pakk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by villur on 23/01/17.
 */
public class XMLreader {


    public static List<News> readXML() throws IOException, SAXException, ParserConfigurationException {

        URL u = new URL("http://postimees.ee/rss");
        InputStream inputFile = u.openStream();

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
                news.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                news.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                news.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());

                newsList.add(news);



            }
        }
        System.out.println(Arrays.toString(newsList.toArray()));
        return newsList;
    }
}


