package com.example.demo.dao;

import com.example.demo.domain.ComVO;

public interface ComDAO {
    public void gotoWork(ComVO cvo);

    public void offWork(ComVO cvo);

    public boolean checkWork(ComVO cvo);

    public void weekTime(ComVO cvo);
}
