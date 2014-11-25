import java.util.Scanner;

public class Subscribe {        
        //calls the search seach function to find user first
        
    
        public void SubscribeToUser(String User){
            
            Scanner in = new Scanner(System.in);
            String ans;
            
            System.out.println("Are you sure you want to subcribe to user " + User + " ? (Yes or No)");
            ans = in.nextLine();
            
            if(ans == "Yes" || ans == "yes"){
                /*IN MY SQL
                The searched user object is added under the "following" field
                Now the user you subscribed to will be displayed on your feed
                */
            }
            else if(ans == "No" || ans == "no"){
                System.out.println("You have not subscribed to this user, you are being returned to the main menu.");
            }
            
        }
        
        
        public void SubscribeToGroup(String Group){
            
            Scanner in = new Scanner(System.in);
            String ans2;
            
            System.out.println("Are you sure you want to subcribe to user " + Group + " ? (Yes or No)");
            ans2 = in.nextLine();
            
            if(ans2 == "Yes" || ans2 == "yes"){
                //add group tag to user's table under the "group" field
                
            }
            else if(ans2 == "No" || ans2 == "no"){
                System.out.println("You have not subscribed to this group, you are being returned to the main menu.");
            } 
        }
}
