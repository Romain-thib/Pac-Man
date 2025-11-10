/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 shun.lembrez
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * Le type IStateGhost
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
     * @param delta la valeur selon le quel on actualise le deplacement
     * @param speedOfGhostState la vitesse du fantôme selon son état
     * @param game instance correpondant a la game
     */
    void moveState(Ghost ghost, long delta, double speedOfGhostState, PacmanGame game);
    
    /**
     *  La methode pour gerer les collisions entre pacman et les fantômes
     * @param ghost le fantôme 
     * @param game instance correpondant a la game qui permet d'accéder a pacman
     */
    void handleCollisionWithPacman(Ghost ghost,PacmanGame game);
        
    
    /**
     * La méthode qui permet d'obtenir le sprite du fantôme selon son état
     * @param ghost Le fantôme auquel appliquer le sprite
     */
    void getSpriteGhost(Ghost ghost);
    
    /**
     * @return l'état suivant
     */
    IStateGhost nextState();

}

