/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * La classe Bonus crée un type d'animated représentant les bonus dans le jeu.
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
public abstract class Bonus extends AbstractAnimated {

    /**
     * Crée une nouvelle instance de Bonus.
     * 
     * @param game La partie de pacman
     * @param xPosition La position en x du bonus
     * @param yPosition La position en y du bonus
     * @param sprites les sprites du bonus
     */
    protected Bonus(PacmanGame game, double xPosition, double yPosition, Sprite sprites) {
        super(game, xPosition, yPosition, sprites);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.
     * butinfo.r304.pacman.model.IAnimated)
     */
    @Override
    public void onCollisionWith(IAnimated other) {
        other.onCollisionWith(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.
     * butinfo.r304.pacman.model.animated.PacMan)
     */
    @Override
    public void onCollisionWith(PacMan other) {
        super.onDestruction();
    }

    /**
     * Gère l'effet des bonus s'ils sont mangés par pac-man.
     */
    public abstract void handleBonus();
}
