package seckill.com.seckilljmvc.jingzhi;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijingzhi
 * @create 2019/5/22
 */
public class ParamInteger implements ParamStrategy<Integer> {
    @Override
    public Integer obtainRealParam(String paramName,HttpServletRequest request) {
        String objString=request.getParameter(paramName);
        return objString==null? null :Integer.valueOf(objString);
    }
}
