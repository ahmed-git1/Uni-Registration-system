
package Project;

import java.util.Arrays;
import java.util.Scanner;

public class advisor extends data{
    //current user array
    Scanner in = new Scanner(System.in);
    String[] user = users.get(userIndex);
    
    
    String advMsg = "\n\nyou have the following options (enter nubmer) \n" +
                           "1 -View Registered Students\n"
                         + "2 -View a Student's Registered Courses\n"
                         + "3 -view Requests\n"
                         + "exit - exit program";
    
    public advisor() throws Throwable{
        
     //if the avdvisor is new the program gives it old requests.  
     for(String[] usr: users){
        if(usr[1].equals("advisor")&&(!usr[0].trim().equals(user[0].trim()))){
                user[3] = usr[3]; overwriteUsers(); break;}
            }
                         
        while(true){
            System.out.println(advMsg);
            System.out.print("\n> ");
            String command = in.next();
            switch(command.toLowerCase()){
                case "1": ViewRegisteredStudents(); continue;
                case "2": ViewStudentsCoursesInitializer();    continue;
                case "3": viewRequests();continue;
                case "exit": System.exit(0);
                default:
                    System.err.println("invalid command"); continue;
                }
     
     }}
    
    //prints users with usertype studnet
     void ViewRegisteredStudents(){
         for(String[] usr: users){
             if(usr[1].equals("student"))
                 System.out.printf("username: %s, %s, password: %s,\n",
                    usr[0], usr[1], usr[2]);}}
     
     void ViewStudentsCoursesInitializer(){
         System.out.print("enter student username: ");
         String str = in.next();
         ViewStudentsCourses(str);
     }
     
     //prints given studnet's courses
     void ViewStudentsCourses(String student){
         boolean wrongName = true;
         //checks if studnet exists
         for(String[] usr: users){
             if(usr[0].equals(student)){
                 wrongName = false;
                 System.out.print(Arrays.toString(getRegisteredCourses(usr)));}
         }
         if(wrongName)
             System.out.println("no student with that username");
         System.out.println(advMsg);
     }
     
     /* 
        if an advisor denies or accepts any request. the request no longer
        appears for all advisors 
     */ 
     void viewRequests() throws Throwable{
         if(!(user[3].trim().isEmpty())){
             String[] Requests = user[3].split("/");
             System.out.println(Arrays.toString(Requests));
             
             for(String message: Requests){
                 
                 String[] Request = message.split("#");

                 String student = Request[0];
                 String course = Request[1];
                 
                 //accept or deny request?
                 System.out.printf("%s wants to enroll in %s -- Y/N?\n> ",student,course);
                 if(yesNo()){
                    System.out.println("entrollment accepted");
                    for(String[] usr: users)
                        if(usr[0].equals(student)){
                             removeRequest(message);
                         usr[3] += course.trim()+"/";
                             overwriteUsers();
                        for(String[] crs: courses){
                            if(crs[0].equals(course)){
                                crs[7] += usr[0].trim()+"/";
                                overwriteCourses();}}
                     }  
                        
                        
                    
                 
     }else{ System.out.println("enrollment denied");
                     removeRequest(message);
             }
             
         
     }System.out.println("no Requests");
     
}}
     //method to remove a request when its been handled
 void removeRequest(String msg)throws Throwable{
     for (String[] advisor : users) 
            if (advisor[1].equals("advisor")) 
                 advisor[3] = advisor[3].replaceAll(msg+"/", "");
                 overwriteUsers();
 }

}
         
        