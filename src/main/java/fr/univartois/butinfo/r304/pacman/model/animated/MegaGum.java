/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 timothee.gros
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import java.util.List;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * La classe MegaGum crée un type d'animated représentant les megagums dans le jeu.
 *
 * @author timothee.gros
 *
 * @version 0.1.0
 */
public class MegaGum extends AbstractAnimated {

    /**
     * Crée une nouvelle instance de MegaGum.
     * 
     * @param game le jeu Pacman
     * @param xPosition la position en x
     * @param yPosition la position en y
     * @param sprite le sprite du MegaGum
     */
    public MegaGum(PacmanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
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
        List<Ghost> ghosts = game.getGhostList();
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).setState(new VulnerableStateGhost());
        }
        super.onDestruction();
    }
}
