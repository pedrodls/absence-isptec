package lib.isptec.listas;







import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Aires Veloso
 */
public class Listas
{

    /**
     *
     * @author Aires Veloso
     * @param lista
     */
//    public static Scanner scanner = new Scanner(System.in);
    public static int enviarLerOpcaoEscolhida(String lista[])
    {
        return enviarLerOpcaoEscolhida(Arrays.asList(lista));
    }

    public static int enviarLerOpcaoEscolhida(List<String> lista)
    {
        int i = 1;
        int sz = lista.size();

        for (String item : lista)
        {
            System.out.println(i++ + " - " + item);
        }

        return lerOpcaoEscolhida(sz, "");
    }

    public static List<Integer> enviarLerOpcoesEscolhidas(String lista[])
    {
        return enviarLerOpcoesEscolhidas(Arrays.asList(lista));
    }

    public static List<Integer> enviarLerOpcoesEscolhidas(List<String> lista)
    {
        int i = 1;
        int sz = lista.size();

        for (String item : lista)
        {
            System.out.println(i++ + " - " + item);
        }
        return lerOpcoesEscolhidas(sz, "");
    }

    public static boolean numeroValido(String numero)
    {
        numero = numero.trim();
        char ch;
        for (int i = 0; i < numero.length(); i++)
        {
            ch = numero.charAt(i);
            if (!Character.isDigit(ch))
            {
                return false;
            }
        }
        return true;
    }

    public static int lerOpcaoEscolhida(int sz, String msg)
    {
        String opcaoEscolhida;
        int opcao;
        Scanner scanner = new Scanner(System.in);
        if (msg != null)
        {
            msg = msg.trim();
        }

        String label = "Escolha uma das seguintes opcoes [1 a " + sz + "] ";
        if ((msg != null) && !msg.isEmpty())
        {
            label += (" [" + msg + "]: ");
        }
        else
        {
            label += ": ";
        }
        while (true)
        {
            System.out.println(label);
            try
            {
                opcaoEscolhida = scanner.next();

                if (!numeroValido(opcaoEscolhida))
                {
                    System.out.println("opcao \"" + opcaoEscolhida + "\" invalida.");
                    continue;
                }
                opcao = Integer.parseInt(opcaoEscolhida);
                if (opcao < 1 || opcao > sz)
                {
                    System.out.println("opcao \"" + opcaoEscolhida + "\" invalida.");
                }
                else
                {
                    break;
                }
            }
            catch (Exception ex)
            {
                System.out.println("opcao invalida.");
            }
        }
        return opcao;
    }

    public static List<Integer> lerOpcoesEscolhidas(int sz, String msg)
    {
        Scanner scanner = new Scanner(System.in);
        if (msg != null)
        {
            msg = msg.trim();
        }

        String label = "Escolha uma ou mais das seguintes opcoes [1 a " + sz + "] ";
        if ((msg != null) && !msg.isEmpty())
        {
            label += (" [" + msg + "]: ");
        }
        else
        {
            label += ": ";
        }

        List<Integer> opcoesEscolhidas = new ArrayList();
        boolean repetir = false;
//System.err.println("0: lerOpcoesEscolhidas(int)");
        while (true)
        {
            System.out.print(label);
            String numbers = scanner.nextLine();
            String opcoesEscolhidasStr[] = numbers.split(",");

            Integer opcao;

            for (String number : opcoesEscolhidasStr)
            {
                number = number.trim();
                try
                {
                    opcao = Integer.valueOf(number);
                    if (opcao < 1 || opcao > sz)
                    {
                        System.out.println("opcao \"" + opcao + "\" invalida.");
                        repetir = true;
                        break;
                    }
                    else
                    {
                        opcoesEscolhidas.add(opcao);
                    }
                }
                catch (NumberFormatException ex)
                {
                    System.err.println(number + " deve ser um numero");
                    repetir = true;
                    break;
                }
            }
            if (!repetir)
            {
                break;
            }
        }
        return opcoesEscolhidas;
    }

    public static String toString(List<Integer> lista)
    {
        String msg = "";
        boolean first = true;
        for (Integer num : lista)
        {
            if (!first)
            {
                msg += ", ";
            }
            msg += num;
            first = false;
        }
        return msg;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Menu Principal
        String items[]
            =
            {
                "Pessoas", "Localidades", "Profissoes"
            };
        List<Integer> opcoes = Listas.enviarLerOpcoesEscolhidas(items);
        System.out.println("Opcoes Escolhidas: " + toString(opcoes));
    }

}
