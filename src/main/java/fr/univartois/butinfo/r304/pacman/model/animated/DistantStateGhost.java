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
 * Le type distantStateGhost
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStateGhostMove.class, participant = StateParticipant.IMPLEMENTATION)
public class DistantStateGhost implements IStateGhostMove {

    
    /**
     * L'attribut temps pour actualise tout les 7 secondes
     */
    private double temps = 7000;
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#moveState(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long)
     */
    @Override
    public void moveState(Ghost ghost, long delta, double speedOfGhostState, PacmanGame game) {
        if(game.getCellAt(ghost.getX()+1, ghost.getY()).isEmpty()){
            ghost.setVerticalSpeed(0);
            ghost.setHorizontalSpeed(speedOfGhostState);
        } else {
            ghost.setVerticalSpeed(speedOfGhostState);
            ghost.setHorizontalSpeed(0);
        }
        temps -= delta;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#nextState()
     */
    @Override
    public IStateGhostMove nextState() {
        if (temps <= 0) {
            return new ClassicStateGhost();
        } else {
            return this;
        }
    }
    
}

