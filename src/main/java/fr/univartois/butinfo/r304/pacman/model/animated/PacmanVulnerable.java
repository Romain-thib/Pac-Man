/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;


/**
 * Le type PacmanVulnerable
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
public class PacmanVulnerable implements IStatePacman{

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public void onCollisionWith(Ghost other) {
        // TODO Auto-generated method stub.
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#changeStatePacman(long)
     */
    @Override
    public IStatePacman changeStatePacman(long time) {
        // TODO Auto-generated method stub.
        return null;
    }
    
}

