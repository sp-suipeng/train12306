package com.sp.gateway.filter;

import com.sp.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoginMemberFilter implements Ordered, GlobalFilter {
    private static Logger LOG = LoggerFactory.getLogger(LoginMemberFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        //排除不需要拦截的请求
        if(path.contains("/admin")
        || path.contains("/hello")
        || path.contains("/member/login")
        || path.contains("/member/sendcode")) {
            LOG.info("不需要登录校验:{}", path);
            return chain.filter(exchange);
        } else {
            LOG.info("需要登录校验:{}", path);
        }

        //获取header中的token参数
        String token = request.getHeaders().getFirst("token");
        LOG.info("会员登录验证开始, token={}", token);
        if(token == null || token.isEmpty()) {
            LOG.info("token为空，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        //校验token是否有效，包括token是否被更改过，是否过期
        boolean validate = JwtUtil.validate(token);
        if(validate) {
            LOG.info("token有效，放行该请求");
            return chain.filter(exchange);
        } else {
            LOG.warn("token 无效，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }


    }

    /**
     * 值越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
