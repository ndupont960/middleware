package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
    	try {
            Registry registry = LocateRegistry.createRegistry(1099);
            ConnectionImpl connection = new ConnectionImpl();
            registry.rebind("ConnectionFactory", connection);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}