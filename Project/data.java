package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class data {
    
    // the program does not read and write to the txt files from the dirctory src/. (the one on the menu left)
    // the program reads from /build/classes -- so if u wanna check the txt files go there.
    


    //current user variables 
    static String userType;
    static String username;
    static String pass;
    static int userIndex;
    /*
        this is how a user is stored in the arraylist and users.txt
    
        [username] [usertype] [password] [] <- empty cell
    
        the empty cell is there for advisor and student
        it stores the registed courses for the student 
        ex.   student2 student 123 BIO101/BIO102/
        user[0] = student2
    
        it also stores the registration requests for advisors
        ex. advisor2 advisor 123 student4#BIO102/student3#MATH001/
        (student4 is trying to register for BIO102)

    */
    
    
    /*
        this is how a course is stored in the arraylist and courses.txt
    
        [course code] [course name] [Prerequesites] [course description] [instructor]
        [max seats] [credits] [enrolled studnets]
    
        ex. BIO101 biology no_prerequisites study_of_living_things paul 5 3 student1/student2/
    */
    
    //arraylist that stores the user arrays
    static ArrayList<String[]> users = new ArrayList<>();
    // arraylsits that stores the course arrays
    static ArrayList<String[]> courses = new ArrayList<>();
    static File usersFile;
    static File coursesFile;
    


    
    public ArrayList<String[]> getUsers() {
        return users;
    }

    public ArrayList<String[]> getCourses() {
        return courses;
    }
    
    // this is to get the info of the text files when you run the program
    public data() throws Throwable {
        // gets file paths
        usersFile = new File(getPath("users.txt"));
        coursesFile = new File(getPath("courses.txt"));

        // reads users
        readFileToList(usersFile, users);
        // reads courses
        readFileToList(coursesFile, courses);
    }

    // gets path of given file (txt)
    public URI getPath(String fileName) throws Throwable {
        String filePath = (data.class.getResource(fileName)).toString();
        return new URI(filePath);
    }

    // takes file contents and appends it to given list
    public void readFileToList(File file, ArrayList<String[]> list) throws Throwable {
        if(list.isEmpty()){
        Scanner reader = new Scanner(file);
        String[] line;
        String[] element;
        int cellSize = 0;
        if(file.getName().equals("users.txt"))
            cellSize = 4;
        else if (file.getName().equals("courses.txt")) 
            cellSize = 8;
            
        while (reader.hasNextLine()) {
           element = new String[cellSize];
           line = reader.nextLine().split(" ", cellSize);
           for(int i =0; i<line.length;i++)
               element[i] = line[i].trim();
           for(int i =0; i<element.length;i++)
               if(element[i] == null)
                   element[i] = "";
            list.add(element);
        }
    
        }}
    
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    
    //everytime something in the arraylist of users is changed this is used to update file
    // exif changed the username of a user.
    static void overwriteUsers() throws Throwable {
        BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile, false));
        for (String[] sublist : users) {
            for (String str : sublist) {
                writer.write(str + " ");
            }
            writer.newLine();
        }
        writer.close();}
    
