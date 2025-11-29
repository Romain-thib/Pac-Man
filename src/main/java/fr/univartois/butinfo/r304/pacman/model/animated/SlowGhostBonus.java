/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * La classe SlowGhostBonus le bonus qui ralentit les fantômes
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
public class SlowGhostBonus extends Bonus{

    /**
     * Crée une nouvelle instance de SlowGhostBonus.
     * @param game correspondant a la game
     * @param xPosition correspondant a la position en x de ce bonus
     * @param yPosition correspondant a la position en y de ce bonus
     * @param sprites les sprites de ce bonus
     */
    public SlowGhostBonus(PacmanGame game, double xPosition, double yPosition, Sprite sprites) {
        super(game, xPosition, yPosition, sprites);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.Bonus#handleBonus()
     */
    @Override
    public void handleBonus() {
        game.getGhostList().forEach(ghost -> ghost.setState(new SlowStateGhost()));
    }

}

