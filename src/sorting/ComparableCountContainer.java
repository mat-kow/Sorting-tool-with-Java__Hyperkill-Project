package sorting;

import org.jetbrains.annotations.NotNull;

record ComparableCountContainer<T extends Comparable<T>>(T key, int count) implements Comparable<ComparableCountContainer<T>> {

    @Override
    public int compareTo(@NotNull ComparableCountContainer<T> that) {
        int compare = Integer.compare(this.count, that.count);
        if (compare != 0)
            return compare;
        return this.key.compareTo(that.key);
    }
}

