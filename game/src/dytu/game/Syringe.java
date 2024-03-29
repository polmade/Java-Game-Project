package dytu.game;

import city.cs.engine.*;
import dytu.world.WorldBuilder;

public class Syringe extends DynamicBody {
    //declare and/or instantiate variables
    private WorldBuilder world;
    private static final Shape syringeShape = new BoxShape(0.74f,0.35f);
    private static final BodyImage syringeImage = new BodyImage("Data/syringe(1).png", 1.5f);
    private static int damage = 10;
    private long creationTime;

    //get world

    @Override
    public WorldBuilder getWorld() {
        return world;
    }

    //allows for comparison of current frame and the creation frame, so that instances can be removed if too old
    public long getCreationTime(){
        return creationTime;
    }

    //allows for the health/infectiousness of covid instances to be reduced
    public static int getDamage() {
        return damage;
    }

    public Syringe(WorldBuilder world, long creationTime) {
        super(world, syringeShape);
        addImage(syringeImage);
        this.world = world;
        this.creationTime = creationTime;
    }
}
