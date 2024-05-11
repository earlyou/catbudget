package com.earlyou.catbudget.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.catbudget.frame.Biz;
import com.earlyou.catbudget.mapper.UserinfoMapper;
import com.earlyou.catbudget.vo.UserinfoVO;

@Service
public class UserinfoBiz implements Biz<String, UserinfoVO> {

	@Autowired
	UserinfoMapper dao;
	
	@Override
	public void register(UserinfoVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(UserinfoVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public UserinfoVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<UserinfoVO> get() throws Exception {
		return dao.selectall();
	}

}