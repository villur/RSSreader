package pakk;

import com.sun.deploy.xml.XMLable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    public TextField urlBox;
    public TextArea textBox1;
    public ScrollPane container;


    @FXML
    private void handleButtonAction() throws ParserConfigurationException, SAXException, IOException {

        VBox newsTitles = new VBox();

        ArrayList<News> news = (ArrayList<News>) XMLreader.readXML(urlBox.getText());

        for (int j = 0; j < news.size(); j++) {
            Button btn = new Button();
            btn.setText(news.get(j).getTitle());
            int finalJ = j;
            btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                        textBox1.setText(news.get(finalJ).getDescription());
                }
            });

            newsTitles.getChildren().add(btn);


            //newsTitles.getChildren().add(new Hyperlink.(news.get(j).getTitle()));
        }

        container.setContent(newsTitles);


    }


}