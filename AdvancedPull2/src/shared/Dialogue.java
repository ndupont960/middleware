package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Dialogue extends Remote {
    void sendMessage(String to, String message) throws RemoteException;
    String[] getMessages() throws RemoteException;
    String[] getClients() throws RemoteException;
}