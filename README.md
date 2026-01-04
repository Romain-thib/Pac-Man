# 🎮 Projet _Pac-Man en JavaFX

⚠️ **Ce dépôt est une copie du projet original (GitLab privé), rendue publique à des fins de présentation dans un portfolio.**

--- 
## Contexte du projet

Ce jeu a été développé dans le cadre d’un projet scolaire en groupe de 4 étudiants.
Nous avons été accompagnés pour structurer notre code, organiser les classes et utiliser des patrons de conception afin d’assurer la maintenabilité et l’évolutivité du projet.

Une version exécutable .jar peut être générée pour lancer le jeu.


## Objectif du jeu

Reproduire le gameplay classique de Pac‑Man :

- contrôler Pac‑Man dans un labyrinthe
- collecter toutes les pastilles
- éviter les fantômes
- gérer les niveaux et la difficulté croissante
- 
---

## Stack technique

- **Java**
- **JavaFX**
- Git (travail collaboratif)

---

## Travail réalisé

- Développement du **backend** du jeu
- Mise en place et utilisation de patrons de conception (ex. Strategy, Singleton…)
- Gestion de niveaux
- Travail en équipe sous forte de temps

## Diagramme de classes

classDiagram
    hide empty members


    %% ---------------------------------------
    %% Gestion des images du jeu (les sprites)
    %% ---------------------------------------


    abstract class Sprite {
        - imageProperty : ObjectBinding<Image>

        # Sprite(imageProperty : ObjectBinding<Image>)
        + getWidth() : int
        + getHeight() : int
        + imageProperty() : ObjectBinding<Image>
        + destroy() : void
    }

    class StaticSprite {
        - StaticSprite(imageProperty : ObjectBinding<Image>)
        + newInstance(image : Image) : Sprite
        + destroy() : void
    }
    StaticSprite --|> Sprite

    class AnimatedSprite {
        - timeline : Timeline
        - AnimatedSprite(imageProperty : ObjectBinding<Image>, timeline : Timeline)
        + newInstance(images : ObservableList<Image>, frameRate : int) : Sprite
        + destroy() : void
    }
    AnimatedSprite --|> Sprite

    interface ISpriteStore {
        + DEFAULT_SPRITE_SIZE : int
        + DEFAULT_FRAME_RATE : int

        + getSprite(identifier : String) : Sprite
        + getSprite(identifiers : String[]) : Sprite
        + getSprite(frameRate : int, identifiers : String[]) : Sprite
        + getSprite(identifiers : List<String>) : Sprite
        + getSprite(frameRate : int, identifiers : List<String>) : Sprite
        + getSpriteSize() : int
    }

    class SpriteStore {
        - INSTANCE : SpriteStore
        - spriteCache : Map<String, Sprite>
        - SpriteStore()
        + getInstance() : SpriteStore
        + getSprite(identifier : String) : Sprite
        + getSprite(frameRate : int, identifiers : List<String>) : Sprite
        - loadImage(name : String) : Image
    }
    SpriteStore ..|> ISpriteStore

    ISpriteStore --> Sprite : "crée"

    %% --------------------------
    %% Gestion de la carte du jeu
    %% --------------------------

    class GameMap {
        - height : int
        - width : int
        - cells : Cell[][]

        + GameMap(height : int, width : int)
        - init() : void
        + getHeight() : int
        + getWidth() : int
        + isOnMap(row : int, column : int) : boolean
        + getAt(row : int, column : int) : Cell
        + setAt(row : int, column : int, cell : Cell) : void
        + getEmptyCells() : List<Cell>
    }

    class Cell {
        - row : int
        - column : int
        - spriteProperty : ObjectProperty<Sprite>
        - wallProperty : ObjectProperty<Wall>
        - imageProperty : ObjectProperty<Image>

        + Cell(row : int, column : int)
        + Cell(sprite : Sprite)
        + Cell(wall : Wall)
        + getRow() : int
        + getColumn() : int
        + getWidth() : int
        + getHeight() : int
        + isEmpty() : boolean
        + getSprite() : Sprite
        + spriteProperty() : ObjectProperty<Sprite>
        + getWall() : Wall
        + wallProperty() : ObjectProperty<Wall>
        + imageProperty() : ObjectProperty<Image>
        + replaceBy(cell : Cell) : void
    }

    class Wall {
        - sprite : Sprite

        + Wall(sprite : Sprite)
        + getSprite() : Sprite
    }

    interface ICardGenerator {
        + generate(height : int, width : int) : GameMap
    }

    class CardGenerator {
        - INSTANCE : CardGenerator
        - CardGenerator()
        + getInstance() : CardGenerator
        + generate(height : int, width : int) : GameMap
        - generateBorderWalls(map : GameMap) : void
        - generateHorizontalWall(map : GameMap) : void
        - createWallCell() : Cell
        - createPathCell() : Cell
    }
    CardGenerator ..|> ICardGenerator

    class CardGeneratorEmpty {
        - INSTANCE : CardGeneratorEmpty
        - CardGeneratorEmpty()
        + getInstance() : CardGeneratorEmpty
        + generate(height : int, width : int) : GameMap
        - generateBorderWalls(map : GameMap) : void
        - createWallCell() : Cell
        - createPathCell() : Cell
    }
    CardGeneratorEmpty ..|> ICardGenerator

    class CardGeneratorDecorated {
        - spriteStore : SpriteStore
        - generator : ICardGenerator

        + CardGeneratorDecorated(generator : ICardGenerator)
        + generate(height : int, width : int) : GameMap
        - generateInsideWalls(map : GameMap) : void
        - createWallCell() : Cell
        - createPathCell() : Cell
    }
    CardGeneratorDecorated ..|> ICardGenerator

    class CardGeneratorFixed {
        - spriteStore : SpriteStore
        - generator : ICardGenerator
        - walls : int[][]

        + CardGeneratorFixed(generator : ICardGenerator)
        + generate(height : int, width : int) : GameMap
        - generateInsideWalls(map : GameMap) : void
        - createWallCell() : Cell
    }
    CardGeneratorFixed ..|> ICardGenerator

    GameMap *-- Cell : "contient *"
    Cell o-- Wall : "1"
    Cell o-- Sprite : "1"
    Wall o-- Sprite : "1"

    CardGenerator o-- SpriteStore
    ICardGenerator --> GameMap : "crée"
    ICardGenerator --> Cell : "crée"

    %% --------------------
    %% Gestion de la partie
    %% --------------------

    class PacmanGame {
        + RANDOM : Random
        + DEFAULT_SPEED : int
        - speed : int
        - width : int
        - height : int
        - spriteStore : ISpriteStore
        - gameMap : GameMap
        - player : PacMan
        - nbGhosts : int
        - nbGums : int
        - movingObjects : List<IAnimated>
        - animatedObjects : List<IAnimated>
        - animation : AnimationTimer
        - controller : IPacmanController
        - factory : IAbstractFactoryPacmanGame
        - currentLevel : Level

        + PacmanGame(gameWidth : int, gameHeight : int, spriteStore : ISpriteStore, nbGhosts : int)
        + setController(controller : IPacmanController) : void
        + getSpriteStore() : ISpriteStore
        + getWidth() : int
        + getHeight() : int
        + prepare() : void
        + start() : void
        + setSpeed() : void
        + getNbGums() : int
        + setNbGums(nbGums : int)
        - createMap() : GameMap
        - createAnimated() : void
        - initStatistics() : void
        - spawnAnimated(animated : IAnimated) : void
        + moveUp() : void
        + moveRight() : void
        + moveDown() : void
        + moveLeft() : void
        + stopMoving() : void
        + getCellAt(x : int, y : int) : Cell
        + addMoving(object : IAnimated) : void
        + addAnimated(object : IAnimated) : void
        + removeAnimated(object : IAnimated) : void
        - clearAnimated() : void
        + pacGumEaten(gum : IAnimated) : void
        + playerIsDead() : void
        - gameOver(message : String) : void
        + getCurrentLevel : Level
        + prepareLevel(levelNumber : int) : void
        - levelCleared(message : String) : void
        + restartCurrentLevel() : void
        + nextLevel() : void
    }

    interface IAnimated {
        + getWidth() : int
        + getHeight() : int
        + setX(xPosition : double) : void
        + getX() : int
        + xProperty() : DoubleProperty
        + setY(yPosition : double) : void
        + getY() : int
        + yProperty() : DoubleProperty
        + setHorizontalSpeed(speed : double) : void
        + getHorizontalSpeed() : double
        + setVerticalSpeed(speed : double) : void
        + getVerticalSpeed() : double
        + setSprite(sprite : Sprite) : void
        + getSprite() : Sprite
        + spriteProperty() : ObjectProperty<Sprite>
        + imageProperty() : ObjectProperty<Image>
        + setDestroyed(destroyed : boolean) : void
        + isDestroyed() : boolean
        + destroyedProperty() : BooleanProperty
        + onCreation() : void
        + onSpawn() : void
        + onStep(timeDelta : long) : boolean
        + isCollidingWith(other : IAnimated) : boolean
        + onCollisionWith(other : IAnimated) : void
        + onCollisionWith(other : PacMan) : void
        + onCollisionWith(other : Ghost) : void
        + onCollisionWith(other : PacGum) : void
        + onCollisionWith(other : MegaGum) : void
        + onCollisionWith(other : Bonus) : void
        + onDespawn() : void
        + onDestruction() : void
        + self() : IAnimated
    }

    interface IPacmanController {
        + setGame(game : PacmanGame) : void
        + prepare(map : GameMap) : void
        + bindScore(scoreProperty : IntegerExpression) : void
        + bindLife(lifeProperty : IntegerExpression) : void
        + addAnimated(movable : IAnimated) : void
        + gameOver(endMessage : String) : void
        + reset() : void
    }

    class GameAnimation {
        - previousTimestamp : long
        - movingObjects : List<IAnimated>
        - animatedObjects : List<IAnimated>

        + GameAnimation(movingObjects : List<IAnimated>, animatedObjects : List<IAnimated>)
        + start() : void
        + handle(now : long) : void
        - updateObjects(delta : long) : void
        - checkCollisions() : void
    }

    interface IAbstractFactoryPacmanGame {
        + createPacman(game : PacmanGame) : PacMan
        + createGhost(game : PacmanGame) : List<Ghost>
        + createMap(width : int, height : int) : GameMap
        + createGum(game : PacmanGame, cellColumn : int, cellRow : int) : IAnimated
    }

    class ConcreteFactoryPacmanGame {
        - generator : ICardGenerator
        - NB_GHOSTS : int
        - spriteStore : SpriteStore
        - RANDOM : Random

        + createPacman(game : PacmanGame) : PacMan
        + createGhost(game : PacmanGame) : List<Ghost>
        + createMap(width : int, height : int) : GameMap
        + createGum(game : PacmanGame, cellColumn : int, cellRow : int) : IAnimated
    }
    ConcreteFactoryPacmanGame ..|> IAbstractFactoryPacmanGame

    class Level {
        - levelNumber : int
        - map : GameMap
        - megaGumProbability : int
        - bonusProbability : int

        + Level(levelNumber : int, map : GameMap, megaGumProbability : int, bonusProbability : int)
        + getLevelNumber() : int
        + getMap() : GameMap
        + getMegaGumProbability() : int
        + getBonusProbability() : int
    }

    class LevelFactory {
        + createLevel(levelNumber : int, width : int, height : int) : Level
    }

    PacmanGame o-- ISpriteStore
    PacmanGame o-- GameMap
    PacmanGame o-- IAnimated : "moving/animated"
    PacmanGame o-- GameAnimation
    PacmanGame o-- IPacmanController
    PacmanGame o-- PacMan

    GameAnimation o-- IAnimated : "*"

