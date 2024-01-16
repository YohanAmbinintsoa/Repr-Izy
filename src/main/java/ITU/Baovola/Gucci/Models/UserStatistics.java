package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import ITU.Baovola.Gucci.Security.MyContext;

public class UserStatistics {
    int month;
    String monthName;
    int year;
    int userCount;

    public static List<UserStatistics> getMonthlyStatistics(String year) throws Exception{
        Connection con=MyContext.getRequester().connect();
        List<UserStatistics> stat=new ArrayList<>();
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("SELECT\r\n" + //
                "    months.month AS registration_month,\r\n" + //
                "    years.year AS registration_year,\r\n" + //
                "    COALESCE(COUNT(u.idutilisateur), 0) AS user_count\r\n" + //
                "FROM\r\n" + //
                "    (\r\n" + //
                "        SELECT EXTRACT(MONTH FROM generate_series(DATE '"+year+"-01-01', DATE '"+year+"-12-31', '1 month'::interval)) AS month\r\n" + //
                "    ) months\r\n" + //
                "CROSS JOIN\r\n" + //
                "    (\r\n" + //
                "        SELECT EXTRACT(YEAR FROM DATE '"+year+"-01-01') AS year\r\n" + //
                "    ) years\r\n" + //
                "LEFT JOIN\r\n" + //
                "    Utilisateur u ON EXTRACT(MONTH FROM u.dateinscription) = months.month AND EXTRACT(YEAR FROM u.dateinscription) = years.year\r\n" + //
                "GROUP BY\r\n" + //
                "    months.month, years.year\r\n" + //
                "ORDER BY\r\n" + //
                "    years.year, months.month;");
        while (res.next()) {
            UserStatistics userStat=new UserStatistics(res.getInt("registration_month"), res.getInt("registration_year"), res.getInt("user_count")); 
            userStat.monthName=Month.of(userStat.getMonth()).name();
            stat.add(userStat);
        }
        con.close();
        return stat;
    }
    
    public UserStatistics(int month, int year, int userCount) {
        this.month = month;
        this.year = year;
        this.userCount = userCount;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getUserCount() {
        return userCount;
    }
    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
}
