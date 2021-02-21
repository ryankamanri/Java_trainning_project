


package Service;

import java.sql.SQLException;
import java.util.*;

import Model.*;
import Model.LoansAccount.Mortgage;
import Model.CreditAccount.CreditAccountTypeValue;
import Model.CreditAccount.CreditAccountTypeValue.CreditAccountType;
import SQLConnection.*;


public class Bank {

    //	����
    public Bank() { }

    //	����
    public Account register(String password,String confirmPassword, String name, String personID, String phoneNumber, double balance,int type) throws SQLException
    {
//		�˻�������
        Account act=null;
//		�ж����������Ƿ�һ��
        if(!password.equals(confirmPassword)) {
            System.out.println("�������벻һ�£�������ȷ��");
            return null;
        } else {
//			������0�����ǿ������˻�
            SQLConnection sqlc=new SQLConnection();
            if(type==0) {
                act=new SavingAccount(sqlc.CreateNewID(),password,name, personID,phoneNumber,balance,false);
                if(sqlc.InsertAccount(Account.AccountType.SavingAccount,act))
                    System.out.println("�����˻������ɹ�");
            }
//			����Ϊ1�����ǿ������˻�
            else if(type==1) {
                double creditLine = 0;
                CreditAccountTypeValue.CreditAccountType creditAccountType = CreditAccountType.ORDINARY;
                //��������ťѡ����������

                act=new CreditAccount(sqlc.CreateNewID(),password,name, personID,phoneNumber,balance,false,creditAccountType);
                if(sqlc.InsertAccount(Account.AccountType.CreditAccount,act))
                    System.out.println("�����˻������ɹ�");
            }
//			����Ϊ2�����ǿ������˻�
            else if(type==2){
                Mortgage mortgage = null;
                int time = 0;
                act=new LoansAccount(sqlc.CreateNewID(),password,name, personID,phoneNumber,balance,false);
                if(sqlc.InsertAccount(Account.AccountType.LoansAccount,act))
                    System.out.println("�����˻������ɹ�");
            }else {
                System.out.println("��ȡʧ�ܣ�������ע��");
            }
            sqlc.Close();

//			���ؿ�������Ϣ��ʾ�����ɹ�
            System.out.println("�����˺�idΪ: " + act.getId());
            return act;
        }

    }
    public static class Login
    {
        private Account account = null;

        /**
         * ��¼����
         * @param id
         * @param password
         * @return
         */
        public static Account LogIn(long id ,String password) throws SQLException
        {
            SQLConnection sqlc = new SQLConnection();
            Account act = sqlc.SelectAccount(id);
            if(act == null)
            {
                System.out.println("�˻�������,�����µ�¼");
                return null;
            }
            if(!password.equals(act.getPassword()))
            {
                System.out.println("�������,�����µ�¼");
                return null;
            }
            sqlc.Close();
            return act;
        }
        public Login(Account act) throws SQLException
        {
            this.account = act;
        }
        
        /**
         * �����˻���Ѻ�Ʋ�
         * @param estate
         * @param nationalDebt
         * @param security
         * @param stock
         */
        public void Mortgage(int estate,int nationalDebt,int security,int stock) throws SQLException
        {
            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return;
            }
            if(account.getAccountType() != Account.AccountType.LoansAccount)
            {
                System.out.println("Υ�����");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            LoansAccount la = (LoansAccount) account;
            la.setMortgage(new Mortgage(estate,nationalDebt,security,stock));
            sqlc.UpdateAccount(la);
            sqlc.Close();
        }

        /**
         * �����˻��������ÿ�����
         * @param type
         */
        public void SetCreditAccountType(CreditAccountType type) throws SQLException
        {
            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return;
            }
            if(account.getAccountType() != Account.AccountType.CreditAccount)
            {
                System.out.println("Υ�����");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            CreditAccount ca = (CreditAccount)account;
            ca.setCreditAccountTypeValue(new CreditAccountTypeValue(type));
            sqlc.UpdateAccount(ca);
            sqlc.Close();
        }

        /**
         * ����/�����˻����
         * @param money
         * @throws SQLException
         */
        public int Saving(double money) throws SQLException
        {
//          if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return 0;
            }
            SQLConnection sqlc = new SQLConnection();
            int x=this.account.Saving(money);
            sqlc.UpdateAccount(account);
            sqlc.Close();
            return x;
        }

        /**
         * �����˻����
         * @param money
         * @param time
         * @throws SQLException
         */
        public int Saving(double money,int time) throws SQLException
        {
//            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return 0;
            }
//            if(account.getAccountType() != Account.AccountType.CreditAccount)
//            {
//                System.out.println("Υ�����");
//                return;
//            }
            SQLConnection sqlc = new SQLConnection();
            CreditAccount ca = (CreditAccount)account;
            int x=ca.Saving(money,time);
            sqlc.UpdateAccount(ca);
            sqlc.Close();
            return x;
        }

        /**
         * �����˻�����,���������
         * @param money
         * @param loansTime
         * @throws SQLException
         */
        public void Apply(double money,int loansTime) throws SQLException
        {
            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return;
            }
            if(account.getAccountType() != Account.AccountType.LoansAccount)
            {
                System.out.println("Υ�����");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            LoansAccount la = (LoansAccount) account;
            la.Apply(money,loansTime);
            sqlc.UpdateAccount(la);
            sqlc.Close();
        }

        /**
         * ����/�����˻�ȡ��
         * @param money
         * @throws SQLException
         */
        public int Withdrawal(double money) throws SQLException
        {
        	int x;
//            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return 0;
            }
            SQLConnection sqlc = new SQLConnection();
            if(account.getAccountType() == Account.AccountType.SavingAccount)
            {
                SavingAccount a = (SavingAccount)account;
                x=a.Withdrawal(money);
                sqlc.UpdateAccount(account);
                sqlc.Close();
                return x;
            } else
            {
                CreditAccount a = (CreditAccount)account;
                x=a.Withdrawal(money);
                
            }
            sqlc.UpdateAccount(account);
            sqlc.Close();
            return x;
        }


        /**
         * �����˻�ȡ��
         * @param money
         * @param time
         * @throws SQLException
         */
        public int Withdrawal(double money,int time) throws SQLException
        {
//            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return 0;
            }
