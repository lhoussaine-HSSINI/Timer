package MVC.Dao;

import MVC.Model.ExpirationEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class All_op_timer {
    private static operationE  operation_expiration;
    public  static  Timer timer;

//    had lhash map tandir fiha key = id wl value= dateExpiration
    public static HashMap<Integer, String> list_date_experation = new HashMap<Integer, String>();

    //    had array list tandir fiha lisaydi li tsala lwa9t dyalhom wkhashom yt7aydo
    public  static ArrayList<Integer> list_date_experation_end = new ArrayList<>();

//    hna tanzid nharat li bghit n3tihom lih
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

//    hna tanxof wax lwa9t safi kmal wla mazal

    public static boolean xof_wax_kmlatwa9to(String date_expiration_str)
    {
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss" ) ;
        LocalDateTime date_expiration = LocalDateTime.parse( date_expiration_str , f ) ;
        LocalDateTime local_date_time_dyali_ana = LocalDateTime.parse(All_op_timer.get_time_ma_local(), f);

        if (date_expiration.equals(local_date_time_dyali_ana) || date_expiration.isBefore(local_date_time_dyali_ana)){
            return true;
        }

        return false;
    }

    //    fonction remove row in HashMap and database
    public static void  remove_row(){
        operation_expiration = new operationE();
//        list_date_experation.remove(id);
        for (int i =0; i<list_date_experation_end.size(); i++){
            list_date_experation.remove(list_date_experation_end.get(i));
            operation_expiration.delete_date_expiration(i);
        }
        All_op_timer.list_date_experation_end.clear();
    }

//    time dyal pc dyali
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

    public static void  main_start() {
        int i = 0;
        operationE op = new operationE();
        List<ExpirationEntity> list_experation = op.getAll_Expiration_date();
        if (!list_experation.isEmpty()) {
            for (ExpirationEntity e : list_experation) {
                if (!All_op_timer.list_date_experation.containsKey(e.getId())) {
                    All_op_timer.list_date_experation.put(e.getId(), e.getDateExpiration());
                    i=1;
                }
            }
            if (i==1){
                All_op_timer.main_start();
            }
        }
        timer = new Timer();
        TimerTask task = new Helper();
        timer.schedule(task, 1000, 5000);
    }

}
class Helper extends TimerTask
{

    public void run()
    {
//        Main.date_experation.remove(4);
        if (All_op_timer.list_date_experation.size()<= 0) {
            All_op_timer.timer.cancel();
            All_op_timer.timer.purge();
            return;
        }

        if (All_op_timer.list_date_experation.size()>0) {
            for (int i : All_op_timer.list_date_experation.keySet()) {
                if (All_op_timer.xof_wax_kmlatwa9to(All_op_timer.list_date_experation.get(i))) {
                    All_op_timer.list_date_experation_end.add(i);
                }
            }
        }
        if (All_op_timer.list_date_experation_end.size()>0)
        {

            All_op_timer.remove_row();
            All_op_timer.list_date_experation_end.clear();
        }
    }
}