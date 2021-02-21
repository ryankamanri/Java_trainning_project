package Model;

/**
 * ¥¢–Ó’Àªß
 */
public class SavingAccount extends Account
{
    public  SavingAccount()
    {
        super();
        this.at = AccountType.SavingAccount;
    }

    public SavingAccount(long id, String password, String name, String personID, String phoneNumber, double balance,boolean isLoss)
    {
        super(id, password, name, personID, phoneNumber, balance,isLoss);
        this.at = AccountType.SavingAccount;
    }

}
