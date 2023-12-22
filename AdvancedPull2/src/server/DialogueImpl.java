package server;

import shared.Dialogue;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogueImpl extends UnicastRemoteObject implements Dialogue {
	private Map<String, List<String>> messages;
    private Map<String, Dialogue> clientDialogs;

    public DialogueImpl() throws java.rmi.RemoteException {
        super();
        messages = new HashMap<>();
        clientDialogs = new HashMap<>();
    }

    public void sendMessage(String to, String message) throws java.rmi.RemoteException {
        List<String> msgs = messages.getOrDefault(to, new ArrayList<>());
        msgs.add(message);
        messages.put(to, msgs);
    }

    public String[] getMessages() throws java.rmi.RemoteException {
        // Implement logic to retrieve messages for a client
        // Return the messages as an array
        // Example:
        return messages.getOrDefault("specific_client_id", new ArrayList<>()).toArray(new String[0]);
    }

    public String[] getClients() throws java.rmi.RemoteException {
        // Implement logic to retrieve the list of connected clients
        // Return the list of clients as an array
        // Example:
        return clientDialogs.keySet().toArray(new String[0]);
    }
}