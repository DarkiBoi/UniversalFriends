package me.ironexception.universal.friends;


import java.util.UUID;

/**
 *
 * Makes it possible to test whether a player is a friend, enemy or something in between.
 * Therefor you have these methods:
 * - addFriend
 * - addEnemy
 * - setNeutral
 * - setValue
 *
 * and functions:
 * - isFriend
 * - isEnemy
 * - isNeutral
 * - getValue
 *
 *
 */
public class UniversalFriends {

    /**
     * // TODO
     */
    public UniversalFriends() {

    }

    /**
     * Saves that the player behind this name is your friend.
     * Behind the scenes this is saving that this name has the neutral friend value 1.
     *
     * @param name of the player you want to add as a friend.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures the player is added as a friend.
     */
    public void addFriend(final String name) {
        setValue(name, 1);
    }

    /**
     * Saves that the player behind this uuid is your friend.
     * Behind the scenes this is saving that this uuid has the neutral friend value 1.
     *
     * @param uuid of the player you want to add as a friend.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures the player is added as a friend.
     */
    public void addFriend(final UUID uuid) {
        setValue(uuid, 1);
    }


    /**
     * Saves that the player behind this name is your enemy.
     * Behind the scenes this is saving that this name has the neutral enemy value -1.
     *
     * @param name of the player you want to add as an enemy.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures the player is added as an enemy.
     */
    public void addEnemy(final String name) {
        setValue(name, -1);
    }

    /**
     * Saves that the player behind this uuid is your enemy.
     * Behind the scenes this is saving that this uuid has the neutral enemy value -1.
     *
     * @param uuid of the player you want to add as an enemy.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures the player is added as an enemy.
     */
    public void addEnemy(final UUID uuid) {
        setValue(uuid, -1);
    }


    /**
     * Saves that the player behind this name is neutral.
     * Behind the scenes this is saving that this name has the neutral value 0.
     *
     * @param name of the player you want to save as neutral.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures the player is added as an enemy.
     */
    public void setNeutral(final String name) {
        setValue(name, 0);
    }

    /**
     * Saves that the player behind this uuid is neutral.
     * Behind the scenes this is saving that this uuid has the neutral value 0.
     *
     * @param uuid of the player you want to save as neutral.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures the player is added as an enemy.
     */
    public void setNeutral(final UUID uuid) {
        setValue(uuid, 0);
    }


    /**
     * Saves the value that the player behind this name should have.
     *
     * @param name of the player you want to change the value of.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures the players value is changed.
     */
    public void setValue(final String name, final float friendValue) {
        // TODO implement
    }

    /**
     * Saves the value that the player behind this uuid should have.
     *
     * @param uuid of the player you want to change the value of.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures the players value is changed.
     */
    public void setValue(final UUID uuid, final float friendValue) {
        // TODO implement
    }


    /**
     * Returns whether the player behind the name is marked as your friend or not.
     *
     * @param name of the player you want to know is a friend.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player is a friend.
     */
    public boolean isFriend(final String name) {
        return getValue(name) > 0;
    }

    /**
     * Returns whether the player behind the uuid is marked as your friend or not.
     *
     * @param uuid of the player you want to know is a friend.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player is a friend.
     */
    public boolean isFriend(final UUID uuid) {
        return getValue(uuid) > 0;
    }


    /**
     * Returns whether the player behind the name is marked as your enemy or not.
     *
     * @param name of the player you want to know is an enemy.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player is a enemy.
     */
    public boolean isEnemy(final String name) {
        return getValue(name) < 0;
    }

    /**
     * Returns whether the player behind the uuid is marked as your enemy or not.
     *
     * @param uuid of the player you want to know is an enemy.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player is a enemy.
     */
    public boolean isEnemy(final UUID uuid) {
        return getValue(uuid) < 0;
    }


    /**
     * Returns whether the player behind the name is neutral.
     * (players that have no value assigned to are neutral by default)
     *
     * @param name of the player you want to know is neutral.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player is neutral.
     */
    public boolean isNeutral(final String name) {
        return getValue(name) == 0;
    }

    /**
     * Returns whether the player behind the uuid is neutral.
     * (players that have no value assigned to are neutral by default)
     *
     * @param uuid of the player you want to know is neutral.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player is neutral.
     */
    public boolean isNeutral(final UUID uuid) {
        return getValue(uuid) == 0;
    }


    /**
     * Returns the value of the player behind the name.
     *
     * @param name of the player you want to know the value of.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures that the value of the player is returned.
     */
    public float getValue(final String name) {
        // TODO implement
        return 0;
    }

    /**
     * Returns the value of the player behind the uuid.
     *
     * @param uuid of the player you want to know the value of.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures that the value of the player is returned.
     */
    public float getValue(final UUID uuid) {
        // TODO implement
        return 0;
    }


    /**
     * Returns whether the player behind the name has a value assigned to it.
     *
     * @param name of the player you want to know has a value.
     *
     * @requires that the inputted name exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player doesn't have a value assigned to it.
     */
    public boolean hasAValue(final String name) {
        // TODO implement
        return false;
    }

    /**
     * Returns whether the player behind the uuid has a value assigned to it.
     *
     * @param uuid of the player you want to know has a value.
     *
     * @requires that the inputted uuid exists and belongs to a minecraft player.
     *
     * @ensures that true is returned when the player doesn't have a value assigned to it.
     */
    public boolean hasAValue(final UUID uuid) {
        // TODO implement
        return false;
    }


}
