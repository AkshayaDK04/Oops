package Olympiad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Member{
    String userid;
    private String name;
    private String mail;
    private String passsword;

    static List<Member> members=new ArrayList<Member>();

    Member(String userid,String name,String mail,String password)
    {
        this.userid=userid;
        this.name=name;
        this.mail=mail;
        this.passsword=password;
        this.registeruser();
    }

    public void registeruser()
    {
        members.add(this);
    }

    public String getuserdetails()
    {
        return "UserId" + this.userid + "\n" +"Name" + this.name + "\n" +"Email" + this.mail + "\n" ;
    }

    public static boolean memberexists(String userid) {
        for (Member m : members) {
            if (m.userid.equals(userid) ) {
                return true; // Return the matching event
            }
        }
        return false; // If no matching event is found, return null
    }
}

class payment{
    public String message(int money)
    {
        return (money + " has been paid");
    }
    public String message(String userid)
    {
        return ("Hey "+userid+"! Your payment is successfull!" );

    }
}


class EventOwner extends Member{
    static int eoid=0;
    private int ownerid;
    static List<EventOwner> owners=new ArrayList<>();
    EventOwner(String userid,String name,String mail,String password,int ownerid)
    {
        super(userid, name, mail, password);
        this.ownerid=ownerid;

    }
    public static int allocateownerid()
    {
        return (++eoid);
    }

    public static boolean ownerexists(int oid) {
        for (EventOwner eo : owners) {
            if (eo.ownerid==oid ) {
                return true; // Return the matching event
            }
        }
        return false; // If no matching event is found, return null
    }
}

class Admin extends Member{
    static int adid=0;
    private int adminid;
    Admin(String userid,String name,String mail,String password,int adminId)
    {
        super(userid, name, mail, password);
        this.adminid=adminId;
    }
    public int allocateadminid()
    {
        return (++adid);
    }
}

class Event{
    static int eid;
    int eventid;
    String eventname;
    int totalcap;
    int filled;
    String owner;
    LocalDate date;
    int fee;

    static List<Event> events=new ArrayList<Event>();

    Event(){

    }

    Event( int eventid,String eventname, int totalcap, String owner,LocalDate date,int fee){
        this.eventid=eventid;
        this.eventname=eventname;
        this.totalcap=totalcap;
        this.owner=owner;
        this.date=date;
        this.fee=fee;
        this.createevent();
    }

    public static int allocateeventid()
    {
        return (++eid);
    }

    public void editevent(String eventname, int totalcap, String owner,LocalDate date,int fee)
    {
        this.eventname=eventname;
        this.totalcap=totalcap;
        this.owner=owner;
        this.date=date;
        this.fee=fee;
    }

    public void createevent()
    {
        events.add(this);
    }

    public static Event returnevent(int eventid) {
        for (Event e : events) {
            if (e.eventid == eventid) {
                return e; // Return the matching event
            }
        }
        return null; // If no matching event is found, return null
    }

    public boolean eventexists(int eventid) {
        for (Event e : events) {
            if (e.eventid == eventid) {
                return true; // Return the matching event
            }
        }
        return false; // If no matching event is found, return null
    }

    public Event returnevent(String eventname) {
        for (Event e : events) {
            if (e.eventname.equals(eventname)) {
                return e; // Return the matching event
            }
        }
        return null; // If no matching event is found, return null
    }

    public static void listevents()
    {
       for(Event e:events) {
           System.out.println( "EventName:" + e.eventname + "\nEventId:" + e.eventid + "\nDate:" + e.date + "\nAvailability:" + (e.totalcap - e.filled + "\nFee:" + e.fee));
       }

    }

}


class RegPerson{
    String name;
    int age;
    String place;

    RegPerson(){

    }
    RegPerson(String name,int age,String place){
        this.name=name;
        this.place=place;
        this.age=age;
    }
}

class EventRegistration {

    static int rid=0;
    String userid;
    int eventid;
    int count;
    int regid;
    long phnno;

    static List<EventRegistration> reg=new ArrayList<>();
    static List<RegPerson> registers=new ArrayList<>();

    EventRegistration(String userid, int eventid, int count, long phnno, int regid) {
        this.userid = userid;
        this.eventid = eventid;
        this.count = count;
        this.regid = regid;
        this.phnno = phnno;
        reg.add(this);
        registerpersons();
    }

    public void registerpersons(){
        Scanner scn=new Scanner(System.in);
        Event e=new Event();
        e=e.returnevent(eventid);
        e.filled+=count;
        for(int i=0;i<count;i++)
        {
            RegPerson r=new RegPerson();
            System.out.println("Enter name:");
            r.name=scn.nextLine();
            System.out.println("Enter age:");
            r.age=scn.nextInt();
            System.out.println("Enter place:");
            r.place=scn.nextLine();
            registers.add(r);

        }
    }

    public static boolean regexists(int oid) {
        for (EventRegistration eo : reg) {
            if (eo.rid==oid ) {
                return true; // Return the matching event
            }
        }
        return false; // If no matching event is found, return null
    }

    public static int allocateregid()
    {
        return (++rid);
    }

}

