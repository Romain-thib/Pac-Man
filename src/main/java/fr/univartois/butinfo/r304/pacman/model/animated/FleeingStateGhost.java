/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * Le type FleeingStateGhost
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */

@StateDesignPattern(state = IStateGhost.class, participant = StateParticipant.IMPLEMENTATION)
public class FleeingStateGhost implements IStateGhost {
    /**
     * L'attribut temps représente le temps restant avant de redevenir vulnerable
     */
    private double time = 5000;
    
    /**
     * Les sprites du fantômes dans cet état
     */
    private Sprite spritesGhost = null;
    
    /**
     * L'attribut SPEED vitesse pour les fantôme fuyant
     */
    private static final double SPEED = -80;
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#moveState(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long, double, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void moveState(Ghost ghost, PacmanGame game) {
        ghost.setStrategyGhost(new ChaseStrategyGhost(SPEED));
        time -= 1;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#handleCollisionWithPacman(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public IStateGhost handleCollisionWithPacman(Ghost ghost, PacmanGame game) {
        // Rien, le fantôme fuis et est invulnérable
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#getSpriteGhost()
     */
    @Override
    public void getSpriteGhost(Ghost ghost) {
        if (spritesGhost == null) {
            spritesGhost = SpriteStore.getInstance().getSprite("ghosts/afraid/1", "ghosts/afraid/2");
        }
        ghost.setSprite(spritesGhost);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#nextState()
     */
    @Override
    public IStateGhost nextState() {
        if (time <= 0) {
            return new InvulnerableStateGhost();
        } else {
            return this;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#handleCollisionWithAnimated(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, fr.univartois.butinfo.r304.pacman.model.IAnimated)
     */
    @Override
    public void handleCollisionWithAnimated(Ghost ghost, IAnimated animated) {
        //
    }

}

