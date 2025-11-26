/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;

import java.util.List;

import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.MegaGum;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;

/**
 * Le type IAbstractFactoryPacmanGame
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
public interface IAbstractFactoryPacmanGame {
    
    /**
     * @param game Récupération de l'instance du jeu pour créer pacman
     * @return Une instance de Pacman
     */
    PacMan createPacman(PacmanGame game);
    
    /**
     * @param game Récupération de l'instance du jeu pour créer des fantôme
     * @return Une instance de Ghost
     */
    List<Ghost> createGhost(PacmanGame game);
    
    /**
     * @param game Récupération de l'instance du jeu pour créer des méga-gomme
     * @return Une instance de MegaGum
     */
    IAnimated createGum(PacmanGame game);
    
    /**
     * @return Une d'instance de GameMap
     */
    GameMap createMap();
}

