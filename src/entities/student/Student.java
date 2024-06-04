package entities.student;


import java.util.Scanner;

public class Student  {

    private Integer id;
    private String telephone;
    private String email;
    private String course;
    private String name;
    
    public Student() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.course = "";
        this.telephone = "";
    }

    public Student(Student student) {
        
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.course = student.getCourse();
        this.telephone = student.getTelephone();

    }

    public static Student create()
    {
        Student st = new Student();

        Scanner sc = new Scanner(System.in);

        Boolean validate = false;

        do
        {
            System.out.print("Nome: ");
            String name = sc.nextLine();
            st.setName(name);
            
        }while(!(st.getName().length() > 0));
       
        do
        {
            System.out.print("Curso: ");
            String course = sc.nextLine();
            st.setCourse(course);
            
        }while(!(st.getCourse().length() > 0));

        do {
            System.out.print("Email: ");
            String email = sc.nextLine();
            st.setEmail(email);
        } while (!(st.getEmail().length() > 0));

       do {

            System.out.print("Telefone: ");
            String telephone = sc.nextLine();

            for(Integer i=0; i < telephone.toCharArray().length ; i++){
                if (!Character.isDigit(telephone.toCharArray()[i])) {

                    System.out.print("\nNúmero de telefone inválido! Insira novamente!\n");

                    validate = false;

                    break;
                }

                if (i == telephone.toCharArray().length - 1) {
                    st.setTelephone(telephone);
                    validate = true;
                }
            }
       
        } while (!validate); 

        validate = false;

        do {

            System.out.print("Número | Matricula: ");
            String id = sc.nextLine();

            for(Integer i=0; i < id.toCharArray().length; i++){
                if (!Character.isDigit(id.toCharArray()[i])) {
                    
                    System.out.print("\nNúmero | Matricula inválido! Insira novamente!\n");
                    
                    validate = false;

                    break;
                }

                if (i == id.toCharArray().length - 1) {
                    
                    st.setId(Integer.parseInt(id));
                    
                    validate = true;

                    break;
                }
            }
       
        } while (!validate); 
       
        return st;
    }

    public static Student edit(Student st)
    {
        Scanner sc = new Scanner(System.in);

        Boolean validate = false;

        do
        {
            System.out.println("Antigo Nome: "+st.getName());
            System.out.print("Novo nome: ");
            String name = sc.nextLine();
            st.setName(name);
            
        }while(!(st.getName().length() > 0));
       
        do
        {
            System.out.println("Antigo Curso: "+st.getCourse());
            System.out.print("Novo Curso: ");
            String course = sc.nextLine();
            st.setCourse(course);
            
        }while(!(st.getCourse().length() > 0));

        do {
            System.out.println("Antigo Email: "+st.getEmail());
            System.out.print("Novo Email: ");
            String email = sc.nextLine();
            st.setEmail(email);
        } while (!(st.getEmail().length() > 0));

       do {

            System.out.println("Antigo Telefone: "+st.getTelephone());
            System.out.print("Novo Telefone: ");
            String telephone = sc.nextLine();

            for(Integer i=0; i < telephone.toCharArray().length ; i++){
                if (!Character.isDigit(telephone.toCharArray()[i])) {

                    System.out.print("\nNúmero de telefone inválido! Insira novamente!\n");

                    validate = false;

                    break;
                }

                if (i == telephone.toCharArray().length - 1) {
                    st.setTelephone(telephone);
                    validate = true;
                }
            }
       
        } while (!validate); 

        validate = false;

        do {

            System.out.println("Antigo Número | Matricula: " + st.getId());
            System.out.print("Novo Número | Matricula: ");
            String id = sc.nextLine();

            for(Integer i=0; i < id.toCharArray().length; i++){
                if (!Character.isDigit(id.toCharArray()[i])) {
                    
                    System.out.print("\nNúmero | Matricula inválido! Insira novamente!\n");
                    
                    validate = false;

                    break;
                }

                if (i == id.toCharArray().length - 1) {
                    
                    st.setId(Integer.parseInt(id));
                    
                    validate = true;

                    break;
                }
            }
       
        } while (!validate); 
       
        return st;
    }

    @Override
    public String toString(){

        return "Dados do Estudante " + " \n{ \n\tNumero: " + this.getId() + ",\n\tNome: " + this.getName() + ",\n\tCurso: " + this.getCourse() + ",\n\tEmail: " + this.getEmail() +  ",\n\tTelephone: " + this.getTelephone() + "\n}";
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
