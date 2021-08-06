package interviewQuestions.distributeProceeds;

public class GeneralPartner extends Role {
    public static final String GENERAL_PARTNER = "GENERAL_PARTNER";

    public GeneralPartner(Long initInvestment) {
        this.initInvestment = initInvestment;
        this.name = GENERAL_PARTNER;
    }

    Long initInvestment;
}
