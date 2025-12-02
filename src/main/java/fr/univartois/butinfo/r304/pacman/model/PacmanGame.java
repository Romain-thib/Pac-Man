/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d'aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d'adéquation
 * à un usage particulier et d'absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d'auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d'un contrat, d'un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d'autres éléments du logiciel.
 *
 * (c) 2022-2025 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.pacman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.r304.pacman.model.animated.Ghost;
import fr.univartois.butinfo.r304.pacman.model.animated.PacMan;
import fr.univartois.butinfo.r304.pacman.model.map.Cell;
import fr.univartois.butinfo.r304.pacman.model.map.GameMap;
import fr.univartois.butinfo.r304.pacman.view.ISpriteStore;
import fr.univartois.butinfo.r304.pacman.view.Sprite;
import fr.univartois.dpprocessor.designpatterns.abstractfactory.AbstractFactoryDesignPattern;
import fr.univartois.dpprocessor.designpatterns.abstractfactory.AbstractFactoryParticipant;
import javafx.animation.AnimationTimer;

/**
 * La classe {@link PacmanGame} gère une partie du jeu Pac-Man.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */

@AbstractFactoryDesignPattern(factory = IAbstractFactoryPacmanGame.class, participant = AbstractFactoryParticipant.CONTEXT)
public final class PacmanGame {

    /**
     * Le génarateur de nombres aléatoires utilisé dans le jeu.
     */
    public static final Random RANDOM = new Random();

    /**
     * La vitesse de déplacement du joueur (en pixels/s). par défaut
     */
    public static final int DEFAULT_SPEED = 150;

    /**
     * La vitesse de déplacement du joueur (en pixels/s).
     */
    private int speed = DEFAULT_SPEED;
    
    /**
     * La largeur de la carte du jeu (en pixels).
     */
    private final int width;

    /**
     * La hauteur de la carte du jeu (en pixels).
     */
    private final int height;

    /**
     * L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu.
     */
    private final ISpriteStore spriteStore;

    /**
     * La carte du jeu.
     */
    private GameMap gameMap;

    /**
     * Le personnage du joueur.
     */
    private PacMan player;
    
    /**
     * L'attribut factory qui créé une instance de ConcreteFactoryPacmanGame
     */
    private IAbstractFactoryPacmanGame factory = new ConcreteFactoryPacmanGame();

    /**
     * Le nombre de fantômes initialement dans le jeu.
     */
    private int nbGhosts;

    /**
     * Le nombre de pac-gommes initialement dans le jeu.
     */
    private int nbGums;
    
    /**
     * L'attribut ghostList qui contient la liste des fantômes du jeu.
     */
    private List<Ghost> ghostList = new ArrayList<>();

    /**
     * La liste des objets mobiles du jeu.
     */
    private final List<IAnimated> movingObjects = new CopyOnWriteArrayList<>();

    /**
     * La liste des objets animés du jeu.
     */
    private final List<IAnimated> animatedObjects = new CopyOnWriteArrayList<>();

    /**
     * L'animation du jeu, qui s'assure que les différents objets évoluent.
     */
    private final AnimationTimer animation = new GameAnimation(movingObjects, animatedObjects);

    /**
     * Le contrôleur du jeu.
     */
    private IPacmanController controller;
    
    /**
     * L'attribut currentLevel qui contient le niveau actuel du jeu.
     */
    private Level currentLevel;

    /**
     * Crée une nouvelle instance de PacmanGame.
     *
     * @param gameWidth La largeur de la carte du jeu.
     * @param gameHeight La hauteur de la carte du jeu.
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param nbGhosts Le nombre de fantômes dans le jeu.
     */
    public PacmanGame(int gameWidth, int gameHeight, ISpriteStore spriteStore, int nbGhosts) {
        this.width = gameWidth;
        this.height = gameHeight;
        this.spriteStore = spriteStore;
        this.nbGhosts = nbGhosts;
    }

    /**
     * Donne l'attribut ghostList de cette instance de PacmanGame.
     *
     * @return L'attribut ghostList de cette instance de PacmanGame.
     */
    public List<Ghost> getGhostList() {
        return ghostList;
    }

    /**
     * Modifie le contrôleur avec lequel interagir pour mettre à jour l'affichage.
     *
     * @param controller Le contrôleur avec lequel interagir.
     */
    public void setController(IPacmanController controller) {
        this.controller = controller;
    }

