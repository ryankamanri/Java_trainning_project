package Model;

import Model.Account.AccountType;

/**
 * �˻���
 *
 * 1/9/2021
 */
public class Account
{
    public enum AccountType
    {
        Administrator,
        SavingAccount,
        CreditAccount,
        LoansAccount
    };


    private long id;
    private String password;
    private String name;
    private String personID;
    private String phoneNumber;
    private double balance;
    private boolean isLoss;
    protected AccountType at;

    public  Account(){}
    public Account(long id,String password)
    {
        this.id = id;
        this.password = password;
    }
    public Account(long id)
    {
        this.id = id;
 
    }
    public Account(long id, String password, String name, String personID, String phoneNumber, double balance,boolean isLoss)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.personID = personID;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.isLoss = isLoss;
    }


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPersonID()
    {
        return personID;
    }

    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public boolean isLoss() { return isLoss; }

    public void setLoss(boolean loss) { isLoss = loss; }

    public AccountType getAccountType()
    {
        return this.at;
    }
    //�������
    public int Saving(double money)
    {
        if (money <= 0) {
            System.out.println("�������С�ڵ���0");
            return -1;
        }
        balance += money;
        return 1;
    }

    public int Withdrawal(double money)
    {
        if (balance < money) {
            System.out.println("����");
            return -1;
        }
        balance -= money;
        return 1;
    }

    /**
     * ת�� this account >> Account a2
     *
     * @param a2
     * @param money
     */
    public int Transfer(Account a2, double money)
    {
        if (money <= 0) {
            System.out.println("ת������С�ڵ���0");
            return -1;
        }
        if (money > balance) {
            System.out.println("����");
            return 0;
        }
        balance -= money;
        a2.balance += money;
        return 1;
    }

//    public void Query()
//    {
//        System.out.println(toString());
//    }

    /**
     * ������1�޸ĳ�����2
     *
     * @param password1
     * @param password2
     */
    public void ChangePassword(String password1, String password2)
    {
        if (!password.equals(password1)) {
            System.out.println("�����������");
        }
        password = password2;
        System.out.println("�޸�����ɹ�");
    }

    @Override
    public String toString()
    {
        return
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", personID='" + personID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", isLoss=" + isLoss +
                ", at=" + at;
    }

    public String toSQLServerString()
    {
        return id + "," + password + "," + name + "," + personID + "," + phoneNumber + "," + balance + "," + isLoss ;
    }


}