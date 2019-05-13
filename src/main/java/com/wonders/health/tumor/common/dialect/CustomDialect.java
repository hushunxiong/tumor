package com.wonders.health.tumor.common.dialect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * Created by xuguobing on 2019/3/6.
 */

@Component
public class CustomDialect extends AbstractDialect implements IExpressionObjectDialect {

    @Autowired
    private CustomIExpressionObjectFactory customIExpressionObjectFactory;

    public CustomDialect() {
        super("Custom");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return customIExpressionObjectFactory;
    }
}
