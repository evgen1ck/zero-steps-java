package academy.tochkavhoda.school;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.Collection;
import java.util.Collections;

public class TraineeMultiValuedMap {

    private final MultiValuedMap<Integer, Trainee> map = new HashSetValuedHashMap<>();

    public boolean addTrainee(Trainee trainee) {
        if (map.get(trainee.getRating()).contains(trainee)) {
            return false;
        }
        return map.put(trainee.getRating(), trainee);
    }

    public boolean removeTrainee(Trainee trainee) {
        if (!map.get(trainee.getRating()).contains(trainee)) {
            return false;
        }
        return map.removeMapping(trainee.getRating(), trainee);
    }

    public int getTraineesCount() {
        return map.size();
    }

    public Collection<Trainee> getTraineesByRating(int rating) {
        if (!map.containsKey(rating)) {
            return Collections.emptyList();
        }
        return map.get(rating);
    }

    public boolean hasAnyBodyRating(int rating) {
        return map.containsKey(rating) && !map.get(rating).isEmpty();
    }
}
