package com.github.cr9ck.clickerapp.model.mapper;

import com.github.cr9ck.clickerapp.model.db.LevelEntity;
import com.github.cr9ck.clickerapp.presentation.view.game.levels.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelMapper /*implements Mapper<Level, LevelEntity> */{

//    @Override
//    public LevelEntity mapToEntity(Level model) {
//        return new LevelEntity(
//                model.getLevelDifficulty().ordinal(),
//                model.getGoal(),
//                model.getCurrentScore(),
//                model.getTimeLeft(),
//                model.getImage()
//        );
//    }
//
//    @Override
//    public Level mapToModel(LevelEntity entity) {
//        return new Level(
//                Level.LevelDifficulty.values()[entity.getLevelId()],
//                entity.getGoal(),
//                entity.getCurrentScore(),
//                entity.getImageResId()
//        );
//    }
//
//    @Override
//    public List<LevelEntity> mapToEntity(List<Level> models) {
//        List<LevelEntity> entities = new ArrayList<>();
//        for (Level level : models) {
//            entities.add(mapToEntity(level));
//        }
//        return entities;
//    }
//
//    @Override
//    public List<Level> mapToModel(List<LevelEntity> entities) {
//        List<Level> models = new ArrayList<>();
//        for (LevelEntity level : entities) {
//            models.add(mapToModel(level));
//        }
//        return models;
//    }
}
