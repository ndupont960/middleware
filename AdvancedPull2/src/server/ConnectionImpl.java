package server;

import shared.Dialogue;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ConnectionImpl extends UnicastRemoteObject {
    private Map<String, Dialogue> clientDialogs;

    public ConnectionImpl() throws RemoteException {
        super();
        clientDialogs = new HashMap<>();
    }

    public Dialogue connect(String nickname) throws RemoteException {
        DialogueImpl dialog = new DialogueImpl();
        clientDialogs.put(nickname, dialog);
        return dialog;
    }

    public void disconnect(String nickname) throws RemoteException {
        clientDialogs.remove(nickname);
    }

    public Dialogue getDialog(String nickname) throws RemoteException {
        return clientDialogs.get(nickname);
    }
}