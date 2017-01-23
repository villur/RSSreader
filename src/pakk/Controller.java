package pakk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class Controller {

    @FXML
    public TextField urlBox;
    public TextArea textBox1;

    @FXML
    private void handleButtonAction() throws ParserConfigurationException, SAXException, IOException {

        XMLreader.readXML(urlBox.getText());
        textBox1.setText(String.valueOf(XMLreader.readXML(urlBox.getText())));

    }


}
