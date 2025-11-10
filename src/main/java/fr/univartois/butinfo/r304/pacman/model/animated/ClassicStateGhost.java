/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * Le type ClassicStateGhost
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStateGhostMove.class, participant = StateParticipant.IMPLEMENTATION)
public class ClassicStateGhost implements IStateGhostMove{

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#moveState(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long)
     */
    @Override
    public void moveState(Ghost ghost, long delta, double speedOfGhostState, PacmanGame game) {
        ghost.setStrategyGhost(new ChaseRandomCompositeStrategyGhost());
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#nextState()
     */
    @Override
    public IStateGhostMove nextState() {
        return this;
    }

}

