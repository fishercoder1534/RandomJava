package sporadic;

import java.util.Set;

/**Posted the question here:
 * https://softwareengineering.stackexchange.com/questions/398727/how-to-generalize-this-method-for-its-duplicate-common-logic/398731#398731
 * */
public class GeneralizeExample {

    /**Apparently the logic in specificMethod is duplicated, how can we generalize them and make it extensible? In case there's SetThree, SetFour in the future that needs to be added to this method?*/
    public void specificMethod(DDBRecord ddbRecord, Set<String> incomingSetOne, Set<String> incomingSetTwo, String incomingString) {
        Set<String> existingSetOne = ddbRecord.getSetOne();
        if (existingSetOne == null) {
            if (!incomingSetOne.isEmpty()) {
                ddbRecord.setSetOne(incomingSetOne);
                ddbRecord.setSetOneCount(incomingSetOne.size());
                existingSetOne = incomingSetOne;
            }
        } else if (existingSetOne != null) {
            if (incomingSetOne.isEmpty()) {
                //remove this string from this set if it exists
                existingSetOne.remove(incomingString);
                int updatedSetOneCount = existingSetOne.size();
                ddbRecord.setSetOneCount(updatedSetOneCount);
                if (updatedSetOneCount == 0) {
                    existingSetOne.add("N/A");
                }
                ddbRecord.setSetOne(existingSetOne);
            } else if (!incomingSetOne.isEmpty()) {
                if (existingSetOne.contains("N/A")) {
                    existingSetOne.remove("N/A");
                }
                //add this incoming set in
                existingSetOne.addAll(incomingSetOne);

                ddbRecord.setSetOne(existingSetOne);
                ddbRecord.setSetOneCount(existingSetOne.size());
            }
        }

        Set<String> existingSetTwo = ddbRecord.getSetTwo();
        if (existingSetTwo == null) {
            if (!incomingSetTwo.isEmpty()) {
                ddbRecord.setSetTwo(incomingSetTwo);
                ddbRecord.setSetTwoCount(incomingSetTwo.size());
                existingSetTwo = incomingSetTwo;
            }
        } else if (existingSetTwo != null) {
            if (incomingSetTwo.isEmpty()) {
                //remove this string from this set if it exists
                existingSetTwo.remove(incomingString);
                int updatedSetTwoCount = existingSetTwo.size();
                ddbRecord.setSetTwoCount(updatedSetTwoCount);
                if (updatedSetTwoCount == 0) {
                    existingSetTwo.add("N/A");
                }
                ddbRecord.setSetTwo(existingSetTwo);
            } else if (!incomingSetTwo.isEmpty()) {
                if (existingSetTwo.contains("N/A")) {
                    existingSetTwo.remove("N/A");
                }
                //add this incoming offer set in
                existingSetTwo.addAll(incomingSetTwo);

                ddbRecord.setSetTwo(existingSetTwo);
                ddbRecord.setSetTwoCount(existingSetTwo.size());
            }
        }

        //do something w/ existingSetOne and existingSetTwo afterwards
    }

    class DDBRecord {
        private Set<String> setOne;
        private Set<String> setTwo;
        private int setOneCount;
        private int setTwoCount;

        public Set<String> getSetOne() {
            return setOne;
        }

        public void setSetOne(Set<String> setOne) {
            this.setOne = setOne;
        }

        public void setSetOneCount(int count) {
            this.setOneCount = count;
        }

        public Set<String> getSetTwo() {
            return setTwo;
        }

        public void setSetTwo(Set<String> setTwo) {
            this.setTwo = setTwo;
        }

        public void setSetTwoCount(int count) {
            this.setTwoCount = count;
        }
    }

}
