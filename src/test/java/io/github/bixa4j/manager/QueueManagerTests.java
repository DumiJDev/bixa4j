package io.github.bixa4j.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QueueManagerTests {

    @Test
    void shouldBeSameInstance() {
        var manager = QueueManager.getManager();
        var manager2 = QueueManager.getManager();

        Assertions.assertEquals(manager, manager2);

    }
}