    /**
     * Donne l'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du
     * jeu.
     *
     * @return L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite}
     *         du jeu.
     */
    public ISpriteStore getSpriteStore() {
        return spriteStore;
    }

    /**
     * Donne la largeur de la carte du jeu (en pixels).
     *
     * @return La largeur de la carte du jeu.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la hauteur de la carte du jeu (en pixels).
     *
     * @return La hauteur de la carte du jeu.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Donne le personnage du joueur.
     *
     * @return Le personnage du joueur.
     */
    public PacMan getPlayer() {
        return player;
    }
    
    /**
     * Donne le niveau actuel du jeu.
     *
     * @return Le niveau actuel.
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Modifie l'attribut speed de cette instance de PacmanGame.
     *
     * @param speed La nouvelle valeur de l'attribut speed pour cette instance de PacmanGame.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Prépare une partie de Pac-Man avant qu'elle ne démarre.
     */
    public void prepare() {
        gameMap = factory.createMap(width, height); // Changer donner la taille de la map height width
        controller.prepare(gameMap);
    }
    
    /**
     * Donne l'attribut nbGums de cette instance de PacmanGame.
     *
     * @return L'attribut nbGums de cette instance de PacmanGame.
     */
    public int getNbGums() {
        return nbGums;
    }

    /**
     * Modifie l'attribut nbGums de cette instance de PacmanGame.
     *
     * @param nbGums La nouvelle valeur de l'attribut nbGums pour cette instance de PacmanGame.
     */
    public void setNbGums(int nbGums) {
        this.nbGums = nbGums;
    }

    /**
     * Démarre la partie de Pac-Man.
     */
    public void start() {
        prepareLevel(1);
        createAnimated();
        initStatistics();
        animation.start();
    }
    
    /**
     * @param levelNumber le numéro du niveau
     */
    public void prepareLevel(int levelNumber) {
        currentLevel = LevelFactory.createLevel(levelNumber, width, height);
        gameMap = currentLevel.getMap();
        controller.prepare(gameMap);
    }
    
    /**
     * Indique que le joueur a terminé un niveau avec succès.
     *
     * @param message Le message indiquant la victoire.
     */
    private void levelCleared(String message) {
        animation.stop();
        int nextLevelNumber = currentLevel.getLevelNumber() + 1;
        prepareLevel(nextLevelNumber);
        createAnimated();
        initStatistics();
        controller.gameOver(message);
    }
    
    /**
     * Redémarre le niveau actuel après une défaite.
     */
    public void restartCurrentLevel() {
        animation.stop();
        int currentLevelNumber;
        if (currentLevel != null) {
            currentLevelNumber = currentLevel.getLevelNumber();
        } else {
            currentLevelNumber = 1;
        }
        prepareLevel(currentLevelNumber);
        createAnimated();
        initStatistics();
        animation.start();
    }
    
    /**
     * Passe au niveau suivant.
     */
    public void nextLevel() {
        animation.start();
    }

    
    /**
     * Crée les différents objets animés présents au début de la partie.
     */
    private void createAnimated() {
        // On commence par enlever tous les éléments mobiles encore présents.
        clearAnimated();

        // On crée le joueur sur la carte.
        
        player = factory.createPacman(this);
        
        animatedObjects.add(player);
        spawnAnimated(player);
        
        ghostList = factory.createGhost(this);
        for (int i = 0; i < nbGhosts; i++) {
            Ghost ghost = ghostList.get(i);
            ghost.setHorizontalSpeed(DEFAULT_SPEED * 0.8);
            animatedObjects.add(ghost);
            spawnAnimated(ghost);
        }
        

        List<Cell> emptyCells = currentLevel.getMap().getEmptyCells();
        nbGums = emptyCells.size(); // mettre à jour le nombre de pac-gommes
        for (int i = 0; i < emptyCells.size(); i++) {
            Cell cell = emptyCells.get(i);

            int cellColumn = cell.getColumn();
            int cellRow = cell.getRow();
            
            addAnimated(factory.createGum(this, cellColumn, cellRow));

        }
    }

    /**
     * Initialise les statistiques de cette partie.
     */
    private void initStatistics() {
        controller.bindLife(player.getHpProperty());
        controller.bindScore(player.getScoreProperty());
    }

