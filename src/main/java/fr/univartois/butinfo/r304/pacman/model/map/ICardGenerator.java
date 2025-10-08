/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 timothee.gros
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map;

import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Le type ICardGenerator
 *
 * @author timothee.gros
 *
 * @version 0.1.0
 */
@StrategyDesignPattern(strategy = ICardGenerator.class, participant = StrategyParticipant.INTERFACE)
public interface ICardGenerator {

    /**
     * Génère une nouvelle carte de jeu de dimensions données.
     *
     * @param height Le nombre de lignes de cellules.
     * @param width Le nombre de colonnes de cellules.
     *
     * @return La carte générée.
     */
    GameMap generate(int height, int width);

}
