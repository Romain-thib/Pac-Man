/**
 * Le module {@code fr.univartois.butinfo.r304.pacman} contient l'implémentation en Java FX du jeu Pac-Ma.
 *
 * @author shun.lembrez
 *
 * @version 0.1.0
 */
module fr.univartois.butinfo.r304.pacman {
  exports fr.univartois.butinfo.r304.pacman;
  exports fr.univartois.butinfo.r304.pacman.controller;
  exports fr.univartois.butinfo.r304.pacman.model;
  exports fr.univartois.butinfo.r304.pacman.model.animated;
  exports fr.univartois.butinfo.r304.pacman.model.animated.bonus;
  exports fr.univartois.butinfo.r304.pacman.model.animated.ghoststate;
  exports fr.univartois.butinfo.r304.pacman.model.animated.pacmanstate;
  exports fr.univartois.butinfo.r304.pacman.model.animated.strategyghost;
  exports fr.univartois.butinfo.r304.pacman.model.map;
  exports fr.univartois.butinfo.r304.pacman.model.map.generator;
  exports fr.univartois.butinfo.r304.pacman.view;

  opens fr.univartois.butinfo.r304.pacman.controller to javafx.fxml;

  requires javafx.fxml;
  requires javafx.graphics;
  requires transitive javafx.controls;
  
  requires fr.univartois.dpprocessor;
}