package lib.isptec.localidades;








import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import lib.isptec.listas.Listas;
import lib.isptec.utils.ListUtils;



/**
 *
 * @author Aires Veloso
 */

public class Localidades
{

    public static String INDEFINIDO = "Indefinido";
    public static String INDEFINIDA = "Indefinida";
    public static int ANGOLA_CODIGO = 6;

    public static Localidades localidadesStatic = new Localidades();
    public static List<Localidade> localidadeListStatic
        = localidadesStatic.getLocalidadesList();

    private List<Localidade> localidadesList;
    private HashMap<Integer, Localidade> localidadesMap;

    public static List<Localidade> getContinentes()
    {
        List<Localidade> continentes = getFilhos(0);
        sort(continentes);
        return continentes;
    }

    public static List<Localidade> getPaises()
    {
        List<Localidade> continentes = getFilhos(0);
        List<Localidade> paises = new ArrayList();
        Localidade indefinido = null;

        for (Localidade continente : continentes)
        {
            paises.addAll(getFilhos(continente.getCodigo()));
        }
        sort(paises);
        colocarLocalidadeIndefinidaNoFim(paises);
        return paises;
    }

    public static Localidade getPais(String nome)
    {
        List<Localidade> continentes = getFilhos(0);
        List<Localidade> paisesDeContinente = new ArrayList();

        for (Localidade continente : continentes)
        {
            paisesDeContinente = getFilhos(continente.getCodigo());
            for (Localidade pais : paisesDeContinente)
            {
                if (pais.getNome().equalsIgnoreCase(nome))
                    return pais;
            }
        }
        return null;
    }
    
    public static List<Localidade> colocarLocalidadeIndefinidaNoFim(
        List<Localidade> lista)
    {
        Localidade indefinida = null;

        for (Localidade loc : lista)
        {
            if (loc.getNome().equals(INDEFINIDO)
                || loc.getNome().equals(INDEFINIDA))
            {
                indefinida = loc;
            }
            lista.remove(loc);
        }
        if (indefinida != null)
        {
            lista.add(indefinida);
        }
        return lista;
    }

    public static Localidade getFilho(Localidade loc, String nome)
    {
        List<Localidade> filhos = getFilhos(loc.getCodigo());
        for (Localidade l : filhos)
        {
            if (l.getNome().equalsIgnoreCase(nome))
                return l;
        }
        return null;
    }
    
    public static List<Localidade> getFilhos(int codigo)
    {
        List<Localidade> lista = new ArrayList();
        Localidade indefinido = null;
        for (Localidade loc : localidadeListStatic)
        {
            if (loc.getLocalidadePai() == codigo)
            {
                if (loc.getNome().equals(INDEFINIDO)
                    || loc.getNome().equals(INDEFINIDA))
                {
                    indefinido = loc;
                }
                else
                {
                    lista.add(loc);
                }
            }
        }
        sort(lista);
        if (indefinido != null)
        {
            lista.add(indefinido);
        }
        return lista;
    }

    public static boolean ehDescendenteDE(Localidade loc, int codAscendente)
    {
        if (codAscendente == 0)
        {
            return true;
        }
        int codPai;
        while (true)
        {
            if (loc.getLocalidadePai() == codAscendente)
            {
                return true;
            }
            codPai = loc.getLocalidadePai();
            if (codPai == 0)
            {
                return false;
            }
            loc = Localidades.getLocalidade(codPai);
        }
    }

    public static Localidade getFilhoIndefinido(int codigo)
    {
        for (Localidade loc : localidadeListStatic)
        {
            if (loc.getLocalidadePai() == codigo)
            {
                if (loc.getNome().equals(INDEFINIDO)
                    || loc.getNome().equals(INDEFINIDA))
                {
                    return loc;
                }
            }
        }
        return null;
    }

    public static Localidade getLocalidade(Integer codigo)
    {
        return localidadesStatic.getLocalidadesMap().get(codigo);
    }

    public static Localidade geLocalidadePaisAngola()
    {
        // provincia de Luanda
        return getPais("Angola");
    }

    public static Localidade geLocalidadeProvinciaDefaultDeAngola()
    {
        int codigoAngola = geLocalidadePaisAngola().getCodigo();
        return getFilhoIndefinido(codigoAngola);
    }

    public static Localidade getLocalidadeProvinciaLuanda(String nome)
    {
        Localidade paisAngola = getPais("Angola");
        // provincia de Luanda
        return getFilho(paisAngola, nome);
    }
    
    public static Localidade getFilhoPadrao(Localidade loc)
    {
        return getFilhoIndefinido(loc.getCodigo());
    }

    public static Localidade getLocalidadeMunicipioLuanda()
    {
        Localidade provLuanda = getLocalidadeProvinciaLuanda("Luanda");
       
        return getFilho(provLuanda, "Luanda");
    }

    public static Localidade getLocalidadeMunicipioIndefinidoDeLuanda()
    {
       Localidade provLuanda = getLocalidadeProvinciaLuanda("Luanda");
       
        return getFilhoIndefinido(provLuanda.getCodigo());
    }

