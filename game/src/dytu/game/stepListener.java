package dytu.game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class stepListener implements city.cs.engine.StepListener {
    private GameView view;
    private Hero hero;
    private HeroCollision herocollide;
    public stepListener(GameView view, Hero hero) {
        this.view = view;
        this.hero = hero;
    }
    @Override
    public void preStep(StepEvent e) {}
    @Override
    public void postStep(StepEvent e) {
        this.view.setCentre(new Vec2(hero.getPosition().x, 0));
    }


}
