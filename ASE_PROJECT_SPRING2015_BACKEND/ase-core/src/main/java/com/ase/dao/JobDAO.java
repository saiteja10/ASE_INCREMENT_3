package com.ase.dao;

import com.ase.domain.Job;
import com.ase.query.JobQuery;
import org.springframework.stereotype.Component;

/**
 * Created by Gurrala on 4/5/2015.
 */
@Component
public class JobDAO extends BaseDAO<Job, JobQuery> {
    @Override
    protected Class<Job> getEntityClass() {
        return Job.class;
    }
}
