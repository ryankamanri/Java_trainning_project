package Model;

import Model.Account.AccountType;
import Model.CreditAccount.CreditAccountTypeValue;
import Model.CreditAccount.CreditAccountTypeValue.CreditAccountType;

public class CreditAccount extends Account{


    //信用卡种类
    public static class CreditAccountTypeValue
    {
        public enum CreditAccountType
        {
            ORDINARY,//普通卡
            GOLD,//金卡
            PLATINUM,//白银卡
            DIAMOND,//钻石卡
            BLACK,//黑卡
        }

        private CreditAccountType type;
        public CreditAccountTypeValue(CreditAccountType type)
        {
            this.type = type;
        }
        /**
         * 将字符串转换为信用卡种类类型
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

    //属性
    private double creditRating=10;
    private CreditAccountTypeValue creditAccountTypeValue;
    private double creditLine;
    private int a;//还款时间

    public CreditAccount()
    {
        super();
        creditAccountTypeValue = new CreditAccountTypeValue(CreditAccountTypeValue.CreditAccountType.ORDINARY);//默认为普通卡
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
     * 数据库连接专用构造函数
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
//     * 查看
//     */
//    public void Query()
//    {
//        System.out.println(toString());
//    }

    /**
     * 由balance余额和createRating信用度决定
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
     * 存款
     */
    public int Saving(double money,int time) {
        if(time>a) {
            setCreditRating(getCreditRating()-2);
            creditLine -= 2 * this.creditAccountTypeValue.Value();
        }
        if(money<0) {
            System.out.println("存入金额有误");
            return -1;
        }
        else {
            if(getCreditLine()>creditLine) {//存在信用消费
                if(money>getCreditLine()-creditLine) {//能够还上
                    setBalance(money-(getCreditLine() - creditLine));
                    creditLine = getCreditLine();

                }
                else {//还不上
                    creditLine += money;
                }
            }
            else {//不存在信用消费
                setBalance(getBalance()+money);
            }
            return 1;
        }
    }
    /**
     * 取款
     */

    public int Withdrawal(double money,int time)
    {
        a=time;
        if(getBalance()+creditLine < money)//余额 + 信用 不足
        {
            System.out.println("余额不足");
            return -1;
        }
        else {
            if(getBalance()<money) {//余额 不足
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
     * 转账
     */
    @Override
    public int Transfer(Account a, double money)
    {
//        if(a.getId() == this.getId())
//        {
//            System.out.println("无效操作");
//            return;
//        }
        if (money <= 0) {
            System.out.println("金额不能为0");
            return -1;
        }
        if (money > getBalance()+creditLine) {
            System.out.println("余额不足");
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
     * 连接数据库
     * @return
     */
    @Override
    public String toSQLServerString()
    {
        return super.toSQLServerString() + "," +creditAccountTypeValue.type + "," + creditLine + "," +creditRating;
    }

}