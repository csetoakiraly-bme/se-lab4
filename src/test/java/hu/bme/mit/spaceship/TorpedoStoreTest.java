package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Success_DecreasesTorpedoCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);

        // Act
        boolean result = store.fire(2);

        // Assert
        assertTrue(result);
        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_LastTorpedo_StoreBecomesEmpty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        store.fire(1);

        // Assert
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_Failure_TorpedoCountUnchanged() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertFalse(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_Zero_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2, 0.0);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }
}
