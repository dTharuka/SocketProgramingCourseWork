package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang.ArrayUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientFormController3 {
    static String emo1 = "";
    static String emo2 = "";
    static String emo3 = "";
    final int port3 = 1234;
    public TextArea textArea3;
    public TextField textMessage3;
    public AnchorPane emojiPane;
    public Label lblC3Message;
    public AnchorPane ancC3Main;
    public AnchorPane ancC3;
    public TextField txtC3UN;
    public PasswordField txtC3PW;
    Socket socket3;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;
    String message3 = "";


    public void initialize() {
        emo1 = "";
        emo2 = "";
        emo3 = "";

        emojiPane.setVisible(false);
        new Thread(() -> {

            try {
                socket3 = new Socket("localhost", port3);

                dataInputStream3 = new DataInputStream(socket3.getInputStream());
                dataOutputStream3 = new DataOutputStream(socket3.getOutputStream());

                while (!message3.equals("exit")) {
                    message3 = dataInputStream3.readUTF();
                    textArea3.appendText(message3 + "\n");
                }

                socket3.close();
                dataOutputStream3.close();
                dataInputStream3.close();

            } catch (IOException ignored) {
            }
        }).start();
    }


    public void sendOnAction3(ActionEvent actionEvent) throws IOException {
//        dataOutputStream3.write(0);
        dataOutputStream3.writeUTF(textMessage3.getText().trim() + emo1 + emo2 + emo3);
        dataOutputStream3.flush();
        textMessage3.clear();
        lblC3Message.setVisible(true);
        emojiPane.setVisible(false);
    }

    public void emoSendOnAction(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
        lblC3Message.setVisible(false);
    }


    public void imageSendOnAction(MouseEvent mouseEvent) throws IOException {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        File selectedFile = fileChooser.showOpenDialog(new Stage());
//
//        if (selectedFile != null) {
//
//            String[] res = selectedFile.getName().split("\\.");
//            BufferedImage finalImage;
//
//            try {
//                finalImage = ImageIO.read(selectedFile);
//            } catch (Exception e) {
//                new Alert(Alert.AlertType.NONE, "Please select a file-type associated with images! try again", ButtonType.CLOSE).showAndWait();
//                return;
//            }
//
//            ByteArrayOutputStream bout = new ByteArrayOutputStream();
//            ImageIO.write(finalImage, res[1], bout);
//
//            /*byte[] temp_payload = dataOutputStream3Name.getBytes(StandardCharsets.UTF_8);
//            byte[] temp_header = ByteBuffer.allocate(4).putInt(temp_payload.length).array();*/
//
//            byte[] payload = bout.toByteArray();
//            /*byte[] header = ByteBuffer.allocate(4).putInt(payload.length).array();
//
//            byte[] frame = ArrayUtils.addAll(temp_header,header);
//
//            frame = ArrayUtils.addAll(frame,sender);
//            frame = ArrayUtils.addAll(frame,temp_payload);
//            frame = ArrayUtils.addAll(frame,payload);*/
//
//            dataOutputStream3.write(-1);
//            dataOutputStream3.write(payload);
//            dataOutputStream3.flush();
//        }



    }

    public void l1emoOnAction(MouseEvent mouseEvent) {
//        emo1 = "\uD83D\uDE42";
        textMessage3.setText(textMessage3.getText()+"\uD83D\uDE42");
    }

    public void l2emoOnAction(MouseEvent mouseEvent) {
//        emo2 = "\uD83D\uDE0D";
        textMessage3.setText(textMessage3.getText()+"\uD83D\uDE0D");
    }

    public void l3emoOnAction(MouseEvent mouseEvent) {
//        emo3 = "\uD83E\uDD2A";
        textMessage3.setText(textMessage3.getText()+"\uD83D\uDE00");
    }

    public void textMessage3(MouseEvent mouseEvent) {
        emojiPane.setVisible(false);
        lblC3Message.setVisible(false);
    }

    public void btnC3LoginOnAction(ActionEvent actionEvent) {
        if(txtC3UN.getText().equals("c3") & txtC3PW.getText().equals("1234")){
            ancC3.setVisible(false);
            ancC3Main.setVisible(true);
        }else {
            ancC3.setVisible(true);
            ancC3Main.setVisible(false);
        }
    }
}
