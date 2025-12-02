# _Pac-Man_ en JavaFX

## Chef de projet :

Timothée Gros

## Description

Ce projet fournit une implémentation de base du jeu _Pac-Man_ en _JavaFX_.
Pour pouvoir développer votre propre implémentation de ce projet, vous devez
en créer une **divergence** en cliquant sur le bouton `Fork` en haut à droite
de cette page.

Lorsque ce sera fait, vous pourrez inviter les membres de votre groupe en tant
que _Developer_ pour vous permettre de travailler ensemble sur ce projet.

## Consignes

Vous pouvez retrouver ci-dessous les liens vers les sujets de TP vous guidant
dans le développement de votre projet :

- [Lancement du projet](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/tp/TP03)
- [Des patrons de conception dans *Pac-Man* (1)](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/tp/TP04)
- [Des patrons de conception dans *Pac-Man* (2)](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/tp/TP05)
- [Des patrons de conception dans *Pac-Man* (3)](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/tp/TP06)
- [Bonnes pratiques de la POO dans le projet *Pac-Man*](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/tp/TP07)

## Diagramme de classes

```plantuml
hide empty members

' --------------------------------------- '
' Gestion des images du jeu (les sprites) '
' --------------------------------------- '

abstract class Sprite {
    - imageProperty : ObjectBinding<Image>

    # Sprite(imageProperty : ObjectBinding<Image>)
    + getWidth() : int
    + getHeight() : int
    + imageProperty() : ObjectBinding<Image>
    + {abstract} destroy() : void
}

class StaticSprite extends Sprite {
    - StaticSprite(imageProperty : ObjectBinding<Image>)
    + {static} newInstance(image : Image) : Sprite
    + destroy() : void
}

class AnimatedSprite extends Sprite {
    - timeline : Timeline

    - AnimatedSprite(imageProperty : ObjectBinding<Image>, timeline : Timeline)
    + {static} newInstance(images : ObservableList<Image>, frameRate : int) : Sprite
    + destroy() : void
}

interface ISpriteStore {
    + {static} DEFAULT_SPRITE_SIZE : int
    + {static} DEFAULT_FRAME_RATE : int

    + {abstract} getSprite(identifier : String) : Sprite
    + getSprite(identifiers : String[]) : Sprite
    + getSprite(frameRate : int, identifiers : String[]) : Sprite
    + getSprite(identifiers : List<String>) : Sprite
    + {abstract} getSprite(frameRate : int, identifiers : List<String>) : Sprite
    + getSpriteSize() : int
}

class SpriteStore implements ISpriteStore {
    - INSTANCE : SpriteStore
    - spriteCache : Map<String, Sprite>
    - SpriteStore() 
    + getInstance() : SpriteStore
    + getSprite(identifier : String) : Sprite
    + getSprite(frameRate : int, identifiers : List<String>) : Sprite
    - loadImage(name : String) : Image
}

ISpriteStore --> Sprite : << crée >>

' -------------------------- '
' Gestion de la carte du jeu '
' -------------------------- '

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

    # Cell(row : int, column : int)
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

class CardGenerator implements ICardGenerator {
    - INSTANCE : CardGenerator
    - CardGenerator()
    + getInstance() : CardGenerator
    + generate(height : int, width : int) : GameMap
    - generateBorderWalls(map : GameMap) : void
    - generateHorizontalWall(map : GameMap) : void
    - createWallCell() : Cell
    - createPathCell() : Cell
}

class CardGeneratorEmpty implements ICardGenerator {
    - INSTANCE : CardGeneratorEmpty
    - CardGeneratorEmpty()
    + getInstance() : CardGeneratorEmpty
    + generate(height : int, width : int) : GameMap
    - generateBorderWalls(map : GameMap) : void
    - createWallCell() : Cell
    - createPathCell() : Cell
}

class CardGeneratorDecorated implements ICardGenerator {
	- {static} spriteStore : SpriteStore
	- generator : ICardGenerator

	+ CardGeneratorDecorated(generator : ICardGenerator)
   	+ generate(height : int, width : int) : GameMap
	- generateInsideWalls(map : GameMap) : void
	- createWallCell() : Cell
   	- createPathCell() : Cell
}

class CardGeneratorFixed implements ICardGenerator {
	- {static} spriteStore : SpriteStore
	- generator : ICardGenerator
	- walls : int[][]

	+ CardGeneratorDecorated(generator : ICardGenerator)
   	+ generate(height : int, width : int) : GameMap
	- generateInsideWalls(map : GameMap) : void
	- createWallCell() : Cell
}

GameMap *-- "*" Cell

Cell o-- "1" Wall
Cell o-- "1" Sprite

Wall o-- "1" Sprite

CardGenerator o-- "1" SpriteStore
ICardGenerator --> GameMap : << crée >>
ICardGenerator --> Cell : << crée >>

' -------------------- '
' Gestion de la partie '
' -------------------- '

class PacmanGame {
    + {static} RANDOM : Random
    + {static} DEFAULT_SPEED : int
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
    - spawnAnimated(animated : IAnimated): void
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
    + {abstract} getWidth() : int
    + {abstract} getHeight() : int
    + {abstract} setX(xPosition : double) : void
    + {abstract} getX() : int
    + {abstract} xProperty() : DoubleProperty
    + {abstract} setY(yPosition : double) : void
    + {abstract} getY() : int
    + {abstract} yProperty() : DoubleProperty
    + {abstract} setHorizontalSpeed(speed : double) : void
    + {abstract} getHorizontalSpeed() : double
    + {abstract} setVerticalSpeed(speed : double) : void
    + {abstract} getVerticalSpeed() : double
    + {abstract} setSprite(sprite : Sprite) : void
    + {abstract} getSprite() : Sprite
    + {abstract} spriteProperty() : ObjectProperty<Sprite>
    + {abstract} imageProperty() : ObjectProperty<Image>
    + {abstract} setDestroyed(destroyed : boolean) : void
    + {abstract} isDestroyed() : boolean
    + {abstract} destroyedProperty() : BooleanProperty
    + {abstract} onCreation() : void
    + {abstract} onSpawn() : void
    + {abstract} onStep(timeDelta : long) : boolean
    + {abstract} isCollidingWith(other : IAnimated) : boolean
    + {abstract} onCollisionWith(other : IAnimated) : void
    + {abstract} onCollisionWith(other : PacMan) : void
    + {abstract} onCollisionWith(other : Ghost) : void
    + {abstract} onCollisionWith(other : PacGum) : void
    + {abstract} onCollisionWith(other : MegaGum) : void
    + {abstract} onCollisionWith(other : Bonus) : void
    + {abstract} onDespawn() : void
    + {abstract} onDestruction() : void
    + {abstract} self() : IAnimated
}

interface IPacmanController {
    + {abstract} setGame(game : PacmanGame) : void
    + {abstract} prepare(map : GameMap) : void
    + {abstract} bindScore(scoreProperty : IntegerExpression) : void
    + {abstract} bindLife(lifeProperty : IntegerExpression) : void
    + {abstract} addAnimated(movable : IAnimated) : void
    + {abstract} gameOver(endMessage : String) : void
    + {abstract} reset() : void
}

class GameAnimation {
    - previousTimestamp : long
    - movingObjects : List<IAnimated>
    - animatedObjects : List<IAnimated>

    + GameAnimation(movingObjects : List<IAnimated>, animatedObjects : List<IAnimated>)
    + start(): void
    + handle(now : long) : void
    - updateObjects(delta : long) : void
    - checkCollisions() : void
}

interface IAbstractFactoryPacmanGame {
      + createPacman(PacmanGame game) : PacMan
      + createGhost(PacmanGame game) : List<Ghost>
      + createMap(width:int, height:int) : GameMap
      + createGum(PacmanGame game, cellColumn:int, cellRow:int) : IAnimated
}

class ConcreteFactoryPacmanGame implements IAbstractFactoryPacmanGame {
      - ICardGenerator generator
      - static final int NB_GHOSTS = 4
      - SpriteStore spriteStore
      - Random RANDOM
      + createPacman(PacmanGame game) : PacMan
      + createGhost(PacmanGame game) : List<Ghost>
      + createMap(width:int, height:int) : GameMap
      + createGum(PacmanGame game, cellColumn:int, cellRow:int) : IAnimated
}

class Level {
    - levelNumber : int
    - map : GameMap
    - megaGumProbability : int
    - bonusProbability : int
    + Level(levelNumber:int, map:GameMap, megaGumProbability:int, bonusProbability:int)
    + getLevelNumber() : int
    + getMap() : GameMap
    + getMegaGumProbability() : int
    + getBonusProbability() : int
}

class LevelFactory {
    + createLevel(levelNumber:int, width:int, height:int) : Level
}

PacmanGame o-- "1" ISpriteStore
PacmanGame o-- "1" GameMap
PacmanGame o-- "*" IAnimated
PacmanGame o-- "1" GameAnimation
PacmanGame o-- "1" IPacmanController
PacmanGame o-- "1" PacMan

GameAnimation o-- "*" IAnimated

' --------------------------------- '
' Gestion de base des objets animés '
' --------------------------------- '

abstract class AbstractAnimated implements IAnimated {
    - {static} MARGIN : int
    # game : PacmanGame
    # xPosition : DoubleProperty
    # yPosition : DoubleProperty
    # horizontalSpeed : double
    # verticalSpeed : double
    # destroyed : BooleanProperty
    # sprite : ObjectProperty<Sprite>
    # image : ObjectProperty<Image>

    # AbstractAnimated(game : PacmanGame, xPosition : double, yPosition : double, sprite : Sprite)
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

AbstractAnimated o-- "1" PacmanGame
AbstractAnimated o-- "1" Sprite

class PacMan extends AbstractAnimated {
    - hp : IntegerProperty
    - score : IntegerProperty
    - state : IStatePacman
    - spriteStore : SpriteStore
    - scoreMult : double

    + PacMan(game : PacmanGame, xPosition : double, yPosition : double, sprite : Sprite)
    + getHpProperty() : IntegerProperty
    + getScoreProperty() : IntegerProperty
    + getHp() : IntegerProperty
    + setHp(hp : int) : void
    + setScoreMult(scoreMult : double) : void
    + setState(state : IStatePacman): void
    + onCollisionWith(other : IAnimated) : void
    + onCollisionWith(other : PacMan) : void
    + onCollisionWith(other : Ghost) : void
    + onCollisionWith(other : PacGum) : void
    + onCollisionWith(other : MegaGum) : void
    + onCollisionWith(other : Bonus) : void
    + onStep(delta : long) : boolean
}

interface IStatePacman {
	+ onCollisionWithGhost(pacman : PacMan) : IStatePacman
	+ changeStatePacman(time : long) : IStatePacman
	+ getSprite(spriteStore : SpriteStore) : Sprite
	+ handleState(game : PacmanGame) : void
}

class PacmanInvulnerable implements IStatePacman{
	- compteur : long
	- sprite : Sprite
	- duree : long
	
	+ onCollisionWithGhost(pacman : PacMan) : IStatePacman
	+ changeStatePacman(time : long) : IStatePacman
	+ getSprite(spriteStore : SpriteStore) : Sprite
	+ handleState(game : PacmanGame) : void
	+ setDuree(duree : long) : void
}

class PacmanVulnerable implements IStatePacman{
	- sprite : Sprite
	
	+ onCollisionWithGhost(pacman : PacMan) : IStatePacman
	+ changeStatePacman(time : long) : IStatePacman
	+ getSprite(spriteStore : SpriteStore) : Sprite
	+ handleState(game : PacmanGame) : void
}

class ScoreBonusState extends PacmanVulnerable {
	- delta : long
	
	+ changeStatePacman(time : long) : IStatePacman
	+ handleState(game : PacmanGame) : void
}

class PacmanVulnerable implements IStatePacman{
	- sprite : Sprite
	
	+ onCollisionWithGhost(pacman : PacMan) : IStatePacman
	+ changeStatePacman(time : long) : IStatePacman
	+ getSprite(spriteStore : SpriteStore) : Sprite
	+ handleState(game : PacmanGame) : void
}

class ScoreBonusState extends PacmanVulnerable {
	- delta : long
	
	+ changeStatePacman(time : long) : IStatePacman
	+ handleState(game : PacmanGame) : void
}

class PacmanSpeedState extends PacmanVulnerable {
	- delta : long
	
	+ changeStatePacman(time : long) : IStatePacman
	+ handleState(game : PacmanGame) : void
}

class PacmanVulnerable implements IStatePacman{
	- sprite : Sprite
	
	+ onCollisionWithGhost(pacman : PacMan) : IStatePacman
	+ changeStatePacman(time : long) : IStatePacman
	+ getSprite(spriteStore : SpriteStore) : Sprite
	+ handleState(game : PacmanGame) : void
}

class PacGum extends AbstractAnimated {
    + PacGum(game : PacmanGame, xPosition : double, yPosition : double, sprite : Sprite)
    + onCollisionWith(other : IAnimated) : void
    + onCollisionWith(other : PacMan) : void
    + onCollisionWith(other : Ghost) : void
    + onCollisionWith(other : PacGum) : void
    + onCollisionWith(other : MegaGum) : void
    + onCollisionWith(other : Bonus) : void
}

class MegaGum extends AbstractAnimated {
    + MegaGum(game : PacmanGame, xPosition : double, yPosition : double, sprite : Sprite)
    + onCollisionWith(other : IAnimated) : void
    + onCollisionWith(other : PacMan) : void
    + onCollisionWith(other : Ghost) : void
    + onCollisionWith(other : PacGum) : void
    + onCollisionWith(other : MegaGum) : void
    + onCollisionWith(other : Bonus) : void
}



enum GhostColor {
  RED
  PINK
  BLUE
  ORANGE
  - moveStrategy : IStrategyGhost
  - GhostColor(strategy : IStrategyGhost)
  + getMoveStrategy() : IStrategyGhost
}

class Ghost extends AbstractAnimated implements IAnimated {
    - strategyGhost : IStrategyGhost
    - stateGhost : IStateGhost
    - color : GhostColor

    + Ghost(game : PacmanGame, xPosition : double, yPosition : double, sprites : Sprite, color : GhostColor)
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

interface IStateGhost{
	+ moveState(ghost : Ghost, game : PacmanGame) : void
	+ handleCollisionWithPacman(Ghost ghost,PacmanGame game) : IStateGhost
	+ getSpriteGhost(Ghost ghost) : void
	+ nextState() : IStateGhost
	+ handleCollisionWithAnimated(Ghost ghost, IAnimated animated) : void
}    

interface IStrategyGhost {
    + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
    + setSpeed(speed : double) : void
}

interface IStateGhostMove {
    + moveState(ghost : Ghost, delta : long, speedOfGhostState : double, game : PacmanGame) : void
    + nextState() : IStateGhost
}

class ChaseStrategyGhost implements IStrategyGhost {
    - speed : double
    + setSpeed(speed : double) : void
    + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
    - changeDirection(ghost : Ghost, game : PacmanGame) : void
}

class DumbStrategyGhost implements IStrategyGhost {
    - speed : double
    - temps : double
    + setSpeed(speed : double) : void
    + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
    - changeDirection(ghost : Ghost) : void
}

class SurroundStrategyGhost implements IStrategyGhost {
    - speedOfGhost : double
    - stateGhost : IStateGhost
    + SurroundStrategyGhost(speedOfGhost : int)
    + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
    + setSpeed(speed : double) : void
}

class ChaseRandomCompositeStrategyGhost implements IStrategyGhost {
    - listeStrategys : IStrategyGhost[]
    - temps : double
    - current : int
    - speed : double
    + moveStrategy(ghost : Ghost, delta : long, game : PacmanGame) : void
    + setSpeed(speed : double) : void
}

class DistantStateGhost implements IStateGhost {
    - temps : double
    + moveState(ghost : Ghost, delta : long, speedOfGhostState : double, game : PacmanGame) : void
    + nextState() : IStateGhost
}

class ClassicStateGhost implements IStateGhost {
    - INSTANCE : ClassicStateGhost
    - ClassicStateGhost()
    + getInstance() : ClassicStateGhost
    + moveState(ghost : Ghost, delta : long, speedOfGhostState : double, game : PacmanGame) : void
    + nextState() : IStateGhost
}

class FleeingStateGhost implements IStateGhost {
    - time : double = 5000
    - spritesGhost : Sprite
    - SPEED : double = -80
    + moveState(ghost : Ghost, game : PacmanGame) : void
    + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
    + getSpriteGhost(ghost : Ghost) : void
    + nextState() : IStateGhost
    + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
}

class InvulnerableStateGhost implements IStateGhost {
    - spritesGhost : Sprite
    + moveState(ghost : Ghost, game : PacmanGame) : void
    + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
    + getSpriteGhost(ghost : Ghost) : void
    + nextState() : IStateGhost
    + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
}

class NearlyInvulnerableStateGhost implements IStateGhost {
    - time : double = 3000
    - spritesGhost : Sprite
    - SPEED : double = -60
    + moveState(ghost : Ghost, game : PacmanGame) : void
    + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
    + getSpriteGhost(ghost : Ghost) : void
    + nextState() : IStateGhost
    + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
}

class VulnerableStateGhost implements IStateGhost {
    - time : double = 10000
    - spritesGhost : Sprite
    - SPEED : double = -60
    + moveState(ghost : Ghost, game : PacmanGame) : void
    + handleCollisionWithPacman(ghost : Ghost, game : PacmanGame) : IStateGhost
    + getSpriteGhost(ghost : Ghost) : void
    + nextState() : IStateGhost
    + handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
}

class SlowStateGhost implements IStateGhost{
	- time : double = 5000                            
	- spritesGhost : Sprite
	- SPEED : double = 50                            

	+ moveState(Ghost ghost, PacmanGame game) : void
	+ handleCollisionWithPacman(Ghost ghost, PacmanGame game) : IStateGhost
	+ getSpriteGhost(Ghost ghost) : void
	+ nextState() : IStateGhost
	+ handleCollisionWithAnimated(ghost : Ghost, animated : IAnimated) : void
}

abstract class Bonus extends AbstractAnimated {
	# Bonus()game : PacmanGame, xPosition : double, yPosition : double, Sprite sprites)
	+ onCollisionWith(other : IAnimated) : void
	+ onCollisionWith(other : PacMan) : void
	+ onCollisionWith(other : Ghost) : void
	+ onCollisionWith(other : PacGum) : void
	+ onCollisionWith(other : MegaGum) : void
	+ onCollisionWith(other : Bonus) : void
}

class ScoreBonus extends Bonus {
	+ ScoreBonus(game : PacmanGame, xPosition double, yPosition double, sprites Sprite)
	+ handleBonus() : void
}

class SlowGhostBonus extends Bonus{
	+ SlowGhostBonus(PacmanGame game, double xPosition, double yPosition, Sprite sprites)
	+ handleCollisionWithAnimated(Ghost ghost, IAnimated animated) : void
}

class BonusComposite implements IAnimated {
	- bonuses : Bonus[]
	- bonusSlowGhost : Bonus
	- rand : Random
	
	# BonusComposite(PacmanGame game, double xPosition, double yPosition, Sprite sprites)
	+ onCollisionWith(IAnimated other) : void
	+ onCollisionWith(PacMan other) : void
}

class PacmanSpeedBonus extends Bonus {
	+ PacmanSpeedBonus(game : PacmanGame, xPosition double, yPosition double, sprites Sprite)
	+ handleBonus() : void
}

class InvulnerableBonus extends Bonus {
	+ PacmanSpeedBonus(game : PacmanGame, xPosition double, yPosition double, sprites Sprite)
	+ InvulnerableBonus() : void
}

Ghost o-- "1" GhostColor
Ghost o-- "1" IStrategyGhost
SurroundStrategyGhost o-- "1" IStateGhost
ChaseRandomCompositeStrategyGhost o-- "2..*" IStrategyGhost
GhostColor o-- "1" IStrategyGhost


' ----------------- '
' Contrôleur JavaFX '
' ----------------- '

class PacmanController implements IPacmanController {
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

PacmanController o-- "1" PacmanGame
```

