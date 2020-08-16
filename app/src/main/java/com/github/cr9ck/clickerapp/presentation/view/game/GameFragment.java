package com.github.cr9ck.clickerapp.presentation.view.game;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.github.cr9ck.clickerapp.R;
import com.github.cr9ck.clickerapp.databinding.FragmentGameBinding;
import com.github.cr9ck.clickerapp.presentation.view.game.levels.EmojiField;
import com.github.cr9ck.clickerapp.presentation.view.game.levels.LevelsAdapter;
import com.github.cr9ck.clickerapp.presentation.viewmodel.GameViewModel;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class GameFragment extends DaggerFragment implements EmojiField.OnItemClickListener {

    private FragmentGameBinding binding;
    private GameViewModel viewModel;
    private ViewModelProvider.Factory factory;
    private MediaPlayer mp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(GameViewModel.class);
        initListeners();
        initViewPager();
        initObservers();
    }

    @Override
    public void onItemPressed() {
        viewModel.onItemPressed();
        mp.start();
    }

    @Inject
    public void setFactory(ViewModelProvider.Factory factory) {
        this.factory = factory;
    }

    @Inject
    public void setMediaPlayer(MediaPlayer mp) {
        this.mp = mp;
    }

    private void initListeners() {
        binding.levelNext.setOnClickListener(v -> selectNextLevel());
        binding.levelPrev.setOnClickListener(v -> selectPreviousLevel());
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                boolean isFirstItem = false;
                if (position == 0) {
                    isFirstItem = true;
                }

                boolean isLastItem = false;
                if (position == getAdapter().getItemCount() - 1) {
                    isLastItem = true;
                }

                binding.levelPrev.setVisibility(isFirstItem ? View.GONE : View.VISIBLE);
                binding.levelNext.setVisibility(isLastItem ? View.GONE : View.VISIBLE);
                viewModel.selectLevel(getAdapter().getLevel(position));
            }
        });
    }

    private void selectPreviousLevel() {
        int currentItem = binding.viewPager.getCurrentItem();
        if (currentItem == 0) return;
        viewModel.selectLevel(getAdapter().getLevel(currentItem - 1));
    }

    private void selectNextLevel() {
        int currentItem = binding.viewPager.getCurrentItem();
        if (currentItem == getAdapter().getItemCount() - 1) return;
        viewModel.selectLevel(getAdapter().getLevel(currentItem + 1));
    }

    private void initViewPager() {
        binding.viewPager.setAdapter(new LevelsAdapter(this));
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setClipChildren(false);
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void initObservers() {
        viewModel.levelsLD.observe(getViewLifecycleOwner(), levelsData -> {
            getAdapter().setLevels(levelsData);
            getAdapter().notifyDataSetChanged();
        });
        viewModel.selectedLevelPosLD.observe(getViewLifecycleOwner(), level -> {
            int itemPosition = getAdapter().getLevelPosition(level);
            binding.viewPager.setCurrentItem(itemPosition, true);
            binding.time.setText(String.valueOf(level.getLevelDifficulty().getTimeToFinish()));
            binding.scoreGoal.setText(getString(R.string.timer_goal, level.getLevelDifficulty().getGoal()));
        });
        viewModel.timerValueLD.observe(getViewLifecycleOwner(), time -> binding.time.setText(String.valueOf(time)));
        viewModel.scoreLD.observe(getViewLifecycleOwner(), score -> binding.score.setText(String.valueOf(score)));
        viewModel.gameStatusLD.observe(getViewLifecycleOwner(), this::handleGameStatus);
    }

    private void handleGameStatus(GameViewModel.GameStatus gameStatus) {
        switch (gameStatus) {
            case INITIAL:
                showDialog(R.string.explanation);
                viewModel.restartGame();
                break;
            case WIN:
                showDialog(R.string.you_win);
                break;
            case LOSE:
                showDialog(R.string.you_lose);
                break;
        }
    }

    private void showDialog(@StringRes int textResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext())
                .setMessage(textResId)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    viewModel.restartGame();
                });
        builder.create().show();
    }

    private @NonNull
    LevelsAdapter getAdapter() {
        return (LevelsAdapter) Objects.requireNonNull(binding.viewPager.getAdapter());
    }
}
