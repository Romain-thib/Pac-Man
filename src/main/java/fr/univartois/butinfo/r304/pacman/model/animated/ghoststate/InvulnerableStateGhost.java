/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated.ghoststate;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost;
import fr.univartois.butinfo.r304.pacman.model.animated.IStrategyGhost;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * La classe InvulnerableStateGhost, l'etat ou le fantôme est invulnerable 
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStateGhost.class, participant = StateParticipant.IMPLEMENTATION)
public class InvulnerableStateGhost implements IStateGhost{
    
    /**
     * Attribut spritesGhost pour geres les sprites du fantôme
     */
    private Sprite spritesGhost = null;

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#moveState(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long, double, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void moveState(Ghost ghost, PacmanGame game) {
        // déplacement de bases
        IStrategyGhost strategy = ghost.getColor().getMoveStrategy();
        strategy.setSpeed(75);
        ghost.setStrategyGhost(strategy);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#handleCollisionWithPacman(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public IStateGhost handleCollisionWithPacman(Ghost ghost, PacmanGame game) {
      //Volontairement vide, le fantôme ne peut pas être mangé
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
        return this;
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

