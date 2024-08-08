package com.example.oauthreact.Filter;

import com.example.oauthreact.entity.UserEntity;
import com.example.oauthreact.provider.JwtProvider;
import com.example.oauthreact.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { //jwt필터

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = ParseBearerToken(request);
            if (token == null) { //Authorization,Bearer 가 아닐경우 진행하지 않게 한다
                filterChain.doFilter(request,response); //필터로 이동
                return;
            }

            String userId = jwtProvider.Validate(token);
            if (userId == null){
                filterChain.doFilter(request,response);
                return;
            }

            UserEntity userEntity =  userRepository.findByUserId(userId);
            String role = userEntity.getRole(); //role = ROLE_USER,ROLE_ADMIN

            //권한이 여러개일 수도 있기 때문에 list로 받는다
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); //빈 Context 생성

            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId,null,authorities);

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);


        }catch (Exception exception){
            exception.printStackTrace();
        }

        filterChain.doFilter(request,response); //다음 필터로 넘어가게 한다


    }



    //토큰 검증해서 가지고 오기
    private String ParseBearerToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization"); //이이름 그대로 사용하기 틀리면 에러 발생

        boolean hasAuthorization = StringUtils.hasText(authorization); //hasnext는 만약에 null이 아니거나 길이가 0이 아닌상태를 검사한다 = 값이 있는지 검사

        if (!hasAuthorization) return null; //true가 아닐경우 null 반환

        //authorization bearer인증 방식인지 확인
        boolean isBearer = authorization.startsWith("Bearer "); //Bearer 한칸 띄우고 로 시작하는지 검사
        if (!isBearer) return null; //false 면 null반환

        String token = authorization.substring(7); //위에 Bearer 에 7번째부터 요소를 가지고 온다

        return token;
    }
}
