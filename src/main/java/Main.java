import MVC.Config.DB;
import MVC.Dao.All_op_timer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    private static Connection con = DB.getConnection();
    private static PreparedStatement ps;
    private static String query;
    static ResultSet resultSet = null;
    public static   Timer timer;
    public static HashMap<Integer, String> list_date_experation = new HashMap<Integer, String>();
    public  static  ArrayList<Integer> list_date_experation_end = new ArrayList<>();
    public static String add_some_days_to_my_datetime(int days) {
        // date formats
        String format = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        // Date format
        DateTimeFormatter dateFormat_li_bina = DateTimeFormatter.ofPattern(format);

        // creating date in java
        Date date = new Date();

        // converting date to LocalDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        System.out.println(dateFormat_li_bina.format(localDateTime));
        // add 2 dyas to date local
        localDateTime = localDateTime.plusDays(days);

        // converting LocalDateTime to Date
        Date currentDatePlus = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // printing the new date
        String newDateinStr = dateFormat.format(currentDatePlus);
//        System.out.println(newDateinStr);
        return newDateinStr;
    }

    public static boolean xof_wax_kmlatwa9to(String date_expiration_str){
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss" ) ;
        LocalDateTime date_expiration = LocalDateTime.parse( date_expiration_str , f ) ;
        LocalDateTime local_date_time_dyali_ana = LocalDateTime.parse(Main.get_time_ma_local(), f);

        if (date_expiration.equals(local_date_time_dyali_ana) || date_expiration.isBefore(local_date_time_dyali_ana)){
            return true;
        }

        return false;
    }

//    fonction remove row in HashMap and database
    public static void  remove_row(){
//        list_date_experation.remove(id);
        for (int i =0; i<list_date_experation_end.size(); i++){
//            list_date_experation.remove(list_date_experation_end.get(i));
            System.out.println("remove  expiration ==> "+list_date_experation.remove(list_date_experation_end.get(i)));
            removedate_expiration(list_date_experation_end.get(i));
        }
    }

    public  static String get_time_ma_local(){
        String format = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        // Date format in java 8
        DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(format);

        // creating date in java
        Date date = new Date();

        // converting date to LocalDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dateFormat8.format(localDateTime);
    }

    public static void getallexpiration(){
            try {
                query = "select * from expiration";
                ps = con.prepareStatement(query);
                resultSet = ps.executeQuery();
                while(resultSet.next()){
                    list_date_experation.put(resultSet.getInt("id"), resultSet.getString("date_expiration"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public static void removedate_expiration(int id){
        try {
            query = "DELETE FROM expiration WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        getallexpiration();
        System.out.println(add_some_days_to_my_datetime(0));
        timer = new Timer();
        TimerTask task = new Helper();

        timer.schedule(task, 1000, 9000);



//        // Add keys and values (Country, City)
//        list_date_experation.put(1,  "2022-11-04T00:01:24");
//        list_date_experation.put(2,  "2022-11-04T00:46:59");
//        list_date_experation.put(3,  "2022-11-04T00:45:24");
//        list_date_experation.put(4,  "2022-11-04T00:50:59");
//        list_date_experation.put(5,  "2022-11-03T00:47:59");
//        list_date_experation.put(6,  "2022-11-04T00:42:59");



    }
}
class Helper extends TimerTask
{

    public void run()
    {
        if (Main.list_date_experation.size()<= 0) {
            Main.timer.cancel();
            Main.timer.purge();
            return;
        }
//        Main.date_experation.remove(4);
        if (Main.list_date_experation.size()>0) {
            for (int i : Main.list_date_experation.keySet()) {
                if (Main.xof_wax_kmlatwa9to(Main.list_date_experation.get(i))) {
                    Main.list_date_experation_end.add(i);
                    System.out.println(Main.list_date_experation.get(i));
                }
            }
            if (Main.list_date_experation_end.size()>0)
            {
                Main.remove_row();
                Main.list_date_experation_end.clear();
            }
        }
    }
}