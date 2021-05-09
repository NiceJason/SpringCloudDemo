package com.nicebin.global.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.nicebin.common.exception.SystemException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * @Author DiaoJianBin
 * @Description   这是一个自定义的过滤器工厂类,要求有两个
 *  名字必须是 配置+GatewayFilterFactory  本类的配置字段是 Surprise
 *  必须继承 AbstractGatewayFilterFactory <配置类>
 *
 *               参考 https://blog.csdn.net/forezp/article/details/85057268
 *
 * @Date 2021/3/28 22:17
 */
@Component
public class MsgGatewayFilterFactory extends AbstractGatewayFilterFactory {

    /**
     * Cookie里是否有token
     * 如果没有则拒绝请求
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            System.out.println("进入MsgGatewayFilterFactory");
            ServerHttpRequest serverRequest = exchange.getRequest();
            HttpCookie tokenCookie = serverRequest.getCookies().getFirst("token");
            if(tokenCookie ==null || StringUtils.isEmpty(tokenCookie.getValue())){
                System.out.println("MsgGatewayFilterFactory拦截 因为cookie里未找到token");
                //这里也可以return Mono.empty()
                throw new SystemException("cookie里未找到token");
            }
            System.out.println("获取到token = "+tokenCookie.getValue());

            System.out.println("退出MsgGatewayFilterFactory");
            return exchange.getSession().flatMap(webSession -> {
                System.out.println("Gateway开始操作WebSession");
                ServerHttpResponse response = exchange.getResponse();

                //获得全局拦截器的记录
                String globalMsg = webSession.getAttribute("GlobalGateway");
                System.out.println(globalMsg);

                //这Session只能在网关里用
                //除非这里自己存到缓存里去，像做SpringSession那样，不然分布式不了
                webSession.getAttributes().put("Gateway","Gateway记录了值");
                JSONObject message = new JSONObject();


                //操作头部，body,cookie
                ServerHttpRequest request = exchange.getRequest();

                HttpHeaders headers = request.getHeaders();
                MultiValueMap<String, HttpCookie> cookies = request.getCookies();
                MultiValueMap<String, String> queryParams = request.getQueryParams();
                Flux<DataBuffer> body = request.getBody();

                //想要操作body的值不容易的
                //参考：https://blog.csdn.net/seantdj/article/details/100546713
                //参考：https://www.haoyizebo.com/posts/876ed1e8/

                //头部里面放值，会报错，这是个ReadOnlyHttpHeaders
                //headers.add("GatewayKey-header","GatewayValue-header");
                //想要设置要这样设，传输中文需要编码，不然乱码
                try{
                    request.mutate().header("GatewayKey-header", URLEncoder.encode("mutate的方式设置头的值是可以的","UTF-8"));
                }catch (Exception e){
                    System.out.println("Gateway头部设置信息出错");
                    System.out.println(e);
                }


                //cookie里面放值，会报错，只能读
                //cookies.add("GatewayKey-cookie",new HttpCookie("GatewayCookieKey","GatewayCookieValue"));

                //往参数里面放，会报错，只能读
                //queryParams.add("GatewayKey-param","GatewayValue-param");

                return chain.filter(exchange);
            });
        });
    }
}
