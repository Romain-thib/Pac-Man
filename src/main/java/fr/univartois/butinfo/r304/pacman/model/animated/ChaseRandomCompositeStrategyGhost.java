/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;

/**
 * Le type ChaseRandomComponentGhost
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
public class ChaseRandomCompositeStrategyGhost implements IStrategyGhost {
    
    /**
     * Liste des strategie a alternée pour les fantômes
     */
    private IStrategyGhost[] listeStrategys  = {new ChaseStrategyGhost(), new DumbStrategyGhost()};

    /**
     * L'attribut temps pour actualise tout les 5 secondes
     */
    private double temps = 5000;

    private int current;
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStrategyGhost#moveStrategy(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void moveStrategy(Ghost ghost, long delta, PacmanGame game) {
     // le fantôme change de strategys tout les 5 secondes
        if (temps <= 0) {
            
            temps = 5000;
        } else {
            temps -= delta;
        }       
    }

}

