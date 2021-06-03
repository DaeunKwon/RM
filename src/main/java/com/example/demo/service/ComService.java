package com.example.demo.service;

import com.example.demo.domain.ComVO;

public interface ComService {
    public void gotoWork(ComVO cvo);

    public void offWork(ComVO cvo);

    public boolean checkWork(ComVO cvo);

    public void weekTime(ComVO cvo);
}
