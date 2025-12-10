/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated.bonus;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.Bonus;
import fr.univartois.butinfo.r304.pacman.model.animated.pacmanstate.ScoreBonusState;
import fr.univartois.butinfo.r304.pacman.view.Sprite;

/**
 * La classe ScoreBonus crée un type de bonus augmentant le score de Pacman.
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
public class ScoreBonus extends Bonus {
    /**
     * Crée une nouvelle instance de ScoreBonus.
     * @param game la partie en cours
     * @param xPosition la position x du bonus
     * @param yPosition la position y du bonus
     * @param sprites les sprites du bonus
     */
    public ScoreBonus(PacmanGame game, double xPosition, double yPosition, Sprite sprites) {
        super(game, xPosition, yPosition, sprites);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.Bonus#handleBonus()
     */
    @Override
    public void handleBonus() {
        game.getPlayer().setState(new ScoreBonusState());
    }
}

