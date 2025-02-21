package com.example.baitap3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mainLayout;
    private Switch switchBackground;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.main_layout);
        switchBackground = findViewById(R.id.switchBackground);
        sharedPreferences = getSharedPreferences("WallpaperPrefs", MODE_PRIVATE);
        changeBackground();

        switchBackground.setOnCheckedChangeListener((buttonView, isChecked) -> {
            changeBackground();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void changeBackground() {
        int[] wallpapers = {
                R.drawable.wallpaper1,
                R.drawable.wallpaper2,
                R.drawable.wallpaper3,
                R.drawable.wallpaper4,
                R.drawable.wallpaper5
        };

        Random random = new Random();
        int lastIndex = sharedPreferences.getInt("last_index", -1);
        int newIndex;

        do {
            newIndex = random.nextInt(wallpapers.length);
        } while (newIndex == lastIndex);

        setBackground(newIndex);

        sharedPreferences.edit().putInt("last_index", newIndex).apply();
    }

    private void setBackground(int index) {
        int[] wallpapers = {
                R.drawable.wallpaper1,
                R.drawable.wallpaper2,
                R.drawable.wallpaper3,
                R.drawable.wallpaper4,
                R.drawable.wallpaper5
        };
        mainLayout.setBackgroundResource(wallpapers[index]);
    }
}