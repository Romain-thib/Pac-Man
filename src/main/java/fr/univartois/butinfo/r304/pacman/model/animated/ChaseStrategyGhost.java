/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Le type ChaseStrategy
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StrategyDesignPattern(strategy = IStrategyGhost.class, participant = StrategyParticipant.IMPLEMENTATION)
public class ChaseStrategyGhost implements IStrategyGhost{
    /**
     * L'attribut SPEED pour gerer la vitesse des famtômes quand il change de direction
     */
    private double speed;

    /**
     * Crée une nouvelle instance de ChaseStrategyGhost. 
     * @param speed : La vitesse de déplacement, peut être négative pour fuir
     */
    public ChaseStrategyGhost(double speed) {
        this.speed = speed;
    }

    /**
     * 
     */
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStrategyGhost#moveStrategy(fr.univartois.butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public void moveStrategy(Ghost ghost,long delta, PacmanGame game) {
     // Le fantôme change de direction toutes le temps delta pas utile ici 
        changeDirection(ghost,game);  
    }

    /**
     * méthode qui permet de changer de direction le famtôme de façon a le pourchasser
     * 
     * @param ghost le famtôme a déplacée
     * @param game l'instance de la game actuel
     */
    private void changeDirection(Ghost ghost, PacmanGame game) {
        
        PacMan pacman = game.getPlayer();
        
        if(pacman.getX() > ghost.getX() && game.getCellAt(ghost.getX()+1, ghost.getY()).isEmpty()) {
            ghost.setVerticalSpeed(0);
            ghost.setHorizontalSpeed(speed);
        }
        else if (pacman.getX() < ghost.getX() && game.getCellAt(ghost.getX()-1, ghost.getY()).isEmpty()){
            ghost.setVerticalSpeed(0);
            ghost.setHorizontalSpeed(-speed);
        }
        else {
            if(pacman.getY() > ghost.getY() && game.getCellAt(ghost.getX(), ghost.getY()+1).isEmpty()) {
                ghost.setVerticalSpeed(speed);
                ghost.setHorizontalSpeed(0);
            }
            else if (pacman.getY() < ghost.getY() && game.getCellAt(ghost.getX(), ghost.getY()-1).isEmpty()){
                ghost.setVerticalSpeed(-speed);
                ghost.setHorizontalSpeed(0);
            }
        }

    }    

}