## Tâches réalisées

### Jalon n°1 - TP n°3

| Fonctionnalité                          | Terminée ? | Auteur(s)      |
| --------------------------------------- | ---------- | -------------- |
| Gestion des collisions spécifiques      | Oui        | Simon Cohet    |
| Représentation des pac-gommes           | Oui        | Simon cohet    |
| Représentation de Pac-Man               | Oui        | Timothée Gros  |
| Intégration de Pac-Man dans la partie   | Oui        | Timothée Gros  |
| Représentation des fantômes             | Oui        | Shun Lembrez   |
| Intégration des fantômes dans la partie | Oui        | Shun Lembrez   |
| Création de la carte du jeu             | Oui        | Romain Thibaut |
| Ajout des pac-gommes sur la carte       | Oui        | Romain Thibaut |

### Jalon n°2 - TP n°4

| Fonctionnalité                             | Patron de Conception ?    | Terminée ? | Auteur(s) |
| ------------------------------------------ | ------------------------- | ---------- | --------- |
| Variantes de génération de labyrinthe      | stratégie                 | oui        | Timothée  |
| Complétion d'un labyrinthe existant        | décorateur                | oui        | Simon     |
| Variantes de déplacement pour les fantômes | stratégie, état, composite| oui        | Shun      |
| Remplissage du readme pour le jalon 1      |                           | oui        | Romain    |

