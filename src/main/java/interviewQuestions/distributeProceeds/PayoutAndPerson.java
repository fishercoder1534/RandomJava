package interviewQuestions.distributeProceeds;

public class PayoutAndPerson {
    String name;
    Double payoutAmount;

    public PayoutAndPerson(String name, Double payoutAmount) {
        this.name = name;
        this.payoutAmount = payoutAmount;
    }

    @Override
    public String toString() {
        return "PayoutAndPerson{" +
                "name='" + name + '\'' +
                ", payoutAmount=" + payoutAmount +
                '}';
    }
}
