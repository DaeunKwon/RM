package com.example.demo.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public Map<String, Object> getUserInfo(int userSeq) {
        // userMapper 라는 부분과 5단계에 있는 mapper.xml 파일의 namespace를 동일하게 맞춰준다
        // .getUserInfo 와 5단계에 있는 <select id= 부분를 동일하게 맞춰준다.
        return sqlSessionTemplate.selectOne("userMapper.getUserInfo", userSeq);

    }

}