public class EventManagement {
    public static void main(String[] args) {
        int n=0;
        while(n!=5)
        {
            System.out.println("Welcome to EventsHandler..!\nNew User? Plz click 1 to register\nClick 2 to look for Events\nClick 3 to create or register an event\nClick 4 to register for an event\nClick 5 to cancel event registration\nClick 6 to exit");
            Scanner scn=new Scanner(System.in);
            n=scn.nextInt();
            switch (n)
            {
                case 1:
                    System.out.println("Enter 1.For event owner registration 2.for ordinary user registration");
                    int m=scn.nextInt();
                    switch (m){
                        case 1:
                            registerowner();
                            break;
                        case 2:
                            registeruser();
                            break;
                    }

                case 2:
                    Event.listevents();
                    break;
                case 3:
                    registerevent();
                    break;
                case 4:
                    registerforevent();
                    break;

                case 5:
                    canceleventregistration();
            }
        }

    }

    public static void registeruser(){
        Scanner scn=new Scanner(System.in);
        System.out.println("Enter name, userid, mailid, pswd");
        String name=scn.nextLine();
        String userid=scn.nextLine();
        String mailid=scn.nextLine();
        String pswd=scn.nextLine();
        Member m=new Member(userid,name,mailid,pswd);
    }

    public static void registerowner(){
        int oid=EventOwner.allocateownerid();
        Scanner scn=new Scanner(System.in);
        System.out.println("Enter name, userid, mailid, pswd");
        String name=scn.nextLine();
        String userid=scn.nextLine();
        String mailid=scn.nextLine();
        String pswd=scn.nextLine();
        EventOwner m=new EventOwner(userid,name,mailid,pswd,oid);
        EventOwner.owners.add(m);
        System.out.println("Your id is "+oid);
    }

    public static void registerevent()
    {
        Scanner scn=new Scanner(System.in);
        System.out.println("Have you registered as a event owner?yes/no");
        String str=scn.nextLine();
        int oid;
        if(str.equalsIgnoreCase("yes"))
        {
            System.out.println("Enter EventOwnerId:");
            oid=scn.nextInt();
            if(!EventOwner.ownerexists(oid))
            {
                System.out.println("You are not yet Registered! Plz register urself");
                registerowner();
            }
        }
        else {
            System.out.println("You are not yet Registered! Plz register urself");
            registerowner();
        }


        int eid=Event.allocateeventid();
        System.out.println("Enter Eventname:");
        scn.next();
        String eventname=scn.nextLine();
        System.out.println("Enter EventOwnerName:");
        String owner=scn.nextLine();
        System.out.println("Enter totalcapacity:");
        int totalcap=scn.nextInt();
        System.out.println("Enter date of event:");
        System.out.println("Enter year:");
        int yr=scn.nextInt();
        System.out.println("Enter month:");
        int month=scn.nextInt();
        System.out.println("Enter date:");
        int dte=scn.nextInt();
        LocalDate date = LocalDate.of(yr, month, dte);
        System.out.println("Enter fee:");
        int fee=scn.nextInt();
        Event e=new Event(eid,eventname,totalcap,owner,date,fee);

    }

    public static void canceleventregistration()
    {
        Scanner scn=new Scanner(System.in);
        System.out.println("Have you registered for event ? yes/no");
        String str= scn.nextLine();
        int rid;
        if(str.equalsIgnoreCase("yes"))
        {
            System.out.println("Enter RegOwnerId:");
            rid=scn.nextInt();

            if(!EventRegistration.regexists(rid))
            {
                System.out.println("You are not yet Registered! Plz register");
                registerforevent();
            }
        }
        else {
            System.out.println("You are not yet Registered! Plz register");
            registerforevent();
        }


    }

    public static void registerforevent()
    {
        Scanner scn=new Scanner(System.in);
        System.out.println("Have you registered as a member ? yes/no");
        String str=scn.nextLine();
        String userid=null;
        if(str.equalsIgnoreCase("yes"))
        {
            System.out.println("Enter Ur userid:");
            userid=scn.nextLine();
            if(!Member.memberexists(userid))
            {
                System.out.println("You are not yet Registered! Plz register urself");
                registeruser();
            }
        }

        System.out.println("Do u wanna explore the events? Yes / No");
        String resp=scn.nextLine();
        if(resp.equals("Yes"))
        {
            Event.listevents();
        }
        System.out.println("Do u wanna register for an event? Yes / No");
        resp=scn.nextLine();
        if(resp.equals("Yes"))
        {
            System.out.println("Enter EventId:");
            int eventid=scn.nextInt();
            System.out.println("Enter Count:");
            int count=scn.nextInt();
            int regid=EventRegistration.allocateregid();
            System.out.println("Enter Phn No:");
            long phnno= scn.nextLong();
            Event e=Event.returnevent(eventid);
            if(count<=(e.totalcap-e.filled)){


                    System.out.println("Total amount is "+(count*e.fee));
                    System.out.println("Enter amount to pay");
                    int amount= scn.nextInt();
                    if(count*e.fee==amount)
                    {
                        payment p=new payment();
                        System.out.println(p.message(amount));
                        System.out.println(p.message(userid));
                    }
                    else {
                        System.out.println("No cant pay! Enter right amount");
                    }

                EventRegistration er=new EventRegistration(userid,eventid,count,phnno,regid);
                System.out.println("Registered for the event successfully! Waiting to see u all in the event");

            }
            else{
                System.out.println("Oops! Sorry..Only "+(e.totalcap-e.filled)+" seats left");
            }
        }

    }
}
