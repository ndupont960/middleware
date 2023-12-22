package advancedpull2;
   
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.ConnectionImpl;
import shared.Dialogue;

import java.rmi.RemoteException;
 
public class ChatPage {
    private Stage primaryStage;
    private Dialogue server;
    private ConnectionImpl connection;
    private String username;

    public ChatPage(Stage primaryStage, Dialogue server, ConnectionImpl connection) {
        this.primaryStage = primaryStage;
        this.server = server;
        this.connection = connection;
    }

    public void show() {
        VBox chatLayout = new VBox(10);
        Label chatLabel = new Label("Chat Page");
        ListView<String> messagesList = new ListView<>();
        TextField receiverField = new TextField();
        TextField messageField = new TextField();
        Button sendMessageButton = new Button("Send Message");
        Button disconnectButton = new Button("Disconnect");

        chatLayout.getChildren().addAll(chatLabel, messagesList, receiverField, messageField, sendMessageButton, disconnectButton);
        Scene chatScene = new Scene(chatLayout, 400, 300);

        sendMessageButton.setOnAction(e -> {
            String receiver = receiverField.getText();
            String message = messageField.getText();
            if (!receiver.isEmpty() && !message.isEmpty()) {
                sendMessage(receiver, message);
                messageField.clear();
            }
        });

        disconnectButton.setOnAction(e -> {
            disconnect();
            ConnectionPage connectionPage = new ConnectionPage();
            connectionPage.start(primaryStage);
        });

        primaryStage.setScene(chatScene);
        primaryStage.setTitle("Chat Page");
        primaryStage.show();
    }

    private void sendMessage(String receiver, String message) {
        try {
            server.sendMessage(receiver, message);
            // Update UI to display the sent message
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            connection.disconnect(username);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}