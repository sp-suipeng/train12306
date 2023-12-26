package com.sp.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class TestFilter implements GlobalFilter, Ordered {
    private static Logger LOG = LoggerFactory.getLogger(TestFilter.class);

    /**
     * 过滤
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOG.info("TestFilter");
        return chain.filter(exchange);
    }

    /**
     * 实现调用顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 9999;
    }
}
