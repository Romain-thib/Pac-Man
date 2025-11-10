/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;

/**
 * La classe InvulnerableStateGhost, l'etat ou le fantôme est invulnerable 
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
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
    public void moveState(Ghost ghost, long delta, double speedOfGhostState, PacmanGame game) {
        // déplacement de bases 
        ghost.setStrategyGhost(ghost.getColor().getMoveStrategy());
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStateGhost#handleCollisionWithPacman(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void handleCollisionWithPacman(Ghost ghost, PacmanGame game) {
        //Volontairement vide, le fantôme ne peut pas être mangé
        
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
            spritesGhost = new SpriteStore().getSprite(
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

}

