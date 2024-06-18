package utils;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatValidator {

    // Método para validar a string com base em uma expressão regular
    public static boolean validarFormato(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean academicYear(String value) {

        String formato = "\\d{4}/\\d{2}\\d{2}";
        
        if (!validarFormato(value, formato)) {
            System.out.println("Erro na entrada do ano lectivo, tente novamente!");
            MainMenu.pauseToSee();
        }
        
        return validarFormato(value, formato);
    }
}
