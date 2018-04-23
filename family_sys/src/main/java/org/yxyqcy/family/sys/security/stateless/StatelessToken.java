package org.yxyqcy.family.sys.security.stateless;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * Created by lcy on 17/3/4.
 */
public class StatelessToken implements AuthenticationToken {

    private static final long serialVersionUID = 1648771769880797631L;

    private String principal;
    private Map<String, ?> params;
    private String clientDigest;

    public StatelessToken(String principal, Map<String, ?> params, String clientDigest) {
        this.principal = principal;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    //principal 身份
    @Override
    public String getPrincipal() {  return principal;}
    //credential 验证
    @Override
    public Object getCredentials() {  return clientDigest;}

    @Override
    public String toString() {
        return "StatelessToken{" +
                "principal=" + principal +
                ", params=" + params +
                ", clientDigest='" + clientDigest + '\'' +
                '}';
    }

    /**
     * 原生数据 顺序处理
     */
    private String originDigest;

    public void setOriginDigest(Map<String, String[]> params, String paramList) {
        if(null == paramList || "".equals(paramList))
            return;
        String[] sequence = paramList.split(",");
        if(sequence.length < 1)
            return;
        StringBuffer buffer = new StringBuffer();
        for (String se:sequence) {
            Object seObj = params.get(se);
            if(null != seObj) {
                String[] objs = (String[]) seObj;
                if(null != objs) {
                    for(String singleParam : objs){
                        buffer.append(singleParam);
                    }
                }
            }
        }
        this.setOriginDigest(buffer.toString());
    }

    public String getOriginDigest() {
        return originDigest;
    }

    public void setOriginDigest(String originDigest) {
        this.originDigest = originDigest;
    }
}
