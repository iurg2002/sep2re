package server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class DAOServer
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException
    {
        UserModel userModel = new UserModelImplimentation();
        Registry registry = LocateRegistry.createRegistry(5099);
        Remote exported = UnicastRemoteObject.exportObject((Remote) userModel, 5099);
        registry.bind("userModel", exported);
        System.out.println("Server running on " + 5099);
    }
}
