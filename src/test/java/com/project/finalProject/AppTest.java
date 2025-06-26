package com.project.finalProject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testAddExpense() {
        App app = new App();
        app.addExpense("Lunch", 250.0);
        assertEquals(1, app.getCount());
        assertEquals("Lunch", app.getExpenses().get(0).description);
        assertEquals(250.0, app.getExpenses().get(0).amount);
    }

    @Test
    public void testGetTotal() {
        App app = new App();
        app.addExpense("Lunch", 200);
        app.addExpense("Transport", 100);
        assertEquals(300.0, app.getTotal(), 0.01);
    }

    @Test
    public void testDeleteExpense() {
        App app = new App();
        app.addExpense("Tea", 10);
        assertTrue(app.deleteExpense(0));
        assertEquals(0, app.getCount());
    }

    @Test
    public void testDeleteInvalidIndex() {
        App app = new App();
        app.addExpense("Coffee", 40);
        assertFalse(app.deleteExpense(5));
        assertEquals(1, app.getCount());
    }
}
