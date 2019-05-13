package com.wonders.health.tumor.common.shiro;

import com.wonders.health.auth.client.AuthServiceI;
import com.wonders.shiro.utils.AddressUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xuguobing on 2017/9/9.
 */
public class MyCasFilter extends CasFilter {

    private AuthServiceI authService;
    private String shiroServerSuccessUrl;
    private String defaultSysServerUrl;
    private String defaultCasServerUrl;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        List<String> ips = AddressUtils.getProxyAddr((HttpServletRequest) request);

        String sysUrl = null;
        try {
            sysUrl = authService.findUrlByProxyIps(ips);
        } catch (Exception e) {
        }
        if (StringUtils.isBlank(sysUrl)) {
            if (StringUtils.isNotBlank(defaultSysServerUrl)) {
                sysUrl = defaultSysServerUrl;
            } else {
                sysUrl = defaultCasServerUrl;
            }
        }
        if (sysUrl == null) {
            sysUrl = "";
        }
        if (StringUtils.isNotBlank(sysUrl)) {
            String successUrl = "";
            if (StringUtils.isBlank(shiroServerSuccessUrl)) {
                successUrl = getSuccessUrl();
            } else {
                successUrl = shiroServerSuccessUrl;
            }
            WebUtils.redirectToSavedRequest(request, response, sysUrl + successUrl);
            return false;
        } else {
            return super.onLoginSuccess(token, subject, request, response);
        }
    }

    public AuthServiceI getAuthService() {
        return authService;
    }

    public void setAuthService(AuthServiceI authService) {
        this.authService = authService;
    }

    public String getShiroServerSuccessUrl() {
        return shiroServerSuccessUrl;
    }

    public void setShiroServerSuccessUrl(String shiroServerSuccessUrl) {
        this.shiroServerSuccessUrl = shiroServerSuccessUrl;
    }

    public String getDefaultSysServerUrl() {
        return defaultSysServerUrl;
    }

    public void setDefaultSysServerUrl(String defaultSysServerUrl) {
        this.defaultSysServerUrl = defaultSysServerUrl;
    }

    public String getDefaultCasServerUrl() {
        return defaultCasServerUrl;
    }

    public void setDefaultCasServerUrl(String defaultCasServerUrl) {
        this.defaultCasServerUrl = defaultCasServerUrl;
    }
}
