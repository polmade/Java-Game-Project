package dytu.game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

public class Level_One extends WorldBuilder {
    public Level_One(){
        super();
        // make the ground
        Shape groundShape = new BoxShape(200, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -11.5f));

        // make the left boundary wall
        Shape wallShape = new BoxShape(2, 9);
        StaticBody leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-13, -3));


        // make the character

        hero.setPosition(new Vec2(-8, -10));
        HeroCollision colliderHero = new HeroCollision(hero);
        hero.addCollisionListener(colliderHero);

        monster.setPosition(new Vec2(-5, -10));

        //drawStuff drawing = new drawStuff(hero);
        //drawing.displayHealth();
    }
}
