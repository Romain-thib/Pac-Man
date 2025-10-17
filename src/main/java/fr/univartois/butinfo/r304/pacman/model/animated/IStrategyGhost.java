/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
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
     * @param ghost le famtôme a déplacée
     * @param delta la valeur selon le lequel on actualise le deplacement 
     * @param game instance correpondant a la game
     */
    void moveStrategy(Ghost ghost, long delta, PacmanGame game);
}

