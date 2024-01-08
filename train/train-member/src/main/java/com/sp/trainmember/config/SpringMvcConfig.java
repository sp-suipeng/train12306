package com.sp.trainmember.config;


import com.sp.traincommon.interceptor.LogInterceptor;
import com.sp.traincommon.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

   @Autowired
   LogInterceptor logInterceptor;

   @Autowired
   MemberInterceptor memberInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(logInterceptor);

       registry.addInterceptor(memberInterceptor)
               .addPathPatterns("/**")
               .excludePathPatterns(
                       "/member/hello",
                       "/member/member/send-code",
                       "/member/member/login"
               );
   }
}
