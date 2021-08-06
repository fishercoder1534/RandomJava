package interviewQuestions.distributeProceeds;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DistributeProceedsServiceImpl implements DistributeProceedsService {

    private static final String ASSOCIATE = "ASSOCIATE";
    private static final String GENERAL_PARTNER = "GENERAL_PARTNER";
    private static final String MANAGING_PARTNER = "MANAGING_PARTNER";

    private final List<Person> shareholders;

    public DistributeProceedsServiceImpl(List<Person> shareholders) {
        this.shareholders = shareholders;
    }

    public DistributionResponse distributeProceeds(Double proceed) {
        Double totalClassBFunds = 0.0;
        int totalClassBUnits = 0;
        int totalUnits = 0;
        for (Person person : shareholders) {
            Map<Role, Integer> assumedRolesToShareCount = person.getAssumedRolesToShareCount();
            for (Role role : assumedRolesToShareCount.keySet()) {
                if (role.name.equals(GENERAL_PARTNER)) {
                    totalClassBFunds += ((GeneralPartner) role).initInvestment;
                    totalClassBUnits += assumedRolesToShareCount.get(role);
                }
                totalUnits += assumedRolesToShareCount.get(role);
            }
        }
        Double remaining = 0.0;
        Double totalClassBProceeds;
        if (proceed > totalClassBFunds) {
            remaining = proceed - totalClassBFunds;
            totalClassBProceeds = totalClassBFunds;
        } else {
            totalClassBProceeds = proceed;
        }
        //distribute to ClassB holders first
        for (Person person : shareholders) {
            Map<Role, Integer> assumedRolesToShareCount = person.getAssumedRolesToShareCount();
            for (Role role : assumedRolesToShareCount.keySet()) {
                if (role.getName().equals(GENERAL_PARTNER)) {
                    Double percentage = assumedRolesToShareCount.get(role) * 1.0 / totalClassBUnits;
                    double classBProceed = percentage * totalClassBProceeds;
                    Map<Role, Double> assumedRolesAndProceeds = person.getAssumedRolesAndProceeds();
                    assumedRolesAndProceeds.put(role, classBProceed);
                    person.setAssumedRolesAndProceeds(assumedRolesAndProceeds);
                    break;
                }
            }
        }
        Double totalClassAProceeds = 0.0;
        Double totalClassCProceeds = 0.0;
        if (remaining > 0) {
            //distribute among all classes if there's any remaining
            for (Person person : shareholders) {
                Map<Role, Integer> assumedRolesToShareCount = person.getAssumedRolesToShareCount();
                for (Role role : assumedRolesToShareCount.keySet()) {
                    Double percentage = assumedRolesToShareCount.get(role) * 1.0 / totalUnits;
                    Double classProceeds = 0.0;
                    if (role.getName().equals(ASSOCIATE)) {
                        classProceeds = percentage * remaining;
                        totalClassAProceeds += classProceeds;
                    } else if (role.getName().equals(MANAGING_PARTNER)) {
                        classProceeds = percentage * remaining;
                        totalClassCProceeds += classProceeds;
                    } else if (role.getName().equals(GENERAL_PARTNER)) {
                        classProceeds = percentage * remaining;
                        totalClassBProceeds += classProceeds;
                    }
                    Map<Role, Double> assumedRolesAndProceeds = person.getAssumedRolesAndProceeds();
                    assumedRolesAndProceeds.put(role, assumedRolesAndProceeds.getOrDefault(role, 0.0) + classProceeds);
                    person.setAssumedRolesAndProceeds(assumedRolesAndProceeds);
                }
            }
        }

        List<PayOutAndShareClass> payOutAndShareClassList = Arrays.asList(
                new PayOutAndShareClass(Math.round(totalClassAProceeds * 100) / 100.0, "Class A"),
                new PayOutAndShareClass(Math.round(totalClassBProceeds * 100) / 100.0, "Class B"),
                new PayOutAndShareClass(Math.round(totalClassCProceeds * 100) / 100.0, "Class C"));
        List<PayoutAndPerson> payoutAndPersonList = shareholders.stream().map(person -> new PayoutAndPerson(person.getName(), person.getTotalPayout())).collect(Collectors.toList());
        DistributionResponse distributionResponse = new DistributionResponse(
                new PayoutByPerson(payoutAndPersonList), new PayoutByShareclass(payOutAndShareClassList));
        return distributionResponse;
    }

}
