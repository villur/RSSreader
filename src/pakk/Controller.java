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
    public TextField urlBox;
    public TextArea textBox1;
    public ScrollPane container;
    public TextField authorBox;
    public Hyperlink hyperLink;

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


    @FXML
    private void handleButtonAction() throws ParserConfigurationException, SAXException, IOException {

        VBox newsTitles = new VBox();

        ArrayList<News> news = (ArrayList<News>) XMLreader.readXML(urlBox.getText());

        for (int j = 0; j < news.size(); j++) {
            Button btn = new Button();
            btn.setText(news.get(j).getTitle());
            int finalJ = j;
            btn.setWrapText(true);
            btn.setPrefWidth(container.getPrefWidth()-15);
            btn.setOnMouseClicked(event -> {
                textBox1.setText(news.get(finalJ).getDescription());
                authorBox.setText(news.get(finalJ).getAuthor());
                hyperLink.setText(news.get(finalJ).getLink());
            });

            // Kasutasin seda linki, et lisada oma elementidele action listeneri.
            // http://www.java2s.com/Code/Java/JavaFX/AddClickactionlistenertoButton.htm

            newsTitles.getChildren().add(btn);
        }

        container.setContent(newsTitles);


    }


}