package controller.constants;

import model.entities.Profile;

public enum EntityConstants {
    EPSILON_HEALTH, SHOTS_PER_SECOND, SKILL_COOLDOWN_IN_SECONDS, COLLECTIBLE_LIFE_TIME, EPSILON_RAPID_SHOOTING_DELAY, EPSILON_SHOOTING_RAPIDITY,
    BABY_EPSILON_RADIUS,
    TRIGORATH_HEALTH, TRIGORATH_MELEE_DAMAGE,
    ARCHMIRE_HEALTH, ARCHMIRE_DROWN_DAMAGE, ARCHMIRE_AOE_DAMAGE, ARCHMIRE_NUM_OF_COLLECTIBLES, ARCHMIRE_COLLECTIBLES_XP,
    BABY_ARCHMIRE_HEALTH, BABY_ARCHMIRE_DROWN_DAMAGE, BABY_ARCHMIRE_AOE_DAMAGE, BABY_ARCHMIRE_NUM_OF_COLLECTIBLES, BABY_ARCHMIRE_COLLECTIBLES_XP,
    NECROPICK_HEALTH, NECROPICK_NUM_OF_COLLECTIBLES, NECROPICK_COLLECTIBLES_XP,
    OMENOCT_HEALTH, OMENOCT_NUM_OF_COLLECTIBLES, OMENOCT_COLLECTIBLES_XP,
    ORB_HEALTH, ORB_NUM_OF_COLLECTIBLES, ORB_COLLECTIBLES_XP,
    WYRM_HEALTH, WYRM_NUM_OF_COLLECTIBLES, WYRM_COLLECTIBLES_XP,
    SQUARANTINE_HEALTH, SQUARANTINE_MELEE_DAMAGE, BULLET_HEALTH, COLLECTIBLE_HEALTH,
    SMILEY_AOE_RADIUS, SMILEY_AOE_ACTIVATION_TIME, SMILEY_AOE_ACTIVATED_LIFETIME, SMILEY_AOE_RECTANGLE_LENGTH, SMILEY_AOE_COOLDOWN;
    
    public static final double ARCHMIRE_SPEED = 1;
    public static final double OMENOCT_NORMAL_SPEED = 1;
    public static final double OMENOCT_PANEL_SPEED = 2;
    public static final double NECROPICK_MIN_RADIUS = 100;
    public static final double NECROPICK_MAX_RADIUS = 200;

    public static final int HOVER_DURATION = 4; // 4 seconds in milliseconds
    public static final int NON_HOVER_DURATION = 4; // 8 seconds in milliseconds
    public static final int OMENOCT_SHOT_DELAY = 1; // 8 seconds in milliseconds
    public static final int ORB_PANEL_CREATION_DELAY = 1; // 8 seconds in milliseconds




    public int getValue() {
        return switch (this) {
            case EPSILON_SHOOTING_RAPIDITY -> Profile.getCurrent().EPSILON_SHOOTING_RAPIDITY;
            case EPSILON_HEALTH -> 100;
            case SHOTS_PER_SECOND -> 2;
            case SKILL_COOLDOWN_IN_SECONDS -> 300;
            case EPSILON_RAPID_SHOOTING_DELAY -> 50;
            case BABY_EPSILON_RADIUS -> 5;
            case TRIGORATH_HEALTH -> 15;
            case TRIGORATH_MELEE_DAMAGE -> 10;
            case ARCHMIRE_HEALTH -> 30;
            case ARCHMIRE_DROWN_DAMAGE -> 10;
            case ARCHMIRE_AOE_DAMAGE -> 2;
            case ARCHMIRE_NUM_OF_COLLECTIBLES -> 5;
            case ARCHMIRE_COLLECTIBLES_XP -> 6;
            case BABY_ARCHMIRE_HEALTH -> 15;
            case BABY_ARCHMIRE_DROWN_DAMAGE -> 5;
            case BABY_ARCHMIRE_AOE_DAMAGE -> 1;
            case BABY_ARCHMIRE_NUM_OF_COLLECTIBLES -> 2;
            case BABY_ARCHMIRE_COLLECTIBLES_XP -> 3;
            case NECROPICK_HEALTH -> 10;
            case NECROPICK_NUM_OF_COLLECTIBLES -> 4;
            case NECROPICK_COLLECTIBLES_XP -> 2;
            case OMENOCT_HEALTH -> 20;
            case OMENOCT_NUM_OF_COLLECTIBLES -> 8;
            case OMENOCT_COLLECTIBLES_XP -> 4;
            case ORB_HEALTH -> 30;
            case ORB_NUM_OF_COLLECTIBLES -> 5;
            case ORB_COLLECTIBLES_XP -> 30;
            case WYRM_HEALTH -> 12;
            case WYRM_NUM_OF_COLLECTIBLES -> 2;
            case WYRM_COLLECTIBLES_XP -> 8;
            case SQUARANTINE_HEALTH -> 10;
            case SQUARANTINE_MELEE_DAMAGE -> 6;
            case BULLET_HEALTH, COLLECTIBLE_HEALTH -> 0;
            case COLLECTIBLE_LIFE_TIME -> 8;
            case SMILEY_AOE_RADIUS -> 100;
            case SMILEY_AOE_ACTIVATION_TIME -> 3;
            case SMILEY_AOE_ACTIVATED_LIFETIME -> 8;
            case SMILEY_AOE_RECTANGLE_LENGTH -> 300;
            case SMILEY_AOE_COOLDOWN -> 1;
        };
    }




}
