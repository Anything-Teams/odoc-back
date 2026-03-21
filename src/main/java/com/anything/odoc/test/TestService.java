package com.anything.odoc.test;

import com.anything.odoc.test.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestDao testDao;

    public TestService(TestDao testDao) {
        this.testDao = testDao;
    }

    public String selectName() {
        return testDao.selectName();
    }
}
