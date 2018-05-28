import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;


class ScreenSpyer extends Thread {

    ServerI server;
    Robot robot; 
    Rectangle rectangle ; 

    public ScreenSpyer(ServerI server, Robot robot, Rectangle rectangle) {
        this.server = server;
        this.robot = robot;
        this.rectangle = rectangle;
        start();
    }

    @Override
    public void run() {

        while (true) {
            BufferedImage image = robot.createScreenCapture(rectangle);
            
            try {
            
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", bytes);
                bytes.flush();
                byte[] imageBytes = bytes.toByteArray();
                bytes.close();
                server.sendImageAsBytesToServer(imageBytes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}