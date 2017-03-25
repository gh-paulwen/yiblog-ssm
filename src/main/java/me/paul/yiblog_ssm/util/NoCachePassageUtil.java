package me.paul.yiblog_ssm.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.mapper.PassageMapper;

public class NoCachePassageUtil implements ICachePassageUtil{
	
	@Autowired
	private PassageMapper pm;

	@Override
	public void flush() {
		
	}

	@Override
	public void save(Passage passage) {
	}

	@Override
	public void set(Passage passage) {
	}

	
	@Override
	public Passage get(String key) {
		return pm.getById(Long.parseLong(key));
	}

	@Override
	public void batchSave(List<Passage> list) {
		
	}

	@Override
	public List<Passage> list(int page, int passagePerPage) {
		return pm.page((page-1)*passagePerPage, passagePerPage);
	}

	@Override
	public List<Passage> list(int page, int passagePerPage, long category) {
		return pm.categoryPage(category,(page-1)*passagePerPage, passagePerPage);
	}

	@Override
	public List<Passage> simpleList(int page, int passagePerPage) {
		return pm.page((page-1)*passagePerPage, passagePerPage);
	}

	@Override
	public List<Passage> simpleList(int page, int passagePerPage, long category) {
		return pm.categoryPage(category,(page-1)*passagePerPage, passagePerPage);
	}

}
