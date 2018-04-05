package ru.jogging.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.jogging.dao.DaoUser;
import ru.jogging.model.User;
import ru.jogging.model.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class UserJogginDetailsService implements UserDetailsService {

    private DaoUser daoUser;

    public UserJogginDetailsService(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user;
        try {
            synchronized (this) {
                user = daoUser.getUserByName(name);
                if (user == null) {
                    throw new UsernameNotFoundException("Пользователь не найден");
                }
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Ошибка получения данных пользователя");
        }

        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);
    }


    // конвертируем User в UserDetails
    private UserDetails buildUserForAuthentication(User user,
                                                   List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                true, true, true, true, authorities);

    }

    private List<GrantedAuthority> buildUserAuthority(UserRoles userRoles) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRoles.toString()));

        return authorities;
    }

}
