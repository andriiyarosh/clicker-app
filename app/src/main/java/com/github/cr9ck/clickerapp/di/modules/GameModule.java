package com.github.cr9ck.clickerapp.di.modules;

import android.content.Context;
import android.media.MediaPlayer;

import com.github.cr9ck.clickerapp.R;
import com.github.cr9ck.clickerapp.presentation.view.game.levels.Level;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.github.cr9ck.clickerapp.presentation.view.game.levels.Level.LevelDifficulty.EASY;
import static com.github.cr9ck.clickerapp.presentation.view.game.levels.Level.LevelDifficulty.HARD;
import static com.github.cr9ck.clickerapp.presentation.view.game.levels.Level.LevelDifficulty.MEDIUM;

@Module
public class GameModule {

    @Provides
    public List<Level> provideGameLevels() {
        List<Level> levels = new ArrayList<>();
        levels.add(new Level(EASY, R.drawable.easy));
        levels.add(new Level(MEDIUM, R.drawable.medium));
        levels.add(new Level(HARD, R.drawable.hard));
        return levels;
    }

    @Provides
    @Singleton
    public MediaPlayer provideMediaPlayer(Context context) {
        return MediaPlayer.create(context, R.raw.click);
    }
}
