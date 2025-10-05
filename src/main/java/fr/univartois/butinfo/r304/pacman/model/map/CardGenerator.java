/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.map;

import fr.univartois.butinfo.r304.pacman.view.SpriteStore;

/**
 * Le type CardGenerator
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
public class CardGenerator {
    
    
    private final SpriteStore spriteStore = new SpriteStore();

    /**
     * Génère une nouvelle carte de jeu de dimensions données.
     *
     * @param height Le nombre de lignes de cellules.
     * @param width  Le nombre de colonnes de cellules.
     *
     * @return La carte générée.
     */
    public GameMap generate(int height, int width) {
        GameMap map = new GameMap(height, width);

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
                isBorder = (row == 0) || (row == height - 1) || (col == 0) || (col == width - 1); // haut ou bas ou gauche ou droite

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

        int margin = 1;

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
        Wall wall = new Wall(spriteStore.getSprite("wall"));
        return new Cell(wall);
    }
}