    public static long getNumeroLocalidades()
    {
        return localidadesStatic.localidadesList.size();
    }

    // retorna codigo da localidade selecionada
    public static int selecionarUmFilho(int codigo, String msg)
    {
        List<Localidade> filhos = getFilhos(codigo);

        return enviarLerOpcaoEscolhida(filhos, msg);
    }

    // retorna Lista de codigos da localidades selecionadas
    public static List<Integer> selecionarFilhos(int codigo)
    {
        List<Localidade> filhos = getFilhos(codigo);
        return enviarLerOpcoesEscolhidas(filhos);
    }

    // retorna Lista de codigos da localidades selecionadas
    public static List<Integer> enviarLerOpcoesEscolhidas(List<Localidade> lista)
    {
        int i = 1;

        for (Localidade item : lista)
        {
            System.out.println(i++ + " - " + item);
        }
        return lerOpcoesEscolhidas(lista);
    }

    // retorna codigo da localidade selecionada
    public static int enviarLerOpcaoEscolhida(List<Localidade> lista, String msg)
    {
        int i = 1;

        for (Localidade item : lista)
        {
            System.out.println(i++ + " - " + item.getNome());
        }

        return lerOpcaoEscolhida(lista, msg);
    }

    // retorna codigo da localidade selecionada
    public static int lerOpcaoEscolhida(List<Localidade> lista, String msg)
    {
        int opcao = Listas.lerOpcaoEscolhida(lista.size(), msg);
        return lista.get(opcao - 1).getCodigo();
    }

