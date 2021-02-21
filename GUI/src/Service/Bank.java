


package Service;

import java.sql.SQLException;
import java.util.*;

import Model.*;
import Model.LoansAccount.Mortgage;
import Model.CreditAccount.CreditAccountTypeValue;
import Model.CreditAccount.CreditAccountTypeValue.CreditAccountType;
import SQLConnection.*;


public class Bank {

    //	构造
    public Bank() { }

    //	开户
    public Account register(String password,String confirmPassword, String name, String personID, String phoneNumber, double balance,int type) throws SQLException
    {
//		账户的引用
        Account act=null;
//		判断两个密码是否一致
        if(!password.equals(confirmPassword)) {
            System.out.println("两次密码不一致，请重新确认");
            return null;
        } else {
//			类型是0，就是开储蓄账户
            SQLConnection sqlc=new SQLConnection();
            if(type==0) {
                act=new SavingAccount(sqlc.CreateNewID(),password,name, personID,phoneNumber,balance,false);
                if(sqlc.InsertAccount(Account.AccountType.SavingAccount,act))
                    System.out.println("储蓄账户开户成功");
            }
//			类型为1，就是开信用账户
            else if(type==1) {
                double creditLine = 0;
                CreditAccountTypeValue.CreditAccountType creditAccountType = CreditAccountType.ORDINARY;
                //这里点击按钮选择种类输入

                act=new CreditAccount(sqlc.CreateNewID(),password,name, personID,phoneNumber,balance,false,creditAccountType);
                if(sqlc.InsertAccount(Account.AccountType.CreditAccount,act))
                    System.out.println("信用账户开户成功");
            }
//			类型为2，就是开贷款账户
            else if(type==2){
                Mortgage mortgage = null;
                int time = 0;
                act=new LoansAccount(sqlc.CreateNewID(),password,name, personID,phoneNumber,balance,false);
                if(sqlc.InsertAccount(Account.AccountType.LoansAccount,act))
                    System.out.println("贷款账户开户成功");
            }else {
                System.out.println("读取失败，请重新注册");
            }
            sqlc.Close();

//			返回开户人信息表示开户成功
            System.out.println("您的账号id为: " + act.getId());
            return act;
        }

    }
    public static class Login
    {
        private Account account = null;

        /**
         * 登录操作
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
                System.out.println("账户不存在,请重新登录");
                return null;
            }
            if(!password.equals(act.getPassword()))
            {
                System.out.println("密码错误,请重新登录");
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
         * 贷款账户抵押财产
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
                System.out.println("该账户已挂失,无法操作");
                return;
            }
            if(account.getAccountType() != Account.AccountType.LoansAccount)
            {
                System.out.println("违规操作");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            LoansAccount la = (LoansAccount) account;
            la.setMortgage(new Mortgage(estate,nationalDebt,security,stock));
            sqlc.UpdateAccount(la);
            sqlc.Close();
        }

        /**
         * 信用账户设置信用卡种类
         * @param type
         */
        public void SetCreditAccountType(CreditAccountType type) throws SQLException
        {
            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return;
            }
            if(account.getAccountType() != Account.AccountType.CreditAccount)
            {
                System.out.println("违规操作");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            CreditAccount ca = (CreditAccount)account;
            ca.setCreditAccountTypeValue(new CreditAccountTypeValue(type));
            sqlc.UpdateAccount(ca);
            sqlc.Close();
        }

        /**
         * 储蓄/贷款账户存款
         * @param money
         * @throws SQLException
         */
        public int Saving(double money) throws SQLException
        {
//          if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return 0;
            }
            SQLConnection sqlc = new SQLConnection();
            int x=this.account.Saving(money);
            sqlc.UpdateAccount(account);
            sqlc.Close();
            return x;
        }

        /**
         * 信用账户存款
         * @param money
         * @param time
         * @throws SQLException
         */
        public int Saving(double money,int time) throws SQLException
        {
//            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return 0;
            }
//            if(account.getAccountType() != Account.AccountType.CreditAccount)
//            {
//                System.out.println("违规操作");
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
         * 贷款账户贷款,并存入余额
         * @param money
         * @param loansTime
         * @throws SQLException
         */
        public void Apply(double money,int loansTime) throws SQLException
        {
            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return;
            }
            if(account.getAccountType() != Account.AccountType.LoansAccount)
            {
                System.out.println("违规操作");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            LoansAccount la = (LoansAccount) account;
            la.Apply(money,loansTime);
            sqlc.UpdateAccount(la);
            sqlc.Close();
        }

