package dytu.game;

import city.cs.engine.World;
import dytu.world.WorldBuilder;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private GameView view;
    private WorldBuilder world;

    public MouseHandler(GameView view, WorldBuilder world){
        this.view = view;
        this.world = world;
    }

    public void mousePressed(MouseEvent e){
        Syringe syringe = new Syringe(world);
        syringe.addCollisionListener(new SyringeCollision(syringe));
        syringe.setGravityScale(0.1f);
        syringe.setBullet(true);
        syringe.setPosition(world.getHero().getPosition());
        syringe.setLinearVelocity(new Vec2(25, 0));
    }
}
