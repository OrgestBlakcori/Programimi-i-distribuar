import java.awt.Rectangle;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JPanel;

public interface ServerI extends Remote {

    void sendImageAsBytesToServer(byte[] imageBytes) throws RemoteException;

    public void setPanel(JPanel panel) throws RemoteException;

    void setClient(ClientI client) throws RemoteException;

    ClientI getClient() throws RemoteException;

    void setRectangle(Rectangle rectangle) throws RemoteException;

    Rectangle getRectangle() throws RemoteException;

}
