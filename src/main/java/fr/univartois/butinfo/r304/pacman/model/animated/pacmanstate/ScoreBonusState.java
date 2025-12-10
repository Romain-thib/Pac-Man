/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated.pacmanstate;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman;

/**
 * Le type ScoreBonusState
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
public class ScoreBonusState extends PacmanVulnerable {
    /**
     * L'attribut delta permet de compter le temps d'invulnérabilité.
     */
    private long delta = 0;
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#changeStatePacman(long)
     */
    @Override
    public IStatePacman changeStatePacman(long time) {
       delta += time;
       if(delta < 15000) { // 3 secondes d'invulnérabilité
           return this;
       }
        return new PacmanVulnerable();
    }
    
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#handleState(fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void handleState(PacmanGame game) {
        game.setSpeed(PacmanGame.DEFAULT_SPEED);
        game.getPlayer().setScoreMult(2);
    }
}

