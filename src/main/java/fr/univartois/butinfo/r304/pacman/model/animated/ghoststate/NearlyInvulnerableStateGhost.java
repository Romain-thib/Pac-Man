/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated.ghoststate;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;


/**
 * La classe NearlyInvulnerableStateGhost représente l'état presque invulnerable d'un fantôme.
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStateGhost.class, participant = StateParticipant.IMPLEMENTATION)
public class NearlyInvulnerableStateGhost extends VulnerableStateGhost {
    /**
     * L'attribut temps représente le temps restant avant de redevenir vulnerable
     */
    private double time = 3000;
    
    /**
     * Les sprites du fantômes dans cet état
     */
    private Sprite spritesGhost = null;
    
    @Override
    public void moveState(Ghost ghost, PacmanGame game) {
        super.moveState(ghost, game);
        time -= 1;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#getSpriteGhost(fr.univartois.butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public void getSpriteGhost(Ghost ghost) {
        if (spritesGhost == null) {
            spritesGhost = SpriteStore.getInstance().getSprite(
                    "ghosts/hurt/1", "ghosts/hurt/2",
                    "ghosts/afraid/1", "ghosts/afraid/2");
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
        if (this.time <= 0) {
            return new InvulnerableStateGhost();
        } else {
            return this;
        }
    }
}

