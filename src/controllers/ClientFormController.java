package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController {
    static String emo1 = "";
    static String emo2 = "";
    static String emo3 = "";
    final int PORT = 5000;
    public AnchorPane emojiPane;
    public Label lblMessage;
    public AnchorPane ancC1Main;
    public TextField txtC1UN;
    public PasswordField txtC1PW;
    public Button btnC1LoginOnAction;
    public AnchorPane ancC1;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    @FXML
    private TextArea textArea;
    @FXML
    private TextField textMessage;

    public void initialize() {
        emo1 = "";
        emo2 = "";
        emo3 = "";

        emojiPane.setVisible(false);
        new Thread(() -> {
            try {

                accept = new Socket("localhost", PORT);

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")) {

                    message = dataInputStream.readUTF();
                    textArea.appendText(message+"\n");
                }

                dataOutputStream.writeUTF(message.trim() + emo1 + emo2 + emo3);
                dataOutputStream.flush();

                accept.close();
                dataOutputStream.close();
                dataInputStream.close();

            } catch (IOException ignored) {
            }

        }).start();

    }

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(
                textMessage.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream.flush();
        textMessage.clear();
        lblMessage.setVisible(true);
        emojiPane.setVisible(false);
    }

    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
        lblMessage.setVisible(false);
    }

    public void imageSendOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    public void textMessage(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
        lblMessage.setVisible(false);
    }

    public void l1emoOnAction(MouseEvent mouseEvent) {

//        emo1 = "\uD83D\uDE42";
        textMessage.setText(textMessage.getText()+"\uD83D\uDE42");
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {

//        emo2 = "\uD83D\uDE0D";
        textMessage.setText(textMessage.getText()+"\uD83D\uDE0D");
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
//        emo3 = "\uD83E\uDD2A";
        textMessage.setText(textMessage.getText()+"\uD83D\uDE00");
    }

    public void btnC1LoginOnAction(ActionEvent actionEvent) {
        if(txtC1UN.getText().equals("c1") & txtC1PW.getText().equals("1234")){
            ancC1.setVisible(false);
            ancC1Main.setVisible(true);
        }else {
            ancC1.setVisible(true);
            ancC1Main.setVisible(false);
        }
    }
}
