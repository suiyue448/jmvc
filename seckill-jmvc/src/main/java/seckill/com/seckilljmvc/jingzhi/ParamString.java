package seckill.com.seckilljmvc.jingzhi;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijingzhi
 * @create 2019/5/22
 */
public class ParamString implements ParamStrategy<String> {
    @Override
    public String obtainRealParam(String paramName, HttpServletRequest request) {
        return request.getParameter(paramName);
    }
}
