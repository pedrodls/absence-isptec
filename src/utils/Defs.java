package utils;

/**
 *
 * @author Pedro João
 */

public class Defs {

        public static final String EMAIL_ADDRESS = "@isptec.co.ao";

        public static final int NAME_SIZE = 50;
        public static final int ID_SIZE = 8;
        public static final long RECORD_SIZE = NAME_SIZE + ID_SIZE;

        public static final String[] MAIN_MENU_LINKS = {
                        "Admin(Demo)",
                        "Coordenação(Em teste)",
                        "Professor(Não funcional)",
                        "Estudante(Não funcional)",
                        "Sair",
        };

        public static final String[] PP2_MENU_LINKS = {
                        "Ficheiros de dados",
                        "Consulta",
                        "Sair",
        };

        public static final String[] ADMIN_LINKS = {
                        "Curso",
                        "Coordenação",
                        "Coordenador",
                        "Professor",
                        "Estudante",
                        "Disciplinas",
                        "Motivo de falta",
                        /*
                         * "Anos academicos",
                         * "Anos letivos",
                         * "Cursos",
                         * "Curso <-> Ano <-> Disciplinas",
                         * "Coordenadores",
                         * "Disciplinas",
                         * "Estudantes",
                         * "Motivos de faltas",
                         */
                        "Sair",

        };

        public static final String[] COORDENATION_LINKS = {
                        "Turmas",
                        "Turma - Estudante",
                        "Curso <-> Ano <-> Disciplina",
                        "Professor <-> e <-> Disciplina",
                        "Justificacao de faltas",
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
                        "Listar",
                        "Editar",
                        "Procurar",
                        "Eliminar",
                        "Sair",
        };

        public static final String ANO_LETIVO_FILE = "./src/data/anos_letivos.dat";
        public static final String JUSTIFICATIVO_FILE = "./src/data/justificativos.dat";
        public static final String MOTIVO_FALTA_FILE = "./src/data/motivos_faltas.dat";
        public static final String FALTA_FILE = "./src/data/faltas.dat";
        public static final String PROVA_PERDIDA_FILE = "./src/data/provas_perdidas.dat";
        public static final String COORDENACAO_FILE = "./src/data/coordenacoes.dat";
        public static final String COORDENADOR_FILE = "./src/data/coordenadores.dat";
        public static final String ESTUDANTE_FILE = "./src/data/estudantes.dat";
        public static final String PROFESSOR_FILE = "./src/data/professores.dat";
        public static final String CURSO_FILE = "./src/data/cursos.dat";
        public static final String ANO_ACADEMICO_FILE = "./src/data/anos_academicos.dat";
        public static final String DISCIPLINA_FILE = "./src/data/disciplinas.dat";
        public static final String CURSO_ANO_DISCIPLINA_FILE = "./src/data/cursoAnoDisciplina.dat";
        public static final String TURMA_FILE = "./src/data/turmas.dat";
        public static final String PROFESSOR_DISCIPLINA_FILE = "./src/data/professores_disciplinas.dat";
        public static final String TURMA_ESTUDANTE_FILE = "./src/data/turmasEstudantes.dat";

        public static final String ALL_FILES[] = {
                        ANO_LETIVO_FILE,
                        JUSTIFICATIVO_FILE,
                        MOTIVO_FALTA_FILE,
                        FALTA_FILE,
                        PROVA_PERDIDA_FILE,
                        COORDENACAO_FILE,
                        COORDENADOR_FILE,
                        ESTUDANTE_FILE,
                        PROFESSOR_FILE,
                        CURSO_FILE,
                        ANO_ACADEMICO_FILE,
                        DISCIPLINA_FILE,
                        CURSO_ANO_DISCIPLINA_FILE,
                        TURMA_FILE,
                        PROFESSOR_DISCIPLINA_FILE,
                        TURMA_ESTUDANTE_FILE,
        };

        public static final String TMP_FILE = "___tmpfile";

}
