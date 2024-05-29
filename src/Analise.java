
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
 * 2.1. Interface de utilizador (IU)/ Menu Principal
 * 
 *      Admin
 *      Login
 *      Criar, Adicionar, Eliminar, Atualizar, Editar: -> Estudante, Professor, Curso,
 *                                                        Disciplina, Coordenação, 
 *                                                        Turma, Lista de Presença
 * 
 *      Coordenador
 *          Login
 *          Analisar/Validar os pedidos de justificação
 *          Eliminar falta
 *
 *      Professor
 *          Login
 *          Marcar Falta
 *          Listar Alunos com suas falta
 *
 *      Estudante
 *          Login
 *          Verificar faltas
 *          Pedidio de jusficação de falta: -> Preencher o formulário: Nº de telefone, 
 *                                             Periodo de ausência, Provas Perdidas: -> 
 *                                             tipo
 *          Verificar o status do pedido de justificação
 *          Listar todas as solicitações passadas
 * 
 * 
 * 2.2. Entidades ligadas a IU
 * 
 * *      Entidade JustificativoIU
 * 
 *          atributo Integer id;
 *          atributo String telephone;
 *          atributo Course course;
 *          atributo List<Subject> subjects;
 *          atributo List<Absence> absences;
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 * 
 *      FimEntidade
 * 
 *      Entidade EstudanteIU
 * 
 *          atributo Integer id;
 *          atributo String telephone;
 *          atributo Course course;
 *          atributo List<Subject> subjects;
 *          atributo List<Absence> absences;
 * 
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 * 
 *      FimEntidade
 *      
 *      Entidade Professor
 *          
 *      FimEntidade

 *      Entidade Coordenador
 *         
 *      FimEntidade


 * 
 * 2.3. Entidades Persistentes
 *        
 *       Ficheiros
 *           justificativo.dat  ---->       JustificativoPersistente
 *           estudante.dat      ---->       ClienteNumeroPersistente
 *       
 *       Tabelas
 * 
 * 
 *      Entidade JustificativoPersistente
 *
 *          atributo deferimento           : String -> DEFERIDO, INDEFERIDO, FALTA DE   
 *                                                     COMPROVATIVO
 *          atributo estudante             : Estudante
 *          atributo estudante             : Estudante
 *          atributo provasPerdidas        : List<ProvasPerdida>
 *          atributo tipoDeProva           : String -> EXAME, PP_1, PP_2   
 *          atributo dataEntrada           : Date   
 *          atributo dataEntrega           : Date   
 * 
 *      FimEntidade
 * 
 *      Entidade EstudantePersistente
 *
 *          atributo estudante             : Estudante
 *          atributo password              : String 
 * 
 *      FimEntidade
 *      
 *      
 *         
 */
public class Analise {

}
