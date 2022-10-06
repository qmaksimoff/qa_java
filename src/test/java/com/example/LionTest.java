package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionTest {

    private final String checkedSex;
    private final boolean expectedHasMane;
    private Lion lion;

    public LionTest(String checkedSex, boolean expectedHasMane) {
        this.checkedSex = checkedSex;
        this.expectedHasMane = expectedHasMane;
    }

    @Parameterized.Parameters
    public static Object[][] getLionData() {
        return new Object[][]{
                {"Самец", true},
                {"Самка", false},
        };
    }

    @Test
    public void doesHaveManeForCorrectValueShowTrueOrFalse() throws Exception {
        Feline feline = new Feline();
        Lion lion = new Lion(checkedSex, feline);
        boolean actualHasMane = lion.doesHaveMane();
        assertEquals("Метод doesHaveMane() в классе Lion не может определить есть ли грива."
                , expectedHasMane, actualHasMane);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    Feline feline;

    @Test
    public void getKittens() throws Exception {
        Lion lion = new Lion("Самка", feline);
        Mockito.when(feline.getKittens()).thenReturn(1);
        assertEquals("Ошибка, метод возвращает не верное значение", 1, lion.getKittens());
    }

    @Test
    public void getFood() throws Exception {
        Feline feline = new Feline();
        Lion lion = new Lion("Самец", feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals("Списки не совпадают", expectedFood, lion.getFood());
    }

    @Test(expected = Exception.class)
    public void constructorWrongLionSexReturnsException() {
        try {
            lion = new Lion("Undefined", feline);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}