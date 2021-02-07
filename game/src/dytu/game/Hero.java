package dytu.game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.pooling.arrays.Vec2Array;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Walker {
    //declare and/or instaniate variables
    private int health = 200;
    private int points = 0;
    private World world;
    //lookingForward allows the stepListener to set the view centre
    private boolean lookingForward = false;



    //define the different shapes for fixtures to be attached to the body
    private static final Shape heroShapeLeft = new PolygonShape(
            -0.65f,3.44f, -2f,-1.20f, -2.04f,-3.46f, 2.69f,-3.46f, 2.71f,3.47f
    );
    private static final Shape heroShapeRight = new PolygonShape(
            0.65f,3.44f, 2.67f,-1.13f, 2.04f,-3.46f, -2.69f,-3.46f, -2.71f,3.47f
    );

    /* This needs work, however, the premise of this segment of code is to change the direction of the player character upon key press.
    It's implemented through the keyHandler class
     */
    private static final BodyImage rightImage = new BodyImage("Data/edright.png", 7.0f);

    private static BodyImage leftImage = new BodyImage("Data/edleft.png", 7.0f);

    private BodyImage image = rightImage;

    //this allows the image attached to the body to be changed, depending on which way the body is now facing
    public BodyImage setImage(String imageUpdate) {

        switch (imageUpdate) {
            case "heroImageLeft" -> {
                this.image = leftImage;
                return this.image;
            }
            case "heroImageRight" -> {
                this.image = rightImage;
                return this.image;
            }
        }
        return this.image;
    }

    public String getImage(){
        if(this.image == rightImage){
            return "rightImage";
        } else if(this.image == leftImage){
            return "leftImage";
        }
        return null;
    }

    //this allows the shape of the fixture attached to the body to be changed, depending on which way the body is now facing
    public Fixture setFixtureShape(String shapeUpdate){

        switch(shapeUpdate) {
            case "heroShapeLeft" -> {
                SolidFixture fixture = new SolidFixture(this, heroShapeLeft, 5.0F);
                return fixture;
            }
            case "heroShapeRight" -> {
                SolidFixture fixture = new SolidFixture(this, heroShapeRight, 5.0F);
                return fixture;
            }
        }
        return null;
    }


    //gets the health, allows for displaying of health on screen
    public int getHealth() {
        return health;
    }

    //change the health of the character upon collision
    public void setHealth(int health) {
        this.health = this.health - health;
    }

    //get the world that th character is in
    public World getWorld(){
        return world;
    }

    //get lookingForward
    public boolean isLookingForward() {
        return lookingForward;
    }
    //set lookingForward
    public void setLookingForward(boolean lookingForward) {
        this.lookingForward = lookingForward;
    }

    public Hero(World world) {
        super(world, heroShapeRight);
        addImage(image);

        this.world = world;
    }
}
