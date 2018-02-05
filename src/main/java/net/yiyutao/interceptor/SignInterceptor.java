package net.yiyutao.interceptor;

import com.alibaba.fastjson.JSONObject;
import net.yiyutao.common.DataResult;
import net.yiyutao.common.StatusCode;
import net.yiyutao.utils.InterParamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: masterYI
 * @date: 2017/10/19
 * @time: 11:54
 * Description:签名处理器拦截器
 */
public class SignInterceptor extends HandlerInterceptorAdapter {

    /**
     * 预处理请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        DataResult result = new DataResult();

        //验证时间戳是否超时
        String time = request.getParameter("timestamp");
        try {
            if (!InterParamUtils.verifyTimeStamp(time)) {
                result.setStatusCode(StatusCode.TimeStampOver);
                response.getWriter().append(JSONObject.toJSONString(result));
                return false;
            }
            //校验参数
            String signKey = "sign";
            String sign = request.getParameter(signKey);
            Map<String, String[]> parameterMap = request.getParameterMap();
            TreeMap<String, String> dataMap = new TreeMap<>();
            if (parameterMap != null) {
                //将所有参数存入Treemap进行自动排序
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    String key = entry.getKey();
                    if (!signKey.equals(key)) {
                        String[] value = entry.getValue();
                        dataMap.put(key, value[0]);
                    }
                }
            }
            if (!InterParamUtils.verifySign(dataMap, sign)) {
                result.setStatusCode(StatusCode.SignFail);
                response.getWriter().append(JSONObject.toJSONString(result));
                return false;
            }
        } catch (Exception e) {
            result.setStatusCode(StatusCode.SysException);
            response.getWriter().append(JSONObject.toJSONString(result));
            return false;
        }
        return true;
    }

}
