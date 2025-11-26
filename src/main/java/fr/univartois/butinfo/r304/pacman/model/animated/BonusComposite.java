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
    private Bonus[] bonuses;
    
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
    protected BonusComposite(PacmanGame game, double xPosition, double yPosition, Sprite sprites) {
        super();
        bonuses = new Bonus[] {new ScoreBonus(game, xPosition, yPosition, sprites)}; //rajouté les prochains bonus ici
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
        other.onCollisionWith(bonuses[r]);
        other.onCollisionWith(bonusSlowGhost);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacMan)
     */
    @Override
    public void onCollisionWith(PacMan other) {
        other.onDestruction();
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


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getWidth()
     */
    @Override
    public int getWidth() {
        return bonusSlowGhost.getWidth();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getHeight()
     */
    @Override
    public int getHeight() {
        return bonusSlowGhost.getHeight();
    }


    /**
     * @param xPosition
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#setX(double)
     */
    @Override
    public void setX(double xPosition) {
        bonusSlowGhost.setX(xPosition);
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getX()
     */
    @Override
    public int getX() {
        return bonusSlowGhost.getX();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#xProperty()
     */
    @Override
    public DoubleProperty xProperty() {
        return bonusSlowGhost.xProperty();
    }


    /**
     * @param yPosition
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#setY(double)
     */
    @Override
    public void setY(double yPosition) {
        bonusSlowGhost.setY(yPosition);
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getY()
     */
    @Override
    public int getY() {
        return bonusSlowGhost.getY();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#yProperty()
     */
    @Override
    public DoubleProperty yProperty() {
        return bonusSlowGhost.yProperty();
    }


    /**
     * @param rotate
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#setRotate(double)
     */
    @Override
    public void setRotate(double rotate) {
        bonusSlowGhost.setRotate(rotate);
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getRotate()
     */
    @Override
    public double getRotate() {
        return bonusSlowGhost.getRotate();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#rotateProperty()
     */
    @Override
    public DoubleProperty rotateProperty() {
        return bonusSlowGhost.rotateProperty();
    }


    /**
     * @param speed
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#setHorizontalSpeed(double)
     */
    @Override
    public void setHorizontalSpeed(double speed) {
        bonusSlowGhost.setHorizontalSpeed(speed);
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getHorizontalSpeed()
     */
    @Override
    public double getHorizontalSpeed() {
        return bonusSlowGhost.getHorizontalSpeed();
    }


    /**
     * @param speed
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#setVerticalSpeed(double)
     */
    @Override
    public void setVerticalSpeed(double speed) {
        bonusSlowGhost.setVerticalSpeed(speed);
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getVerticalSpeed()
     */
    @Override
    public double getVerticalSpeed() {
        return bonusSlowGhost.getVerticalSpeed();
    }


    /**
     * @param sprite
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#setSprite(fr.univartois.butinfo.r304.pacman.view.Sprite)
     */
    @Override
    public void setSprite(Sprite sprite) {
        bonusSlowGhost.setSprite(sprite);
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#getSprite()
     */
    @Override
    public Sprite getSprite() {
        return bonusSlowGhost.getSprite();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#spriteProperty()
     */
    @Override
    public ObjectProperty<Sprite> spriteProperty() {
        return bonusSlowGhost.spriteProperty();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#imageProperty()
     */
    @Override
    public ObjectProperty<Image> imageProperty() {
        return bonusSlowGhost.imageProperty();
    }


    /**
     * @param destroyed
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#setDestroyed(boolean)
     */
    @Override
    public void setDestroyed(boolean destroyed) {
        bonusSlowGhost.setDestroyed(destroyed);
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#isDestroyed()
     */
    @Override
    public boolean isDestroyed() {
        return bonusSlowGhost.isDestroyed();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#destroyedProperty()
     */
    @Override
    public BooleanProperty destroyedProperty() {
        return bonusSlowGhost.destroyedProperty();
    }


    /**
     * 
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#onCreation()
     */
    @Override
    public void onCreation() {
        bonusSlowGhost.onCreation();
    }


    /**
     * 
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#onSpawn()
     */
    @Override
    public void onSpawn() {
        bonusSlowGhost.onSpawn();
    }


    /**
     * @param delta
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#onStep(long)
     */
    @Override
    public boolean onStep(long delta) {
        return bonusSlowGhost.onStep(delta);
    }


    /**
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return bonusSlowGhost.toString();
    }


    /**
     * @param other
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#isCollidingWith(fr.univartois.butinfo.r304.pacman.model.IAnimated)
     */
    @Override
    public boolean isCollidingWith(IAnimated other) {
        return bonusSlowGhost.isCollidingWith(other);
    }


    /**
     * 
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#onDespawn()
     */
    @Override
    public void onDespawn() {
        bonusSlowGhost.onDespawn();
    }


    /**
     * 
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#onDestruction()
     */
    @Override
    public void onDestruction() {
        bonusSlowGhost.onDestruction();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#self()
     */
    @Override
    public IAnimated self() {
        return bonusSlowGhost.self();
    }


    /**
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#hashCode()
     */
    @Override
    public int hashCode() {
        return bonusSlowGhost.hashCode();
    }


    /**
     * @param obj
     * @return
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return bonusSlowGhost.equals(obj);
    }

    
}

