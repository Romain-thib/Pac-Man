/**
 * Ce fichier fait partie du projet projet-2025-2026.
 *
 * (c) 2025 romain.thibaut
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.GhostColor;
import fr.univartois.butinfo.r304.pacman.model.animated.MegaGum;
import fr.univartois.butinfo.r304.pacman.model.animated.PacGum;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.map.CardGeneratorEmpty;
import fr.univartois.butinfo.r304.pacman.model.map.CardGeneratorFixed;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.model.map.ICardGenerator;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.butinfo.r304.pacman.view.SpriteStore;
import fr.univartois.dpprocessor.designpatterns.abstractfactory.AbstractFactoryDesignPattern;
import fr.univartois.dpprocessor.designpatterns.abstractfactory.AbstractFactoryParticipant;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Le type ConcreteFactoryPacmanGame
 *
 * @author romain.thibaut
 *
 * @version 0.1.0
 */

@StrategyDesignPattern(strategy = ICardGenerator.class, participant = StrategyParticipant.CONTEXT)
@AbstractFactoryDesignPattern(factory = ConcreteFactoryPacmanGame.class, participant = AbstractFactoryParticipant.IMPLEMENTATION)
public class ConcreteFactoryPacmanGame implements IAbstractFactoryPacmanGame {
    
    /**
     * Le générateur de cartes
     */
    private ICardGenerator generator = new CardGeneratorFixed(CardGeneratorEmpty.getInstance());
        
    /**
     * L'attribut NB_GHOSTS le nombre de fantômes dans le jeu
     */
    private static final int NB_GHOSTS = 4;
    
    /**
     * L'attribut spriteStore les sprites du jeu
     */
    private SpriteStore spriteStore = SpriteStore.getInstance();
    
    private Random RANDOM = new Random();
     
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
    public List<Ghost> createGhost(PacmanGame game) {
        List <Ghost> ghostList = new ArrayList<>();
        GhostColor[] colors = GhostColor.values();
        for (int i = 0; i < NB_GHOSTS; i++) {
            GhostColor color = colors[i % colors.length];

            Sprite ghostSprite = spriteStore.getSprite(
                    "ghosts/" + color.name().toLowerCase() + "/1",
                    "ghosts/" + color.name().toLowerCase() + "/2");
            Ghost ghost = new Ghost(game, 0, 0, ghostSprite, color);
            ghostList.add(ghost); 
        }
        return ghostList;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAbstractFactoryPacmanGame#createMap()
     */
    @Override
    public GameMap createMap(int width, int height) {
        int cellSize = SpriteStore.getInstance().getSpriteSize();

        // Convertir les dimensions de la carte en nombre de cellules
        int numRows = height / cellSize;
        int numCols = width / cellSize;

        return generator.generate(numRows, numCols);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.pacman.model.IAbstractFactoryPacmanGame#createGum(fr.univartois.butinfo.r304.pacman.model.PacmanGame, int, int)
     */
    @Override
    public IAnimated createGum(PacmanGame game, int cellColumn, int cellRow) {
        int r = RANDOM.nextInt(100);
        if (r <= 1) {
           MegaGum megagum = new MegaGum(
                   game, 
                   cellColumn * spriteStore.getSpriteSize(),
                   cellRow * spriteStore.getSpriteSize(),
                   spriteStore.getSprite("megagum")
           );
           return megagum;
        } else {
            PacGum gum = new PacGum(
                    game,
                    cellColumn * spriteStore.getSpriteSize(),
                    cellRow * spriteStore.getSpriteSize(),
                    spriteStore.getSprite("pacgum") // sprite de la pac-gomme
            );
            return gum;
        }
    }
}

