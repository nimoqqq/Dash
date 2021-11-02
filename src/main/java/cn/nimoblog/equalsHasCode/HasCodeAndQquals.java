package cn.nimoblog.equalsHasCode;

import lombok.Data;

import java.util.*;

/**
 * 重写 equals 和 hasCode 方法
 */
@Data
public class HasCodeAndQquals {

    private String name;

    private int age;

    private String card;

    private String address;

    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        HasCodeAndQquals that = (HasCodeAndQquals) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getAddress());
    }
}
