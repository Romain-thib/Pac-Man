/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.animated.strategyghost.ChaseStrategyGhost;
import fr.univartois.butinfo.r304.pacman.model.animated.strategyghost.DumbStrategyGhost;
import fr.univartois.butinfo.r304.pacman.model.animated.strategyghost.SurroundStrategyGhost;

/**
 * La classe GhostColor enumère les différentes couleurs de fantômes et leurs stratégies de déplacement associées
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
public enum GhostColor {
    /**
     * L'attribut RED...
     */
    RED(new ChaseStrategyGhost(75)),/**
     * L'attribut PINK...
     */
    PINK(new SurroundStrategyGhost(75)),/**
     * L'attribut BLUE...
     */
    BLUE(new SurroundStrategyGhost(-75)),/**
     * L'attribut ORANGE...
     */
    ORANGE(new DumbStrategyGhost(75));
    
    /**
     * L'attribut moveStrategy...
     */
    private IStrategyGhost moveStrategy;
    
    /**
     * Crée une nouvelle instance de GhostColor.
     * 
     * @param strategy la stratégie de déplacement du fantôme
     */
    private GhostColor(IStrategyGhost strategy) {
        this.moveStrategy = strategy;
    }
    
    /**
     * Donne l'attribut moveStrategy de cette instance de GhostColor.
     *
     * @return L'attribut moveStrategy de cette instance de GhostColor.
     */
    public IStrategyGhost getMoveStrategy() {
        return moveStrategy;
    }
}

