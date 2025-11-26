/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * Le type InvulnerableBonus
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
public class InvulnerableBonus extends Bonus {
    /**
     * Crée une nouvelle instance de InvulnerableBonus.
     * @param game : La partie en cours
     * @param xPosition : la position x du bonus
     * @param yPosition : la position y du bonus
     * @param sprites : le sprite du bonus
     */
    public InvulnerableBonus(PacmanGame game, double xPosition, double yPosition,
            Sprite sprites) {
        super(game, xPosition, yPosition, sprites);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.Bonus#handleBonus()
     */
    @Override
    public void handleBonus() {
        PacmanInvulnerable state = new PacmanInvulnerable();
        state.setDuree(15000);
        game.getPlayer().setState(state);
    }
}

