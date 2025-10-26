import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @InjectMocks
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private Database database;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        database = new Database();
    }

    @Test
    public void shouldSetBunProperly() {
        when(mockBun.getName()).thenReturn("Black Bun");
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void shouldAddIngredientCorrectly() {
        when(mockIngredient.getName()).thenReturn("Cutlet");
        when(mockIngredient.getPrice()).thenReturn(100f);
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void shouldRemoveIngredientByIndex() {
        when(mockIngredient.getName()).thenReturn("Dinosaur");
        when(mockIngredient.getPrice()).thenReturn(200f);
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }
    @Test
    public void shouldAddFirstIngredient() {
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(0));
        assertEquals("hot sauce", burger.ingredients.get(0).getName());
    }
    @Test
    public void shouldAddSecondIngredient() {
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        assertEquals("sour cream", burger.ingredients.get(1).getName());
    }
    @Test
    public void shouldAddThirdIngredient() {
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        assertEquals("chili sauce", burger.ingredients.get(2).getName());
    }
    @Test
    public void shouldMoveFirstIngredient() {
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        burger.moveIngredient(1, 0);
        assertEquals("sour cream", burger.ingredients.get(0).getName());
    }
    @Test
    public void shouldMoveSecondIngredient() {
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        burger.moveIngredient(1, 0);
        assertEquals("hot sauce", burger.ingredients.get(1).getName());
    }
    @Test
    public void shouldMoveThirdIngredient() {
        List<Ingredient> ingredients = database.availableIngredients();
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        burger.moveIngredient(1, 0);
        assertEquals("chili sauce", burger.ingredients.get(2).getName());
    }
}


