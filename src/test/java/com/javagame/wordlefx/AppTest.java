package com.javagame.wordlefx;

import com.javagame.wordlefx.controller.Controller;
import com.javagame.wordlefx.controller.ControllerImpl;
import com.javagame.wordlefx.model.Model;
import com.javagame.wordlefx.model.ModelImpl;
import javafx.scene.paint.Color;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class AppTest {
    @Test
    public void shouldTrue() {
        Assert.assertTrue(true);
    }

    /** Model Tests */
    @Test
    public void shouldAddLetter() {
        Model model = new ModelImpl();
        model.addLetter("A");
        Assert.assertEquals("A", model.getWord());
    }

    @Test
    public void shouldDelLetter() {
        Model model = new ModelImpl();
        model.addLetter("A");
        model.delLetter();
        Assert.assertEquals("", model.getWord());
    }

    @Test
    public void shouldGenerateWord() {
        Model model = new ModelImpl();
        Assert.assertEquals(5, model.getWord().length());
    }

    @Test
    public void shouldGetWord() {
        Model model = new ModelImpl();
        model.addLetter("A");
        model.addLetter("P");
        model.addLetter("P");
        model.addLetter("L");
        model.addLetter("E");
        Assert.assertEquals("APPLE", model.getWord());
    }

    @Test
    public void shouldGetCurrentGrid() {
        Model model = new ModelImpl();
        model.addLetter("H");
        model.addLetter("I");
        int[] expectedCurrent = new int[]{2, 0};
        Assert.assertEquals(expectedCurrent, model.getCurrentGrid());
    }

    /* Controller Tests */

    @Test
    public void shouldAddLetterController() {
        Controller controller = new ControllerImpl(new ModelImpl());
        controller.addLetter("A");
        Assert.assertEquals("A", controller.getWord());
    }

    @Test
    public void shouldDelLetterController() {
        Controller controller = new ControllerImpl(new ModelImpl());
        controller.addLetter("A");
        controller.delLetter();
        Assert.assertEquals("", controller.getWord());
    }

    @Test
    public void shouldSubmitSolution() {
        Model model = new ModelImpl();
        Controller controller = new ControllerImpl(model);
        model.addLetter("A");
        model.addLetter("P");
        model.addLetter("P");
        model.addLetter("L");
        model.addLetter("E");
        List<String> word = List.of("A", "P", "P", "L", "E");
        List<Color> expected = List.of(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN);
        List<Color> actual = controller.submitWord(word);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldSubmitIncorrect() {
        Model model = new ModelImpl();
        Controller controller = new ControllerImpl(model);
        model.addLetter("A");
        model.addLetter("P");
        model.addLetter("P");
        model.addLetter("L");
        model.addLetter("E");
        List<String> word = List.of("H", "E", "L", "L", "O");
        List<Color> expected = List.of(Color.DARKGRAY, Color.YELLOW, Color.YELLOW, Color.GREEN, Color.DARKGRAY);
        List<Color> actual = controller.submitWord(word);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetWordController() {
        Controller controller = new ControllerImpl(new ModelImpl());
        controller.addLetter("A");
        controller.addLetter("P");
        controller.addLetter("P");
        controller.addLetter("L");
        controller.addLetter("E");
        Assert.assertEquals("APPLE", controller.getWord());
    }

    @Test
    public void shouldGetCurrentGridController() {
        Controller controller = new ControllerImpl(new ModelImpl());
        controller.addLetter("H");
        controller.addLetter("I");
        int[] expectedCurrent = new int[]{2, 0};
        Assert.assertEquals(expectedCurrent, controller.getCurrentGrid());
    }
}
