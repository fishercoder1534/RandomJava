package interviewQuestions.distributeProceeds;

import java.util.HashMap;
import java.util.Map;

public class Person {

    private String name;
    private Map<Role, Integer> assumedRolesAndShareCount;
    private Map<Role, Double> assumedRolesAndProceeds;

    public void setAssumedRolesAndProceeds(Map<Role, Double> assumedRolesAndProceeds) {
        this.assumedRolesAndProceeds = assumedRolesAndProceeds;
    }

    public String getName() {
        return name;
    }

    public Map<Role, Double> getAssumedRolesAndProceeds() {
        return this.assumedRolesAndProceeds;
    }

    public void setAssumedRoles(Map<Role, Integer> assumedRoles) {
        this.assumedRolesAndShareCount = assumedRoles;
    }

    public Map<Role, Integer> getAssumedRolesToShareCount() {
        return this.assumedRolesAndShareCount;
    }

    public Person(String name) {
        this.name = name;
        this.assumedRolesAndShareCount = new HashMap<>();
        this.assumedRolesAndProceeds = new HashMap<>();
    }

    public Double getTotalPayout() {
        Double total = 0.0;
        for (Role role : this.assumedRolesAndProceeds.keySet()) {
            total += assumedRolesAndProceeds.get(role);
        }
        return Math.round(total * 100) / 100.0;
    }
}