### Jalon n°3 - TP n°5

| Fonctionnalité                       | Patron de Conception ? | Terminée ? | Auteur(s) |
| ------------------------------------ | ---------------------- | ---------- | --------- |
| Pac-Man vulnérable                   | état                   |  oui       | romain    |
| Pac-Man invulnérable                 | état                   |  oui       | romain    |
| Fantômes vulnérables                 | état                   |  oui       | shun      |
| Fantômes fuyants                     | état                   |  oui       | simon     |
| Fantômes presque invulnérables       | état                   |  oui       | simon     |
| Fantômes invulnérables               | état                   |  oui       | shun      |
| Réutilisation des fantômes existants |                        |  oui       | shun      |
| Ajout des méga-gommes                |                        |  oui       | Timothée  |

### Jalon n°4 - TP n°6

| Fonctionnalité                                       | Patron de Conception ? | Terminée ? | Auteur(s)                                     |
| ---------------------------------------------------- | ---------------------- | ---------- | --------------------------------------------- |
| Définition d'un seul `SpriteStore`                 | Singleton              | oui        |  Timothée                                     |
| Définition d'une seule instance quand c'est possible | Singleton              | oui        |  Timothée                                     |
| Ajout des bonus (score)                              |                        | oui        |  Simon                                        |
| Ajout des bonus (vitesse)                            |                        | oui        |  Simon                                        |
| Ajout des bonus (invulnérable)                       |                        | oui        |  Simon                                        |
| Ajout des bonus (fantomes lents)                     |                        | oui        |  Simon                                        |
| Ajout des bonus multiples                            |                        | oui        |  Shun                                         |
| Gestion des différents niveaux                       | AbstractFactory        | oui        |  Romain                                       |


### Jalon n°5 - TP n°7

| Fonctionnalité                             | Patron de Conception ? | Terminée ? | Auteur(s)                                     |
| ------------------------------------------ | ---------------------- | ---------- | --------------------------------------------- |
| Correction des avertissements              |                        | oui        | Tous                                          |
| Correction des défauts sur *SonarQube*     |                        | oui        | Tous                                          |
| Rangement des classes en paquetages        |                        |            |                                               |
| Modularisation du projet                   |                        |            |                                               |
_À venir..._

