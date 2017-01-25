package pakk;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    //Defineerin oma vajalikud väljad

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


        //Võtan hyperLink väljast URLi ja proovin seda avada browseris
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

        //Loon uue vertical box konteineri kuhu hakkan oma peaklirja objekte lisama.
        VBox newsTitles = new VBox();


        //Tekitan omale listi uudiste objektidest
        ArrayList<News> news = (ArrayList<News>) XMLreader.readXML(urlField.getText());


        //For loopiga käin läbi kõik uudised ja korjan sealt pealkirjad välja ning teen nendest FX elenemdid mida saab vajutada
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

            newsTitles.getChildren().add(title);
        }

        //Lisan konteinerisse oma pealkirja objektid/fx elemendid.
        container.setContent(newsTitles);


    }


}