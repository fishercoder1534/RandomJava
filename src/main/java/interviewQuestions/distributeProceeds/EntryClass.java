package interviewQuestions.distributeProceeds;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EntryClass {

    public static void main(String... args) {
        EntryClass entryClass = new EntryClass();
        List<Person> shareholders = entryClass.initShareholders();
        DistributeProceedsService distributeProceedsService = new DistributeProceedsServiceImpl(shareholders);
        if (args.length > 0) {
            System.out.println(args[0]);
            System.out.println(distributeProceedsService.distributeProceeds(Double.parseDouble(args[0])));
        }
        DistributionResponse response = distributeProceedsService.distributeProceeds(1000.0);
        List<PayoutAndPerson> payoutAndPersonList = response.payoutByPerson.payoutAndPersonList;
        System.out.println(payoutAndPersonList.toString());
        List<PayOutAndShareClass> payOutAndShareClassList = response.payoutByShareclass.payOutAndShareClassList;
        System.out.println(payOutAndShareClassList.toString());
        System.out.println("Finished running the program.");
    }

    private List<Person> initShareholders() {
        Person alex = new Person("Alex");
        Map<Role, Integer> alexRoles = alex.getAssumedRolesToShareCount();
        alexRoles.put(new GeneralPartner(250l), 10);
        alex.setAssumedRoles(alexRoles);

        Person becky = new Person("Becky");
        Map<Role, Integer> beckyRoles = becky.getAssumedRolesToShareCount();
        beckyRoles.put(new GeneralPartner(250l), 10);
        beckyRoles.put(new ManagingPartner(), 5);
        becky.setAssumedRoles(beckyRoles);

        Person david = new Person("David");
        Map<Role, Integer> davidRoles = david.getAssumedRolesToShareCount();
        davidRoles.put(new Associate(), 10);
        david.setAssumedRoles(davidRoles);

        return Arrays.asList(alex, becky, david);
    }
}
