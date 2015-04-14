package com.ase.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import java.io.File;

/**
 * This class will help generate the sql statements to update the DB.
 *
 * @author siddhartha
 */

public class SchemaUpdater {

    public static void main(String args[]) {
        if (args.length < 1) {
            throw new RuntimeException("Please provide the path to the hibernate.cfg.xml as first argument");
        }

        File f = new File(args[0]);
        if (!f.exists()) {
            throw new RuntimeException("The file specified does not exists : " + f.getAbsolutePath());
        }
        Configuration cfg = new Configuration();
        cfg.configure(f);
        SchemaUpdate su = new SchemaUpdate(cfg);
        su.setOutputFile("diff.sql");
        su.execute(true, true);
    }

}