//            if(account.getAccountType() != Account.AccountType.CreditAccount)
//            {
//                System.out.println("Υ�����");
//                return;
//            }
            SQLConnection sqlc = new SQLConnection();
            CreditAccount ca = (CreditAccount)account;
           int x= ca.Withdrawal(money,time);
            sqlc.UpdateAccount(ca);
            sqlc.Close();
            return x;
        }

        /**
         * �����˻�����
         * @param time
         * @throws SQLException
         */
        public int Repay(int time) throws SQLException
        {
            if(account == null) return 0;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return -1;
            }
            if(account.getAccountType() != Account.AccountType.LoansAccount)
            {
                System.out.println("Υ�����");
                return -2;
            }
            SQLConnection sqlc = new SQLConnection();
            LoansAccount la = (LoansAccount) account;
            la.Repay(time);
            sqlc.UpdateAccount(la);
            sqlc.Close();
            return 1;
        }

        /**
         * �˻�ת��
         * @param otherId ����һ���˻���id
         * @param money
         * @throws SQLException
         */
        public int Transfer(long otherId,double money) throws SQLException
        {
//            if (account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return -2;
            }
            SQLConnection sqlc = new SQLConnection();
            Account other = sqlc.SelectAccount(otherId);
            if(other == null)
            {
                System.out.println("�Է��˻�������,ת��ʧ��");
                return -3;
            }
           int x= this.account.Transfer(other,money);
            sqlc.UpdateAccount(account);
            sqlc.UpdateAccount(other);
            return x;
        }

        /**
         * �˻���ѯ
         */
        public void Query()
        {
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return;
            }
            System.out.println(account.toString());
        }

        /**
         * ����Ա��ѯ�����˻�
         * @throws SQLException
         */
        public void QueryAll() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("Υ�����");
                return;
            }
            Administrator la = (Administrator) account;
            la.QueryAll();
        }

        /**
         * ����Աͳ�����
         * @throws SQLException
         */
        public void CountBalance() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("Υ�����");
                return;
            }
            Administrator la = (Administrator) account;
            la.CountBalance();
        }

        /**
         * ����Աͳ����������
         * @throws SQLException
         */
        public void CountCreditLine() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("Υ�����");
                return;
            }
            Administrator la = (Administrator) account;
            la.CountCreditLine();
        }

        /**
         * ����Աͳ�ƴ�������
         * @throws SQLException
         */
        public void CountLoans() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("Υ�����");
                return;
            }
            Administrator la = (Administrator) account;
            la.CountLoans();
        }

        /**
         * ����Աͳ���ʲ�����
         * @throws SQLException
         */
        public void Rank() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("Υ�����");
                return;
            }
            Administrator la = (Administrator) account;
            la.Rank();
        }
        /**
         * ����Ա���
         */
        public void RelieveLoss(long id) throws SQLException {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("Υ�����");
                return;
            }
            Administrator la = (Administrator) account;
            la.RelieveLoss(id);

        }
        /**
         * �˻��޸�����
         * @param password1
         * @param password2
         * @throws SQLException
         */
        public void ChangePassword(String password1,String password2) throws SQLException
        {
            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("���˻��ѹ�ʧ,�޷�����");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            this.account.ChangePassword(password1,password2);
            sqlc.UpdateAccount(account);
            sqlc.Close();
        }

    }

    /*
     * ��ʧ
     */
    public static boolean Loss(long id,String password) throws SQLException
    {
    	boolean flag=false;
        Login login = new Login(Bank.Login.LogIn(id,password));
        Account account =  login.account;
        if( account != null)
        {
            System.out.println("��ʧ�ɹ�");
            flag=true;
            account.setLoss(true);
            SQLConnection sqlc = new SQLConnection();
            sqlc.UpdateAccount(account);
            sqlc.Close();
        }
        else {
            System.out.println("��ʧʧ��");
        }

        return flag;

    }


//	ע��

    public static boolean LogOut(long id,String password) throws SQLException
    {
        SQLConnection sqlc = new SQLConnection();
        Account a = sqlc.SelectAccount(id);
        if(a == null)
        {
            System.out.println("δע���˻�������ע��");
            return false;
        }
        if(!password.equals(a.getPassword()))
        {
            System.out.println("�������");
            return false;
        }

         
        sqlc.DeleteAccount(a);
        sqlc.Close();
        System.out.println("�˻�ע���ɹ�");
        return true;
    
    }






}