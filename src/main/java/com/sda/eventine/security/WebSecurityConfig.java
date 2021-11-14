package com.sda.eventine.security;

//
//@Configuration
//@AllArgsConstructor
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserService userService;
//    private final BCryptPasswordEncoder encoder;
//
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/api/**")
//                    .permitAll()
//                    .antMatchers("/h2-console/**")
//                    .permitAll()
//                .anyRequest()
//                .authenticated().and()
//                .formLogin();
//    }
//
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(encoder);
//        provider.setUserDetailsService(userService);
//        return provider;
//    }
//}
