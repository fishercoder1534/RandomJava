package interviewQuestions.distributeProceeds;

public class PayOutAndShareClass {
    Double payoutAmount;
    String shareClass;

    public PayOutAndShareClass(Double payoutAmount, String shareClass) {
        this.payoutAmount = payoutAmount;
        this.shareClass = shareClass;
    }

    @Override
    public String toString() {
        return "PayOutAndShareClass{" +
                "payoutAmount=" + payoutAmount +
                ", shareClass='" + shareClass + '\'' +
                '}';
    }
}
