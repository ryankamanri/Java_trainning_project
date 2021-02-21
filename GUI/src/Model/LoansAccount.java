package Model;

import java.lang.management.MemoryType;
import java.util.Date;

public class LoansAccount extends Account
{
//
    //����һ����Ѻ�ڲ���
    public static class Mortgage//��Ѻ�Ʋ���
    {
        public int getEstate()
        {
            return estate;
        }

        public void setEstate(int estate)
        {
            this.estate = estate;
        }

        private int estate = 0;//���ز� ÿ��1000k

        public int getNationalDebt()
        {
            return nationalDebt;
        }

        public void setNationalDebt(int nationalDebt)
        {
            this.nationalDebt = nationalDebt;
        }

        private int nationalDebt = 0;//��ծ ÿ��1k

        public int getSecurity()
        {
            return security;
        }

        public void setSecurity(int security)
        {
            this.security = security;
        }

        private int security = 0;//֤ȯ ÿ��1k

        public int getStock()
        {
            return stock;
        }

        public void setStock(int stock)
        {
            this.stock = stock;
        }

        private int stock = 0;//��Ʊ ÿ��1k
        private double mortgageLine = 0;//�����Ѻ�Ʋ����
        public Mortgage(){}
        public Mortgage(int estate,int nationalDebt,int security,int stock)
        {
            //���µ����Ѻ�Ʋ����
            this.mortgageLine += 1000000 * (estate - this.estate) + 1000 * (nationalDebt - this.nationalDebt) + 1000 * (security - this.security) + 1000 * (stock - this.stock);
            this.estate = estate;
            this.nationalDebt = nationalDebt;
            this.security = security;
            this.stock = stock;

        }
        public Mortgage(int estate,int nationalDebt,int security,int stock,double mortgageLine)
        {
            //���µ����Ѻ�Ʋ����
            this.mortgageLine = mortgageLine;
            this.estate = estate;
            this.nationalDebt = nationalDebt;
            this.security = security;
            this.stock = stock;

        }
        /**
         * �����Ѻ�Ʋ����ܹ�ֵ
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

    //����
    private int loansTime;//����ʱ��,�û�����,��λΪ��,��Χ1 - 12����
    private Mortgage mortgage;
    private double basicInterest ;//������Ϣ
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
	private double overdueInterest ;//������Ϣ

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
     * ��Ѻ�Ʋ�
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
     * ��ȡ������
     * @return
     */
    public double getLoans()
    {
        return mortgage.MortgageCount() - mortgage.mortgageLine;
    }
    /**
     * �������,��ŵ������
     * @param money
     */
    public void Apply(double money,int loansTime)
    {
        basicInterest = 0.01 * (Math.log(loansTime + 1) - Math.log(loansTime + 1) % 0.01);//������Ϣ
        overdueInterest = 0.05 * (Math.log(loansTime + 1) - Math.log(loansTime + 1) % 0.01);//������Ϣ
        if(mortgage.mortgageLine != mortgage.MortgageCount())
        {
            System.out.println("����δ������Ŀ,�޷��ٴν��");
            return;
        }
        if(money > mortgage.mortgageLine)
        {
            System.out.println("���������ܶ���ڵ�Ѻ�ܶ�");
            return;
        }
        this.loansTime = loansTime;
        mortgage.mortgageLine -= money;
        setBalance(getBalance() + money);

    }

    /**
     * ������
     * @param time ��������
     */
    public void Repay(int time)
    {
        double basicRepay = mortgage.MortgageCount() - mortgage.mortgageLine;//����
        double repay;//�������ϢӦ����
        if(time < loansTime) repay = basicRepay * Math.pow((1 + basicInterest),time);
        else repay = basicRepay * (Math.pow((1 + basicInterest),loansTime) * Math.pow((1 + overdueInterest),time - loansTime));
        if(getBalance() < repay)
        {
            System.out.println(String.format("����,�޷�����,Ӧ��%.2f" , repay ));
            return;
        }
        setBalance(getBalance() - repay);
        mortgage.mortgageLine = mortgage.MortgageCount();
        loansTime = 0;
        System.out.println("����" + repay);
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