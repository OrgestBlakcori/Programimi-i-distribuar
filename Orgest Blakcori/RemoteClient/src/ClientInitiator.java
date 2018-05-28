import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ClientInitiator {

    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Shtyp IP e serverit");
        String port = JOptionPane.showInputDialog("Shtyp porten e serverit");
        new ClientInitiator().initialize(ip, Integer.parseInt(port));
    }
    private ClientI client;
    private ServerI server;

    public void initialize(String ip, int port) {

        Robot robot; 
        Rectangle rectangle; 

        try {
            client = new ClientImpl();
            
            Registry myRegistry = LocateRegistry.getRegistry(ip, port);
            server = (ServerI) myRegistry.lookup("lidhja");
            
            rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            robot = new Robot(getGraphicsDevice());

            client.setRobot(robot);
            server.setRectangle(rectangle);
            server.setClient(client);


            drawGUI();
            
            new ScreenSpyer(server, robot, rectangle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private GraphicsDevice getGraphicsDevice() {
        GraphicsEnvironment graphicEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return graphicEnvironment.getDefaultScreenDevice();
    }

    private void drawGUI() {
        JFrame frame = new JFrame("");
        JButton button = new JButton("EXIT");

        frame.setBounds(1300, 700, 150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);}});
        frame.setVisible(true);}}