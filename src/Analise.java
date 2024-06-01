
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
 * 
 *      StudentUI
 *          Read                            : Student
 * 
 *      JustificationUI
 *          Create, Edit, Verify, Drop      : Justification
 *  
 *      CoordinationUI
 *          Create, Read, Update, Delete    : Coordination
 *          Create, Read, Update, Delete    : Student
 *          Create, Read, Update, Delete    : Course
 *          Create, Read, Update, Delete    : Teacher
 *          Create, Read, Update, Delete    : Classroom
 *          Create, Read, Update, Delete    : Subject
 *          List, Verify, Deferiment        : Justication
 *
 * 2.2. Entidades ligadas a IU
 *      
 *      Entidade Justification
 * 
 *          atributo Integer id;
 *          atributo Date createdAt;
 *          atributo Date verifiedAt;
 *          atributo Student student;
 *          atributo Period period;
 *          atributo List<LostedTest> lostedTests;
 *          atributo Verified verified;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 *      
 *      Entidade Coordination
 * 
 *          atributo Integer id;
 *          atributo Course course;
 *          atributo Teacher coordinator;
 *
 *          operacao criar()
 *          operacao eliminar()
 *          operacao actualizar()
 *          operacao visualizar()
 * 
 *      FimEntidade
 *      
 *      Entidade Student
 *  
 *          atributo Integer id;
 *          atributo Person person;
 *          atributo String telephone;
 *          atributo Course course;
 *          atributo List<Year> years;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 *      Entidade Subject
 *  
 *          atributo Integer id;
 *          atributo String name;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 *      Entidade Course
 *  
 *          atributo Integer id;
 *          atributo string name;
 *          atributo List<Year> years;
 *          atributo List<Subject> subjects;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 *      Entidade Classroom
 *  
 *          atributo Integer id;
 *          atributo string name;
 *          atributo List<Student> students;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 *      Entidade Teacher
 *  
 *          atributo Integer id;
 *          atributo Person person;
 *          atributo String telephone;
 *          atributo List<Course> courses;
 *          atributo List<Year> years;
 *          atributo List<Subject> subjects;
 *          atributo List<Classroom> classrooms;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 *      Entidade Year
 *  
 *          atributo Integer id;
 *          atributo List<subject> subjects;
 *          atributo List<Classroom> classrooms;
 *          atributo List<TeacherSubject> teachersSubjects;
 * 
 *          operacao create()
 *          operacao read()
 *          operacao update()
 *          operacao delete()
 * 
 *      FimEntidade
 * 
 * 
 * 2.3. Entidades Persistentes
 *        
 *       Ficheiros
 *           justificativo.dat  ---->  JustificativoPersistente
 *       
 *       Tabelas
 *          
 *       Entidade Verified
 *           atributo Enum(DEFERIDO, INDEFERIDO);
 *       FimEntidade
 * 
 * 
 *         
 */

public class Analise {

}
