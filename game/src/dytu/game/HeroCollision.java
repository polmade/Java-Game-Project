package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class HeroCollision implements CollisionListener {
    private Hero hero;

    public HeroCollision(Hero hero){
        this.hero = hero;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Covid){
            hero.setHealth(3* (((Covid) collisionEvent.getOtherBody()).getRadiusInt()));
            //System.out.println(hero.getHealth());
            //collide(collisionEvent);
            //hero.setPosition(new Vec2(hero.getPosition().x-3, hero.getPosition().y));


            //THIS NEEDS DOING
            hero.setLinearVelocity(new Vec2(-150, 5));
        }
    }
}