//everytime something in the arraylist of courses is changed this is used to update file
    static void overwriteCourses() throws Throwable {
        BufferedWriter writer = new BufferedWriter(new FileWriter(coursesFile, false));
        for (String[] sublist : courses) {
            for (String str : sublist) {
                writer.write(str + " ");
            }
            writer.newLine();
        }
        writer.close();}
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    
    // boolean-- yes or no prompt
    public boolean yesNo() {
        Scanner sn = new Scanner(System.in);
        while (true) {
            String str = sn.nextLine().toLowerCase();
            switch (str) {
                case "no":
                    return false;
                case "n":
                    return false;
                case "yes":
                    return true;
                case "y":
                    return true;
                default:
                    System.err.println("yes or no?");
            }
        }
    }

    // initializes one of the usertype classes based on input
    public void userTypeCheck(String str) throws Throwable {
        student st; admin ad; advisor adv;
        switch(str){
            case "student": st = new student(); break;
            case "admin": ad = new admin(); break;
            case "advisor": adv = new advisor(); break;
        }
    }
    
    //checks number of remaining seats in course
    public int checkAvailableSeats(String[] course) {
        if(!(course[7].isEmpty())){
        String[] studentsRegistered = course[7].split("/");
        if (studentsRegistered.length >= Integer.valueOf(course[5]))
            return 0;
        return Integer.valueOf(course[5]) - studentsRegistered.length;}
        return Integer.valueOf(course[5]);
    }
    
    //checks if user has passed the prequisites of a course
    public boolean passedPrerequisites(String[] user, String[] course) {
        String[] passedPreqs;
        String[] requiredPreqs;
        if (!(course[2].equals("no_prerequisites"))) {
            if (!(user[3].isEmpty())){
                passedPreqs = user[3].trim().split("/");
                requiredPreqs = course[2].trim().split("/");
                boolean containsAllPreqs = false;
                for(String preq: requiredPreqs)
                    for(String pasdPerq: passedPreqs){  
                        if(pasdPerq.contains(preq)){
                            containsAllPreqs = true; break;}
                        containsAllPreqs = false;
                    }
                return containsAllPreqs;
            }
            return false;
        }
        return true;
    }
    
    // returns the registred courses of a user
    public String[] getRegisteredCourses(String[] user) {
            return user[3].split("/");
    }
    
    // this is used to print one of the arraylists.
    public String arrString(ArrayList<String[]> arr){
        String result = "";
        for(String[] strArr: arr){
            result += "[";
            for(int i =0; i < strArr.length;i++){
                if(i!=strArr.length-1)
                    result += strArr[i]+",";
                else 
                    result += strArr[i]+"]";
            }
            result += " ";
        }
        return result;
    }
    
    //lists the preqrequisites of a course
    public void ListPrerequisites(String str) {
        boolean codeWrong = true;
        for (String[] course : courses) {
            if (course[0].equals(str)) {
                codeWrong = false;
                if (!(course[2].contains("no_prerequisites"))) {
                    String[] Preqs = course[2].split("/");
                    for (String preqq : Preqs) {
                        preqq = preqq.replaceAll("/", "");
                        System.out.print("[" + preqq + "]<-");
                    }
                    for (String preq : Preqs) {
                        preq = preq.replaceAll("/", "");
                        ListPrerequisites(preq);
                    }
                }
            }

        }

    }
    
    
    
    //prints courses arraylist in a formatted fashion
    public void viewCourses() {
        for (String[] course : courses) {
            System.out.printf("%s, %s, credits: %s, prerequisite: %s, Instructor: %s, remaining seats: %s\n%s \n\n",
                    course[0], course[1], course[6], course[2], course[4], checkAvailableSeats(course), course[3].replaceAll("_", " "));
        }}
    
    //prints users arraylist in a formatted fashion
    public void viewUsers() {
        for (String[] user : users) {
            System.out.printf("username: %s, %s, password: %s,\n",
                    user[0], user[1], user[2]);
        }}
    
    //checks if a username is taken
    public static boolean usernameTaken(String str){
          for(String[] user: users){
              if(str.equals(user[0]))
                  return true;
          }
          return false;
      }
     
    //prompt a signup that adds a user to the users arraylist
     public static void signUp()throws Throwable{
    // this is how a user is structured (username, userType, password, [empty cell])
    String[] user = new String[4];
    Scanner sn = new Scanner(System.in);
         
    //username
    System.out.print("username: ");
        while(true){
            username = sn.next();
            if(usernameTaken(username)){
                System.err.println("username is taken"); continue;
            }
            user[0]=username; 
            break;
        }
    System.out.print("sign up as admin, student or advisor?: ");
        //choose user type
        while (true) {            
            userType = sn.next().toLowerCase();
            if(!(userType.equals("admin")||userType.equals("student")||userType.equals("advisor"))){
                System.err.println("invlaid userType"); continue;
            }
            user[1]=userType; 
             break;
             
        }
        //password
        System.out.print("password: ");
        while(true){
            pass = sn.next();
            user[2]=pass;
             break;
        }
        user[3] = "";// 4th cell set to empty to avoid nullPointers later
        
        //loads user into users.
        users.add(user); 
        
        //overwrites the users arraylist with the updated arraylist
        overwriteUsers();
      //user is created
    }
     
    //prompts a login where you have to enter a regisiterd user's info
    public static String login()throws Throwable{
        System.out.println("\nplease log in");
        Scanner sn = new Scanner(System.in);
        System.out.print("username: ");
        while (true) {            

        username = sn.next(); 
        if(!(usernameTaken(username))){
            System.err.println("username doenst exists"); continue;}
        break;
            }
        userIndex = userIndex();
         // checks password entered matches password attached to username
        System.out.print("password: ");
        while (true) {
        pass = sn.next();
        if(!(users.get(userIndex)[2].equals(pass))){
            System.err.println("wrong password"); continue;}
        break;
        }
        
       // returns the userType attached to username
        
       return users.get(userIndex)[1].toString();
    }    
 
      
      //returns index of the array containing a username in the users arraylist
      public static int userIndex(){
          for(String[] user: users){
              if(username.equals(user[0]))
                  return users.indexOf(user);
      }  return 0;    }
 
}