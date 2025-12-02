/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 UTILISATEUR
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;

import fr.univartois.butinfo.r304.pacman.model.map.CardGeneratorDecorated;
import fr.univartois.butinfo.r304.pacman.model.map.CardGeneratorEmpty;
import fr.univartois.butinfo.r304.pacman.model.map.CardGeneratorFixed;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.model.map.ICardGenerator;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;

/**
 * Le type LevelFactory
 *
 * @author UTILISATEUR
 *
 * @version 0.1.0
 */
public class LevelFactory {
    /**
     * @param levelNumber le numéro du niveau
     * @param width la largeur
     * @param height la hauteur
     * @return Une instance de Level
     */
    public static Level createLevel(int levelNumber, int width, int height) {
        ICardGenerator generator;

        switch (levelNumber) {
            case 1:
                generator = new CardGeneratorDecorated(CardGeneratorEmpty.getInstance());
                break;
            case 2:
                generator = new CardGeneratorFixed(CardGeneratorEmpty.getInstance());
                break;
            default:
                generator = new CardGeneratorFixed(CardGeneratorEmpty.getInstance());
                break;
        }

        int cellSize = SpriteStore.getInstance().getSpriteSize();
        int numRows = height / cellSize;
        int numCols = width / cellSize;

        GameMap map = generator.generate(numRows, numCols);


        int megaGumProbability;
        int bonusProbability;

        switch (levelNumber) {
            case 1:
                megaGumProbability = 100;   // 10% chance
                bonusProbability = 100;     // 10% chance
                break;
            case 2:
                megaGumProbability = 50;   // 5% chance
                bonusProbability = 50;     // 5% chance
                break;
            case 3:
                megaGumProbability = 30;    // 3% chance
                bonusProbability = 20;      // 2% chance
                break;    
            default: // Niveau difficile → très peu de bonus
                megaGumProbability = 8;    // 0.8% chance
                bonusProbability = 5;      // 0.5% chance
                break;
        }

        return new Level(levelNumber, map, megaGumProbability, bonusProbability);
    }    
}

