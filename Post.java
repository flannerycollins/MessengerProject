public class Post {
    
    public void Post(){
        
        text = null;
        user = null;
        date = null;
        iD = 0;
        time = 0;
    }
    
    public void Post(String pText, String pUser, String pDate, int pID, int pTime){
        
        text = pText;
        user = pUser;
        date = pDate;
        iD = pID;
        time = pTime;
    }
    
    String text;
    String user;
    String date;
    int iD;
    int time;
}
