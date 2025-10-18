public class ReceiptFormatter {


    public static String fixPriceFormatting(String input) {

        int index = input.indexOf("Price: ");
        if (index >= 0 && index + 7 <= input.length()) { // длина "Price: "
            String originalPrice = input.substring(index + 7).trim();
            String fixedPrice = originalPrice.replaceAll(",0+$", ",00"); // сохраняем только первые два нуля
            return input.replace(originalPrice, fixedPrice);
        }
        return input;
    }

    public static String unifyLineEndings(String input) {
        return input.replaceAll("\r\n|\r", "\n");
    }
}