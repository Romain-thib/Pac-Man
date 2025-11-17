/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 simon.cohet
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map;

import java.util.Iterator;

import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.decorator.DecoratorDesignPattern;

/**
 * Le type CardGeneratorDecorated
 *
 * @author simon.cohet
 *
 * @version 0.1.0
 */
@DecoratorDesignPattern(ICardGenerator.class)
public class CardGeneratorDecorated implements ICardGenerator {
    /**
     * Le generateur de carte à décorer
     */
    private ICardGenerator generator;
    /**
     * L'attribut spriteStore permet de recuprer les sprites
     */
    private SpriteStore spriteStore = SpriteStore.getInstance();
    
    /**
     * Crée une nouvelle instance de CardGeneratorDecorated.
     * @param generator le générateur à décorer
     */
    public CardGeneratorDecorated(ICardGenerator generator) {
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
        int height = map.getHeight()-2;
        int width = (map.getWidth()-2)/2;
        
       for (int i = 0; i < height; i++) {
           for (int j = 0; j < width; j++) {
               if (i%3 != 0 && j%6 != 0) {
                   map.setAt(i+1, j+1, createWallCell());
                   map.setAt(i+1, width+(width-j), createWallCell());
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

    /**
     * Crée une nouvelle cellule contenant un mur.
     *
     * @return Une cellule mur.
     */
    private Cell createPathCell() {
        return new Cell(spriteStore.getSprite("path"));
    }
}

