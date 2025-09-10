package homework7.task3;

import java.util.HashSet;
import java.util.Set;

public class PowerfulSet {


    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> intersections = new HashSet<>();

        for (T num : set1) {

            if (set2.contains(num)) { // если есть пересечение, то помещаем элимент в сет
                intersections.add(num);
            }
        }
        return intersections;
    }

    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> intersections = new HashSet<>();
        intersections.addAll(set1); // помещаем весь сет
        intersections.addAll(set2); // и еще один
        ;

        return intersections;
    }


    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> intersections = new HashSet<>();

        for (T num : set1) {

            if (!set2.contains(num)) { // если элимента нет то помещаем его в сет
                intersections.add(num);
            }
        }
        return intersections;

    }
}
