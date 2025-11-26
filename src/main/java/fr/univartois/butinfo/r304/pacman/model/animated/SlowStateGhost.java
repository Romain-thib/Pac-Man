/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
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
 * La classe SlowStateGhost permet de rendre les fantômes lents
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStateGhost.class, participant = StateParticipant.IMPLEMENTATION)
public class SlowStateGhost implements IStateGhost{
    
    /**
     * L'attribut temps représente le temps que les fantôme reste lent
     */
    private double time = 5000;
    
    /**
     * Les sprites du fantômes dans cet état
     */
    private Sprite spritesGhost = null;
    
    /**
     * L'attribut SPEED vitesse pour les fantôme lent
     */
    private static final double SPEED = 50;
    
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#moveState(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long, double, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void moveState(Ghost ghost, PacmanGame game) {
        IStrategyGhost strategy = ghost.getColor().getMoveStrategy();
        strategy.setSpeed(SPEED);
        time -= 1;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#handleCollisionWithPacman(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public IStateGhost handleCollisionWithPacman(Ghost ghost, PacmanGame game) {
        // Rien, les famtômes sont lents mais pas mangables
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#getSpriteGhost()
     */
    @Override
    public void getSpriteGhost(Ghost ghost) {
        //redonne les sprites de bases
        if(spritesGhost == null) {
            spritesGhost = SpriteStore.getInstance().getSprite(
                    "ghosts/" + ghost.getColor().name().toLowerCase() + "/1",
                    "ghosts/" + ghost.getColor().name().toLowerCase() + "/2");
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
        animated.onCollisionWith(ghost);
    }

}

