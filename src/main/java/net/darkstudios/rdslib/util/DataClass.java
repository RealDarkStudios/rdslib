package net.darkstudios.rdslib.util;

import net.darkstudios.rdslib.util.item.IDataClass;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DataClass<T> implements IDataClass {
    private final List<?> fields;

    public DataClass(List<?> pFields) {
        this.fields = pFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T t = (T) o;
        
        return Objects.equals(fields, ((DataClass<?>) o).fields);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(fields.toArray());
    }

    @Override
    public String toString() {
        return String.format("DataClass[fields=%s]", fields);
    }
}
