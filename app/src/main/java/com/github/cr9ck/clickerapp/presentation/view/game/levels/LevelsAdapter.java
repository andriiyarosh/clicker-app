package com.github.cr9ck.clickerapp.presentation.view.game.levels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.cr9ck.clickerapp.R;

import java.util.ArrayList;
import java.util.List;

public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.LevelViewHolder> {

    private List<Level> levels = new ArrayList<>();
    private EmojiField.OnItemClickListener listener;

    public LevelsAdapter(EmojiField.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level, parent, false);
        return new LevelViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        holder.bind(levels.get(position));
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public Level getLevel(int position) {
        return levels.get(position);
    }

    public int getLevelPosition(Level level) {
        return levels.indexOf(level);
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public static class LevelViewHolder extends RecyclerView.ViewHolder {

        private EmojiField.OnItemClickListener listener;

        public LevelViewHolder(@NonNull View itemView, EmojiField.OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;
        }

        public void bind(Level level) {
            ((EmojiField)itemView.findViewById(R.id.emojiField)).initLevel(level);
            itemView.findViewById(R.id.emojiField).setOnClickListener(v-> listener.onItemPressed());
        }
    }
}
