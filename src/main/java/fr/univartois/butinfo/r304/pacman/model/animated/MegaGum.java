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
 * Le type MegaGum
 *
 * @author timothee.gros
 *
 * @version 0.1.0
 */
public class MegaGum extends AbstractAnimated {

    /**
     * Crée une nouvelle instance de MegaGum.
     * 
     * @param game
     * @param xPosition
     * @param yPosition
     * @param sprite
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

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.
     * butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public void onCollisionWith(Ghost other) {
        // Les fantomes ignorent les megagums
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.
     * butinfo.r304.pacman.model.animated.PacGum)
     */
    @Override
    public void onCollisionWith(PacGum other) {
        // Les megagums ne rentrent pas en collisions avec les pacgums
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.
     * butinfo.r304.pacman.model.animated.PacGum)
     */
    @Override
    public void onCollisionWith(MegaGum other) {
        // Les megagum ne rentrent pas en collisions les unes avec les autres
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Bonus)
     */
    @Override
    public void onCollisionWith(Bonus other) {
        // Les megagum ne rentrent pas en collisions lavec les bonus.
    }
}
