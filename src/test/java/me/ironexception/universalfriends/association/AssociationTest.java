package me.ironexception.universalfriends.association;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssociationTest {

    @Test
    @DisplayName("Grab associations by value")
    void byValue() {
        assertEquals(Association.ALLY, Association.byValue(1), "ByValue works for allies");
        assertEquals(Association.NEUTRAL, Association.byValue(0), "ByValue works for neutrals");
        assertEquals(Association.ENEMY, Association.byValue(-1), "ByValue works for enemies");
    }

    @Test
    void closestToValue() {
        final double[] convergeToEnemy = new double[] {
                //Double.MIN_VALUE,
                -2,
                -1,
                -0.5
        };

        final double[] convergeToNeutral = new double[] {
                -0.4,
                0,
                0.4
        };

        final double[] convergeToAlly = new double[] {
                0.5,
                1,
                2,
                //Double.MAX_VALUE
        };

        for (double v : convergeToEnemy) {
            assertEquals(Association.ENEMY, Association.closestToValue(v), v + " is closest to ENEMY");
        }

        for (double v : convergeToNeutral) {
            assertEquals(Association.NEUTRAL, Association.closestToValue(v), v + " is closest to NEUTRAL");
        }

        for (double v : convergeToAlly) {
            assertEquals(Association.ALLY, Association.closestToValue(v), v + " is closest to ALLY");
        }
    }
}