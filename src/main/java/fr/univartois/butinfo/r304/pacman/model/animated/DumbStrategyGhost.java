/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import java.util.Random;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Le type DumbStrategy
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StrategyDesignPattern(strategy = IStrategyGhost.class, participant = StrategyParticipant.IMPLEMENTATION)
public class DumbStrategyGhost implements IStrategyGhost {

    /**
     * L'attribut SPEED pour gerer la vitesse des famtôme quand il change de direction
     */
    private static final double SPEED = 75;

    /**
     * L'attribut temps pour actualisée le déplacement
     */
    private double temps = 2000;

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.pacman.model.animated.IStrategyGhost#moveStrategy(fr.
     * univartois.butinfo.r304.pacman.model.animated.Ghost, long,
     * fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public void moveStrategy(Ghost ghost, long delta, PacmanGame game) {
        // le fantôme change de direction tout les 2 secondes
        if (temps <= 0) {
            changeDirection(ghost);
            temps = 2000;
        } else {
            temps -= delta;
        }
    }

    /**
     * méthode qui permet de changer de direction le fantôme de facon aléatoire
     * 
     * @param ghost le famtome en question 
     */
    private void changeDirection(Ghost ghost) {
        Random r = new Random();
        int random = r.nextInt(4);
        switch (random) {
            case 0:
                ghost.setHorizontalSpeed(-SPEED);
                ghost.setVerticalSpeed(0);
                break;
            case 1:
                ghost.setHorizontalSpeed(SPEED);
                ghost.setVerticalSpeed(0);
                break;
            case 2:
                ghost.setVerticalSpeed(-SPEED);
                ghost.setHorizontalSpeed(0);
                break;
            case 3:
                ghost.setVerticalSpeed(SPEED);
                ghost.setHorizontalSpeed(0);
                break;
            default:
                break;
        }
    }

}
