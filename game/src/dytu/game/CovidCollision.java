package dytu.game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

public class CovidCollision implements CollisionListener {
    private Covid covid;

    public CovidCollision(Covid covid){
        this.covid = covid;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Hero){
            System.out.println("Collision detected with: "+collisionEvent.getOtherBody().getName());
        } if (collisionEvent.getOtherBody() instanceof Syringe){
            //change infectiousness of covid instance, and check if covid instance has health of <=0 and then destroy if true
            covid.setInfectiousness(Syringe.getDamage());
            collisionEvent.getOtherBody().destroy();
            System.out.println(covid.getInfectiousness());
            if(covid.getInfectiousness()<=0){
                covid.destroy();
            }
            //System.out.println("Collision detected");
        }
    }
}
