package me.worldinc1988.streak.data;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StreakData {
    private Map<String, Integer> streaks;
    private final String filePath = "streaks.yaml";
    
    public StreakData() {
        streaks = new HashMap<>();
        load();
    }
    
    public int getStreak(String player) {
        return streaks.getOrDefault(player, 0);
    }
    
    public void setStreak(String player, int streak) {
        streaks.put(player, streak);
        save();
    }
    
    public void incrementStreak(String player) {
        int currentStreak = getStreak(player);
        setStreak(player, currentStreak + 1);
    }
    
    public void resetStreak(String player) {
        setStreak(player, 0);
    }
    
    private void load() {
        Yaml yaml = new Yaml();
        try {
            if (Files.exists(Paths.get(filePath))) {
                streaks = yaml.load(Files.newInputStream(Paths.get(filePath)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void save() {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(new File(filePath))) {
            yaml.dump(streaks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}