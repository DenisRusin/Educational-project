package homework7.task1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UniqueValues {

    public <T> Set<T> getUniqueValues(ArrayList<T> values){

        return new HashSet<>(values); // в сет записываются только уникальные значения
    }
}
