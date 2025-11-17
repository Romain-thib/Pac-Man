/**
 * Ce fichier fait partie du projet projet-2025-2026.
 * 
 * (c) 2025 shun.lembrez
 * 
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;


/**
 * Le type Ghost
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
@StrategyDesignPattern(strategy = IStrategyGhost.class, participant = StrategyParticipant.CONTEXT)
public class Ghost extends AbstractAnimated{
    
    /**
     * L'attribut strategyGhost pour la gestion des stratégies des fantômes
     */
    private IStrategyGhost strategyGhost;
    
    
    /**
     * L'attribut stateGhost pour gerer les états des fantômes
     */
    private IStateGhost stateGhost;
    
    /**
     * L'attribut color pour les fantômes
     */
    private GhostColor color;
      

    
    /**
     * Crée une nouvelle instance de Ghost.
     * 
     * @param game l'instance du jeu
     * @param xPosition la position sur l'axe horizontal du fantôme
     * @param yPosition la position sur l'axe vertical du famtôme
     * @param sprites les sprites du fantôme
     * @param color couleur du famtôme (RED, PINK, BLUE, ORANGE)
     * @param stateGhost état du famtôme en question
     */
    public Ghost(PacmanGame game, double xPosition, double yPosition, Sprite sprites, GhostColor color) {
        super(game, xPosition, yPosition, sprites);
        this.color = color;
        this.strategyGhost = color.getMoveStrategy();
        this.stateGhost = new InvulnerableStateGhost();
 
    }


    /**
     * Donne l'attribut color de cette instance de Ghost.
     *
     * @return L'attribut color de cette instance de Ghost.
     */
    public GhostColor getColor() {
        return color;
    }
    
    
    /**
     * Modifie l'attribut color de cette instance de Ghost.
     *
     * @param color La nouvelle valeur de l'attribut color pour cette instance de Ghost.
     */
    public void setColor(GhostColor color) {
        this.color = color;
    }
    
    /**
     * Modifie l'attribut strategyGhost de cette instance de Ghost.
     * 
     * @param strategy permet de set la strategy utilise
     */
    public void setStrategyGhost(IStrategyGhost strategy) {
        this.strategyGhost = strategy;
    }
    
    /**
     * Modifie l'attribut color de cette instance de Ghost.
     * 
     * @param stateGhost attribut correspondant a l'état en cours
     *
     */
    public void setState(IStateGhost stateGhost) {
        this.stateGhost = stateGhost;
        stateGhost.getSpriteGhost(this);
        stateGhost.moveState(this, game);
    }
    

    /*
     * Gestion des collisions avec les autres objets animés.
     * 
     * @param other de type {@link IAnimated}
     *
     * @see
     * fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.IAnimated)
     */
    @Override
    public void onCollisionWith(IAnimated other) {
        stateGhost.handleCollisionWithAnimated(this, other);
    }


    /*
     * Gestion des collisions avec les autres objets animés.
     * 
     * @param other de type {@link PacMan}
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacMan)
     */
    @Override
    public void onCollisionWith(PacMan other) {
        // Volontairement vide pour l'instant car on a pas encore l'état de pac-man 
        // si il a mange mega gum
        // a faire plus tard
        setState(stateGhost.handleCollisionWithPacman(this, this.game));
        
    }


    /*
     * Gestion des collisions avec les autres objets animés.
     * 
     * @param other de type {@link Ghost}
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Ghost)
     */
    @Override
    public void onCollisionWith(Ghost other) {
        // Volontairement vide car les fantômes ne réagissent pas entre eux
    }


    /*
     * Gestion des collisions avec les autres objets animés.
     * 
     * @param other de type {@link PacGum}
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.PacGum)
     */
    @Override
    public void onCollisionWith(PacGum other) {
        // Volontairement vide car les fantômes ne réagissent pas aux pac-gums
    }
    
    
    /*
     * @param delta le temps écoulé depuis la dernière mise à jour en millisecondes
     *
     * @see fr.univartois.butinfo.r304.pacman.model.animated.AbstractAnimated#onStep(long)
     */
    @Override
    public boolean onStep(long delta) {
        strategyGhost.moveStrategy(this,delta,game);
        setState(stateGhost.nextState());
        return super.onStep(delta);
    }


    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.MegaGum)
     */
    @Override
    public void onCollisionWith(MegaGum other) {
        //
    }


    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAnimated#onCollisionWith(fr.univartois.butinfo.r304.pacman.model.animated.Bonus)
     */
    @Override
    public void onCollisionWith(Bonus other) {
        // les fantomes n'utilisent pas de bonus
    }

}

