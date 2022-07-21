import java.io.*;
import oracle.jdbc.*;
import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class projmenu 
{
    public static void main(String[] args) throws SQLException
    {
        try
        {
            int choose_option = -1;
            int choose_table_option = -1;
            //Connection to Oracle server
            OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
            ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:ACAD111");
            Connection conn = ds.getConnection("anirmal1","Athira95");
            BufferedReader readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
            
            while(choose_option != 0)
            {
                    //PRINT THE MENU 
                System.out.println("\nSelect the operation you want to perform: \n");
                System.out.println("(1) Display all the tables in the database.");
                System.out.println("(2) Show class details for a given class");
                System.out.println("(3) Display all the prerequisite courses for the given course.");
                System.out.println("(4) Enroll a student into the class.");
                System.out.println("(5) Drop a student from the class.");
                System.out.println("(6) Delete a student from the students table");
                System.out.println("(7) exit");
                System.out.print("\nEnter the value: ");
                choose_option = Integer.parseInt(readKeyBoard.readLine());             
                switch(choose_option)
                {
                        //CASE: 1 DISPLAY THE TABLE AS PER INPUT
                    case 1: System.out.println("\nSelect the table you want to display: \n");
                            System.out.println("(1) students");
                            System.out.println("(2) courses");
                            System.out.println("(3) classes");
                            System.out.println("(4) g_enrollments");
                            System.out.println("(5) prerequisites");
                            System.out.println("(6) course_credit");
                            System.out.println("(7) score_grade");
                            System.out.println("(8) logs");
                            System.out.print("\nEnter the value: ");
                            choose_table_option = Integer.parseInt(readKeyBoard.readLine());
                            displayTables(conn, choose_table_option);
                            break;
                    case 2: classInfo(conn);
                            break;
                    case 3: getCoursePrereq(conn);
                            break;
                    case 4: enrollStudent(conn);
                            break;
                    case 5:  dropStudent(conn);
                            break;
                    case 6: deleteStudent(conn);
                            break;
                    case 0: System.out.println("Exiting the program.");
                            break;
                    default: System.out.println("Invalid option selected.");
                             break;
                }
            }
            conn.close();
        }
        catch(SQLException ex){
            System.out.println("\n*** SQLException Caught ***\n"+ex);
        }
        catch(Exception e){
            System.out.println("\n*** Other Exception Caught ***\n"+e);
        }
    }
//TO DISPLAY THE TABLES
      public static void displayTables(Connection conn, int choose_table_option) throws SQLException
    {
        try
        {
            CallableStatement cs;
            ResultSet rs;
            //Calling show_students procedure from the SQL_pro2 package to display student's table
            switch(choose_table_option)
            {
                case 1: //STUDENT TABLE
                        cs = conn.prepareCall("begin project2_procedures.show_students(?); end;");
                        cs.registerOutParameter(1, OracleTypes.CURSOR);
                        
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("Students: ");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("B#\tfirstname\tlastname\tstatus\tgpa\temail");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
                        while (rs.next())
                        {
                            System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3)+"\t\t\t"+rs.getString(4)+"\t\t\t"+rs.getString(5)+"\t\t"+rs.getString(6));
                        }
                        break;
                case 2: //COURSES TABLE
                        cs = conn.prepareCall("begin project2_procedures.show_courses(?); end;");
                        cs.registerOutParameter(1,OracleTypes.CURSOR);
                      
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("\nCOURSES TABLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        System.out.println("DEPT_CODE\t\tCOURSE_NO\t\tTITLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        while (rs.next())
                        {
                        System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
                        }
                        break;
                case 3: //CLASSES TABLE
                        cs = conn.prepareCall("begin project2_procedures.show_classes(?); end;");
                        cs.registerOutParameter(1,OracleTypes.CURSOR);
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("\nCLASSES TABLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("CLASSID\tDEPT_CODE\tCOURSE_NO\tSECT_NO\tYEAR\tSEMESTER\tLIMIT\tCLASS_SIZE\tROOM\t");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                        while (rs.next())
                        {
                        System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\t\t"+rs.getString(8));
                        }
                        break;
                case 4: //G_ENROLLMENTS TABLE
                        cs = conn.prepareCall("begin project2_procedures.show_genrollments(?); end;");
                        cs.registerOutParameter(1,OracleTypes.CURSOR);
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("\nG ENROLLMENTS TABLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        System.out.println("B#\t\t\tCLASSID\t\t\tSCORE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        while (rs.next())
                        {
                        System.out.println(rs.getString (1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
                        }
                        break;
                case 5: //PRE REQUISITES TABLE
                        cs = conn.prepareCall("begin project2_procedures.show_prerequisites(?); end;");
                        cs.registerOutParameter(1,OracleTypes.CURSOR);
                     
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("\nPREREQUISITES TABLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        System.out.println("DEPT_CODE\t\tCOURSE_NO\t\tPRE_DEPT_CODE\t\tPRE_COURSE_NO");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        while (rs.next())
                        {
                        System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3)+"\t\t\t"+rs.getString(4));
                        }
                        break;
                case 6: //COURSE CREDIT TABLE
                        cs = conn.prepareCall("begin project2_procedures.show_course_credit(?); end;");
                        cs.registerOutParameter(1,OracleTypes.CURSOR);  
                      
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("\tCOURSE CREDIT TABLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");                                       
                        System.out.printf("COURSE NO\tCREDITS");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        while(rs.next())
                        {
                        System.out.println(rs.getString(1)+"\t"+rs.getString(2));
                        }
                        break;
                case 7: //SCORE GRADE
                        cs = conn.prepareCall("begin project2_procedures.show_score_grade(?); end;");
                        cs.registerOutParameter(1,OracleTypes.CURSOR);  
                   
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("\tSCORE GRADE TABLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");                                       
                        System.out.printf("SCORE\tLGRADE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        while(rs.next())
                        {
                        System.out.println(rs.getString(1)+"\t"+rs.getString(2));
                        }
                        break; 
                case 8: //LOGS TABLE
                        cs = conn.prepareCall("begin project2_procedures.show_logs(?); end;");
                        cs.registerOutParameter(1,OracleTypes.CURSOR);  
                       
                        cs.execute();
                        rs = (ResultSet)cs.getObject(1);
                        System.out.println("\nLOGS TABLE");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");                                       
                        System.out.printf("LOG NO\tUSER NAME\tOP TIME\tTABLE NAME\tOPERATION\tKEY VALUE\n");
                        System.out.println("-------------------------------------------------------------------------------------------------------------");
                        while(rs.next())
                        {
                        System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t\t"+rs.getString(6));
                        }
                        break;
      
    


                default: System.out.println("Invalid option selected.");
                         break;
            }
        
        }
        catch(SQLException ex){
                System.out.println("SQL Exception in Print table function:");
                System.out.println(ex);
        }
        return;
    }

//TO GET THE CLASS DETAILS AS PER THE GIVEN CLASSID
    public static void classInfo(Connection conn)
    // This function is used to get the the class details
    {
    try
    {
    BufferedReader readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the class ID");
    String cid = readKeyBoard.readLine();
    CallableStatement cs = conn.prepareCall("{? = CALL get_class_details(:1,:2,:3)}");
    cs.setString(1,cid);
    cs.registerOutParameter(2,Types.VARCHAR);
    cs.registerOutParameter(3,OracleTypes.CURSOR);
    cs.execute();

    String showmessage = null;
    showmessage =  cs.getString(2);
    ResultSet rs = (ResultSet)cs.getObject(3);

    if(showmessage == null)
    {
   
    System.out.println("B#\tFIRST NAME\tLAST NAME");
    System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    while(rs.next())
    {	
    System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t" + rs.getString(3));
    }
    }
    else
    {
    System.out.println(showmessage);
    }
    cs.close();
    }
    catch(SQLException ex){
    System.out.println("SQL Exception in classInfoStudents function");
    }
    catch(Exception e){
    System.out.println("Exception in classInfoStudents");
    }

    return;
    }

  
//DELETE THE STUDENT ACCORDING TO GIVEN B#
    public static void deleteStudent(Connection conn) throws SQLException
    {
    try
    {
        BufferedReader readKey = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the student SID to delete");
        String sid = readKey.readLine();
        CallableStatement cs = conn.prepareCall("{? = CALL begin delete_student(:1)}");
        cs.setString(1,sid);
        cs.execute();
        }
        catch(SQLException ex){
        System.out.println("SQLException in delete Student function");
    }
    catch(Exception e){
    System.out.println("Exception in delete Student function");
    }
    }

//GET THE PREREQUISITE COURSE FOR A GIVEN COURSE
    public static void getCoursePrereq(Connection conn) throws SQLException

    {
         //This function is used to get the prerequisite courses
    try
    {
    BufferedReader readKey = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the DeptCode");
    String deptCode = readKey.readLine();
    System.out.println("Enter the course number");
    String coursen = readKey.readLine();
    int courseNo = Integer.parseInt(coursen);
    int count = 0;
    String result;
    CallableStatement cs = conn.prepareCall("begin  get_prereq_courses(:1,:2,:3,:4); end;");
    cs.setString(1,deptCode);
    cs.setInt(2,courseNo);
    cs.setInt(3,count);
    cs.registerOutParameter(4, Types.VARCHAR);
    cs.execute();
    result = cs.getString(4);
    //Print the results
    System.out.println("PRE-REQUISITE COURSES");
    System.out.println("-----------------------------------------------------------------------------------------------");  
    System.out.println(result);
    cs.close();
    }
    catch(SQLException ex){
    System.out.println("SQL Exception in getCoursePrereq funtion");
    }
    catch(Exception e){
    System.out.println("Exception in getCoursePrereq function");
    }
    }

 //ENROLL A STUDENT, WITH GIVEN B# INTO THE GIVEN CLASS

    public static void enrollStudent(Connection conn) throws SQLException
    {
         //This function is used to enroll a new student
    try
    {
    BufferedReader readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the student ID");
    String sid = readKeyBoard.readLine();
    System.out.println("Enter the class ID");
    String cid = readKeyBoard.readLine();
    CallableStatement cs = conn.prepareCall("begin enroll_student(:1,:2,:3); end;");
    cs.setString(1,sid);
    cs.setString(2,cid);
    cs.registerOutParameter(3,Types.VARCHAR);
    cs.execute();

    String showmessage = null;
    showmessage =  cs.getString(3);
    System.out.println(showmessage);		

    cs.close();
    }
    catch(SQLException ex){
    System.out.println("SQL Exception in enrollment function");
    }
    catch(Exception e){
    System.out.println("Exception in enrollement");
    }

    return;
    }

//DROP A STUDENT FROM A PARTICULAR CLASS

    public static void dropStudent(Connection conn) throws SQLException
    {  
         //This function is used to drop a student
    try
    {
    BufferedReader readKeyBoard = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the student ID");
    String sid = readKeyBoard.readLine();
    System.out.println("Enter the class ID");
    String cid = readKeyBoard.readLine();
    CallableStatement cs = conn.prepareCall("begin drop_student(:1,:2,:3); end;");
    cs.setString(1,sid);
    cs.setString(2,cid);
    cs.registerOutParameter(3,Types.VARCHAR);
    cs.execute();

    String showmessage = null;
    showmessage =  cs.getString(3);
    System.out.println(showmessage);
            
    cs.close();
    }
    catch(SQLException ex){
    System.out.println("SQL Exception in dropstudent function");
    }
    catch(Exception e){
    System.out.println("Exception in dropstudent");
    }

    return;	
    }

}
