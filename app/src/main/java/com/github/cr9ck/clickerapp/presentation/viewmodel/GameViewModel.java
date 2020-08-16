package com.github.cr9ck.clickerapp.presentation.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.cr9ck.clickerapp.presentation.view.game.levels.Level;
import com.github.cr9ck.clickerapp.presentation.view.game.levels.Timer;

import java.util.List;

import javax.inject.Inject;

public class GameViewModel extends ViewModel {

    private Level selectedLvl;

    private MutableLiveData<Level> selectedLevel = new MutableLiveData<>();
    public LiveData<Level> selectedLevelPosLD = selectedLevel;

    private MutableLiveData<List<Level>> levels = new MutableLiveData<>();
    public LiveData<List<Level>> levelsLD = levels;

    private MutableLiveData<Integer> timerValue = new MutableLiveData<>();
    public LiveData<Integer> timerValueLD = timerValue;

    private MutableLiveData<Integer> score = new MutableLiveData<>(0);
    public LiveData<Integer> scoreLD = score;

    private MutableLiveData<GameStatus> gameStatus = new MutableLiveData<>();
    public LiveData<GameStatus> gameStatusLD = gameStatus;

    private @Nullable
    Timer timer;

    @Inject
    public GameViewModel() {
        gameStatus.setValue(GameStatus.INITIAL);
    }

    @Inject
    public void setLevels(List<Level> levels) {
        this.levels.setValue(levels);
    }

    public void selectLevel(Level level) {
        if (level == selectedLvl)
            return;
        selectedLvl = level;
        selectedLvl.resetCurrentScore();
        score.setValue(selectedLvl.getCurrentScore());
        selectedLevel.setValue(selectedLvl);
        if (timer != null) {
            timer.reset();
        }
        timer = new Timer(selectedLvl.getLevelDifficulty().getTimeToFinish());
        timer.setOnTimerUpdateListener(time -> {
            timerValue.postValue(time);
            if (time <= 0)
                gameStatus.postValue(GameStatus.LOSE);
        });
        timer.reset();
        timer.setTime(selectedLvl.getLevelDifficulty().getTimeToFinish());
        timerValue.setValue(selectedLvl.getLevelDifficulty().getTimeToFinish());
    }

    public void onItemPressed() {
        if (timer != null && !timer.isStarted()) {
            timer.reset();
            timer.start();
        }

        if (selectedLvl.clickAction()) {
            score.setValue(selectedLvl.getCurrentScore());
        } else {
            gameStatus.setValue(GameStatus.WIN);
            timer.stop();
        }
    }

    public void toggleTimer(boolean enable) {
        if (timer == null) return;
        if (!enable && timer.isStarted()) {
            timer.reset();
            selectedLvl.resetCurrentScore();
            score.setValue(selectedLvl.getCurrentScore());
        } else if (enable && !timer.isStarted()) {
            selectedLvl.resetCurrentScore();
            score.setValue(selectedLvl.getCurrentScore());
        }
    }

    public void restartGame() {
        gameStatus.setValue(GameStatus.DEFAULT);
        if (timer != null)
            timer.reset();
        if (selectedLvl == null) return;
        selectedLvl.resetCurrentScore();
        selectedLevel.setValue(selectedLvl);
        score.setValue(selectedLvl.getCurrentScore());
    }

    public enum GameStatus {
        INITIAL,
        DEFAULT,
        WIN,
        LOSE
    }
}
