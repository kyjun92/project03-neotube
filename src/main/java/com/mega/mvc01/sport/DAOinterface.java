package com.mega.mvc01.sport;

import java.util.List;

import org.springframework.ui.Model;

public interface DAOinterface {

	void insert();
	void update();
	void one();
	void delete();
	List<VideoVO> list();
}