        /**
         * 储蓄/贷款账户取款
         * @param money
         * @throws SQLException
         */
        public int Withdrawal(double money) throws SQLException
        {
        	int x;
//            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
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
         * 信用账户取款
         * @param money
         * @param time
         * @throws SQLException
         */
        public int Withdrawal(double money,int time) throws SQLException
        {
//            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return 0;
            }
//            if(account.getAccountType() != Account.AccountType.CreditAccount)
//            {
//                System.out.println("违规操作");
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
         * 贷款账户还款
         * @param time
         * @throws SQLException
         */
        public int Repay(int time) throws SQLException
        {
            if(account == null) return 0;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return -1;
            }
            if(account.getAccountType() != Account.AccountType.LoansAccount)
            {
                System.out.println("违规操作");
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
         * 账户转账
         * @param otherId 另外一个账户的id
         * @param money
         * @throws SQLException
         */
        public int Transfer(long otherId,double money) throws SQLException
        {
//            if (account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return -2;
            }
            SQLConnection sqlc = new SQLConnection();
            Account other = sqlc.SelectAccount(otherId);
            if(other == null)
            {
                System.out.println("对方账户不存在,转账失败");
                return -3;
            }
           int x= this.account.Transfer(other,money);
            sqlc.UpdateAccount(account);
            sqlc.UpdateAccount(other);
            return x;
        }

        /**
         * 账户查询
         */
        public void Query()
        {
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return;
            }
            System.out.println(account.toString());
        }

        /**
         * 管理员查询所有账户
         * @throws SQLException
         */
        public void QueryAll() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("违规操作");
                return;
            }
            Administrator la = (Administrator) account;
            la.QueryAll();
        }

        /**
         * 管理员统计余额
         * @throws SQLException
         */
        public void CountBalance() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("违规操作");
                return;
            }
            Administrator la = (Administrator) account;
            la.CountBalance();
        }

        /**
         * 管理员统计信用消费
         * @throws SQLException
         */
        public void CountCreditLine() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("违规操作");
                return;
            }
            Administrator la = (Administrator) account;
            la.CountCreditLine();
        }

        /**
         * 管理员统计贷款消费
         * @throws SQLException
         */
        public void CountLoans() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("违规操作");
                return;
            }
            Administrator la = (Administrator) account;
            la.CountLoans();
        }

        /**
         * 管理员统计资产排名
         * @throws SQLException
         */
        public void Rank() throws SQLException
        {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("违规操作");
                return;
            }
            Administrator la = (Administrator) account;
            la.Rank();
        }
        /**
         * 管理员解挂
         */
        public void RelieveLoss(long id) throws SQLException {
            if(account == null) return;
            if(account.getAccountType() != Account.AccountType.Administrator)
            {
                System.out.println("违规操作");
                return;
            }
            Administrator la = (Administrator) account;
            la.RelieveLoss(id);

        }
        /**
         * 账户修改密码
         * @param password1
         * @param password2
         * @throws SQLException
         */
        public void ChangePassword(String password1,String password2) throws SQLException
        {
            if(account == null) return;
            if(account.isLoss())
            {
                System.out.println("该账户已挂失,无法操作");
                return;
            }
            SQLConnection sqlc = new SQLConnection();
            this.account.ChangePassword(password1,password2);
            sqlc.UpdateAccount(account);
            sqlc.Close();
        }

    }

    /*
     * 挂失
     */
    public static boolean Loss(long id,String password) throws SQLException
    {
    	boolean flag=false;
        Login login = new Login(Bank.Login.LogIn(id,password));
        Account account =  login.account;
        if( account != null)
        {
            System.out.println("挂失成功");
            flag=true;
            account.setLoss(true);
            SQLConnection sqlc = new SQLConnection();
            sqlc.UpdateAccount(account);
            sqlc.Close();
        }
        else {
            System.out.println("挂失失败");
        }

        return flag;

    }


//	注销

    public static boolean LogOut(long id,String password) throws SQLException
    {
        SQLConnection sqlc = new SQLConnection();
        Account a = sqlc.SelectAccount(id);
        if(a == null)
        {
            System.out.println("未注册账户，请先注册");
            return false;
        }
        if(!password.equals(a.getPassword()))
        {
            System.out.println("密码错误");
            return false;
        }

         
        sqlc.DeleteAccount(a);
        sqlc.Close();
        System.out.println("账户注销成功");
        return true;
    
    }






}