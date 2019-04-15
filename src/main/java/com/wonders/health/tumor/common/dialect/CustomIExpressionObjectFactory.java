package com.wonders.health.tumor.common.dialect;

import com.google.common.collect.Sets;
import com.wonders.health.tumor.common.tags.AuthData;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Set;

/**
 * Created by xuguobing on 2019/3/6.
 */
@Component
public class CustomIExpressionObjectFactory implements IExpressionObjectFactory {

    public static final String DIC = "dic";

    public static final String AUTH = "auth";

    private Set<String> names = Sets.newHashSet(DIC, AUTH);

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return names;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        if (DIC.equals(expressionObjectName)) {
//            return new DictData();
        } else if (AUTH.equals(expressionObjectName)) {
            return new AuthData();
        }
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return false;
    }
}
