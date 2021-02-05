package dytu.game;

import city.cs.engine.*;

public class Hero extends Walker {

    private int health = 200;

    private static final Shape heroShape = new PolygonShape(
            0.12f,2.06f,
            1.38f,1.32f,
            0.45f,-2.05f,
            -0.34f,-2.06f,
            -1.43f,1.32f
    );

    private static final BodyImage image = new BodyImage("Data/hero.png", 5.0f);

    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        this.health = this.health - health;
    }

    public void removeHero(){
        if( this.health <= 0){

        }
    }

    public Hero(World world) {
        super(world, heroShape);
        addImage(image);
    }
}
