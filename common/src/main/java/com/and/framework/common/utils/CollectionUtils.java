package com.and.framework.common.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangyadong on 2018/5/3.
 */

public class CollectionUtils {

    /**
     * This method is to be deprecated, please use Arrays.asList instead.
     *
     * @deprecated
     */
    public static <T> List<T> arrayToList(T[] array) {
        return Arrays.asList(array);
    }

    public static Float getMaxValue(List<Float> list) {
        return Collections.max(list);
    }

    public static Float getMinValue(List<Float> list) {
        return Collections.min(list);
    }

    public static boolean isValidate(Collection<?> families) {
        return families != null && !families.isEmpty();
    }

    public static boolean isValidate(Object[] families) {
        return families != null && families.length > 0;
    }

}
