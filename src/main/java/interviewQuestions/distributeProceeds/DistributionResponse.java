package interviewQuestions.distributeProceeds;

public class DistributionResponse {
    public DistributionResponse(PayoutByPerson payoutByPerson, PayoutByShareclass payoutByShareclass) {
        this.payoutByPerson = payoutByPerson;
        this.payoutByShareclass = payoutByShareclass;
    }

    PayoutByPerson payoutByPerson;
    PayoutByShareclass payoutByShareclass;
}
