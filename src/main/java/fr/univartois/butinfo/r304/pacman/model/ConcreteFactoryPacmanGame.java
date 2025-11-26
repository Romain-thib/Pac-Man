/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;

import java.util.List;

import fr.univartois.butinfo.r304.pacman.model.animated.MegaGum;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;

/**
 * Le type ConcreteFactoryPacmanGame
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */
public class ConcreteFactoryPacmanGame implements IAbstractFactoryPacmanGame {
     
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAbstractFactoryPacmanGame#createPacman(fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public PacMan createPacman(PacmanGame game) {
        return new PacMan(game, 0, 0, SpriteStore.getInstance().getSprite("pacman/closed", "pacman/half-open",
                "pacman/open", "pacman/half-open"));
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAbstractFactoryPacmanGame#createGhost(fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public List createGhost(PacmanGame game) {
        // TODO Auto-generated method stub.
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAbstractFactoryPacmanGame#createMegaGum(fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    public MegaGum createMegaGum(PacmanGame game) {
        // TODO Auto-generated method stub.
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAbstractFactoryPacmanGame#createGum(fr.univartois.butinfo.r304.pacman.model.PacmanGame)
     */
    @Override
    public IAnimated createGum(PacmanGame game) {
        // TODO Auto-generated method stub.
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAbstractFactoryPacmanGame#createMap()
     */
    @Override
    public GameMap createMap() {
        // TODO Auto-generated method stub.
        return null;
    }

}

