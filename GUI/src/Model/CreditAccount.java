package Model;

import Model.Account.AccountType;
import Model.CreditAccount.CreditAccountTypeValue;
import Model.CreditAccount.CreditAccountTypeValue.CreditAccountType;

public class CreditAccount extends Account{


    //���ÿ�����
    public static class CreditAccountTypeValue
    {
        public enum CreditAccountType
        {
            ORDINARY,//��ͨ��
            GOLD,//��
            PLATINUM,//������
            DIAMOND,//��ʯ��
            BLACK,//�ڿ�
        }

        private CreditAccountType type;
        public CreditAccountTypeValue(CreditAccountType type)
        {
            this.type = type;
        }
        /**
         * ���ַ���ת��Ϊ���ÿ���������
         * @param type
         * @return
         */
        public static CreditAccountType parseCATV(String type)
        {
            switch (type)
            {
                case "ORDINARY":return CreditAccountType.ORDINARY;
                case "GOLD":return CreditAccountType.GOLD;
                case "PLATINUM":return CreditAccountType.PLATINUM;
                case "DIAMOND":return CreditAccountType.DIAMOND;
                case "BLACK":return CreditAccountType.BLACK;
                default: return CreditAccountType.ORDINARY;
            }
        }

        public int Value()
        {
            switch (type)
            {
                case ORDINARY:return 1000;
                case GOLD:return 5000;
                case PLATINUM:return 10000;
                case DIAMOND:return 50000;
                case BLACK:return 100000;
                default: return 0;
            }
        }
    }

    //����
    private double creditRating=10;
    private CreditAccountTypeValue creditAccountTypeValue;
    private double creditLine;
    private int a;//����ʱ��

    public CreditAccount()
    {
        super();
        creditAccountTypeValue = new CreditAccountTypeValue(CreditAccountTypeValue.CreditAccountType.ORDINARY);//Ĭ��Ϊ��ͨ��
        creditLine=(creditAccountTypeValue.Value())*creditRating;
        this.at = AccountType.CreditAccount;
    }

    public CreditAccount(long id, String password, String name, String personID, String phoneNumber, double balance,boolean isLoss, CreditAccountTypeValue.CreditAccountType creditAccountType)
    {
        super(id, password, name, personID, phoneNumber,balance,isLoss);
        creditAccountTypeValue = new CreditAccountTypeValue(creditAccountType);
        creditLine=(creditAccountTypeValue.Value())*creditRating;
        this.at = AccountType.CreditAccount;
    }
    /**
     * ���ݿ�����ר�ù��캯��
     */
    public CreditAccount(long id, String password, String name, String personID, String phoneNumber, double balance,boolean isLoss, CreditAccountTypeValue.CreditAccountType creditAccountType,double creditLine,double creditRating)
    {
        super(id, password, name, personID, phoneNumber,balance,isLoss);
        this.creditAccountTypeValue = new CreditAccountTypeValue(creditAccountType);
        this.creditLine = creditLine;
        this.creditRating = creditRating;
        this.at = AccountType.CreditAccount;
    }

    @Override
    public String toString() {
        return super.toString()+", creditLine=" + creditLine + ", creditRating=" + creditRating ;
    }
//    /*
//     * �鿴
//     */
//    public void Query()
//    {
//        System.out.println(toString());
//    }

    /**
     * ��balance����createRating���öȾ���
     * @return
     */
    public double getCreditLine()
    {

        return (creditAccountTypeValue.Value())*creditRating;
    }


    public double getCreditRating()
    {
        return creditRating;
    }

    public void setCreditRating(double creditRating)
    {
        this.creditRating = creditRating;
    }
    public CreditAccountTypeValue getCreditAccountTypeValue()
    {
        return creditAccountTypeValue;
    }

    public void setCreditAccountTypeValue(CreditAccountTypeValue creditAccountTypeValue)
    {
        this.creditAccountTypeValue = creditAccountTypeValue;
        this.creditLine = creditAccountTypeValue.Value()*10;
    }

    /*
     * ���
     */
    public int Saving(double money,int time) {
        if(time>a) {
            setCreditRating(getCreditRating()-2);
            creditLine -= 2 * this.creditAccountTypeValue.Value();
        }
        if(money<0) {
            System.out.println("����������");
            return -1;
        }
        else {
            if(getCreditLine()>creditLine) {//������������
                if(money>getCreditLine()-creditLine) {//�ܹ�����
                    setBalance(money-(getCreditLine() - creditLine));
                    creditLine = getCreditLine();

                }
                else {//������
                    creditLine += money;
                }
            }
            else {//��������������
                setBalance(getBalance()+money);
            }
            return 1;
        }
    }
    /**
     * ȡ��
     */

    public int Withdrawal(double money,int time)
    {
        a=time;
        if(getBalance()+creditLine < money)//��� + ���� ����
        {
            System.out.println("����");
            return -1;
        }
        else {
            if(getBalance()<money) {//��� ����
                creditLine = getBalance()+creditLine-money;
                setBalance(0);

            }
            else {
                setBalance(getBalance()-money);
            }
            return 1;
        }
    }
    /*
     * ת��
     */
    @Override
    public int Transfer(Account a, double money)
    {
//        if(a.getId() == this.getId())
//        {
//            System.out.println("��Ч����");
//            return;
//        }
        if (money <= 0) {
            System.out.println("����Ϊ0");
            return -1;
        }
        if (money > getBalance()+creditLine) {
            System.out.println("����");
            return 0;
        }
        if(money>getBalance()&&money <= getBalance()+creditLine) {
            a.setBalance(a.getBalance()+money);
            creditLine = getBalance()+getCreditLine()-money;
            setBalance(0.0);

            return 1;
        }
        else {
            a.setBalance(a.getBalance()+money);
            setBalance(getBalance()-money);
            return 1;
        }
    }
    /**
     * �������ݿ�
     * @return
     */
    @Override
    public String toSQLServerString()
    {
        return super.toSQLServerString() + "," +creditAccountTypeValue.type + "," + creditLine + "," +creditRating;
    }

}