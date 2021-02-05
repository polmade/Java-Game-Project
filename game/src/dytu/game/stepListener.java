package dytu.game;

import city.cs.engine.StepEvent;
import org.jbox2d.common.Vec2;

public class stepListener implements city.cs.engine.StepListener {
    private GameView view;
    private dytu.game.hero hero;
    private heroCollision herocollide;
    public stepListener(GameView view, dytu.game.hero hero) {
        this.view = view;
        this.hero = hero;
    }
    @Override
    public void preStep(StepEvent e) {}
    @Override
    public void postStep(StepEvent e) {
        //set view centre for the world, based on position of hero character
        this.view.setCentre(new Vec2(hero.getPosition().x, 0));
        //System.out.println(this.hero.getPosition().y);
    }


}
