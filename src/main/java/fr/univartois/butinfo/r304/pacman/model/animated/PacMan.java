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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Le type PacMan
 *
 * @author timothee.gros
 *
 * @version 0.1.0
 */
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
        hp.set(hp.get()-1);
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
        score.set(score.get()+10); 
    }  
}

