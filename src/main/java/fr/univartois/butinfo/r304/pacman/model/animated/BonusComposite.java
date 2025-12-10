/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import java.util.Random;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.bonus.InvulnerableBonus;
import fr.univartois.butinfo.r304.pacman.model.animated.bonus.PacmanSpeedBonus;
import fr.univartois.butinfo.r304.pacman.model.animated.bonus.ScoreBonus;
import fr.univartois.butinfo.r304.pacman.model.animated.bonus.SlowGhostBonus;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.dpprocessor.designpatterns.composite.CompositeDesignPattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;

/**
 * La classe BonusComposite qui gère les bonus multiple pour pacman
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@CompositeDesignPattern(IAnimated.class)
public class BonusComposite implements IAnimated {
    
    /**
     * L'attribut bonuses qui est une liste de bonus pour pacman
     */
    private IAnimated[] bonuses;
    
    /**
     * L'attribut bonusSlowGhost qui est un bonus pour ralentir les fantômes
     */
    private Bonus bonusSlowGhost;
    
    /**
     * L'attribut rand pour choisir un bonus aléatoirement
     */
    private Random rand = new Random();

    /**
     * Crée une nouvelle instance de BonusComposite.
     * @param game correspondant a la game
     * @param xPosition correspondant a la position en x de ce bonus
     * @param yPosition correspondant a la position en y de ce bonus
     * @param sprites les sprites de ce bonus
     */
    public BonusComposite(PacmanGame game, double xPosition, double yPosition, Sprite sprites) {
        super();
        bonuses = new Bonus[] {new ScoreBonus(game, xPosition, yPosition, sprites),
                new PacmanSpeedBonus(game, xPosition, yPosition, sprites),
                new InvulnerableBonus(game, xPosition, yPosition, sprites)
                }; //rajouté les prochains bonus ici si on en fait plus pour pacman
        
        bonusSlowGhost = new SlowGhostBonus(game, xPosition, yPosition, sprites);
    }

    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.IAnimated)
     */
    @Override
    public void onCollisionWith(IAnimated other) {
        int r = rand.nextInt(bonuses.length);
        bonuses[r].onCollisionWith(other);
        bonusSlowGhost.onCollisionWith(other);
        }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacMan)
     */
    @Override
    public void onCollisionWith(PacMan other) {
    	this.onCollisionWith((IAnimated) other); 
        this.onDestruction();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public void onCollisionWith(Ghost other) {
        //Vide car les bonus ne rentrent pas en collision avec les fantômes
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacGum)
     */
    @Override
    public void onCollisionWith(PacGum other) {
        //Vide car les pacgums ne rentrent pas en collision avec les bonus
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.MegaGum)
     */
    @Override
    public void onCollisionWith(MegaGum other) {
        //Vide car les megaGum ne rentrent pas en collision avec les bonus
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Bonus)
     */
    @Override
    public void onCollisionWith(Bonus other) {
        //Vide car les bonus ne rentrent pas en collision entre eux
    }


    @Override
    public int getWidth() {
        return bonusSlowGhost.getWidth();
    }

    @Override
    public int getHeight() {
        return bonusSlowGhost.getHeight();
    }

    @Override
    public void setX(double xPosition) {
        bonusSlowGhost.setX(xPosition);
    }

    @Override
    public int getX() {
        return bonusSlowGhost.getX();
    }

    @Override
    public DoubleProperty xProperty() {
        return bonusSlowGhost.xProperty();
    }

    @Override
    public void setY(double yPosition) {
        bonusSlowGhost.setY(yPosition);
    }

    @Override
    public int getY() {
        return bonusSlowGhost.getY();
    }

    @Override
    public DoubleProperty yProperty() {
        return bonusSlowGhost.yProperty();
    }

    @Override
    public void setRotate(double rotate) {
        bonusSlowGhost.setRotate(rotate);
    }

    @Override
    public double getRotate() {
        return bonusSlowGhost.getRotate();
    }

    @Override
    public DoubleProperty rotateProperty() {
        return bonusSlowGhost.rotateProperty();
    }

    @Override
    public void setHorizontalSpeed(double speed) {
        bonusSlowGhost.setHorizontalSpeed(speed);
    }

    @Override
    public double getHorizontalSpeed() {
        return bonusSlowGhost.getHorizontalSpeed();
    }

    @Override
    public void setVerticalSpeed(double speed) {
        bonusSlowGhost.setVerticalSpeed(speed);
    }

    @Override
    public double getVerticalSpeed() {
        return bonusSlowGhost.getVerticalSpeed();
    }

    @Override
    public void setSprite(Sprite sprite) {
        bonusSlowGhost.setSprite(sprite);
    }

    @Override
    public Sprite getSprite() {
        return bonusSlowGhost.getSprite();
    }

    @Override
    public ObjectProperty<Sprite> spriteProperty() {
        return bonusSlowGhost.spriteProperty();
    }

    @Override
    public ObjectProperty<Image> imageProperty() {
        return bonusSlowGhost.imageProperty();
    }

    @Override
    public void setDestroyed(boolean destroyed) {
        bonusSlowGhost.setDestroyed(destroyed);
    }

    @Override
    public boolean isDestroyed() {
        return bonusSlowGhost.isDestroyed();
    }

    @Override
    public BooleanProperty destroyedProperty() {
        return bonusSlowGhost.destroyedProperty();
    }

    @Override
    public void onCreation() {
        bonusSlowGhost.onCreation();
    }

    @Override
    public void onSpawn() {
        bonusSlowGhost.onSpawn();
    }

    @Override
    public boolean onStep(long delta) {
        return bonusSlowGhost.onStep(delta);
    }

    @Override
    public String toString() {
        return bonusSlowGhost.toString();
    }

    @Override
    public boolean isCollidingWith(IAnimated other) {
        return bonusSlowGhost.isCollidingWith(other);
    }

    @Override
    public void onDespawn() {
        bonusSlowGhost.onDespawn();
    }

    @Override
    public void onDestruction() {
        bonusSlowGhost.onDestruction();
    }

    @Override
    public IAnimated self() {
        return bonusSlowGhost.self();
    }

    @Override
    public int hashCode() {
        return bonusSlowGhost.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return bonusSlowGhost.equals(obj);
    }
}

