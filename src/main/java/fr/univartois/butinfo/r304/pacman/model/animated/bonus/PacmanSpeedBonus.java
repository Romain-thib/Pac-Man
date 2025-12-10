/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated.bonus;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.Bonus;
import fr.univartois.butinfo.r304.pacman.model.animated.pacmanstate.PacmanSpeedState;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * La classe PacmanSpeedBonus crée un type de bonus augmentant la vitesse de Pacman pendant un certain temps.
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
public class PacmanSpeedBonus extends Bonus {
    /**
     * Crée une nouvelle instance de PacmanSpeedBonus.
     * @param game la partie en cours
     * @param xPosition la position x du bonus
     * @param yPosition la position y du bonus
     * @param sprites Le sprite du bonus
     */
    public PacmanSpeedBonus(PacmanGame game, double xPosition, double yPosition,
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
        game.getPlayer().setState(new PacmanSpeedState());
    }
}

