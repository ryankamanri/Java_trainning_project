package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import SQLConnection.SQLConnection;
import Model.LoansAccount.Mortgage;
import Service.Bank;

public class Administrator extends Account {


    public Administrator() {
        super();
        this.at = AccountType.Administrator;
    }

    public Administrator(long id, String password) {
        super(id,password);
        this.at = AccountType.Administrator;
    }


    //    �鿴�����˻�
    public void QueryAll() throws SQLException {
        SQLConnection sqlc = new SQLConnection();
        Account[] a = sqlc.SelectAllAccount();
        sqlc.Close();
        int number = sqlc.Number();
        for(int i=0;i<number;i++) {
            System.out.println(a[i]);
        }

        //System.out.println(Arrays.toString(acts));
    }

    //    ͳ�������˻����
    public double CountBalance() throws SQLException
    {
        double balance = 0;
        SQLConnection sqlc = new SQLConnection();
        Account[] a = sqlc.SelectAllAccount();
        int number = sqlc.Number();
        sqlc.Close();
        for(int i=0;i<number;i++) {
            balance+=a[i].getBalance();
        }

        return balance;

    }

    //    ͳ��͸֧���
    public double CountCreditLine() throws SQLException
    {
        double creditLine = 0;
        SQLConnection sqlc = new SQLConnection();
        Account[] a = sqlc.SelectAllAccount();
        int number = sqlc.Number();
        sqlc.Close();
        for(int i=0;i<number;i++) {
            creditLine+=((CreditAccount) a[i]).getCreditLine();
        }
        return creditLine;

    }

    //  ͳ�ƴ�����
    public double CountLoans() throws SQLException
    {
        double loans=0;;
        SQLConnection sqlc = new SQLConnection();
        Account[] a = sqlc.SelectAllAccount();
        int number = sqlc.Number();
        sqlc.Close();
        for(int i=0;i<number;i++) {
            if(a[i].getAccountType() == AccountType.LoansAccount)
            loans+=((LoansAccount) a[i]).getLoans();
        }
        sqlc.Close();
        return loans;
    }


    //ͳ���û��ʲ�����
    public void Rank() throws SQLException
    {

        SQLConnection sqlc = new SQLConnection();
        Account[] a = sqlc.SelectAllAccount();
        int number = sqlc.Number();
        sqlc.Close();
        for(int i=0;i<number;i++) {
            for(int j=0;j<sqlc.Number();j++) {
                if(a[j].getBalance()<a[j+1].getBalance()) {
                    Account b=a[j];
                    a[j]=a[j+1];
                    a[j+1]=b;
                }
            }
        }
        for(int i=0;i<number;i++) {
            System.out.println(a[i].getId()+" "+a[i].getBalance());
        }
    }
    
    //    ���
    public void RelieveLoss(long id) throws SQLException {
        SQLConnection sqlc = new SQLConnection();
        Account account = sqlc.SelectAccount(id);
        if( account != null)
        {
            System.out.println("��ҳɹ�");
            account.setLoss(false);
            sqlc.UpdateAccount(account);
            sqlc.Close();
        }
        else {
            System.out.println("���ʧ��");
        }

    }


}