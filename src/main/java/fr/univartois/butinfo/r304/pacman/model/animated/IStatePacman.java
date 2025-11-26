/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * Le type IStatePacman
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = IStatePacman.class, participant = StateParticipant.INTERFACE)
public interface IStatePacman {
   
    /**
     * @param pacman Savoir si pacman est en collision avec un fantome
     * @return IStatePacman pour gérer sa vulnerabilité
     */
    public IStatePacman onCollisionWithGhost(PacMan pacman);
    
    /**
     * @param time Le temps que pacman ne peut pas rentrer en collision avec des fantômes 
     * @return IStatePacman l'état actuelle de Pacman
     */
    public IStatePacman changeStatePacman(long time);
    
    /**
     * @param spriteStore Les sprites de pacman
     * @return Sprite l'apparence de pacman selon son état
     */
    public Sprite getSprite(SpriteStore spriteStore);
    
    /**
     * Gêre les modifications de l'états de pac-man
     * @param game : la partie à laquelle appliquer l'effet
     */
    public void handleState(PacmanGame game);
}

