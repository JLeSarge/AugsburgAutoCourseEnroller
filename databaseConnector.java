public class databaseConnector {
    private static String  URL;
    private static Student  user;
    private static Connection myConn;
    private static Statment myStat;
    private static Date event;

    public static void setURL(String url){
        URL = url;
    }
    public static void setUSER(Student student){
        user = student;
    }
    //connects to database usering url to find it and user and password to access
    public static void connectDatabaseIntance(){
        myConn = DriverManger.getConnection(URL,user,"password");
    }
    public static void createStatment(){
        myStat = myConn.createToStatment();
    }
    public static void createEventTime(){
        event = new Date();
    }
    //the actual sql statment that inserts the information (name,studentid,date)
    public static void createSQL(){
        String sql = "insert into basic-database (name, augsburg_id, event_log, Enrollment_Condition) values ('"
            +user.getName()+"', '"+user.getStudentID()+"', "+event+"', "+user.getEnrolled()+"')";
    }
    //uses mystat to create an update statment for sql
    public static void executeSQL(){
        myStat.executeUpdate(createSQL());
    }
    //combines other methods to generate all the variables in one method.
    public static void initalizeVariables(String url, Student student){
        setURL(url);
        setUSER(user);
        createStatment();
        createEventTIme();
    }
    //runs connect to database instance, intialize variables and executes the sql update
    public static void connect(String url, Student student){
        try{
            connectDatabaseIntance();
            initalizeVariables(url,student);
            executeSQL();
        }
        catch (Exeption exc){
            exc.printStackTrace();
        }
    }
}
