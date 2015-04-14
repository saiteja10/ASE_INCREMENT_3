package com.ase.hibernate;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
    private static Logger log = LoggerFactory.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            SessionFactory sf = null;
            String hibernateFileLocatio="hibernate.cfg.xml";
            //From the properties file
            if (StringUtils.isNotBlank(hibernateFileLocatio)) {

                ClassLoader loader = HibernateUtil.class.getClassLoader();
                if (loader == null)
                    loader = ClassLoader.getSystemClassLoader();

                java.net.URL configURL = loader.getResource(hibernateFileLocatio);

                if (null != configURL) {
                    log.info("Configuring hibernate using " + hibernateFileLocatio + " located at " + configURL.toString());
                    sf = new Configuration().configure(configURL).buildSessionFactory();
                } else
                    log.warn("Config file not found " + hibernateFileLocatio);
            }
            //Default hibernate.cfg.xml from resource
            if (null == sf)
                sf = new Configuration().configure().buildSessionFactory();
            return sf;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

}
