/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated.ghoststate;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.IStateGhostMove;
import fr.univartois.butinfo.r304.pacman.model.animated.strategyghost.ChaseRandomCompositeStrategyGhost;
import fr.univartois.dpprocessor.designpatterns.singleton.Instance;
import fr.univartois.dpprocessor.designpatterns.singleton.SingletonDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * La classe ClassicStateGhost , l'etat classique du fantôme 
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@SingletonDesignPattern
@StateDesignPattern(state = IStateGhostMove.class, participant = StateParticipant.IMPLEMENTATION)
public final class ClassicStateGhost implements IStateGhostMove{

    /**
     * L'attribut INSTANCE...
     */
    @Instance
    private static final ClassicStateGhost INSTANCE = new ClassicStateGhost();
    
    /**
     * Crée une nouvelle instance.
     */
    private ClassicStateGhost() {
        super();
    }
    
    /**
     * Donne l'attribut instance de cette instance.
     *
     * @return L'attribut instance de cette instance.
     */
    @Instance
    public static ClassicStateGhost getInstance() {
        return INSTANCE;
    }
    
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