    // retorna Lista de codigos da localidades selecionadas
    public static List<Integer> lerOpcoesEscolhidas(List<Localidade> lista)
    {
        Scanner scanner = new Scanner(System.in);
        List<Integer> opcoesEscolhidas = new ArrayList();
        int sz = lista.size();
        boolean repetir = false;
//System.err.println("0: lerOpcoesEscolhidas(int)");
        while (true)
        {
            System.out.print("Escolha uma ou mais das seguintes opcoes [1 a "
                + sz + "]: ");
            String numbers = scanner.nextLine();
            String opcoesEscolhidasStr[] = numbers.split(",");

            Integer opcao;
            Integer codigoOpcao;

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
                        codigoOpcao = lista.get(opcao - 1).getCodigo();
                        if (!ListUtils.includes(opcoesEscolhidas, codigoOpcao))
                        {
                            opcoesEscolhidas.add(codigoOpcao);
                        }
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

    public static Localidade selecionarMunicipioAposSelecionarProvinciaDeAngola(
        String msg)
    {
        /*
            provincia = lerProvinciaAngola()
            lerMunicipios(provincia)
            retornar municipioSelecionado
         */
        int provinciaCodigo, municipioCodigo;
        String label = "Selecione uma das provincias [" + msg + "]: ";
        if (msg != null)
        {
            msg = msg.trim();
        }

        if ((msg != null) && !msg.isEmpty())
        {
            label += msg;
        } 
        System.out.println(label);
        provinciaCodigo = selecionarUmFilho(ANGOLA_CODIGO, msg);

        System.out.println("Selecione um dos municipios da provincia " +
            Localidades.getLocalidade(provinciaCodigo).getNome() + 
            "[" + msg + "]: ");
        municipioCodigo = selecionarUmFilho( provinciaCodigo, msg);

        return Localidades.getLocalidade(municipioCodigo);
    }

    public static List<Integer> selecionarMunicipiosAposSelecionarProvinciaDeAngola(String msg)
    {
        /*
            provincia = lerProvinciaAngola()
            lerMunicipios(provincia)
            retornar municipioSelecionado
         */
        int provinciaCodigo;

        String label = "Selecione uma das provincias";
        if (msg != null)
        {
            msg = msg.trim();
        }

        if ((msg != null) && !msg.isEmpty())
        {
            label +=  (" [" + msg + "]: ");
        }
        else
            label += ": ";
        System.out.println(label);
        provinciaCodigo = selecionarUmFilho(ANGOLA_CODIGO, msg);

        System.out.println("Selecione um ou mais municipios da provincia de "
            + getLocalidade(provinciaCodigo).getNome() +
            (((msg != null) && !msg.isEmpty()) ? (" [ " + msg + "]: "): ": " ));
        return selecionarFilhos(provinciaCodigo);
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

    private Localidades()
    {
        localidadesList = Arrays.asList(
            // continentes
            new Localidade(1, "Africa", 0),
            new Localidade(2, "America", 0),
            new Localidade(3, "Europa", 0),
            new Localidade(4, "Asia", 0),
            // continente indefinido
            new Localidade(5, "Indefinido", 0),
            // paises
            new Localidade(6, "Angola", 1),
            new Localidade(7, "Mocambique", 1),
            new Localidade(8, "Africa do Sul", 1),
            new Localidade(9, "Nigeria", 1),
            new Localidade(10, "Brasil", 2),
            // provincias de Angola
            new Localidade(11, "Luanda", 6),
            new Localidade(12, "Cabinda", 6),
            new Localidade(13, "Zaire", 6),
            new Localidade(14, "Uige", 6),
            new Localidade(15, "Lunda-Norte", 6),
            new Localidade(16, "Lunda-Sul", 6),
            new Localidade(17, "Malange", 6),
            new Localidade(18, "Bengo", 6),
            new Localidade(19, "Kwanza-Norte", 6),
            new Localidade(20, "Kwanza-Sul", 6),
            new Localidade(21, "Huambo", 6),
            new Localidade(22, "Bie", 6),
            new Localidade(23, "Moxico", 6),
            new Localidade(24, "Kuando Kubango", 6),
            new Localidade(25, "Benguela", 6),
            new Localidade(26, "Huila", 6),
            new Localidade(27, "Cunene", 6),
            new Localidade(28, "Namibe", 6),
            // provincia indefinida de Angola
            new Localidade(29, "Indefinida", 6),
            /*
                Municios de Luanda:
                Belas, Cacuaco, Cazenga, Icolo e Bengo,
                Luanda, Kissama, Kilamba Kiaxi, Talatona, Viana
             */
            new Localidade(30, "Belas", 11),
            new Localidade(31, "Cacuaco", 11),
            new Localidade(32, "Cazenga", 11),
            new Localidade(33, "Icolo e Bengo", 11),
            new Localidade(34, "Luanda", 11),
            new Localidade(35, "Kissama", 11),
            new Localidade(36, "Kilamba Kiaxi", 11),
            new Localidade(37, "Talatona", 11),
            new Localidade(38, "Viana", 11),
            // municipio indefinido da provincia de Luanda
            new Localidade(39, "Indefinido", 11),
            // pais indefinido de Africa
            new Localidade(40, "Indefinido", 1),
            // pais indefinido da America
            new Localidade(41, "Indefinido", 2),
            // pais indefinido da Europa
            new Localidade(42, "Indefinido", 3),
            // pais indefinido da Asia
            new Localidade(43, "Indefinido", 4),
            // provincia indefinida de Mocambique
            new Localidade(44, "Indefinida", 7),
            // provincia indefinida de Africa do Sul
            new Localidade(45, "Indefinida", 8),
            // estado indefinido de Nigeria
            new Localidade(46, "Indefinido", 9),
            // estado indefinido de Brasil
            new Localidade(47, "Indefinido", 10),
            // municipio indefinidos da provincia de Cabinda
            new Localidade(48, "Indefinido", 12),
            // municipio indefinidos da provincia de Zaire
            new Localidade(49, "Indefinido", 13),
            // municipio indefinidos da provincia de Uige
            new Localidade(50, "Indefinido", 14),
            // municipio indefinidos da provincia de Lunda-Norte
            new Localidade(51, "Indefinido", 15),
            // municipio indefinidos da provincia de Lunda-Sul
            new Localidade(52, "Indefinido", 16),
            // municipio indefinidos da provincia de Malange
            new Localidade(53, "Indefinido", 17),
            // municipio indefinidos da provincia de Bengo
            new Localidade(54, "Indefinido", 18),
            // municipio indefinidos da provincia de Kwanza-Norte
            new Localidade(55, "Indefinido", 19),
            // municipio indefinidos da provincia de Kwanza-Sul
            new Localidade(56, "Indefinido", 20),
            // municipio indefinidos da provincia de Huambo
            new Localidade(57, "Indefinido", 21),
            // municipio indefinidos da provincia de Bie
            new Localidade(58, "Indefinido", 22),
            // municipio indefinidos da provincia de Moxico
            new Localidade(59, "Indefinido", 23),
            // municipio indefinidos da provincia de Kuando Kubango
            new Localidade(60, "Indefinido", 24),
            // municipio indefinidos da provincia de Benguela
            new Localidade(61, "Indefinido", 25),
            // municipio indefinidos da provincia de Huila
            new Localidade(62, "Indefinido", 26),
            // municipio indefinidos da provincia de Cunene
            new Localidade(63, "Indefinido", 27),
            // municipio indefinidos da provincia de Namibe
            new Localidade(64, "Indefinido", 28)
        );
        localidadesMap = new HashMap<>();
        for (Localidade localidade : this.localidadesList)
        {
            localidadesMap.put(localidade.getCodigo(), localidade);
        }
    }

    public static void sort(List<Localidade> lista)
    {
        Collections.sort(lista, new CompareLocalidades());
    }

    public static class CompareLocalidades implements Comparator
    {

        @Override
        public int compare(Object o1, Object o2)
        {
            Localidade s1 = (Localidade) o1;
            Localidade s2 = (Localidade) o2;
            return s1.getNome().compareToIgnoreCase(s2.getNome());
        }
    }

    @Override
    public String toString()
    {
        String msg = "";
        boolean first = true;

        for (Localidade loc : this.localidadesList)
        {
            if (!first)
            {
                msg += ", ";
            }
            else
            {
                msg += "Localidades{\n";
            }
            first = false;
            msg += loc;
        }
        msg += "\n}";
        return msg;
    }

    // Getters and Setters
    private List<Localidade> getLocalidadesList()
    {
        return localidadesList;
    }

    public HashMap<Integer, Localidade> getLocalidadesMap()
    {
        return localidadesMap;
    }

}
