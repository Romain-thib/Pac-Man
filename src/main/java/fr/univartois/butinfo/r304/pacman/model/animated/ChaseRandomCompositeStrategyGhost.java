/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.dpprocessor.designpatterns.composite.CompositeDesignPattern;

/**
 * la classe ChaseRandomComponentGhost qui alterne entre une stratégie de poursuite et une stratégie aléatoire pour les fantômes
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@CompositeDesignPattern(IStrategyGhost.class)
public class ChaseRandomCompositeStrategyGhost implements IStrategyGhost {
    
    /**
     * Liste des strategie a alternée pour les fantômes
     */
    private IStrategyGhost[] listeStrategys  = {new ChaseStrategyGhost(75), new DumbStrategyGhost(75)};

    /**
     * L'attribut temps pour actualise tout les 5 secondes
     */
    private double temps = 5000;

    /**
     * L'index de la stratégie courante
     */
    private int current = 0;
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStrategyGhost#moveStrategy(fr.univartois.butinfo.r304.pacman.model.animated.Ghost, long, fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void moveStrategy(Ghost ghost, long delta, PacmanGame game) {
    	// Le fantôme change de stratégie toutes les 5 secondes
        if (temps <= 0) {
            current = (current + 1) % listeStrategys.length;
            temps = 5000;
        } else {
            temps -= delta;
        }
        
        listeStrategys[current].moveStrategy(ghost, delta, game);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStrategyGhost#setSpeed(double)
     */
    @Override
    public void setSpeed(double speed) {
        //
    }

}

