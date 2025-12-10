/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map.generator;

import fr.univartois.butinfo.r304.pacman.model.map.Cell;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.model.map.ICardGenerator;
import fr.univartois.butinfo.r304.pacman.model.map.Wall;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.decorator.DecoratorDesignPattern;

/**
 * La class CardGeneratorDecorated décore un générateur de carte en ajoutant des murs pour une map fixe que on a créer
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
@DecoratorDesignPattern(ICardGenerator.class)
public class CardGeneratorFixed implements ICardGenerator {
    /**
     * Le generateur de carte à décorer
     */
    private ICardGenerator generator;
    /**
     * L'attribut spriteStore permet de recuprer les sprites
     */
    private SpriteStore spriteStore = SpriteStore.getInstance();
    
    /**
     * L'attribut walls correspond aux murs qui seront placé lors
     * de la génération de la map
     */
    private int[][] walls = {
            {2, 2, 4, 3},
            {6, 2, 9, 3},
            {11, 2, 15, 3},
            {17, 1, 17, 3},
            {2, 5, 4, 5},
            {1, 7, 4, 8},
            {2, 11, 2, 11},
            {4, 10, 5, 11},
            {7, 10, 8, 11},
            {10, 11, 12, 11},
            {14, 11, 17, 11},
            {16, 10, 17, 10},
            {6, 5, 7, 6},
            {9, 5, 10, 6},
            {6, 8, 10, 8},
            {12, 5, 15, 6},
            {12, 8, 14, 9},
            {16, 8, 17, 8},
            {17, 5, 17, 7},
            {10, 9, 10, 9},
            {1, 9, 2, 9},
    };
    
    /**
     * Crée une nouvelle instance de CardGeneratorDecorated.
     * @param generator le générateur à décorer
     */
    public CardGeneratorFixed(ICardGenerator generator) {
        this.generator = generator;
    }
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.map.ICardGenerator#generate(int, int)
     */
    @Override
    public GameMap generate(int height, int width) {
        GameMap map = generator.generate(height, width);
        generateInsideWalls(map);
        return map;
    }

    /**
     * Génère quelques murs au centre de la carte (exemple en forme de croix).
     *
     * @param map La carte sur laquelle placer les murs.
     */
    private void generateInsideWalls(GameMap map) {
       for (int[] wall : walls) {
        for (int i = wall[0]; i <= wall[2]; i++) {
            for (int j = wall[1]; j <= wall[3]; j++) {
                map.setAt(j, i, createWallCell());
                map.setAt(j, 35-i, createWallCell());
                map.setAt(23-j, i, createWallCell());
                map.setAt(23-j, 35-i, createWallCell());
            }
        }
    }
    }

    /**
     * Crée une nouvelle cellule contenant un mur.
     *
     * @return Une cellule mur.
     */
    private Cell createWallCell() {
        Wall wall = new Wall(spriteStore.getSprite("wall"));
        return new Cell(wall);
    }
}

