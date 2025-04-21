
package Project;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class admin extends data{
    Scanner in = new Scanner(System.in);
    //current user array
    String adminMsg = "\nyou have the following options (enter nubmer) \n" +
                           "1 -manage Courses\n"
                         + "2 -Manage Users\n"
                         + "3 -view Stats\n"
                         + "exit -exit program";
    String[] user = users.get(userIndex);
    
     public admin() throws Throwable{
     
     while(true){
            //displays options menu
            System.out.println(adminMsg);
            System.out.print("\n> ");
            String command = in.next();
            switch(command.toLowerCase()){
                case "1": manageCourses(); continue;
                case "2": manageUsers();    continue;
                case "3": stats();  continue;
                case "exit": System.exit(0);
                default:
                    System.err.println("invalid command"); continue;
                }
        }
     
     }
     String mangCrsMsg =  "\n1 -update course\n"
                          + "2 -remove a course\n"
                          + "3 -add a course\n"
                          + "exit -return to previous  menu";
     
     void manageCourses() throws Throwable{
         viewCourses();
         boolean stop = true;
         while(stop){
         System.out.println(mangCrsMsg);
         System.out.print("\n>");
         String str = in.next();    
    
         switch(str){
             case "1": updateCourse(); continue;
             case "2": removeCourse(); continue;
             case "3": addCourse(); continue;
             case "exit": stop = false; continue;
             default: System.err.println("invalid input"); continue;
         
            }}
         
        
     }
     
     //update course info
    void updateCourse() throws Throwable {
        System.out.print("enter course code: ");
        String str = in.next();
        boolean codeWrong = true;
        //prints course with code.
        for (String[] course : courses) {
            if (course[0].equals(str)) {
                codeWrong = false;
                System.out.printf("%s, %s, prerequisite: %s, available seats: %d%n",
                        course[0], course[1], course[2], checkAvailableSeats(course));

                String updtCourseMsg = "\n1 -change course code\n"
                        + "2 -change course name\n"
                        + "3 -chagne course prerequisites\n"
                        + "4 -change course description\n"
                        + "5 -chnage course Instructor\n"
                        + "6 -change course maximum seats\n"
                        + "7 -change course credits\n"
                        + "exit -return to course management menu";

                boolean stop = true;
                //menu for what to do with given course
                while (stop) {
                    System.out.println(updtCourseMsg);
                    System.out.print("\n>");
                    String command = in.next();

                    switch (command.toLowerCase()) {
                        case "1":
                            System.out.print("enter new course Code:");
                            String newCode = in.next();
                            course[0] = newCode.trim();
                            System.out.println("course code updated --\n");
                            overwriteCourses();
                            continue;
                        case "2":
                            System.out.print("enter new course Name:");
                            String newName = in.next();
                            course[1] = newName.trim();
                            System.out.println("course name updated --\n");
                            overwriteCourses();
                            continue;
                        case "3":
                            //asks if you want to remove or add a preq
                            System.out.println(course[2]);
                            System.out.print("add(1) or remove(2) a prerequisite?\n>");
                            String cmd = in.next();
                            // loops everytime user wants to add a preq.
                            switch (cmd){
                                
                                // add preq
                                case "1":
                                    System.out.print("enter new prerequisite Code: ");
                            String newPreq = in.next();
                            boolean preqWrong = true;
                            for (String[] preq : courses) {
                                if (preq[0].equals(newPreq)&& !(course[0].equals(newPreq))) {
                                    preqWrong = false;
                                    course[2] = newPreq.trim() + "/";
                                    System.out.println("course prerequisite updated --\n");
                                    if(course[2].contains("no_prerequisites"))
                                        course[2] = course[2].replace("no_prerequisites", "");
                                    overwriteCourses();continue;
                                   
                                }
                            }
                            if (preqWrong) {
                                System.out.println("invalid code");
                            } 
                            continue;
                                // remove preq
                                case "2":
                                    System.out.print("enter prerequisite Code: ");
                            String Preq = in.next();
                                if (course[2].contains(Preq)) {
                                    course[2] = course[2].replace(Preq.trim() + "/", "");
                                    System.out.println("course prerequisite removed --\n");
                                    if(course[2].trim().isEmpty())
                                        course[2] = course[2].trim().concat("no_prerequisites");
                                    overwriteCourses();continue;
                                }System.out.println(course[0]+" does not have that prerequisite");
                                continue;
                            default: continue;
                            }
                        case "4":
                            in.nextLine(); 
                            System.out.print("enter new course description:");
                            course[3] = in.nextLine().replaceAll(" ", "_");
                            System.out.print("course description updated");
                            overwriteCourses(); continue;
                        case "5":
                            System.out.print("enter new course Instructor");
                            course[4] = in.next().trim();
                            System.out.println("course Instructor updated");
                            overwriteCourses(); continue;
                        
                        case "6":   
                            while(true){
                            System.out.print("enter new course max seat number:");
                            try {
                              course[5] = String.valueOf(in.nextInt());
                              System.out.println("max seat number updated");
                              break;
                            } catch (InputMismatchException e) {
                                System.err.println("enter an integer");
                                in.next();
                            }}
                        
                        case "7":   
                            while(true){
                            System.out.print("enter new course credits:");
                            try {
                              course[6] = String.valueOf(in.nextInt());
                              System.out.println("credits updated");
                              break;
                            } catch (InputMismatchException e) {
                                System.err.println("enter an integer");
                                in.next();
                            }}
                            
                        case "exit":
                            stop = false;
                            break;
                        default:
                            System.out.println("invalid input");
                            continue;
                    }
                }

            }
        }
        if (codeWrong) {
            System.err.println("invalid code");
        }

    }
     
    // add course to courses arrylist
     void addCourse() throws Throwable {
        String[] newCourse = new String[8];
        
        boolean courseTaken = true;
        while(courseTaken){
        System.out.print("enter new course code: \n>");
        newCourse[0] = in.next();
        courseTaken = false;
        for(String[] course : courses){
            if(course[0].trim().equals(newCourse[0].trim())){
                courseTaken = true;
                System.out.println("course already exists"); break;}
                }
        }
     
     
        
        
        System.out.print("enter course name: \n>");
        newCourse[1] = in.next();
        
        in.nextLine(); 
        System.out.print("enter course description: \n>");
        String desc = in.nextLine();
        newCourse[3] = desc.replaceAll(" ", "_");
        
        
        boolean stop = true;
        //asks if you want to remove or add a preq
        System.out.print("enter course prerequisite or type \"elective\" to not list any prerequisites.");
        newCourse[2] = "";
        
        // loops everytime user wants to add a preq.
        while (stop) {
            System.out.print("\n>");
            String str = in.next();
            System.out.println();
            if (str.toLowerCase().equals("elective")) {
                newCourse[2] = "no_prerequisites";
                break;
            }
            boolean codeWrong = true;
            for (String[] course : courses) {
                if (course[0].equals(str) && !(newCourse[2].trim().contains(str))) {
                    codeWrong = false;
                    newCourse[2] += course[0].trim() + "/";
                    System.out.print("add more prerequisites?(Y/N):");
                    if (yesNo()) {
                        System.out.print("add a course prerequisite:");
                        break;
                    } else {
                        stop = false;
                        break;
                    }
                }

            }
            if (codeWrong) {
                System.out.println("invalid input");
            }
        }
        //instructor
        System.out.print("enter course Instructor name: \n>");
        newCourse[4] = in.next();
        //max seats
        while(true){
            System.out.print("enter course max seat number:");
            try {
            newCourse[5] = String.valueOf(in.nextInt());
            break;
            } catch (InputMismatchException e) {
              System.err.println("enter an integer");
              in.next();
              }}
        //credits
        while(true){
            System.out.print("enter course credits: ");
            try {
            newCourse[6] = String.valueOf(in.nextInt());
            break;
            } catch (InputMismatchException e) {
              System.err.println("enter an integer");
              in.next();
              }}
        System.out.println("course added");
        newCourse[7] = "";
        courses.add(newCourse);
        overwriteCourses();
    }
     
     //removes a course from arraylist
    void removeCourse() throws Throwable{
        System.out.print("enter cousre code: ");
        String str = in.next();
        int index = 0;
        boolean strWrong = true;
        // checks if code is right and deletes element with index of course
        for (String[] course : courses) {
                if (course[0].equals(str)){
                    index = courses.indexOf(course);
                    strWrong = false;
                    System.out.println("course removed--");
                    
                }}
        if(strWrong)
            System.out.println("invalid input");
        else {
            courses.remove(index); overwriteCourses();}
            
    } 
    String mangUrsMsg =  "\n1 -add a user\n"
                          + "2 -remove a user\n"
                          + "exit -return to previous  menu";
    
    //works the same as courses menu above
    void manageUsers()throws  Throwable{
        viewUsers(); 
        boolean stop = true;
         while(stop){   
         System.out.println(mangUrsMsg);
         System.out.print("\n>");
         String str = in.next();    
         switch(str.toLowerCase()){
             case "1": addUser(); continue;
             case "2": removeUser(); continue;
             case "exit": stop = false; continue;
             default: System.err.println("invalid input"); continue;
            }}
         
        
     }
    
    //just prompts a signup to add a user
    void addUser()throws Throwable{
        signUp();
        System.out.println("user added");
    }
    void removeUser()throws Throwable{
    System.out.print("enter username: ");
        String str = in.next();
        int index = 0;
        boolean strWrong = true;
        for (String[] user : users) {
                if (user[0].equals(str)){
                    index = users.indexOf(user);
                    strWrong = false;
                    System.out.println("user removed--");
                    
                }}
        if(strWrong)
            System.out.println("invalid input");
        else {
            users.remove(index); overwriteUsers();}
            
    }
    
    void stats(){
        System.out.println("number of users: "+ users.size());
        viewUsers();
        System.out.println();
        System.out.println("number of courses: "+ courses.size());
        viewCourses();
    }
   
    
    }
     
