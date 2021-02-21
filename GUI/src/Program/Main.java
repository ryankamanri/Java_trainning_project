package Program;
import Model.*;
import SQLConnection.SQLConnection;
import Service.*;
import java.sql.*;

public class Main
{
    public static void main(String[] args) throws SQLException
    {

        Bank bank = new Bank();
//        bank.register("123456","123456","h","430122","199",2000,1);
        Bank.Login user1 = new Bank.Login(Bank.Login.LogIn(1,"123456"));
        user1.ChangePassword("123456","111111");
        user1.Query();
//        Bank.Register();
//        bank.Loss(8,"123456");
//        bank.LogIn(8,"123456").Query();
//        double c,d;
//        CreditAccount a= new CreditAccount(001,"pwd","name","ID","123",123, CreditAccount.CreditAccountTypeValue.CreditAccountType.DIAMOND);
//        CreditAccount b= new CreditAccount();
//        a.setBalance(100);
//        a.Query();0
//        /*
//         * 转账测试
//         */
////		a.Transfer(b, 10);
////		a.Query();
////		b.Query();
//
//
//        /*
//         * 取款测试
//         */
//
//        a.Withdrawal(500, 10);
//        a.Query();
//        /*
//         * 存款测试
//         */
//        a.Saving(500,12);
//        a.Query();

        //数据库测试
        //查询
//        SQLConnection sqlc = new SQLConnection();
//
//        Account a = sqlc.SelectAccount(1);
//        System.out.println(a.toString());
//        sqlc.Close();
        //查询所有
//        SQLConnection sqlc = new SQLConnection();
//        Account[] accounts = sqlc.SelectAllAccount();
//        for (Object i : accounts) {
//            System.out.println(i);
//        }
//        System.out.println(sqlc.Number());
//        sqlc.Close();
//        //查询帐户数量  怕
//        SQLConnection sqlc = new SQLConnection();
//        sqlc.Number();
//        sqlc.Close();

        //修改
//        SQLConnection sqlc = new SQLConnection();
//        Account a = new LoansAccount(2,"684","684","684","64",64,new LoansAccount.Mortgage(0,54,64,984),645);
//        sqlc.UpdateAccount("LoanAccount",a);
//        sqlc.Close();
        //增加
//        SQLConnection sqlc = new SQLConnection();
//
//        Account a = new LoansAccount(3,"684","684","684","64",64,new LoansAccount.Mortgage(0,54,64,984),645);
//        sqlc.InsertAccount(Bank.AccountType.LoansAccount.toString(),a);
//            sqlc.Close();
//        //删除
//          SQLConnection sqlc = new SQLConnection();
//          sqlc.GetConnection();
//          Account a = new LoansAccount(3,"684","684","684","64",64,new LoansAccount.Mortgage(0,54,64,984),645);
//          sqlc.DeleteAccount(Bank.AccountType.LoansAccount.toString(),a);
//        sqlc.Close();


//        LoansAccount a = new LoansAccount(1,"pwd","hwl","430122","199",1000);
//        a.Query();
//        a.setMortgage(new LoansAccount.Mortgage(0,1,1,1));
//        a.Query();
//        a.Apply(100,12);
//
//        a.Query();
//        a.Repay(13);
//        a.Query();

    }
}
