package com.earlyou.catbudget.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.catbudget.vo.UserinfoVO;

@Repository
@Mapper
public interface UserinfoMapper {
	public void insert(UserinfoVO userinfo) throws Exception;
	public void delete(String uid) throws Exception;
	public void update(UserinfoVO userinfo) throws Exception;
	public UserinfoVO select(String uid) throws Exception;
	public List<UserinfoVO> selectall() throws Exception;
}
