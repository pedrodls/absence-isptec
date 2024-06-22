package utils;

/**
 *
 * @author Pedro João
 */

public class Defs {

        public static final String EMAIL_ADDRESS = "@isptec.co.ao";

        public static final String ASSUNTO = "Justificativo de Faltas";

        public static final int NAME_SIZE = 50;
        public static final int TELEPHONE_SIZE = 9;
        public static final int EMAIL_SIZE = 50;
        public static final int ID_SIZE = 8;
        public static final long RECORD_SIZE = NAME_SIZE + ID_SIZE;

        public static final String[] MAIN_MENU_LINKS = {
                        "Admin",
                        "Coordenação",
                        "Professor",
                        "Estudante(Demo)",
                        "Sair",
        };

        public static final String[] ADMIN_LINKS = {
                        "Ano Académico",
                        "Curso",
                        "Disciplina",
                        "Professor",
                        "Coordenador",
                        "Estudante",
                        "Motivo de falta",
                        "Funcionário",
                        "Sair",

        };

        public static final String[] COORDINATION_JUSTIFICATION_LINKS = {
                        "Listar",
                        "Procurar",
                        "Justificar",
                        "Não Justificar",
                        "Sair",

        };

        public static final String[] STUDENT_JUSTIFICATION_LINKS = {
                        "Solicitar",
                        "Listar",
                        "Editar",
                        "Procurar",
                        "Eliminar",
                        "Sair",

        };

        public static final String[] JUSTIFICATION_LINKS = {
                        "Por Justificar",
                        "Justificados",
                        "N/Justificados",
                        "Sair",
        };

        public static final String[] COORDENATION_LINKS = {
                        "Turma",
                        "Turma     <-> Estudante",
                        "Curso     <-> Disciplina",
                        "Professor <-> Disciplina",
                        "Justificacao de faltas(Em teste)",
                        "Sair",

        };

        public static final String[] TEACHER_LINKS = {
                        "Meu Perfil",
                        "Minhas Turmas",
                        "Faltas",
                        "Sair",

        };

        public static final String[] STUDENT_LINKS = {
                        "Meu Perfil",
                        "Minhas Turmas",
                        "Minhas Faltas",
                        "Justificação de Faltas",
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

        public static final String JUSTIFICATIVO_FILE = "./src/data/justificativos.dat";
        public static final String MOTIVO_FALTA_FILE = "./src/data/motivos_faltas.dat";
        public static final String FALTA_FILE = "./src/data/faltas.dat";
        public static final String PROVA_PERDIDA_FILE = "./src/data/provas_perdidas.dat";
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
        public static final String EMPLOYEE_FILE = "./src/data/employee.dat";
        public static final String ALL_FILES[] = {
                        EMPLOYEE_FILE,
                        JUSTIFICATIVO_FILE,
                        ANO_ACADEMICO_FILE,
                        MOTIVO_FALTA_FILE,
                        FALTA_FILE,
                        PROVA_PERDIDA_FILE,
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
