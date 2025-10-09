import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    @InjectMocks
    private Burger burger;
    private Database database;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private float bunPrice;
    private float ingredientPrice;
    private float expectedTotalPrice;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        database = new Database();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100f, 100f, 300f},   // Черная булочка (100) + котлета (100)
                {200f, 200f, 600f},   // Белая булочка (200) + динозавр (200)
                {300f, 300f, 900f}    // Красная булочка (300) + колбаса (300)
        });
    }

    public BurgerTest(float bunPrice, float ingredientPrice, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Test
    public void shouldCalculateTotalPrice() {
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        float totalPrice = burger.getPrice();
        assertEquals(expectedTotalPrice, totalPrice, 0.01);
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
        assertTrue(burger.ingredients.contains(mockIngredient));
    }

    @Test
    public void shouldRemoveIngredientByIndex() {
        when(mockIngredient.getName()).thenReturn("Dinosaur");
        when(mockIngredient.getPrice()).thenReturn(200f);

        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldIncludeBunNameInReceipt() {
        when(mockBun.getName()).thenReturn("Red Bun");
        when(mockBun.getPrice()).thenReturn(300f);
        when(mockIngredient.getName()).thenReturn("Chili Sauce");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(300f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Red Bun"));
    }
    @Test
    public void shouldIncludeIngredientNameInReceipt() {
        when(mockBun.getName()).thenReturn("Red Bun");
        when(mockBun.getPrice()).thenReturn(300f);
        when(mockIngredient.getName()).thenReturn("Chili Sauce");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(300f);
        burger.setBuns(mockBun);    burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("sauce Chili Sauce"));
    }
    @Test
    public void shouldIncludeTotalPriceInReceipt() {
        when(mockBun.getName()).thenReturn("Red Bun");
        when(mockBun.getPrice()).thenReturn(300f);
        when(mockIngredient.getName()).thenReturn("Chili Sauce");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(300f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 900,"));
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


