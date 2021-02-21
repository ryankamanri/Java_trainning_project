package Model;

import java.lang.management.MemoryType;
import java.util.Date;

public class LoansAccount extends Account
{
//
    //创建一个抵押内部类
    public static class Mortgage//抵押财产类
    {
        public int getEstate()
        {
            return estate;
        }

        public void setEstate(int estate)
        {
            this.estate = estate;
        }

        private int estate = 0;//房地产 每个1000k

        public int getNationalDebt()
        {
            return nationalDebt;
        }

        public void setNationalDebt(int nationalDebt)
        {
            this.nationalDebt = nationalDebt;
        }

        private int nationalDebt = 0;//国债 每个1k

        public int getSecurity()
        {
            return security;
        }

        public void setSecurity(int security)
        {
            this.security = security;
        }

        private int security = 0;//证券 每个1k

        public int getStock()
        {
            return stock;
        }

        public void setStock(int stock)
        {
            this.stock = stock;
        }

        private int stock = 0;//股票 每个1k
        private double mortgageLine = 0;//当今抵押财产额度
        public Mortgage(){}
        public Mortgage(int estate,int nationalDebt,int security,int stock)
        {
            //更新当今抵押财产额度
            this.mortgageLine += 1000000 * (estate - this.estate) + 1000 * (nationalDebt - this.nationalDebt) + 1000 * (security - this.security) + 1000 * (stock - this.stock);
            this.estate = estate;
            this.nationalDebt = nationalDebt;
            this.security = security;
            this.stock = stock;

        }
        public Mortgage(int estate,int nationalDebt,int security,int stock,double mortgageLine)
        {
            //更新当今抵押财产额度
            this.mortgageLine = mortgageLine;
            this.estate = estate;
            this.nationalDebt = nationalDebt;
            this.security = security;
            this.stock = stock;

        }
        /**
         * 计算抵押财产的总估值
         */
        public double MortgageCount()
        {
            return 1000000 * estate + 1000 * nationalDebt + 1000 * security + 1000 * stock;
        }

        public double getMortgageLine()
        {
            return mortgageLine;
        }

        @Override
        public String toString()
        {
            return "Mortgage{" +
                    "estate=" + estate +
                    ", nationalDebt=" + nationalDebt +
                    ", security=" + security +
                    ", stock=" + stock +
                    ", mortgageLine=" + mortgageLine +
                    '}';
        }
        public String toSQLServerString()
        {
            return estate + "," + nationalDebt + "," + security + "," + stock + "," +mortgageLine;
        }
    }

    //属性
    private int loansTime;//贷款时长,用户设置,单位为月,范围1 - 12个月
    private Mortgage mortgage;
    private double basicInterest ;//基础利息
    public double getBasicInterest() {
		return basicInterest;
	}

	public void setBasicInterest(double basicInterest) {
		this.basicInterest = basicInterest;
	}

	public double getOverdueInterest() {
		return overdueInterest;
	}

	public void setOverdueInterest(double overdueInterest) {
		this.overdueInterest = overdueInterest;
	}
	private double overdueInterest ;//逾期利息

    public LoansAccount()
    {
        super();
        this.mortgage = new Mortgage();
        this.at = AccountType.LoansAccount;
    }

    public LoansAccount(long id, String password, String name, String personID, String phoneNumber, double balance,boolean isLoss)
    {
        super(id, password, name, personID, phoneNumber, balance,isLoss);
        this.mortgage = new Mortgage();
        this.at = AccountType.LoansAccount;
    }

    public LoansAccount(long id, String password, String name, String personID, String phoneNumber, double balance,boolean isLoss,int loansTime,Mortgage mortgage,double basicInterest,double overdueInterest)
    {
        super(id, password, name, personID, phoneNumber, balance,isLoss);
        this.loansTime = loansTime;
        this.mortgage = mortgage;
        this.basicInterest = basicInterest;
        this.overdueInterest = overdueInterest;
        this.at = AccountType.LoansAccount;
    }


    public Mortgage getMortgage()
    {
        return mortgage;
    }

    /**
     * 抵押财产
     * @param mortgage
     */
    public void setMortgage(Mortgage mortgage)
    {
        this.mortgage = mortgage;
    }

    public int getLoansTime()
    {
        return loansTime;
    }

    public void setLoansTime(int loansTime)
    {
        if(loansTime <= 12) this.loansTime = loansTime;
    }

    /**
     * 获取贷款金额
     * @return
     */
    public double getLoans()
    {
        return mortgage.MortgageCount() - mortgage.mortgageLine;
    }
    /**
     * 申请贷款,存放到余额中
     * @param money
     */
    public void Apply(double money,int loansTime)
    {
        basicInterest = 0.01 * (Math.log(loansTime + 1) - Math.log(loansTime + 1) % 0.01);//基础利息
        overdueInterest = 0.05 * (Math.log(loansTime + 1) - Math.log(loansTime + 1) % 0.01);//逾期利息
        if(mortgage.mortgageLine != mortgage.MortgageCount())
        {
            System.out.println("存在未还款项目,无法再次借贷");
            return;
        }
        if(money > mortgage.mortgageLine)
        {
            System.out.println("贷款申请总额大于抵押总额");
            return;
        }
        this.loansTime = loansTime;
        mortgage.mortgageLine -= money;
        setBalance(getBalance() + money);

    }

    /**
     * 还贷款
     * @param time 贷款月数
     */
    public void Repay(int time)
    {
        double basicRepay = mortgage.MortgageCount() - mortgage.mortgageLine;//本金
        double repay;//本金加利息应还款
        if(time < loansTime) repay = basicRepay * Math.pow((1 + basicInterest),time);
        else repay = basicRepay * (Math.pow((1 + basicInterest),loansTime) * Math.pow((1 + overdueInterest),time - loansTime));
        if(getBalance() < repay)
        {
            System.out.println(String.format("余额不足,无法还款,应还%.2f" , repay ));
            return;
        }
        setBalance(getBalance() - repay);
        mortgage.mortgageLine = mortgage.MortgageCount();
        loansTime = 0;
        System.out.println("还款" + repay);
    }

    @Override
    public String toString()
    {
        return  super.toString()+
                ", loansTime=" + loansTime +
                ", mortgage=" + mortgage +
                ", basicInterest=" + basicInterest +
                ", overdueInterest=" + overdueInterest;
    }
    @Override
    public String toSQLServerString()
    {
        return super.toSQLServerString() + "," + loansTime + "," + mortgage.toSQLServerString() + "," + basicInterest + "," + overdueInterest;
    }
}