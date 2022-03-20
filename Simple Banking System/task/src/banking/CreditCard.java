package banking;


public class CreditCard {
    String bin = "400000";
    String accountIndentifier;
    String checkSum = "5";

    public CreditCard(String bin, String accountIndentifier, String checkSum) {
        this.bin = bin;
        this.accountIndentifier = accountIndentifier;
        this.checkSum = checkSum;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getAccountIndentifier() {
        return accountIndentifier;
    }

    public void setAccountIndentifier(String accountIndentifier) {
        this.accountIndentifier = accountIndentifier;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
}

