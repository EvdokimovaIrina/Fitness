package ru.jogging.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.jogging.controller.returnTheResult.FactoryRestResult;
import ru.jogging.controller.returnTheResult.FactoryRestResultImpl;
import ru.jogging.dao.DaoJogging;
import ru.jogging.dao.DaoJoggingImpl;
import ru.jogging.dao.DaoUser;
import ru.jogging.dao.DaoUserImpl;
import ru.jogging.service.*;

@Configuration
public class AppContext {
    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }

    @Bean
    public DaoJogging daoJogging(){
        DaoJogging daoJogging = new DaoJoggingImpl(sessionFactory);
        return  daoJogging;
    }

    @Bean
    public DaoUser daoUser(){
        DaoUser daoUser = new DaoUserImpl(sessionFactory);
        return  daoUser;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetailsService userDetailsService = new UserJogginDetailsService(daoUser());
        return userDetailsService;
    }

    @Bean
    public JoggingService joggingService(){
        JoggingService joggingService = new JoggingServiceImpl(daoJogging(),userService());
        return joggingService;
    }

    @Bean
    public UserService userService(){
        UserService userService = new UserServiceImpl(daoUser());
        return userService;
    }

    @Bean
    public FactoryRestResult factoryRestResult(){
        FactoryRestResult factoryRestResult = new FactoryRestResultImpl();
        return factoryRestResult;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }
}
