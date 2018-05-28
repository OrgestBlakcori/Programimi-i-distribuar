import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ServerImpl extends UnicastRemoteObject implements ServerI, Serializable {

    public ClientI client;
    public Rectangle rectangle;
    private JPanel cPanel;

    public ServerImpl() throws RemoteException {
    }
    
    @Override
    public void setPanel(JPanel p) throws RemoteException {
        this.cPanel = p;
    }

    public void setClient(ClientI client) throws RemoteException {
        this.client = client;
    }

    public ClientI getClient() throws RemoteException {
        return client;
    }

    public void setRectangle(Rectangle rectangle) throws RemoteException {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() throws RemoteException {
        return rectangle;
    }

    public void sendImageAsBytesToServer(byte[] imageBytes) throws RemoteException {
        if (cPanel != null) {
            updatePanel(imageBytes);
        }
    }

    public Image readImageBytes(byte[] imageBytes) {
        try {
            InputStream in = new ByteArrayInputStream(imageBytes);
            return ImageIO.read(in);
        } catch (IOException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void updatePanel(byte[] imageBytes) {
        Graphics graphics = cPanel.getGraphics();
        int width = cPanel.getWidth();
        int height = cPanel.getHeight();
        Image image;
        ImageIcon imageIcon;
        
        image = readImageBytes(imageBytes);
        try {
        
            if (image != null) {
                image = image.getScaledInstance(width, height, Image.SCALE_FAST);
                
                graphics.drawImage(image, 0, 0, width, height, cPanel);
            }
        } catch (Exception ex){}}}