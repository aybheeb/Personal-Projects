/*
* Name: Habeeb Sowemimo
* Class: EECS 2500
* Project: zero
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Project0List{
    public static void main (String[] args) {
        ArrayList<Institution> universityList= new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Project0List commands = new Project0List();
        //initial message to help user navigate
        System.out.println("""
                Valid commands are:
                    Help- show commands
                    Add- Add University
                    List- List Universities
                    Search- Search University
                    Delete- Delete University
                    Save- Save list to file
                    Exit- Exit the program""");
        //loop that keep taking commands unless you exit
        while (true) {
            System.out.print("Enter command: ");
            String userCommand = input.nextLine();
            switch (userCommand.toLowerCase()) {
                case ("help") ->
                    //do help command
                        commands.doHelpCommand();
                case ("add") ->
                    //do add command
                        commands.doAddCommand(input, universityList);
                case ("list") ->
                    //do list command
                        doListCommand(universityList);
                case ("search") ->
                    //do search command
                        doSearchCommand(input, universityList);
                case ("delete") ->
                    //do delete command
                        doDeleteCommand(input, universityList);
                case ("save") -> {
                    //do save command
                    saveToFile(universityList);
                    System.out.println("File successfully saved to saved.txt");
                }
                case ("exit") -> {
                    //do exit command
                    System.out.println("Exiting the program.");
                    input.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid command");
            }
        }
    }

    public void doHelpCommand() {  //displays help commands
        System.out.println("""
                Valid commands are:
                    Help- show commands
                    Add- Add University
                    List- List Universities
                    Search- Search University
                    Delete- Delete University
                    Save- Save list to file
                    Exit- Exit the program""");
    }

    public void doAddCommand(Scanner input, ArrayList<Institution> universityList){
        //prompt user for university details
        System.out.print("Enter name of university: ");
        String officialName = input.nextLine();
        //check if university name already exists
        boolean isDuplicate = false;
        for(Institution university : universityList){
            if(university.getOfficialName().equalsIgnoreCase(officialName)){
                System.out.println("University with the same name already exists.");
                isDuplicate = true;
                break;
            }
        }
        if (!isDuplicate) {

            System.out.print("Enter nickname of university: ");
            String nickName = input.nextLine();

            System.out.print("Enter city located: ");
            String city = input.nextLine();

            System.out.print("Enter state located: ");
            String state = input.nextLine();

            int studentBody = 0;
            int yearFounded = 0;
            try {
                System.out.print("Enter year founded: ");
                String yearFoundedInput = input.nextLine();
                if(!yearFoundedInput.isEmpty()){
                    yearFounded = Integer.parseInt(yearFoundedInput);
                }
                System.out.print("Enter total amount of students: ");
                String studentBodyInput = input.nextLine();
                if (!studentBodyInput.isEmpty()) {
                    studentBody = Integer.parseInt(studentBodyInput);
                }
            }catch(NumberFormatException e){ //handles exception for invalid inputs other than integers
                System.out.println("Invalid input for student body or year founded. University not added.");
                return;
            }
            //create a new Institution object and add it to the list
            Institution newInstitution = new Institution(officialName, nickName, city, state, studentBody, yearFounded);
            universityList.add(newInstitution);
            System.out.println("University successfully added.");
        }
    }

    public static void doListCommand(ArrayList<Institution> universityList){ //lists universities
        if(universityList.isEmpty()) {
            System.out.println("No university added to the list.");
        }
        else{
            System.out.println("Added universities are:" + "\n");

            for(Institution university: universityList){
                System.out.println(university);
                System.out.println();
            }
        }
    }

    public static void doSearchCommand(Scanner input, ArrayList<Institution> universityList){ //searches for universities by name or nickname
        System.out.println("Enter name or nickname: ");
        String searchName = input.nextLine();
        boolean found = false;
        for (Institution university: universityList){
            if (university.getOfficialName().equalsIgnoreCase(searchName) || university.getNickname().equalsIgnoreCase(searchName) ){
                System.out.println("University found: ");
                System.out.println(university);
                found = true;
                break;
            }
        }
        if (!found){ //if university name is not in the list
            System.out.println("University not found.");
        }
    }

    public static void doDeleteCommand(Scanner input, ArrayList<Institution> universityList){ //deletes universities by name
        System.out.println("Enter name of the university: ");
        String deleteName = input.nextLine();
        boolean deleted = false;

        // Iterate through the universityList and remove the matching university
        Iterator<Institution> iterator = universityList.iterator();
        while(iterator.hasNext()){
            Institution university = iterator.next();
            if(university.getOfficialName().equalsIgnoreCase(deleteName)){
                iterator.remove(); //removes university from the list
                deleted = true;
                break; //exit loop
            }
        }
        if(deleted){
            System.out.println("University deleted.");
        }else {
            System.out.println("University not found.");
        }
    }

    public static void saveToFile(ArrayList<Institution> universityList){
        try (PrintWriter writer = new PrintWriter(new FileWriter("saved.txt", true))){
            if(universityList.isEmpty()){
                System.out.println("List is empty");
            }else{
               for(Institution university : universityList) {
                   //save university details to the file
                   writer.println("University Name: " + university.getOfficialName());
                   writer.println("Nickname: " + university.getNickname());
                   writer.println("City: " + university.getCity());
                   writer.println("State: " + university.getState());
                   writer.println("Year Founded: " + university.getYearFounded());
                   writer.println("Student Body: " + university.getStudentBody());
                   writer.println();
                   writer.flush();
               }
            }
        } catch (IOException e){
            System.out.println("Error saving list to file.");        }
    }
}

