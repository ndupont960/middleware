package client;

import shared.Dialogue;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	private Dialogue dialogue;

    public void connect(String nickname) {
        try {
            Registry registry = LocateRegistry.getRegistry("server_address", 1099);
            dialogue = (Dialogue) registry.lookup("ChatServer");
            // Logic to register client with the server using nickname
            // For example: dialog.registerClient(nickname);
            System.out.println("Connected as " + nickname);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void disconnect(String nickname) {
        try {
            // Logic to unregister client with the server
            // For example: dialog.unregisterClient(nickname);
            System.out.println("Disconnected");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void sendMessage(String to, String message) {
        try {
            dialogue.sendMessage(to, message);
            System.out.println("Message sent to " + to);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public String[] getMessages() {
        try {
            // Logic to retrieve messages for the client
            // For example: return dialog.getMessages();
            return new String[]{};
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return new String[]{};
        }
    }

    public String[] getClients() {
        try {
            // Logic to retrieve list of connected clients
            // For example: return dialog.getClients();
            return new String[]{};
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return new String[]{};
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect("YourNickname");
        // Use client methods to interact with the server
        client.sendMessage("ReceiverNickname", "Hello!");
        String[] messages = client.getMessages();
        String[] clients = client.getClients();
        client.disconnect("YourNickname");
    }
}