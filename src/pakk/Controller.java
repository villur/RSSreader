package pakk;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Controller {



    @FXML
    public TextField urlField;
    public TextArea textBox;
    public ScrollPane container;
    public TextField authorBox;
    public Hyperlink hyperLink;



    //Minu meetod mis avab hyperlinki kui seda vajutada
    @FXML
    private void openHyperLink() throws MalformedURLException {
        //http://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java

        String url = hyperLink.getText();

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //peamine meetod mis loeb sisse andmed kasutades XML readerit ning loob news objektide listi põhjal kogu meie visuaalse poole sisu
    @FXML
    private void handleButtonAction() throws ParserConfigurationException, SAXException, IOException {

        VBox newsTitles = new VBox();

        ArrayList<News> news = (ArrayList<News>) XMLreader.readXML(urlField.getText());

        for (int j = 0; j < news.size(); j++) {
            Hyperlink title = new Hyperlink();
            title.setText(news.get(j).getTitle());
            int finalJ = j;
            title.setWrapText(true);
            title.setPrefWidth(container.getPrefWidth()-15);
            title.setOnMouseClicked(event -> {
                textBox.setText(news.get(finalJ).getDescription());
                authorBox.setText(news.get(finalJ).getAuthor());
                hyperLink.setText(news.get(finalJ).getLink());

            });

            // Kasutasin seda linki, et lisada oma elementidele action listeneri.
            // http://www.java2s.com/Code/Java/JavaFX/AddClickactionlistenertoButton.htm

            newsTitles.getChildren().add(title);
        }

        container.setContent(newsTitles);


    }


}