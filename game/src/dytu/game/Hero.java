package dytu.game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.pooling.arrays.Vec2Array;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Walker {

    private int health = 200;
    private int points = 0;
    private World world;


    private static final Shape heroShapeLeft = new PolygonShape(
            -0.9f,3.4f, -2.62f,-0.66f, -2.65f,-3.44f, 2.68f,-3.46f, 2.53f,3.4f
    );



    private static final Shape heroShapeRight = new PolygonShape(
            /*0.12f,2.06f,
            1.38f,1.32f,
            0.45f,-2.05f,
            -0.34f,-2.06f,
            -1.43f,1.32f
             */
            0.65f,3.44f, 2.67f,-1.13f, 2.04f,-3.46f, -2.69f,-3.46f, -2.71f,3.47f
    );

    /* This needs work, however, the premise of this segment of code is to change the direction of the player character upon key press.
    It's implemented throught the keyHandler class,
     */
    private static final BodyImage rightImage = new BodyImage("Data/edright.png", 7.0f);

    private static BodyImage leftImage = new BodyImage("Data/edleft.png", 7.0f);

    private static Shape shapeSetter = heroShapeRight;
    private BodyImage image = rightImage;

    public void setShapeSetter(String stateUpdate) {

        switch (stateUpdate) {
            case "heroShapeLeft" -> {
                shapeSetter = heroShapeLeft;
                this.image = leftImage;
            }
            case "heroShapeRight" -> {
                shapeSetter = heroShapeRight;
                this.image = rightImage;
            }
        }
    }


    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        this.health = this.health - health;
    }


    public World getWorld(){
        return world;
    }

    public Hero(World world) {
        super(world, shapeSetter);
        addImage(image);
        this.world = world;
    }
}
