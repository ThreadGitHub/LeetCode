package org.leetcode.utils;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;

/**
 * 数组参数转换器
 * @author thread
 * @date 2023/10/27 21:53
 */
public class ArrayConver extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (source instanceof String) {
            source = ((String) source).replaceAll(" ", "");
            // 分割为数组
            String str = source.toString().replace("[", "")
                    .replace("]", "");
            String[] array = new String[0];
            if (StringUtils.isNotBlank(str)) {
                array = str.split(",");
            }
            // 如果是int数组
            if (int[].class.isAssignableFrom(targetType)) {
                return Arrays.stream(array).mapToInt(Integer::parseInt).toArray();
            }
            // 如果是浮点数组
            else if (float[].class.isAssignableFrom(targetType)) {
                float[] result = new float[array.length];
                Object[] objs = Arrays.stream(array).map(Float::parseFloat).toArray();
                for (int i = 0; i < objs.length; i++) {
                    result[i] = (float)objs[i];
                }
                return result;
            }
            else if (double[].class.isAssignableFrom(targetType)) {
                return Arrays.stream(array).mapToDouble(Double::parseDouble).toArray();
            }
            // 如果是字符数组
            else {
                return array;
            }
        }
        return source;
    }
}