    /**
     * Fait apparaître un objet animé sur la carte du jeu.
     *
     * @param animated L'objet à faire apparaître.
     */
    private void spawnAnimated(IAnimated animated) {
        List<Cell> spawnableCells = gameMap.getEmptyCells();
        if (!spawnableCells.isEmpty()) {
            Cell cell = spawnableCells.get(RANDOM.nextInt(spawnableCells.size()));
            animated.setX(cell.getColumn() * (double)spriteStore.getSpriteSize());
            animated.setY(cell.getRow() * (double)spriteStore.getSpriteSize());
            addMoving(animated);
        }
    }

    /**
     * Déplace le personnage du joueur vers le haut.
     */
    public void moveUp() {
        stopMoving();
        player.setVerticalSpeed(-speed);
        player.setRotate(270);
    }

    /**
     * Déplace le personnage du joueur vers la droite.
     */
    public void moveRight() {
        stopMoving();
        player.setHorizontalSpeed(speed);
        player.setRotate(0);
    }

    /**
     * Déplace le personnage du joueur vers le bas.
     */
    public void moveDown() {
        stopMoving();
        player.setVerticalSpeed(speed);
        player.setRotate(90);
    }

    /**
     * Déplace le personnage du joueur vers la gauche.
     */
    public void moveLeft() {
        stopMoving();
        player.setHorizontalSpeed(-speed);
        player.setRotate(180);
    }

    /**
     * Arrête le déplacement du joueur.
     */
    public void stopMoving() {
        player.setVerticalSpeed(0);
        player.setHorizontalSpeed(0);
    }

    /**
     * Donne la cellule à la position donnée sur la carte.
     *
     * @param x La position en x de la cellule.
     * @param y La position en y de la cellule.
     *
     * @return La cellule à la position donnée.
     */
    public Cell getCellAt(int x, int y) {
        // On traduit cette position en position dans la carte.
        int row = y / spriteStore.getSpriteSize();
        int column = x / spriteStore.getSpriteSize();

        // On récupère enfin la cellule à cette position dans la carte.
        return gameMap.getAt(row, column);
    }

    /**
     * Ajoute un objet mobile dans le jeu.
     *
     * @param object L'objet à ajouter.
     */
    public void addMoving(IAnimated object) {
        movingObjects.add(object);
        addAnimated(object);
    }

    /**
     * Ajoute un objet animé dans le jeu.
     *
     * @param object L'objet à ajouter.
     */
    public void addAnimated(IAnimated object) {
        animatedObjects.add(object);
        controller.addAnimated(object);
        object.onSpawn();
    }

    /**
     * Supprime un objet animé dans le jeu.
     *
     * @param object L'objet à supprimer.
     */
    public void removeAnimated(IAnimated object) {
        animatedObjects.remove(object);
        movingObjects.remove(object);
        object.onDespawn();
        object.onDestruction();
    }

    /**
     * Supprime tous les objets animé dans le jeu.
     */
    private void clearAnimated() {
        for (IAnimated animated : animatedObjects) {
            animated.onDespawn();
            animated.onDestruction();
        }
        animatedObjects.clear();
        movingObjects.clear();
    }
    
    /**
   * Indique que le joueur a mangé une pac-gomme.
   *
   * @param gum La pac-gomme qui a été mangée.
   */
    public void pacGumEaten(IAnimated gum) {
        nbGums--;
        removeAnimated(gum);

        if (nbGums <= 0) {
            levelCleared("YOU WIN!");
        }
    }
    
  /**
  * Indique que le joueur a mangé une mega-gomme.
  *
  * @param megagum La mega-gomme qui a été mangée.
  */
    public void megaGumEaten(IAnimated megagum) {
        nbGums--;
        removeAnimated(megagum);

        if (nbGums <= 0) {
            levelCleared("YOU WIN!");
        }
    }

    /**
     * Termine la partie lorsque le joueur est tué.
     */
    public void playerIsDead() {
        gameOver("YOU HAVE BEEN KILLED!");
    }

    /**
     * Termine la partie en cours.
     *
     * @param message Le message indiquant le résultat de la partie.
     */
    private void gameOver(String message) {
        animation.stop();
        controller.gameOver(message);
    }
}
