package com.payina.apigatewayservice.filter;

import com.payina.apigatewayservice.exception.InvalidTokenException;
import com.payina.apigatewayservice.exception.TokenException;
import com.payina.apigatewayservice.util.JwtUtil;
import com.payina.apigatewayservice.util.RouteValidator;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    private  ServerHttpRequest request = null;

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain)-> {

            if(routeValidator.isSecured.test(exchange.getRequest())){
                // header contains token or not..
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new TokenException("missing authentication header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                   authHeader =  authHeader.substring(7);

                }
                try {
                    // REST call to AUTH service
                //    client.getForObject("http://AUTH_SERVICE/validate?token=" + authHeader, String.class)


                 jwtUtil.validateToken(authHeader);

                 // to send user info to other microservice
                    request =  exchange.getRequest()
                            .mutate()
                            .header("loggedInUser" , jwtUtil.extractUsername(authHeader))

                            .build();
                }catch (Exception ex){
                    System.out.println("invalid token ...");
                    throw new InvalidTokenException(ex.getMessage());
                }
            }

            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config{

    }
}
