package com.github.cr9ck.clickerapp.model.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LevelEntity {

    @PrimaryKey
    private int levelId;
    private int goal;
    private int currentScore;
    private long timeLeft;
    private int imageResId;

    public LevelEntity(int levelId, int goal, int currentScore, long timeLeft, int imageResId) {
        this.levelId = levelId;
        this.goal = goal;
        this.currentScore = currentScore;
        this.timeLeft = timeLeft;
        this.imageResId = imageResId;
    }

    public int getLevelId() {
        return levelId;
    }

    public int getGoal() {
        return goal;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public int getImageResId() {
        return imageResId;
    }
}
