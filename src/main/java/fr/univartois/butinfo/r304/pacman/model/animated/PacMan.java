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

/**
 * Le type PacMan
 *
 * @author timothee.gros
 *
 * @version 0.1.0
 */
public class PacMan extends AbstractAnimated{
    private IntegerProperty hp; 
    private IntegerProperty score;
    /**
     * Crée une nouvelle instance de PacMan.
     * @param game : instance de jeu 
     * @param xPosition : position sur l'axe horizontal
     * @param yPosition : position sur l'axe vertical
     * @param sprite : apparence du personnage
     * @param hp : nombre de points de vie
     * @param score : score du joueur
     */
    public PacMan(PacmanGame game, double xPosition, double yPosition, Sprite sprite, IntegerProperty hp,
            IntegerProperty score) {
        super(game, xPosition, yPosition, sprite);
        this.hp = hp;
        this.score = score;
    }
    
    /**
     * Donne l'attribut hp de cette instance de PacMan.
     *
     * @return L'attribut hp de cette instance de PacMan.
     */
    public IntegerProperty getHp() {
        return hp;
    }
    
    /**
     * Donne l'attribut score de cette instance de PacMan.
     *
     * @return L'attribut score de cette instance de PacMan.
     */
    public IntegerProperty getScore() {
        return score;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.IAnimated)
     */
    @Override
    public void onCollisionWith(IAnimated other) {
        // TODO Auto-generated method stub.
    }  
}

