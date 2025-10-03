/**
 * Ce fichier fait partie du projet projet-2025-2026.
 * 
 * (c) 2025 shun.lembrez
 * 
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model.animated;

import java.util.Random;

import fr.univartois.butinfo.r304.pacman.model.IAnimated;
import fr.univartois.butinfo.r304.pacman.model.PacmanGame;
import fr.univartois.butinfo.r304.pacman.view.Sprite;


/**
 * Le type Ghost
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
public class Ghost extends AbstractAnimated{
    
    /**
     * L'attribut color pour les fantômes
     */
    private GhostColor color;
    
    /**
     * L'attribut temps pour la gestion du déplacement des fantômes
     */
    private long temps = 2000;
    
    
    /**
     * L'attribut SPEED pour gerer la vitesse des famtôme quand il change de direction
     */
    private static final double SPEED = 0.8;

    
    /**
     * Crée une nouvelle instance de Ghost.
     * @param game de type {@link PacmanGame}
     * @param xPosition de type {@link Double}
     * @param yPosition de type {@link Double}
     * @param sprite de type {@link Sprite}
     */
    public Ghost(PacmanGame game, double xPosition, double yPosition, Sprite sprites) {
        super(game, xPosition, yPosition, sprites);
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
        other.onCollisionWith(this);
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
        // Le fantôme change de direction toutes les 2 secondes 
        if (temps <= 0) {
            changeDirection(delta);
            temps = 2000;
        } else {
            temps -= delta;
        }
        return super.onStep(delta);
    }


    /**
     * @param delta le temps écoulé depuis la dernière mise à jour en millisecondes
     */
    private void changeDirection(long delta) {
        Random r = new Random();
        int random = r.nextInt(4);
        switch (random) {
            case 0:
                setHorizontalSpeed(-SPEED);
                setVerticalSpeed(0);
                break;
            case 1:
                setHorizontalSpeed(SPEED);
                setVerticalSpeed(0);
                break;
            case 2:
                setVerticalSpeed(-SPEED);
                setHorizontalSpeed(0);
                break;
            case 3:
                setVerticalSpeed(SPEED);
                setHorizontalSpeed(0);
                break;
            default:
                break;
        }
        
    }

}

