package model.entities;

//import model.entities.Skill;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Profile {
    private static Profile current = new Profile();
    public CopyOnWriteArrayList<Integer> WAVE_ENEMY_COUNT = new CopyOnWriteArrayList<>(List.of(2));
    public int UPS = 70;
    public int FPS = 70;
    public int EPSILON_RADIUS = 20; // not sure
    public int EPSILON_MELEE_DAMAGE = 10;
    public int EPSILON_RANGED_DAMAGE = 5;
    public int EPSILON_SHOOTING_RAPIDITY = 1;
    public double PANEL_SHRINKAGE_COEFFICIENT = 0.125;
    public String activeSkillSaveName = "";
    public CopyOnWriteArrayList<String> acquiredSkillsNames = new CopyOnWriteArrayList<>();
    public int totalXP = 0;
    public int currentGameXP = 0;

    public static Profile getCurrent() {
        return current;
    }

    public static void setCurrent(Profile current) {
        Profile.current = current;
    }

    public void updateINSTANCE() {
//        this.activeSkillSaveName = Skill.activeSkill != null ? Skill.activeSkill.getName() : "";
        this.acquiredSkillsNames.clear();
    }

    public void saveXP() {
        totalXP += Profile.getCurrent().currentGameXP;
        currentGameXP = 0;
    }
}
