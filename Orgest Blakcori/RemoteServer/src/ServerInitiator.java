import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class ServerInitiator {

    private JFrame frame = new JFrame();
    private JDesktopPane desktop = new JDesktopPane();
    private ServerI server;

    public static void main(String args[]) {

        int port = Integer.parseInt(JOptionPane.showInputDialog("Sheno portin:"));
        new ServerInitiator().initialize(port);

    }

    public void initialize(int port) {

        try {
            Registry registry = LocateRegistry.createRegistry(port);

            server = new ServerImpl();

            registry.rebind("lidhja", server);


            drawGUI();


            boolean nullClient = true;
            while (nullClient) {
                try {
                    if (server.getClient() != null) {
                        System.out.println("U konektua klient i ri");
                        ClientHandler clientHandler = new ClientHandler(server, desktop);
                        nullClient = false;
                    }
                } catch (RemoteException ex) {
                    
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerInitiator.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (RemoteException ex) {
            Logger.getLogger(ServerInitiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void drawGUI() {
        frame.add(desktop, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);}}