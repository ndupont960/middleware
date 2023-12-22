package advancedpull2;
  
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.ConnectionImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shared.Dialogue;

public class ConnectionPage extends Application {
    private Dialogue server;
    private ConnectionImpl connection;

    @Override
    public void start(Stage primaryStage) {
        VBox connectionLayout = new VBox(10);
        Label usernameLabel = new Label("Enter Username:");
        TextField usernameField = new TextField();
        Button connectButton = new Button("Connect");

        connectionLayout.getChildren().addAll(usernameLabel, usernameField, connectButton);
        Scene connectionScene = new Scene(connectionLayout, 300, 200);

        connectButton.setOnAction(e -> {
            String username = usernameField.getText();
            if (!username.isEmpty()) {
                connectToServer(username);
                openChatPage(primaryStage, connection);
            }
        });

        primaryStage.setScene(connectionScene);
        primaryStage.setTitle("Connection Page");
        primaryStage.show();
    }

    private void connectToServer(String username) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (Dialogue) registry.lookup("ChatServer");
            this.connection = new ConnectionImpl();
            connection.connect(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openChatPage(Stage primaryStage, ConnectionImpl connection) {
        ChatPage chatPage = new ChatPage(primaryStage, server, connection);
        chatPage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}