/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 timothee.gros
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map;

import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.singleton.Instance;
import fr.univartois.dpprocessor.designpatterns.singleton.SingletonDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * La class CardGeneratorEmpty permet de générer une carte de jeu vide.
 *
 * @author timothee.gros
 *
 * @version 0.1.0
 */
@SingletonDesignPattern
@StrategyDesignPattern(strategy = ICardGenerator.class, participant = StrategyParticipant.IMPLEMENTATION)
public class CardGeneratorEmpty implements ICardGenerator {

    /**
     * L'attribut INSTANCE...
     */
    @Instance
    private static final CardGeneratorEmpty INSTANCE = new CardGeneratorEmpty();
    
    /**
     * Crée une nouvelle instance.
     */
    private CardGeneratorEmpty() {
        super();
    }
    
    /**
     * Donne l'attribut instance de cette instance.
     *
     * @return L'attribut instance de cette instance.
     */
    @Instance
    public static CardGeneratorEmpty getInstance() {
        return INSTANCE;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.map.ICardGenerator#generate(int, int)
     */
    @Override
    public GameMap generate(int height, int width) {
        GameMap map = new GameMap(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map.setAt(i, j, createPathCell());
            }
        }
        generateBorderWalls(map);
        return map;
    }

    /**
     * Génère les murs qui forment la bordure de la carte.
     *
     * @param map La carte sur laquelle placer les murs.
     */
    private void generateBorderWalls(GameMap map) {
        int height = map.getHeight();
        int width = map.getWidth();
        boolean isBorder;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                isBorder = (row == 0) || (row == height - 1) || (col == 0) || (col == width - 1);
                if (isBorder) {
                    map.setAt(row, col, createWallCell());
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
        Wall wall = new Wall(SpriteStore.getInstance().getSprite("wall"));
        return new Cell(wall);
    }

    /**
     * Crée une nouvelle cellule contenant un mur.
     *
     * @return Une cellule mur.
     */
    private Cell createPathCell() {
        return new Cell(SpriteStore.getInstance().getSprite("path"));
    }

}
