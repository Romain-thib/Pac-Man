/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Le type StrategyInterface
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StrategyDesignPattern(strategy = IStrategyGhost.class, participant = StrategyParticipant.INTERFACE)
public interface IStrategyGhost {
    
    /**
     * La méthode moveStrategy pour le déplacement des fantômes
     * 
     * @param ghost de type {@link Ghost}
     */
    void moveStrategy(Ghost ghost);
}

