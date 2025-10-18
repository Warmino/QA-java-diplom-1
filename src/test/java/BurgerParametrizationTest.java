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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParametrizationTest{

    @InjectMocks
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private float bunPrice;
    private float ingredientPrice;
    private float expectedTotalPrice;

    public BurgerParametrizationTest(float bunPrice, float ingredientPrice, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100f, 100f, 300f},
                {200f, 200f, 600f},
                {300f, 300f, 900f}
        });
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
    public void shouldIncludeBunNameInReceipt() {
        when(mockBun.getName()).thenReturn("Red Bun");
        when(mockBun.getPrice()).thenReturn(300f);
        when(mockIngredient.getName()).thenReturn("Chili Sauce");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(300f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String rawReceipt = burger.getReceipt();

        String formattedReceipt = ReceiptFormatter.fixPriceFormatting(rawReceipt);
        String unifiedReceipt = ReceiptFormatter.unifyLineEndings(formattedReceipt);

        String expectedReceipt =
                "(==== Red Bun ====)\n" +
                        "= sauce Chili Sauce =\n" +
                        "(==== Red Bun ====)\n" +
                        "\n" +
                        "Price: 900,00\n";

        assertEquals(expectedReceipt.trim(), unifiedReceipt.trim());
    }

    @Test
    public void shouldIncludeIngredientNameInReceipt() {
        when(mockBun.getName()).thenReturn("Red Bun");
        when(mockBun.getPrice()).thenReturn(300f);
        when(mockIngredient.getName()).thenReturn("Chili Sauce");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(300f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String rawReceipt = burger.getReceipt();

        String formattedReceipt = ReceiptFormatter.fixPriceFormatting(rawReceipt);
        String unifiedReceipt = ReceiptFormatter.unifyLineEndings(formattedReceipt);

        String expectedReceipt =
                "(==== Red Bun ====)\n" +
                        "= sauce Chili Sauce =\n" +
                        "(==== Red Bun ====)\n" +
                        "\n" +
                        "Price: 900,00\n";

        assertEquals(expectedReceipt.trim(), unifiedReceipt.trim());
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

        String rawReceipt = burger.getReceipt();

        String formattedReceipt = ReceiptFormatter.fixPriceFormatting(rawReceipt);
        String unifiedReceipt = ReceiptFormatter.unifyLineEndings(formattedReceipt);

        String expectedReceipt =
                "(==== Red Bun ====)\n" +
                        "= sauce Chili Sauce =\n" +
                        "(==== Red Bun ====)\n" +
                        "\n" +
                        "Price: 900,00\n";

        assertEquals(expectedReceipt.trim(), unifiedReceipt.trim());
    }

}
