package iot.server.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()// rest api이므로 기본설정 사용 안함, 기본설정은 비인증시 로그인 폼 화면으로 리다이렉트
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable() // rest Api 이므로 csrf 보안이 필요없으므로 disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성 X
                .and()
                    .authorizeRequests() // 다음 리퀘스트에 대한 사용 권한 체크
                        .antMatchers("/member/signin","/member/signup").permitAll() // 가입 및 인증 주소는 누구나 접근 가능
                        .antMatchers(HttpMethod.GET,"/exception/**").permitAll() // exception 허용
                        .antMatchers(HttpMethod.POST,"/useHistory").permitAll()
                        .antMatchers(HttpMethod.POST,"/disinfectant").permitAll()
                        .antMatchers(HttpMethod.GET,"/hello").permitAll()
                        .anyRequest().hasRole("MEMBER") // 그 외 나머지 요청은 모두 인증된 회원만 접근 가능용
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);// jwt 토큰 필터를 id/password 필터 전에 넣는다.
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs","/swagger-resources/**","/swagger-ui.html"
        ,"/webjars/**","/swagger/**");
    }
}