%% ---------------------------------
    %% Gestion de base des objets animés
    %% ---------------------------------

    abstract class AbstractAnimated {
        - MARGIN : int
        # game : PacmanGame
        # xPosition : DoubleProperty
        # yPosition : DoubleProperty
        # horizontalSpeed : double
        # verticalSpeed : double
        # destroyed : BooleanProperty
        # sprite : ObjectProperty<Sprite>
        # image : ObjectProperty<Image>

        + getWidth() : int
        + getHeight() : int
        + setX(xPosition : double) : void
        + getX() : int
        + xProperty() : DoubleProperty
        + setY(yPosition : double) : void
        + getY() : int
        + yProperty() : DoubleProperty
        + setHorizontalSpeed(speed : double) : void
        + getHorizontalSpeed() : double
        + setVerticalSpeed(speed : double) : void
        + getVerticalSpeed() : double
        + setSprite(sprite : Sprite) : void
        + getSprite() : Sprite
        + spriteProperty() : ObjectProperty<Sprite>
        + imageProperty() : ObjectProperty<Image>
        + setDestroyed(destroyed : boolean) : void
        + isDestroyed() : boolean
        + destroyedProperty() : BooleanProperty
        + onCreation() : void
        + onSpawn() : void
        + onStep(delta : long) : boolean
        - isOnWall(x : int, y : int) : boolean
        + isCollidingWith(other : IAnimated) : boolean
        + onDespawn() : void
        + onDestruction() : void
        + self() : IAnimated
        + hashCode() : int
        + equals(obj : Object) : boolean
    }

    AbstractAnimated o-- PacmanGame
    AbstractAnimated o-- Sprite

    class PacMan {
        - hp : IntegerProperty
        - score : IntegerProperty
        - state : IStatePacman
        - spriteStore : SpriteStore
        - scoreMult : double

        + getHpProperty() : IntegerProperty
        + getScoreProperty() : IntegerProperty
        + getHp() : IntegerProperty
        + setHp(hp : int) : void
        + setScoreMult(scoreMult : double) : void
        + setState(state : IStatePacman) : void
        + onCollisionWith(other : IAnimated) : void
        + onCollisionWith(other : PacMan) : void
        + onCollisionWith(other : Ghost) : void
        + onCollisionWith(other : PacGum) : void
        + onCollisionWith(other : MegaGum) : void
        + onCollisionWith(other : Bonus) : void
        + onStep(delta : long) : boolean
    }
    PacMan --|> AbstractAnimated

    interface IStatePacman {
        + onCollisionWithGhost(pacman : PacMan) : IStatePacman
        + changeStatePacman(time : long) : IStatePacman
        + getSprite(spriteStore : SpriteStore) : Sprite
        + handleState(game : PacmanGame) : void
    }

    class PacmanInvulnerable {
        - compteur : long
        - sprite : Sprite
        - duree : long

        + onCollisionWithGhost(pacman : PacMan) : IStatePacman
        + changeStatePacman(time : long) : IStatePacman
        + getSprite(spriteStore : SpriteStore) : Sprite
        + handleState(game : PacmanGame) : void
        + setDuree(duree : long) : void
    }
    PacmanInvulnerable ..|> IStatePacman

    class PacmanVulnerable {
        - sprite : Sprite

        + onCollisionWithGhost(pacman : PacMan) : IStatePacman
        + changeStatePacman(time : long) : IStatePacman
        + getSprite(spriteStore : SpriteStore) : Sprite
        + handleState(game : PacmanGame) : void
    }
    PacmanVulnerable ..|> IStatePacman

    class ScoreBonusState {
        - delta : long

        + changeStatePacman(time : long) : IStatePacman
        + handleState(game : PacmanGame) : void
    }
    ScoreBonusState --|> PacmanVulnerable

    class PacmanSpeedState {
        - delta : long

        + changeStatePacman(time : long) : IStatePacman
        + handleState(game : PacmanGame) : void
    }
    PacmanSpeedState --|> PacmanVulnerable

    class PacGum {
        + onCollisionWith(other : IAnimated) : void
        + onCollisionWith(other : PacMan) : void
        + onCollisionWith(other : Ghost) : void
        + onCollisionWith(other : PacGum) : void
        + onCollisionWith(other : MegaGum) : void
        + onCollisionWith(other : Bonus) : void
    }
    PacGum --|> AbstractAnimated

    class MegaGum {
        + onCollisionWith(other : IAnimated) : void
        + onCollisionWith(other : PacMan) : void
        + onCollisionWith(other : Ghost) : void
        + onCollisionWith(other : PacGum) : void
        + onCollisionWith(other : MegaGum) : void
        + onCollisionWith(other : Bonus) : void
    }
    MegaGum --|> AbstractAnimated

    class GhostColor {
        + getMoveStrategy() : IStrategyGhost
        - moveStrategy : IStrategyGhost
    }

    class Ghost {
        - strategyGhost : IStrategyGhost
        - stateGhost : IStateGhost
        - color : GhostColor

        + getColor() : GhostColor
        + setColor(color : GhostColor) : void
        + setStrategyGhost(strategy : IStrategyGhost) : void
        + setState(stateGhost : IStateGhost) : void
        + onCollisionWith(other : IAnimated) : void
        + onCollisionWith(other : PacMan) : void
        + onCollisionWith(other : Ghost) : void
        + onCollisionWith(other : PacGum) : void
        + onCollisionWith(other : MegaGum) : void
        + onCollisionWith(other : Bonus) : void
        + onStep(delta : long) : boolean
    }
    Ghost --|> AbstractAnimated
    Ghost o-- GhostColor
    Ghost o-- IStrategyGhost

    interface IStateGhost {
        + moveState(ghost : Ghost, game : PacmanGame) : void
        + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
        + getSpriteGhost(ghost : Ghost) : void
        + nextState() : IStateGhost
        + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
    }

    interface IStrategyGhost {
        + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
        + setSpeed(speed : double) : void
    }

    class ChaseStrategyGhost {
        - speed : double
        + setSpeed(speed : double) : void
        + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
        - changeDirection(ghost : Ghost, game : PacmanGame) : void
    }
    ChaseStrategyGhost ..|> IStrategyGhost

    class DumbStrategyGhost {
        - speed : double
        - temps : double
        + setSpeed(speed : double) : void
        + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
        - changeDirection(ghost : Ghost) : void
    }
    DumbStrategyGhost ..|> IStrategyGhost

    class SurroundStrategyGhost {
        - speedOfGhost : double
        - stateGhost : IStateGhost
        + SurroundStrategyGhost(speedOfGhost : int)
        + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
        + setSpeed(speed : double) : void
    }
    SurroundStrategyGhost ..|> IStrategyGhost
    SurroundStrategyGhost o-- IStateGhost

    class ChaseRandomCompositeStrategyGhost {
        - listeStrategys : IStrategyGhost[]
        - temps : double
        - current : int
        - speed : double
        + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
        + setSpeed(speed : double) : void
    }
    ChaseRandomCompositeStrategyGhost ..|> IStrategyGhost
    ChaseRandomCompositeStrategyGhost o-- "2..*" IStrategyGhost

    class DistantStateGhost {
        - temps : double
        + moveState(ghost : Ghost, delta : long, speedOfGhostState : double, game : PacmanGame) : void
        + nextState() : IStateGhost
    }
    DistantStateGhost ..|> IStateGhost

    class ClassicStateGhost {
        - INSTANCE : ClassicStateGhost
        - ClassicStateGhost()
        + getInstance() : ClassicStateGhost
        + moveState(ghost : Ghost, delta : long, speedOfGhostState : double, game : PacmanGame) : void
        + nextState() : IStateGhost
    }
    ClassicStateGhost ..|> IStateGhost

    class FleeingStateGhost {
        - time : double
        - spritesGhost : Sprite
        - SPEED : double
        + moveState(ghost : Ghost, game : PacmanGame) : void
        + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
        + getSpriteGhost(ghost : Ghost) : void
        + nextState() : IStateGhost
        + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
    }
    FleeingStateGhost ..|> IStateGhost

    class InvulnerableStateGhost {
        - spritesGhost : Sprite
        + moveState(ghost : Ghost, game : PacmanGame) : void
        + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
        + getSpriteGhost(ghost : Ghost) : void
        + nextState() : IStateGhost
        + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
    }
    InvulnerableStateGhost ..|> IStateGhost

    class NearlyInvulnerableStateGhost {
        - time : double
        - spritesGhost : Sprite
        - SPEED : double
        + moveState(ghost : Ghost, game : PacmanGame) : void
        + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
        + getSpriteGhost(ghost : Ghost) : void
        + nextState() : IStateGhost
        + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
    }
    NearlyInvulnerableStateGhost ..|> IStateGhost

    class VulnerableStateGhost {
        - time : double
        - spritesGhost : Sprite
        - SPEED : double
        + moveState(ghost : Ghost, game : PacmanGame) : void
        + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
        + getSpriteGhost(ghost : Ghost) : void
        + nextState() : IStateGhost
        + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
    }
    VulnerableStateGhost ..|> IStateGhost

    class SlowStateGhost {
        - time : double
        - spritesGhost : Sprite
        - SPEED : double
        + moveState(ghost : Ghost, game : PacmanGame) : void
        + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
        + getSpriteGhost(ghost : Ghost) : void
        + nextState() : IStateGhost
        + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
    }
    SlowStateGhost ..|> IStateGhost

    class Bonus {
        + onCollisionWith(other : IAnimated) : void
        + onCollisionWith(other : PacMan) : void
        + onCollisionWith(other : Ghost) : void
        + onCollisionWith(other : PacGum) : void
        + onCollisionWith(other : MegaGum) : void
        + onCollisionWith(other : Bonus) : void
    }
    Bonus --|> AbstractAnimated

    class ScoreBonus {
        + handleBonus() : void
    }
    ScoreBonus --|> Bonus

    class SlowGhostBonus {
        + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
    }
    SlowGhostBonus --|> Bonus

    class BonusComposite {
        - bonuses : Bonus[]
        - bonusSlowGhost : Bonus
        - rand : Random
        + onCollisionWith(other : IAnimated) : void
        + onCollisionWith(other : PacMan) : void
    }
    BonusComposite ..|> IAnimated
    BonusComposite o-- Bonus

    class PacmanSpeedBonus {
        + handleBonus() : void
    }
    PacmanSpeedBonus --|> Bonus

    class InvulnerableBonus {
        + InvulnerableBonus() : void
    }
    InvulnerableBonus --|> Bonus

    Ghost o-- GhostColor
    Ghost o-- IStrategyGhost

    %% -----------------
    %% Contrôleur JavaFX
    %% -----------------

    class PacmanController {
        - game : PacmanGame
        - stage : Stage
        - backgroundPane : GridPane
        - animatedPane : Pane
        - message : Label
        - score : Label
        - life : Label
        - started : boolean

        + setGame(game : PacmanGame) : void
        + setStage(stage : Stage) : void
        - addKeyListeners() : void
        + prepare(map : GameMap) : void
        - createBackground(map : GameMap) : void
        + bindScore(scoreProperty : IntegerExpression) : void
        + bindLife(lifeProperty : IntegerExpression) : void
        + addAnimated(animated : IAnimated) : void
        + gameOver(endMessage : String) : void
        + reset() : void
    }
    PacmanController ..|> IPacmanController
    PacmanController o-- PacmanGame

