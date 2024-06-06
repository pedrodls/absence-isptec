package utils;


/**
 *
 * @author Pedro João
 */

public class Defs
{
    public static final String[] MAIN_MENU_LINKS = {
        "Admin",
        "Coordenação",
        "Professor",
        "Estudante",
        "Sair",

    };

    public static final String[] ADMIN_LINKS = {
        "Anos academicos",
        "Anos letivos",
        "Cursos",
        "Curso <-> Ano <-> Disciplinas",
        "Coordenadores",
        "Disciplinas",
        "Estudante",
        "Professores",
        "Motivos de faltas",
        "Sair",

    };

    public static final String[] COORDENATION_LINKS = {
        "Turmas",
        "Turma - Estudante",
        "Professor <-> Disciplina",
        "Pedido de Justificacoes de falta",
        "Sair",

    };

    public static final String[] TEACHER_LINKS = {
        "Marcar Falta",
        "Minhas Turmas",
        "Meus Dados",
        "Pedido de Justificacoes de falta",
        "Sair",

    };

    public static final String[] CRUD_LINKS = {
        "Criar",
        "Editar",
        "Atualizar",
        "Desactivar",
        "Sair",
    };

    public static final String ANO_LETIVO_FILE = "./data/anos_letivos.dat";

    public static final String JUSTIFICATION_FILE = "justificacoes.dat";
    
    public static final String TMP_FILE = "___tmpfile";

}
