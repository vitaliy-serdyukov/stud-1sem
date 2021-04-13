package MyFriends;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

  private Scanner scan = new Scanner(System.in);
  private ArrayList<Friend> friends = new ArrayList<>();
  private String fileName = "Friends.txt";



  public static void main(String[] args)  {

    new Main().run();

  }


  public void run()  {

    String menuHeader = "MENU: ";
    String leadText = "Please choose: ";
    String[] menuItems = {"1. Show list of friends", "2. Enter new friend", "3. Delete friend",
        "4. Save list", "5. Load list", "9. Quit"};

    boolean run = true;
    int choice = -1;
    while (run) {
      Menu menu = new Menu(menuHeader, leadText, menuItems);
      menu.printMenu();
      choice = menu.readChoice();

      Main main = new Main();
      switch (choice) {
        case 1:
          System.out.println("Showing  our list of friends...");
          showList();
          break;
        case 2:
          System.out.println("Entering a new friend to our list of friends... ");
          enterNewFriend();
          break;
        case 3:
          System.out.println("Removing a friend from our list of friends...");
          deleteFriend();
          break;
        case 4:
          System.out.println("Saving our list of friends...");
          saveList();
          break;
        case 5:
          System.out.println("Loading  our list of friends...");
          loadList();
          break;
        case 9:
          System.out.println("Quitting...");
          run = false;
          break;
        default:
          System.out.println("The number you have chosen is invalid");
          break;

      }
    }
  }

  public void showList() { // shows the list of friends

    if (friends.size() == 0) {
      System.out.println("There are no friends in our list of friends yet...");
    } else {
    for (int i = 0; i < friends.size(); i++) {
      int friendNumber = i + 1;
      System.out.println("\nFriend number: " + friendNumber + ". " + friends.get(i));
      }
    }
  }

  public void enterNewFriend() { // creates a new friend with with insertion of Friend instance to ArrayList

    System.out.println("Now you would like to write a new friend.");
    Friend friend = new Friend();
    System.out.println("Please enter the name: ");
    friend.setName(scan.nextLine());
    System.out.println("Please enter phone number: ");
    friend.setPhone(scan.nextLine());
    System.out.println("Please enter e-mail:");
    friend.setEmail(scan.nextLine());
    friends.add(friend);

  }

  public void deleteFriend() { // removes Friend instance from ArrayList

    showList();

    System.out.println("Please enter the number of friend you wish to delete:");
    int friendOut = scan.nextInt() - 1;
    friends.remove(friendOut);

  }


  public void saveList() { // method for saving from ArrayList to Friends.txt file ("write to file")

    try {

      File fileSave = new File(fileName);
      PrintStream fileOut = new PrintStream(fileSave);

      fileOut.print(friends.toString().replaceAll("\\[","").replaceAll("]", "").
          replaceAll(", Name", "Name" ));
      fileOut.close();

    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }

  }

  public void loadList()  {  // method for loading from Friends.txt file to ArrayList ("read from file")

  //to do: received output doesn't look great, got all three parameters in one line...
    // have tried something with "contains" and name.split(",") or email.split (";") without success

    try {
      friends.clear();
      File fileRead  = new File(fileName) ;
      Scanner fileReader = new Scanner(fileRead);
      while (fileReader.hasNext()) {
        String name = fileReader.nextLine();
        String phone = fileReader.nextLine();
        String email = fileReader.nextLine();
        Friend friend = new Friend(name, phone,email);
        friends.add(friend);
        }
      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
  }
}



