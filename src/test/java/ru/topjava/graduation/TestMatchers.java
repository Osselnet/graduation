package ru.topjava.graduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.topjava.graduation.model.AbstractBaseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.graduation.TestUtil.readListFromJsonMvcResult;

public class TestMatchers<T> {
    private final Class<T> clazz;
    private final String[] fieldsToIgnore;
    private final boolean useEquals;

    private TestMatchers(Class<T> clazz, boolean useEquals, String... fieldsToIgnore) {
        this.clazz = clazz;
        this.useEquals = useEquals;
        this.fieldsToIgnore = fieldsToIgnore;
    }

    public static <T> TestMatchers<T> useFieldsComparator(Class<T> clazz, String... fieldsToIgnore) {
        return new TestMatchers<>(clazz, false, fieldsToIgnore);
    }

    public <T extends AbstractBaseEntity> void assertMatch(T actual, T expected) {
        if (useEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).isEqualToIgnoringGivenFields(expected, fieldsToIgnore);
        }
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        if (useEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
        }
    }

    public ResultMatcher contentJson(Iterable<T> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, clazz), expected);
    }
}
