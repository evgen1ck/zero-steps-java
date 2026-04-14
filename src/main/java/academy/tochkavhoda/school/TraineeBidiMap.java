package academy.tochkavhoda.school;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.HashSet;
import java.util.Set;

public class TraineeBidiMap {

    private final BidiMap<String, Trainee> map = new DualHashBidiMap<>();

    public void addTrainee(Trainee trainee, String passport) throws TrainingException {
        if (map.containsValue(trainee)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
        if (map.containsKey(passport)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_PASSPORT);
        }
        map.put(passport, trainee);
    }

    public void replaceTraineePassport(Trainee trainee, String passport) throws TrainingException {
        if (!map.containsValue(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        if (map.containsKey(passport)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_PASSPORT);
        }
        String oldPassport = map.getKey(trainee);
        map.remove(oldPassport);
        map.put(passport, trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!map.containsValue(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        map.removeValue(trainee);
    }

    public void removeTraineeByPassport(String passport) throws TrainingException {
        if (!map.containsKey(passport)) {
            throw new TrainingException(TrainingErrorCode.PASSPORT_NOT_FOUND);
        }
        map.remove(passport);
    }

    public int getTraineesCount() {
        return map.size();
    }

    public String getPassportByTrainee(Trainee trainee) throws TrainingException {
        if (!map.containsValue(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return map.getKey(trainee);
    }

    public Trainee getTraineeByPassport(String passport) throws TrainingException {
        if (!map.containsKey(passport)) {
            throw new TrainingException(TrainingErrorCode.PASSPORT_NOT_FOUND);
        }
        return map.get(passport);
    }

    public Set<Trainee> getAllTrainees() {
        return new HashSet<>(map.values());
    }

    public Set<String> getAllpassports() {
        return new HashSet<>(map.keySet());
    }

    public boolean hasAnybodyPassport(String passport) {
        return map.containsKey(passport);
    }
}
