package com.example.demo.service;

import com.example.demo.domain.ProjectVO;

public interface ProjectService {
    public void write(ProjectVO pvo);

    public void modify(ProjectVO pvo);

    public void remove(ProjectVO pvo);

}
