package Project;
public class Interface extends data{
    public static void main(String[] args)throws Throwable{
        
        data info = new data();
        System.out.print("do you already have an account? Y/N: ");
        if(!(info.yesNo()))
            signUp();
        info.userTypeCheck(login());   
        
}}