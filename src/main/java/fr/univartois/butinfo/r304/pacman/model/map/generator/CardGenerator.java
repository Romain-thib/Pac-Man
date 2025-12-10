/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map.generator;

import fr.univartois.butinfo.r304.pacman.model.map.Cell;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.model.map.ICardGenerator;
import fr.univartois.butinfo.r304.pacman.model.map.Wall;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.singleton.Instance;
import fr.univartois.dpprocessor.designpatterns.singleton.SingletonDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * La class CardGenerator permet de générer une carte de jeu simple.
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
@SingletonDesignPattern
@StrategyDesignPattern(strategy = ICardGenerator.class, participant = StrategyParticipant.IMPLEMENTATION)
public final class CardGenerator implements ICardGenerator {

    /**
     * L'attribut INSTANCE contient l'unique instance de cette classe.
     */
    @Instance
    private static final CardGenerator INSTANCE = new CardGenerator();
    
    /**
     * Crée une nouvelle instance.
     */
    private CardGenerator() {
        super();
    }
    
    /**
     * Donne l'attribut instance de cette instance.
     *
     * @return L'attribut instance de cette instance.
     */
    @Instance
    public static CardGenerator getInstance() {
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

        generateHorizontalWall(map);

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
     * Génère quelques murs au centre de la carte (exemple en forme de croix).
     *
     * @param map La carte sur laquelle placer les murs.
     */
    private void generateHorizontalWall(GameMap map) {
        int height = map.getHeight();
        int width = map.getWidth();

        int margin = 2;

        int centerCol = width / 2;
        int leftCenterGap = centerCol - margin / 2;
        int rightCenterGap = centerCol + margin / 2 - 1;

        for (int row = 2; row < height - 2; row += margin) {
            for (int col = margin + 1; col < width - margin - 1; col++) {
                if (col >= leftCenterGap && col <= rightCenterGap) {
                    continue;
                }

                map.setAt(row, col, createWallCell());
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
