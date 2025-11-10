/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;

/**
 * Le type FleeingStateGhost
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
public class FleeingStateGhost implements IStateGhost {
    /**
     * L'attribut temps représente le temps restant avant de redevenir vulnerable
     */
    private double time = 5000;
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#moveState(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long, double, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void moveState(Ghost ghost, long delta, double speedOfGhostState, PacmanGame game) {
        ghost.setStrategyGhost(new ChaseStrategyGhost(-speedOfGhostState));
        time -= 1;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#handleCollisionWithPacman(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void handleCollisionWithPacman(Ghost ghost, PacmanGame game) {
        // Rien, le fantôme fuis et est invulnérable
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#getSpriteGhost()
     */
    @Override
    public void getSpriteGhost(Ghost ghost) {
        ghost.setSprite(new SpriteStore().getSprite("ghosts/afraid/1", "ghosts/afraid/2"));
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#nextState()
     */
    @Override
    public IStateGhost nextState() {
        if (time <= 0) {
            return null; // TODO : Remplacer par état vulnérable
        } else {
            return this;
        }
    }

}

