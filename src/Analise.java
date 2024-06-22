
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 *
 * @author Pedro João
 * 1. Objectivo: Desenvolver um sistema para pedidos do justificação de faltas do ISPTEC: 
 * 
 * 
 * 2.   Análise
 * 2.1. Interface de utilizador (IU) / Menu Principal & Sub-menus
 * 
 *      JustificativoUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      FaltaUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      MotivoFaltaUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 *  
 *      CursoUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      CoordenacaoUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      CoordenadorUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      AnoLectivoUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      AnoAcademicoUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      DisciplinaUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      TurmaUI   
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      ProfessorUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      ProfessorDisciplinaUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      EstudanteUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *      CursoAnoDisciplinaUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 *      
 *      TurmaEstudanteUI
 *          criar
 *          eliminar
 *          actualizar
 *          visualizar
 * 
 *
 * 2.2. Entidades ligadas a IU
 *      
 *      Entidade Justificativo
 *          atributo id                 :   Integer
 *          atributo tipoDespacho       :   TipoDespachoEnum
 *          atributo idCoordenacao      :   Integer
 *          atributo dataDespacho       :   Date
 *          atributo dataCriacao        :   Date
 *          atributo periodoInicialFalta:   Date
 *          atributo periodoFinalFalta  :   Date
 *          atributo assunto            :   String
 *          atributo idEstudante        :   Integer
 *          atributo idAnoAcademico     :   Integer
 *          atributo idMotivoFalta      :   Integer
 *          atributo provaPerdida       :   Boolean
 *          atributo tipoProva          :   TipoProvaEnum   
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 *      
 *      Entidade Falta
 *          atributo id                 :   Integer
 *          atributo tipoFalta          :   TipoFaltaEnum
 *          atributo idEstudante        :   Integer
 *          atributo idDisciplina       :   Integer
 *          atributo idProfessor        :   Integer
 *          atributo dataCriacao        :   Date
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade   MotivoFalta
 *          atributo id                 :   Integer
 *          atributo descricao          :   String
 *      FimEntidade
 * 
 *      Entidade ProvaPerdida
 *          atributo id                 :   Integer
 *          atributo idFalta            :   Integer
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade Coordenacao
 *          atributo id                 :   Integer
 *          atributo descricao          :   String
 *          atributo idCurso            :   Integer
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade Coordenador
 *          atributo id                 :   Integer
 *          atributo estado             :   Bolean
 *          atributo idProfessor        :   Integer
 *          atributo idCoordenacao      :   Integer
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade Estudante
 *          atributo idMatricula        :   Integer
 *          atributo idCurso            :   Integer
 *          atributo descricao          :   String
 *          atributo email              :   String
 *          atributo telefone           :   String
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade Professor
 *          atributo id                 :   Integer
 *          atributo descricao          :   String
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade ProfessorDisciplina
 *          atributo id                 :   Integer
 *          atributo idProfessor        :   Integer
 *          atributo idDisciplina       :   Integer
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade Turma
 *          atributo id                 :   Integer
 *          atributo idCurso            :   Integer
 *          atributo idAnoAcademico     :   Integer
 *          atributo descricao          :   String
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade TurmaEstudante
 *          atributo id                     :   Integer
 *          atributo idTurma                :   Integer
 *          atributo idProfessorDisciplina  :   Integer
 *          atributo idEstudante            :   Integer
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 *      Entidade CursoAnoDisciplina
 *          atributo id                 :   Integer
 *          atributo idCurso            :   Integer
 *          atributo idAnoAcademico     :   Integer
 *          atributo idDisciplina       :   Integer
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 *      FimEntidade
 * 
 * 
 * 2.3. Entidades Persistentes
 * 
 *      Ficheiros
 *          justificativos.dat          ---->   JustificativoPersistente
 *          *motivos_faltas.dat          ---->   MotivoFaltaPersistente
 *          faltas.dat                  ---->   FaltaPersistente
 *          provas_perdidas.dat         ---->   ProvaPerdidaPersistente
 *          *coordenacoes.dat            ---->   CoordenacaoPersistente
 *          *coordenadores.dat           ---->   CoordenadorPersistente
 *          *estudantes.dat              ---->   EstudantePersistente
 *          *professores.dat             ---->   ProfessorPersistente
 *          *cursos.dat                  ---->   CursoPersistente
 *          *anos_letivos.dat            ---->   AnoLetivoPersistente
 *          *anos_academicos.dat         ---->   AnoAcademicoPersistente
 *          *disciplinas.dat             ---->   disciplinaPersistente
 *          -cursoAnoDisciplina.dat      ---->   CursoAnoDisciplinaPersistente
 *          -turmas.dat                  ---->   TurmaPersistente
 *          -professores_disciplinas.dat ---->   ProfessorDisciplinaPersistene
 *          -turmasEstudantes.dat        ---->   TurmaEstudantePersistente
 * 
 *      Tabelas
 *      Entidade   JustificativoPersistente
 *          atributo id                 :   Integer
 *          atributo tipoDespacho       :   String(20)
 *          atributo dataDespacho       :   Long
 *          atributo dataCriacao        :   Long
 *          atributo periodoInicialFalta:   Long
 *          atributo periodoFinalFalta  :   Long
 *          atributo assunto            :   String(20)
 *          atributo idEstudanteTurma   :   Integer
 *          atributo idMotivoFalta      :   Integer
 *          atributo provaPerdida       :   Integer
 *          atributo tipoProva          :   String(20)  
 *          atributo idFuncionario      :   Integer
 *      FimEntidade
 * 
 *      Entidade   MotivoFaltaPersistente
 *          atributo id                 :   Integer
 *          atributo descricao          :   String(20)
 *      FimEntidade
 * 
 *      Entidade   FaltaPersistente
 *          atributo id                 :   Integer
 *          atributo idEstudanteTurma        :   Integer
 *          atributo idProfessorDisciplina       :   Integer
 *          atributo dataCriacao        :   Long
 *      FimEntidade
 * 
 *      Entidade   ProvaPerdidaPersistente
 *          atributo id                 :   Integer
 *          atributo idFalta            :   Integer
 *      FimEntidade
 *  
 *      Entidade   CoordenacaoPersistente
 *          atributo id                 :   Integer
 *          atributo descricao          :   String(20)
 *          atributo idCurso            :   Integer
 *      FimEntidade
 * 
 *      Entidade   CoordenadorPersistente
 *          atributo id                 :   Integer
 *          atributo estado             :   Integer
 *          atributo idProfessor        :   Integer
 *          atributo idCoordenacao      :   Integer
 *      FimEntidade
 *  
 *      Entidade   EstudantePersistente
 *          atributo idMatricula        :   Integer
 *          atributo idCurso            :   Integer
 *          atributo descricao          :   String(20)
 *          atributo email              :   String(20)
 *          atributo telefone           :   String(20)
 *      FimEntidade
 * 
 *      Entidade   ProfessorPersistente
 *          atributo id                 :   Integer
 *          atributo descricao          :   String(20)
 *      FimEntidade
 * 
 *      Entidade   CursoPersistente
 *          atributo id                 :   Integer
 *          atributo descricao          :   String(20)
 *      FimEntidade
 * 
 *      Entidade   AnoAcademicoPersistente
 *          atributo id                 :   Integer
 *          atributo descricao          :   String(20)
 *      FimEntidade
 * 
 *      Entidade   AnoLetivoPersistente
 *          atributo id                 :   Integer
 *          atributo descricao          :   String(20)
 *      FimEntidade
 * 
 *      Entidade   DisciplinaPersistente
 *          atributo id                 :   Integer
 *          atributo descricao          :   String(20)
 *      FimEntidade
 * 
 *      Entidade   CursoAnoDisciplinaPersistente
 *          atributo id                 :   Integer
 *          atributo idCurso            :   Integer
 *          atributo idAnoAcademico     :   Integer
 *          atributo idDisciplina       :   Integer
 *      FimEntidade
 * 
 *      Entidade   TurmaPersistente
 *          atributo id                 :   Integer
 *          atributo idCurso            :   Integer
 *          atributo idAnoAcademico     :   Integer
 *          atributo descricao          :   String(20)
 *      FimEntidade
 *      
 *      Entidade   ProfessorDisciplinaPersistente
 *          atributo id                     :   Integer
 *          atributo idTurma                :   Integer
 *          atributo idProfessor            :   Integer
 *          atributo idCursoAnoDisciplina   :   Integer
 * 
 *      FimEntidade
 * 
 *      Entidade    TurmaEstudantePersistente
 *          atributo id                     :   Integer
 *          atributo idTurma                :   Integer
 *          atributo idProfessorDisciplina  :   Integer
 *          atributo idEstudante            :   Integer
 *      FimEntidade
 * 
 * 
 * 2.5  Enumeraveis
 *          TipoDespachoEnum            { DEFERIDO, INDEFERIDO, FALTA_COMPROVATIVO }
 *          TipoProvaEnum               { EXAME, 1_PP, 2_PP                        }
 *          TipoFaltaEnum               { NORMAL, PROVA                            }
 *          TipoEstadoEnum              { JUSTIFICADO, PENDENTE, NAO_JUSTIFICADO   }
 *         
 */

public class Analise {

}
