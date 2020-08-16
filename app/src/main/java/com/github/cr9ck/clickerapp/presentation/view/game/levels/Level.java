package com.github.cr9ck.clickerapp.presentation.view.game.levels;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import java.util.Objects;

public class Level implements Comparable<Level>{
    private @NonNull LevelDifficulty levelDifficulty;
    private int currentScore;
    private @DrawableRes
    int image;

    public Level(@NonNull LevelDifficulty levelDifficulty, @DrawableRes int image) {
        this.levelDifficulty = levelDifficulty;
        this.image = image;
        this.currentScore = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level1 = (Level) o;
        return image == level1.image &&
                levelDifficulty == level1.levelDifficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelDifficulty, image);
    }

    @Override
    public int compareTo(Level level) {
        return this.levelDifficulty.ordinal() - level.levelDifficulty.ordinal();
    }

    public LevelDifficulty getLevelDifficulty() {
        return levelDifficulty;
    }

    public void setLevelDifficulty(@NonNull LevelDifficulty levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    public void resetCurrentScore() {
        this.currentScore = 0;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public boolean clickAction() {
        if (currentScore + levelDifficulty.scoreForClick > levelDifficulty.getGoal())
            return false;
        currentScore += levelDifficulty.scoreForClick;
        return true;
    }

    public @DrawableRes int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public enum LevelDifficulty {
        EASY(1000, 20, 20),
        MEDIUM(1500, 15, 25),
        HARD(2000, 10, 50);
        private int goal;
        private int scoreForClick;
        private int timeToFinish;
        private int timeLeft;

        LevelDifficulty(int goal, int timeToFinish, int scoreForClick) {
            this.goal = goal;
            this.timeToFinish = timeToFinish;
            this.scoreForClick = scoreForClick;
        }

        public int getTimeToFinish() {
            return timeToFinish;
        }

        public void reduceTime(int amount) {
            timeLeft -= amount;
        }

        public int getGoal() {
            return goal;
        }

        public int getScoreForClick() {
            return scoreForClick;
        }
    }
}
