/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated.pacmanstate;

import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.state.StateDesignPattern;
import fr.univartois.dpprocessor.designpatterns.state.StateParticipant;

/**
 * La classe PacmanInvulnerable représente l'état invulnérable de Pacman.
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
@StateDesignPattern(state = PacmanInvulnerable.class, participant = StateParticipant.IMPLEMENTATION)
public class PacmanInvulnerable implements IStatePacman{
    
    /**
     * L'attribut compteur permet de compter le temps d'invulnérabilité.
     */
    private long compteur = 0;
    
    /**
     * L'atribut duree indique la durée 
     */
   private long duree = 3000;
    
    /**
     * L'attribut sprite qui gère l'apparence de pacman.
     */
    private Sprite sprite = null;

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public IStatePacman onCollisionWithGhost(PacMan pacman) {
        return this;        
    }
    
    /**
     * Modifie l'attribut duree de cette instance de PacmanInvulnerable.
     *
     * @param duree La nouvelle valeur de l'attribut duree pour cette instance de PacmanInvulnerable.
     */
    public void setDuree(long duree) {
        this.duree = duree;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#changeStatePacman(long)
     */
    @Override
    public IStatePacman changeStatePacman(long time) {
       compteur += time;
       if(compteur < duree) { // 3 secondes d'invulnérabilité
           return this;
       }
        return new PacmanVulnerable();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#getSprite()
     */
    @Override
    public Sprite getSprite(SpriteStore spriteStore) {
        if (sprite == null) {
            sprite = spriteStore.getSprite("pacman/closed-invulnerable", "pacman/half-open-invulnerable", "pacman/open-invulnerable", "pacman/half-open-invulnerable");
        }
        return sprite;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.IStatePacman#handleState()
     */
    @Override
    public void handleState(PacmanGame game) {
        game.getPlayer().setScoreMult(1);
        game.setSpeed(PacmanGame.DEFAULT_SPEED);
    }
}

