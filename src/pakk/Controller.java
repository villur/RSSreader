package pakk;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {

    @FXML
    public TextField urlBox;
    public TextArea textBox1;
    public ScrollPane container;
    public TextField authorBox;


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
            btn.setOnMouseClicked(event -> textBox1.setText(news.get(finalJ).getFormattedNews()));

            // Kasutasin seda linki, et lisada oma elementidele action listeneri.
            // http://www.java2s.com/Code/Java/JavaFX/AddClickactionlistenertoButton.htm

            newsTitles.getChildren().add(btn);
        }

        container.setContent(newsTitles);


    }


}