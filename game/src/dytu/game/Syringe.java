package dytu.game;

import city.cs.engine.*;

public class Syringe extends DynamicBody {
    private World world;
    private static final Shape syringeShape = new BoxShape(0.74f,0.35f);
    private static final BodyImage syringeImage = new BodyImage("Data/syringe(1).png", 1.5f);
    private static int damage = 10;
    private boolean collisionWithCovid;

    public void setCollisionWithCovid(boolean setter){
        this.collisionWithCovid = setter;
    }

    public boolean getCollisionWithCovid(){
        return collisionWithCovid;
    }

    public static int getDamage() {
        return damage;
    }

    public Syringe(World world) {
        super(world, syringeShape);
        addImage(syringeImage);
    }
}
