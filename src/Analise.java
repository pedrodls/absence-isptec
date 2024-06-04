
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
 * 
 *      JustificationUI
 *          Create, Edit, Verify, Drop      : Justification
 *  
 *
 * 2.2. Entidades ligadas a IU
 *      
 *      Entidade JustificationUI
 * 
 *          atributo LocalDate createdAt;
 *          atributo LocalDate absenceStart;
 *          atributo LocalDate absenceEnd;
 * 
 *          atributo Student student;
 *          atributo String description;
 * 
 *          atributo List<LostedTest> lostedTests;
 *          atributo LostedTypeEnum lostedTestType;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 *      
 *      Entidade Student
 *  
 *          atributo Integer id;
 *          atributo String name;
 * 
 *          atributo String email;
 *          atributo String course;
 *          atributo String telephone;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 *      Entidade LostedTest
 *  
 *          atributo String teacher;
 *          atributo String subject;
 *          atributo LocalDate date;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 * 
 * 
 * 2.3. Entidades Persistentes
 * 
 *      Ficheiros
 *          justification.dat      ---->     PersistenceJustification
 *
 *      Tabelas
 *
 *      Entidade PersistenceJustification
 * 
 *          atributo LocalDate createdAt;
 *          atributo LocalDate absenceStart;
 *          atributo LocalDate absenceEnd;
 *          atributo String description;
 *          atributo Student student;
 *          atributo List<LostedTest> lostedTests;
 * 
 *      FimEntidade
 * 
 *      Entidade PersistenceStudent
 *  
 *          atributo Integer id;
 *          atributo String name;
 * 
 *          atributo String email;
 *          atributo String course;
 *          atributo String telephone;
 * 
 *      FimEntidade
 * 
 *      Entidade PersistenceLostedTest
 *  
 *          atributo String teacher;
 *          atributo String subject;
 *          atributo LocalDate date;
 * 
 *      FimEntidade
 *       
 *         
 */

public class Analise {

}
