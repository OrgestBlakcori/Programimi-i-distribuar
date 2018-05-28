import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;




class ClientHandler extends Thread {
    private JDesktopPane desktop = null;
    private final ServerI server;
    private final JInternalFrame interFrame = new JInternalFrame("Desktopi i klientit", true, true, true);
    private final JPanel cPanel = new JPanel();

    public ClientHandler(ServerI server, JDesktopPane desktop) {
        this.server = server;
        this.desktop = desktop;
        start();
    }

    public void drawGUI() {
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel, BorderLayout.CENTER);
        interFrame.setSize(100, 100);
        desktop.add(interFrame);
        try {
        
            interFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
        }
        
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }

    public void run() {

        try {
        
            drawGUI();
            
            Rectangle clientScreenDim = server.getRectangle();
            server.setPanel(cPanel);
            
            
            new ClientCommandsSender(server, cPanel, clientScreenDim);
            
        } catch (RemoteException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
}}}