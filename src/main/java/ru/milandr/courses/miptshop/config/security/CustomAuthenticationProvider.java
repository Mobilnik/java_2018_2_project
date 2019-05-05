package ru.milandr.courses.miptshop.config.security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.milandr.courses.miptshop.common.utils.PasswordUtils;
import ru.milandr.courses.miptshop.daos.UserDao;
import ru.milandr.courses.miptshop.entities.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    private final UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (name == null || password == null) {
            throw new BadCredentialsException("Wrong username or password");
        }

        User user = userDao.findByName(authentication.getName());
        if (user == null) {
            throw new BadCredentialsException("Wrong username or password");
        }

        String passwordSalt = user.getPasswordSalt();
        if (passwordSalt == null) {
            throw new CredentialsExpiredException("DB error");
        }

        String dbHash = user.getPasswordHash();
        if (dbHash == null) {
            throw new CredentialsExpiredException("DB error");
        }

        String currentHash;
        try {
            currentHash = PasswordUtils.getPasswordHash(password, passwordSalt);
        } catch (NoSuchAlgorithmException e) {
            throw new CredentialsExpiredException("Hash calculation error");
        }

        if (currentHash == null) {
            throw new BadCredentialsException("Wrong username or password");
        }

        if (!currentHash.equals(dbHash)) {
            throw new BadCredentialsException("Wrong username or password");
        }

        return new UsernamePasswordAuthenticationToken(name, user.getPasswordHash(), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
