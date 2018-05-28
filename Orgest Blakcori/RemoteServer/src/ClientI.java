import java.awt.Robot;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientI  extends Remote{

    public void sendActionsToClient(String action) throws RemoteException;

    public void setRobot(Robot robot) throws RemoteException;
    
}
