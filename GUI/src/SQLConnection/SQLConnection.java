package SQLConnection;

import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLConnection
{
    //连接数据库的所有属性
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//jdbc ->sql server 驱动器
    private static String dbURL = "jdbc:sqlserver://192.168.43.145:1433;DatabaseName=Account";//数据库所在主机与端口
    private static String userName = "user";
    private static String userPwd = "123456";
    static Connection dbConn = null;

    public SQLConnection()
    {
        try
        {
            Class.forName(driverName);
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("连接数据库成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.print("连接失败");
        }
    }
    public void Close() throws SQLException
    {
        dbConn.close();
    }
    public boolean InsertAccount(Account.AccountType type, Account a) throws SQLException
    {
        try
        {
            PreparedStatement pstmt = dbConn.prepareStatement("INSERT INTO dbo.Account values(?,?,?)");
            pstmt.setString(1, type.toString());
            pstmt.setString(2, a.toSQLServerString());
            pstmt.setString(3,String.valueOf(a.getId()));
            pstmt.executeUpdate();
            System.out.println("成功写入" + a.toString());
            pstmt.close();
            return true;
        }catch (Exception e)
        {
            System.out.println("写入失败");
            return false;
        }
    }

    public boolean DeleteAccount(Account a) throws SQLException
    {
        try
        {
            PreparedStatement pstmt = dbConn.prepareStatement("DELETE FROM dbo.Account WHERE class=?");
            pstmt.setString(1, a.toSQLServerString());
            pstmt.executeUpdate();
            System.out.println("删除成功" + a.toString());
            pstmt.close();
            return true;
        }catch(Exception e)
        {
            System.out.println("删除失败");
            return false;
        }

    }

    public boolean UpdateAccount(Account a) throws SQLException
    {
        try
        {
            PreparedStatement pstmt = dbConn.prepareStatement("UPDATE dbo.Account SET class=? WHERE id=?");
            pstmt.setString(1,a.toSQLServerString());
            pstmt.setString(2,String.valueOf(a.getId()));
            pstmt.executeUpdate();
            System.out.println("修改成功");
            pstmt.close();
            return true;
        }catch(Exception e)
        {
            System.out.println("修改失败");
            return false;
        }
    }

    public Account SelectAccount(long id) throws SQLException
    {
        String queryAnswer = null,queryType = null;
        Account a = null;
        try
        {
            PreparedStatement pstmt = dbConn.prepareStatement("SELECT type,class FROM dbo.Account WHERE id=?");
            pstmt.setString(1, String.valueOf(id));
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                queryType = rs.getString(1);
                queryAnswer = rs.getString(2);
            }

            if(queryAnswer == null)
            {
                System.out.println("该账户不存在");
                return null;
            }
            System.out.println("查询成功");
            pstmt.close();
        }catch(Exception e)
        {
            System.out.println("查询失败");
            return null;
        }
        String[] splitQueryAnswer = queryAnswer.split(",");
        switch (queryType)
        {
            case "Administrator": a = new Administrator(
                    Long.parseLong(splitQueryAnswer[0]),
                    splitQueryAnswer[1]
            );break;
            case "CreditAccount": a = new CreditAccount(
                    Long.parseLong(splitQueryAnswer[0]),
                    splitQueryAnswer[1],
                    splitQueryAnswer[2],
                    splitQueryAnswer[3],
                    splitQueryAnswer[4],
                    Double.parseDouble(splitQueryAnswer[5]),
                    Boolean.parseBoolean(splitQueryAnswer[6]),
                    CreditAccount.CreditAccountTypeValue.parseCATV(splitQueryAnswer[7]),
                    Double.parseDouble(splitQueryAnswer[8]),
                    Double.parseDouble(splitQueryAnswer[9])
            ); break;
            case "SavingAccount": a = new SavingAccount(
                    Long.parseLong(splitQueryAnswer[0]),
                    splitQueryAnswer[1],
                    splitQueryAnswer[2],
                    splitQueryAnswer[3],
                    splitQueryAnswer[4],
                    Double.parseDouble(splitQueryAnswer[5]),
                    Boolean.parseBoolean(splitQueryAnswer[6])
            );break;
            case "LoansAccount" : a = new LoansAccount(
                    Long.parseLong(splitQueryAnswer[0]),
                    splitQueryAnswer[1],
                    splitQueryAnswer[2],
                    splitQueryAnswer[3],
                    splitQueryAnswer[4],
                    Double.parseDouble(splitQueryAnswer[5]),
                    Boolean.parseBoolean(splitQueryAnswer[6]),
                    Integer.parseInt(splitQueryAnswer[7]),
                    new LoansAccount.Mortgage(Integer.parseInt(splitQueryAnswer[8]),
                            Integer.parseInt(splitQueryAnswer[9]),
                            Integer.parseInt(splitQueryAnswer[10]),
                            Integer.parseInt(splitQueryAnswer[11]),
                            Double.parseDouble(splitQueryAnswer[12])),
                    Double.parseDouble(splitQueryAnswer[13]),
                    Double.parseDouble(splitQueryAnswer[14])
            );break;
        }
        return a;
    }

    /**
     * 查询所有账户 管理员账户操作
     */
    public Account[] SelectAllAccount()
    {
        String[] queryAnswer = new String[1000],queryType = new String[1000];
//        Account[] a = new Account[1000];
        int indexAccount = 0;//放在Account数组中的第indexAccount个
        try
        {
            PreparedStatement pstmt = dbConn.prepareStatement("SELECT * FROM dbo.Account");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                queryType[indexAccount] = rs.getString(1);
                queryAnswer[indexAccount] = rs.getString(2);
                indexAccount++;
            }
            pstmt.close();
        }catch(Exception e)
        {
            System.out.println("查询失败");
            return null;
        }

        //查询结果已放入queryType,queryAnswer中
        Account[] a = new Account[indexAccount];
        for(int i = 0; i < indexAccount;i++)
        {
            String[] splitQueryAnswer = queryAnswer[i].split(",");
            switch (queryType[i])
            {
                case "Administrator": a[i] = new Administrator(
                        Long.parseLong(splitQueryAnswer[0]),
                        splitQueryAnswer[1]
                );break;
                case "CreditAccount": a[i] = new CreditAccount(
                        Long.parseLong(splitQueryAnswer[0]),
                        splitQueryAnswer[1],
                        splitQueryAnswer[2],
                        splitQueryAnswer[3],
                        splitQueryAnswer[4],
                        Double.parseDouble(splitQueryAnswer[5]),
                        Boolean.parseBoolean(splitQueryAnswer[6]),
                        CreditAccount.CreditAccountTypeValue.parseCATV(splitQueryAnswer[7]),
                        Double.parseDouble(splitQueryAnswer[8]),
                        Double.parseDouble(splitQueryAnswer[9])
                ); break;
                case "SavingAccount": a[i] = new SavingAccount(
                        Long.parseLong(splitQueryAnswer[0]),
                        splitQueryAnswer[1],
                        splitQueryAnswer[2],
                        splitQueryAnswer[3],
                        splitQueryAnswer[4],
                        Double.parseDouble(splitQueryAnswer[5]),
                        Boolean.parseBoolean(splitQueryAnswer[6])
                );break;
                case "LoansAccount" : a[i] = new LoansAccount(
                        Long.parseLong(splitQueryAnswer[0]),
                        splitQueryAnswer[1],
                        splitQueryAnswer[2],
                        splitQueryAnswer[3],
                        splitQueryAnswer[4],
                        Double.parseDouble(splitQueryAnswer[5]),
                        Boolean.parseBoolean(splitQueryAnswer[6]),
                        Integer.parseInt(splitQueryAnswer[7]),
                        new LoansAccount.Mortgage(Integer.parseInt(splitQueryAnswer[8]),
                                Integer.parseInt(splitQueryAnswer[9]),
                                Integer.parseInt(splitQueryAnswer[10]),
                                Integer.parseInt(splitQueryAnswer[11]),
                                Double.parseDouble(splitQueryAnswer[12])),
                    Double.parseDouble(splitQueryAnswer[13]),
                    Double.parseDouble(splitQueryAnswer[14])
                );break;
            }
        }
        return a;
    }

    /**
     * 查询帐户数量
     * @return
     */
    public int Number()
    {
        int indexAccount = 0;//放在Account数组中的第indexAccount个
        try
        {
            PreparedStatement pstmt = dbConn.prepareStatement("SELECT id FROM dbo.Account");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                indexAccount++;
            }
        }catch(Exception e)
        {
            System.out.println("查询失败");
            return 0;
        }
        return indexAccount;
    }

    /**
     * 分配新的id
     * @return
     */
    public long CreateNewID()
    {
        long id = 1;//放在Account数组中的第indexAccount个
        List<Long> list = new ArrayList<Long>();
        try
        {
            PreparedStatement pstmt = dbConn.prepareStatement("SELECT id FROM dbo.Account");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                list.add(rs.getLong(1));
            }
        }catch(Exception e)
        {
            System.out.println("查询失败");
            return 0;
        }
        while(list.contains(id)) id++;
        return id;
    }
}