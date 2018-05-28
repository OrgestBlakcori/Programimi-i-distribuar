import java.awt.Robot;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientImpl extends UnicastRemoteObject implements ClientI, Serializable {

    private Robot robot;

    public ClientImpl() throws RemoteException {

    }

    public void sendActionsToClient(String action) throws RemoteException {
        Scanner scanner = new Scanner(action);
        int command = scanner.nextInt();
        
        switch (command) {
            case -1:
                robot.mousePress(scanner.nextInt());
                break;
            case -2:
                robot.mouseRelease(scanner.nextInt());
                break;
            case -3:
                robot.keyPress(scanner.nextInt());
                break;
            case -4:
                robot.keyRelease(scanner.nextInt());
                break;
            case -5:
                robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                break;
        }

    }
    public void setRobot(Robot robot) throws RemoteException {
        this.robot = robot;}}