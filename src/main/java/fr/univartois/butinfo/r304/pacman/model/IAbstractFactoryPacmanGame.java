/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;

import java.util.List;

import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.dpprocessor.designpatterns.abstractfactory.AbstractFactoryDesignPattern;
import fr.univartois.dpprocessor.designpatterns.abstractfactory.AbstractFactoryParticipant;

/**
 * Le type IAbstractFactoryPacmanGame
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
@AbstractFactoryDesignPattern(factory = IAbstractFactoryPacmanGame.class, participant = AbstractFactoryParticipant.INTERFACE)
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
     * @param cellColumn la colonne de la cellule
     * @param cellRow la ligne de la cellule
     * @return Une instance de MegaGum
     */
    IAnimated createGum(PacmanGame game, int cellColumn, int cellRow);
    
    /**
     * @param width la taille en largeur de la map
     * @param height la taill en longueur de la map
     * @return Une d'instance de GameMap
     */
    GameMap createMap(int width, int height);
}

