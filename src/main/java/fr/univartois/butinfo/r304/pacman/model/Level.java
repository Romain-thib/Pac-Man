/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 UTILISATEUR
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;


import fr.univartois.butinfo.r304.pacman.model.map.GameMap;

/**
 * Le type Level
 *
 * @author UTILISATEUR
 *
 * @version 0.1.0
 */
public class Level {
    /**
     * L'attribut levelNumber, le numéro d'un niveau
     */
    private final int levelNumber;

    /**
     * L'attribut map La map du niveau
     */
    private final GameMap map;
    
    /**
     * Crée une nouvelle instance de Level.
     * @param levelNumber le numéro du niveau
     * @param map La map du niveau
     */
    public Level (int levelNumber, GameMap map) {
        this.levelNumber = levelNumber;
        this.map = map;
    }
    
    
    /**
     * @return Le numéro du niveau
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * @return La map du niveau
     */
    public GameMap getMap() {
        return map;
    }
}

