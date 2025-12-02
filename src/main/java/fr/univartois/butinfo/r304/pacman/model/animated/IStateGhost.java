/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * La classe IStateGhost interface pour les états des fantômes
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStateGhost.class, participant = StateParticipant.INTERFACE)
public interface IStateGhost {

    /**
     * La méthode moveState pour le déplacement des fantômes
     * 
     * @param ghost le famtôme a déplacée
     * @param game instance correpondant a la game
     */
    void moveState(Ghost ghost, PacmanGame game);
    
    /**
     *  La methode pour gerer les collisions entre pacman et les fantômes
     * @param ghost le fantôme 
     * @param game instance correpondant a la game qui permet d'accéder a pacman
     * @return retourne la l'état en fonction de l'état actuel en conséquence de la collision avec pacman
     */
    IStateGhost handleCollisionWithPacman(Ghost ghost,PacmanGame game);
        
    
    /**
     * La méthode qui permet d'obtenir le sprite du fantôme selon son état
     * @param ghost Le fantôme auquel appliquer le sprite
     */
    void getSpriteGhost(Ghost ghost);
    
    /**
     * @return l'état suivant
     */
    IStateGhost nextState();

    /**
     * @param ghost le fantôme
     * @param animated l'objet avec lequel il y a collision
     */
    void handleCollisionWithAnimated(Ghost ghost, IAnimated animated);
}

