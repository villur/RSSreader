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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by villur on 23/01/17.
 */
public class XMLreader {


    public static List<News> readXML() throws IOException, SAXException, ParserConfigurationException {

        URL url = new URL("http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/world/rss.xml");
        File inputFile = new File(url.getFile());




        //File inputFile = new File("www.postimees.ee/rss.xml");
        DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("item");
        //System.out.println("----------------------------");

        List<News> newsList = new ArrayList<News>();

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            //System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                News news = new News();
                news.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                news.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                news.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());

                newsList.add(news);



            }
        }
        //System.out.println(Arrays.toString(newsList.toArray()));
        return newsList;
    }
}


