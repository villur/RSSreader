package pakk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    public TextField urlBox;
    public TextArea textBox1;




    @FXML
    private void handleButtonAction() throws ParserConfigurationException, SAXException, IOException {

        ArrayList<News> news2;
        news2 = (ArrayList<News>) XMLreader.readXML(urlBox.getText());

        XMLreader.readXML(urlBox.getText());
        //textBox1.setText(String.valueOf(XMLreader.readXML(urlBox.getText())));


        for (int i = 0; i < news2.size(); i++) {
            textBox1.setText((news2.get(i).getTitle()));
        }
    }


}
