package me.ironexception.universalfriends.configuration;

import me.ironexception.universalfriends.Standard;
import me.ironexception.universalfriends.association.Association;
import me.ironexception.universalfriends.json.Bounds;
import me.ironexception.universalfriends.person.IPerson;

import java.awt.font.NumericShaper;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Operations {

    /**
     * Gives you a set of all players with that specific value.
     * WARNING: This should not be used to get all friends or something like that. It's only there for completeness
     *
     * @param configuration the config.
     * @param value that every player in the set should have.
     * @param <T>
     * @return all players that are saved as your enemy. No matter how hostile they are.
     */
    public static <T extends IPerson> Set<T> getByExactValue(final Configuration<T> configuration, final double value) {
        return filterMatchingPersons(configuration, t -> t.getValue() == value);
    }


    /**
     * Gives you a set of all players that fit the association. This means that when the association is friends eg
     * all players that are your friend get returned. It wouldn't make much sense to only return players with the value of 1 instead.
     *
     * @param configuration the config.
     * @param association the association that the set of players that get returned should fit.
     * @param <T>
     * @return all players that fit the association.
     */
    public static <T extends IPerson> Set<T> getByAssociation(final Configuration<T> configuration, final Association association) {
        if (association == Association.ALLY) return getFriends(configuration);
        if (association == Association.ENEMY) return getEnemies(configuration);
        return getByExactValue(configuration, association.getValue());
    }


    /**
     * Gives you a set of all your friends. No matter how friendly they are.
     *
     * @param configuration the config.
     * @param <T>
     * @return all players that are saved as your friends. No matter how friendly they are.
     */
    public static <T extends IPerson> Set<T> getFriends(final Configuration<T> configuration) {
        return filterMatchingPersons(configuration, t -> t.getValue() > Standard.STANDARD_NEUTRAL);
    }

    /**
     * Gives you a set of all your enemies. No matter how hostile they are.
     *
     * @param configuration the config.
     * @param <T>
     * @return all players that are saved as your enemy. No matter how hostile they are.
     */
    public static <T extends IPerson> Set<T> getEnemies(final Configuration<T> configuration) {
        return filterMatchingPersons(configuration, t -> t.getValue() < Standard.STANDARD_NEUTRAL);
    }

    /**
     * Gives you a set with all players that are within the range you provided.
     * Notice that when neutral (0) is within the range it can't return all players
     * (Because everyone has that value and you can't return a theoretically infinite set). Only those that are stored.
     *
     * @param configuration the config.
     * @param rangeLower the minimum friendliness value that can occur in the resulting set. It is including.
     * @param rangeUpper the maximum friendliness value that can occur in the resulting set. It is including.
     * @param <T>
     * @return an immutable set with all players that are within the range.
     */
    public static <T extends IPerson> Set<T> getInFriendlinessRange(Configuration<T> configuration, double rangeLower, double rangeUpper) {
        return filterMatchingPersons(configuration, t -> t.getValue() >= rangeLower && t.getValue() <= rangeUpper);
    }

    public static <T extends IPerson> Set<T> filterMatchingPersons(Configuration<T> configuration, Predicate<T> predicate) {
        return Collections.unmodifiableSet(configuration.getFriendList().stream().filter(predicate).collect(Collectors.toSet()));
    }

    /**
     * <p>
     *     Introduces a new {@link IPerson} to a {@link Configuration}, making sure the friendliness value bounds are satisfied.
     * </p>
     *
     * <p>
     *     If the friendliness value of the new {@link IPerson} exceeds the standardised bounds (minimum <code>-2</code> and maximum <code>2</code>), it will be scaled down to the nearest boundary.
     *     All other persons in this configuration will be scaled down by the same amount.
     * </p>
     *
     * For example, a friends list with the following friendliness values:
     * <ul>
     *     <li>1</li>
     *     <li>0</li>
     *     <li>-2</li>
     * </ul>
     * We introduce a new {@link IPerson} with a friendliness value of <code>4</code>:
     * <ul>
     *     <li>...</li>
     *     <li>4</li>
     * </ul>
     * Because <code>4</code> is out of bounds, it is brought down to the nearest boundary of value <code>2</code>. This represents a multiplication by <code>0.5</code>. All friendliness values within the friends list will be multiplied by that amount. We end up with a new list where all the proportions between friendliness values have stayed the same.
     * <ul>
     *     <li>0.5</li>
     *     <li>0</li>
     *     <li>-1</li>
     *     <li>2</li>
     * </ul>
     *
     * @param configuration The {@link Configuration} to mutate
     * @param person        The {@link IPerson} to add
     * @param <T>           The type of {@link IPerson} this configuration holds
     * @return              The mutated {@link Configuration}
     */
    public static <T extends IPerson> Configuration<T> introduceNewSafe(Configuration<T> configuration, T person) {
        Bounds bounds = configuration.getBounds();
        configuration.getFriendList().add(person);
        double value = person.getValue();
        double multiplier;
        if (value < bounds.getMinimum()) {
            multiplier = bounds.getMinimum() / value;
        } else if (value > bounds.getMaximum()) {
            multiplier = bounds.getMaximum() / value;
        } else {
            return configuration; // This value is within bounds, we do not need to (re)scale the configuration.
        }

        multiplyFriendlinessValues(configuration, multiplier);
        return configuration;
    }

    /**
     * Halves every friendliness value within a configuration
     * @see Operations#multiplyFriendlinessValues(Configuration, double)
     * @param configuration The {@link Configuration} to mutate
     * @param <T>           The type of {@link IPerson} this configuration holds
     * @return              The mutated {@link Configuration}
     */
    public static <T extends IPerson> Configuration<T> halveFriendlinessValues(Configuration<T> configuration) {
        return multiplyFriendlinessValues(configuration, 0.5d);
    }

    /**
     * Mutates a configuration, multiplying all person's friendliness values by a given multiplier.
     * @param configuration The {@link Configuration} to mutate
     * @param multiplier    The multiplier to multiply the friendliness values by
     * @param <T>           The type of {@link IPerson} this configuration holds
     * @return              The mutated {@link Configuration}
     */
    public static <T extends IPerson> Configuration<T> multiplyFriendlinessValues(Configuration<T> configuration, double multiplier) {
        consume(configuration, t -> {
            t.setValue(t.getValue() * multiplier);
        });
        return configuration;
    }

    public static <T extends IPerson> Configuration<T> multiplyFriendlinessValuesInRange(Configuration<T> configuration, double multiplier, double rangeLower, double rangeUpper) {
        consumeWithFilter(configuration, t -> !(t.getValue() < rangeLower || t.getValue() > rangeUpper), t -> {
            t.setValue(t.getValue() * multiplier);
        });
        return configuration;
    }

    private static <T extends IPerson> void consume(Configuration<T> configuration, Consumer<T> consumer) {
        configuration.getFriendList().forEach(consumer);
    }

    private static <T extends IPerson> void consumeWithFilter(Configuration<T> configuration, Predicate<T> predicate, Consumer<T> consumer) {
        configuration.getFriendList().stream().filter(predicate).forEach(consumer);
    }

}
