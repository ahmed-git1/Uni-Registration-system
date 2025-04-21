
package Project;

import java.util.ArrayList;
import java.util.Scanner;

public class student extends data  { 
    
    Scanner in = new Scanner(System.in);
    //current user array
    String[] user = users.get(userIndex);
    String studnetMsg = "\n\nyou have the following options (enter nubmer) \n" +
                           "1 -view available courses\n"
                         + "2 -Register for Course\n"
                         + "3 -view registered courses\n"
                         + "4 -Drop Course\n"
                         + "5 -view validated courses\n"
                         + "6 -view schedule\n"
                         + "7 -update information\n"
                         + "8 -submit Request\n"
                         + "9 -list course Prerequisites\n"
                         + "exit -exit program";
    
    public student() throws Throwable{     
        while(true){
            //display options menu.
            System.out.println(studnetMsg);
            System.out.print("\n> ");
            String command = in.next();
            switch(command.toLowerCase()){
                case "1": viewCourses(); continue;
                case "2": Register();    continue;
                case "3": viewRegistredCourses();  continue;
                case "4": dropCourse(); continue;
                case "5": viewValidated(); continue;
                case "6": viewSchedule(); continue;
                case "7": UpdateInfo(); continue;
                case "8": SubmitRequest(); continue;
                case "9": intializeListPrerequisites(); continue;
                case "exit": System.exit(0);
                default:
                    System.err.println("invalid command"); continue;
                }
        }
      
    }
     
   
    //register for a course
    public void Register() throws Throwable{
        System.out.print("enter course code: "); String str = in.next();
        boolean codeWrong = true;
           for(String[] course:courses){
               if(course[0].equals(str)){
                   // code enterd is correct
                   codeWrong = false;
                   //user not already in course
                   if(!(user[3].contains(str))){
                       //user has passed the preqerquisites
                       if(passedPrerequisites(user, course)){
                           //course has available seats
                         if(checkAvailableSeats(course)!=0){
                             user[3] += str+"/"; 
                             overwriteUsers();
                             
                             course[7] += user[0]+"/";
                             overwriteCourses();
                             
                             System.out.println("Registration complete"); break;
                         } else System.err.println("course is full");
                             } else System.out.println("you haven't passed the prerequisites for this course");break;
                            } else System.out.println("you are already registered in that course"); break;
                        }}
               if(codeWrong)
               System.err.println("invalid code");}
    
    //drops a course
    void dropCourse()throws Throwable{
        System.out.print("enter course code: "); String str = in.next();
         boolean codeWrong = true;
           for(String[] course:courses){
               if(course[0].equals(str)){
                   codeWrong = false;
                   //checks if user is in the course
                   if(user[3].contains(str)){
                       user[3]=user[3].replaceAll(str+"/", "");
                       course[7]=course[7].replaceAll(user[0]+"/", "");
                       overwriteCourses(); overwriteUsers();
                       System.out.println("course Dropped");
                   }else
                       System.out.println("you are not registerted in that course");     
               }
           }
           if(codeWrong)
           System.err.println("invalid code");
        }
    
    // view user's registerd courses
    void viewRegistredCourses(){
        //checks if use is in any courses
        if (!(user[3].isEmpty())){
            for(String course: getRegisteredCourses(user)){
                System.out.print(course+" ");
            }
        }else
            System.out.println("you are not enrolled in any courses");
    }
    
    void viewValidated(){
       viewCourses();
    }
    
    void viewSchedule(){
        //checks if use is in any courses
        if (!(user[3].isEmpty())){
            int hour = 8;
            for(String course: getRegisteredCourses(user)){
                System.out.print(hour+":00 ["+course+"] ");
                hour++;
            }
        }
    }
    
    //change user info.
    void UpdateInfo() throws Throwable{
        String infoMsg = "1 -update username\n"
                         + "2 -update password\n"
                         + "exit -return to previous  menu";
        boolean stop = true;
        while(stop){
           System.out.println(infoMsg);

            System.out.print("\n>");
            String str = in.next();
            switch(str.toLowerCase()){
            case "1": 
                System.out.print("\nenter new username: "); 
                user[0] = in.next();
                
                System.out.println("username updated--\n");
                overwriteUsers(); continue;
            case"2": 
                System.out.print("\nenter new password: ");
                user[2] = in.next();
                System.out.println("password updated--\n");
                overwriteUsers();  continue;
            case"exit":
                stop = false;break;
            default: System.err.println("invalid input");continue;
                
        
        }}
        
    }
    
    //sumbits a request in the format [studnetName]#[CourseCode]/
    //sent to all advisors.
    void SubmitRequest()throws Throwable{
        System.out.print("enter course code: ");
        String str = in.next();
        boolean codeWrong = true;
        for(String[] course:courses){
               if(course[0].equals(str)){
                   codeWrong = false;
                   if(!(user[3].contains(str))){
                   if(checkAvailableSeats(course)==0){
                        for(String[] usr: users){
                            if(usr[1].equals("advisor"))
                                usr[3] += user[0]+"#"+str+"/"; overwriteUsers();}
                                System.out.println("request sent");
                   
                   } else System.out.println("course is not full.");
                   } else System.out.println("you are already in that course");
                   }
               }
        if(codeWrong)
               System.err.println("invalid code");

               }
    
      //feeds string to the method 
      void intializeListPrerequisites(){
        System.out.print("enter course code: ");
        String str = in.next();
        ListPrerequisites(str);
      }
      
      
}
               
    


    
    
    
    
    
   
    
        
        
 

