/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 timothee.gros
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Le type PacMan
 *
 * @author timothee.gros
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStatePacman.class, participant = StateParticipant.CONTEXT)
public class PacMan extends AbstractAnimated{
    /**
     * L'attribut hp.
     */
    private IntegerProperty hp; 
    /**
     * L'attribut score.
     */
    private IntegerProperty score;
    
    /**
     * Le multiplicateur de score de pacman lorsqu'il mange une pacgum
     */
    private double scoreMult;
    
    /**
     * L'attribut vulnerabilities.
     */
    private IStatePacman state = new PacmanVulnerable();  
    
    /**
     * L'attribut spriteStore pour gérer les sprites de pacman.
     */
    private SpriteStore spriteStore; 
    
    /**
     * Crée une nouvelle instance de PacMan.
     * @param game : instance de jeu 
     * @param xPosition : position sur l'axe horizontal
     * @param yPosition : position sur l'axe vertical
     * @param sprite : apparence du personnage
     */
    public PacMan(PacmanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        hp = new SimpleIntegerProperty(3);
        score = new SimpleIntegerProperty(0);
        scoreMult = 1;
        spriteStore = SpriteStore.getInstance();
    }
    
    /**
     * Donne l'attribut hp de cette instance de PacMan.
     *
     * @return L'attribut hp de cette instance de PacMan.
     */
    public IntegerProperty getHpProperty() {
        return hp;
    }
    
    /**
     * Donne l'attribut score de cette instance de PacMan.
     *
     * @return L'attribut score de cette instance de PacMan.
     */
    public IntegerProperty getScoreProperty() {
        return score;
    }
    
    /**
     * Modifie l'attribut scoreMult de cette instance de PacMan.
     *
     * @param scoreMult La nouvelle valeur de l'attribut scoreMult pour cette instance de PacMan.
     */
    public void setScoreMult(double scoreMult) {
        this.scoreMult = scoreMult;
    }
    
    /**
     * Modifie l'attribut state de cette instance de PacMan.
     *
     * @param state La nouvelle valeur de l'attribut state pour cette instance de PacMan.
     */
    public void setState(IStatePacman state) {
        this.state = state;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.IAnimated)
     */
    @Override
    public void onCollisionWith(IAnimated other) {
        other.onCollisionWith(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacMan)
     */
    @Override
    public void onCollisionWith(PacMan other) {
        // je ne gère pas les collisions avec moi même
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public void onCollisionWith(Ghost other) {
        state = state.onCollisionWithGhost(this);
        if (hp.get() <= 0) {
            game.playerIsDead();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacGum)
     */
    @Override
    public void onCollisionWith(PacGum other) {
        score.set(score.get()+(int)Math.round(10*scoreMult)); 
        game.pacGumEaten(other);
    }
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#onStep(long)
     */
    @Override
    public boolean onStep(long delta) {
        state = state.changeStatePacman(delta);
        state.handleState(game);
        this.setSprite(state.getSprite(spriteStore));
        return super.onStep(delta);
    }
    
    // Getters et Setters, possibilité de les changé car ce sont des IntegerProperty
    /**
     * @param hp Le nombre de points de vie à définir
     */
    public void setHp(int hp) {
        this.hp.set(hp);
    }
    
    /**
     * @return L'attribut hp de cette instance de PacMan.
     */
    public IntegerProperty getHp() {
        return hp;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacGum)
     */
    @Override
    public void onCollisionWith(MegaGum other) {
        score.set(score.get()+(int)Math.round(50*scoreMult)); 
        game.megaGumEaten(other);
    }
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Bonus)
     */
    @Override
    public void onCollisionWith(Bonus other) {
        other.handleBonus();
    }
}

