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
 * @author romain.thibaut
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
     * L'attribut megaGumProbability La probabilité d'apparition des méga-gommes
     */
    private final int megaGumProbability;
    
    /**
     * L'attribut bonusProbability La probabilité d'apparition des bonus
     */
    private final int bonusProbability;
    
    /**
     * Crée une nouvelle instance de Level.
     * @param levelNumber le numéro du niveau
     * @param map La map du niveau
     * @param megaGumProbability la probabilité d'apparition des méga-gommes
     * @param bonusProbability la probabilité d'apparition des bonus
     */
    public Level(int levelNumber, GameMap map, int megaGumProbability, int bonusProbability) {
        this.levelNumber = levelNumber;
        this.map = map;
        this.megaGumProbability = megaGumProbability;
        this.bonusProbability = bonusProbability;
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
    
    /**
     * @return La probabilité d'apparition des méga-gommes
     */
    public int getMegaGumProbability() {
        return megaGumProbability;
    }

    /**
     * @return La probabilité d'apparition des bonus
     */
    public int getBonusProbability() {
        return bonusProbability;
    }
}

