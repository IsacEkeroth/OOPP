import view.Entity;
import view.MainView;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class App {

    private static final int windowWidth = 1280;
    private static final int windowHeight = 720;

    public static void start() {
        MainView view = new MainView(windowWidth, windowHeight);

        // this should go in the model facade but is here for now
        Entity bgEntity = new Entity(new Point(0, 0), 0, new Point(windowWidth, windowHeight),
                getImage("./src/view/images/akvarium1.jpg"));
        Entity f1 = new Entity(new Point(0, 0), 1, new Point(50, 50),
                getImage("./src/view/images/icon-grupp26.png"));
        Entity f2 = new Entity(new Point(200, 200), 1, new Point(50, 50),
                getImage("./src/view/images/icon-grupp26.png"));

        view.addEntity(bgEntity);
        view.addEntity(f1);
        view.addEntity(f2);

        view.repaint();

    }

    private static BufferedImage getImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // fallback
        }
        return img;
    }

}
