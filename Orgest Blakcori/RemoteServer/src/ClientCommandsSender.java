import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.*;


public class ClientCommandsSender implements KeyListener,
        MouseMotionListener, MouseListener {

    private final ServerI server ;
    private final JPanel cPanel;
    private final Rectangle clientScreenDim ;

    ClientCommandsSender(ServerI server, JPanel p, Rectangle r) {
        this.server = server;
        cPanel = p;
        clientScreenDim = r;
        
        cPanel.addKeyListener(this);
        cPanel.addMouseListener(this);
        cPanel.addMouseMotionListener(this);

    }

    public void mouseMoved(MouseEvent e) {
        double xScale = clientScreenDim.getWidth() / cPanel.getWidth();
        System.out.println("Pozita x: " + xScale);
        double yScale = clientScreenDim.getHeight() / cPanel.getHeight();
        System.out.println("Pozita y: " + yScale);
        System.out.println("Miu levizi");

       String command = EnumCommands.MOVE_MOUSE.getAbbrev() + " " + (int) (e.getX() * xScale) + " " +(int) (e.getY() * yScale);
       sendActionToClient(command);
    }

    private void sendActionToClient(String action)
    {
         try {
              server.getClient().sendActionsToClient(action);
            
        } catch (RemoteException ex) {
            Logger.getLogger(ClientCommandsSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mousePressed(MouseEvent e) {

        int button = e.getButton();
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        String command = EnumCommands.PRESS_MOUSE.getAbbrev() + " " + xButton;
        sendActionToClient(command);
        System.out.println(command);
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("Miu u leshua");
        int button = e.getButton();
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        String command = EnumCommands.RELEASE_MOUSE.getAbbrev() + " " + xButton;
        sendActionToClient(command);
    }
    public void keyPressed(KeyEvent e) {
        System.out.println("Tasti u shtyp");
        
        String command = EnumCommands.PRESS_KEY.getAbbrev() + " " + e.getKeyCode();
        sendActionToClient(command);
    }

    public void keyReleased(KeyEvent e) {
        System.out.println("Tasti u leshua");
         String command = EnumCommands.RELEASE_KEY.getAbbrev() + " " + e.getKeyCode();
        sendActionToClient(command);
    }
    
    public void mouseEntered(MouseEvent e) {
    }

    
    public void mouseExited(MouseEvent e) {

    }
    public void keyTyped(KeyEvent e) {
    }
        public void mouseClicked(MouseEvent e) {
    }
        public void mouseDragged(MouseEvent e) {
    }}