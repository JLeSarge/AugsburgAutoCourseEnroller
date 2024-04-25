import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.SQLException;

public class DatabaseConnector {
    private static String  URL;
    private static Student  student;
    private static Connection myConn = null;
    private static Statement myStat;
    private static LocalDate event;

    public static void setURL(String url){
        URL = url;
    }
    public static void setUSER(Student stu){
        student = stu;
    }
    public static void createStatment(){
        try {
        myStat = myConn.createStatement();
        } 
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void createConnectionInstance(){
        try{
            myConn = DriverManager.getConnection(URL+"user=root&password=password");
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void createEventTime(){
        event = LocalDate.now();
    }
    //the actual sql statment that inserts the information (name,studentid,date)
    public static String createSQL(){
        return
                ("insert into basic-database (name, augsburg_id, event_log, Enrollment_Condition) values ('"
            +student.getName()+"', '"+student.getStudentID()+"', "+event+"', "+student.getEnrolled()+"')");
    }
    //uses mystat to create an update statment for sql
    public static void executeSQL(){
        try{
        myStat.executeUpdate(createSQL());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    //combines other methods to generate all the variables in one method.
    public static void initalizeVariables(String url, Student student){
        setURL(url);
        setUSER(student);
        createStatment();
        createEventTime();
    }
    //runs connect to database instance, intialize variables and executes the sql update
    public static void connectAndUpdate(Student stu){
        String url = "jdbc:mysql://localhost/basic-database?";
        try{
            loadDriver();
            createConnectionInstance();
            initalizeVariables(url,stu);
            executeSQL();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    // initalize driver
    public static void loadDriver(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
