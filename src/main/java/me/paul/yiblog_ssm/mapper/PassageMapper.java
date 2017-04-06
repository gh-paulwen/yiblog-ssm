package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.Passage;

public interface PassageMapper {
	
	Passage getByIdAndAuthor(long id,long author);
	
	Passage getByCommentAndAuthor(long comment,long author);
	
	Passage getById(long id);
	
	void insert (Passage passage);
	
	void update(Passage passage);
	
	List<Passage> page(int from , int count);
	
	List<Passage> categoryPage(long category , int from , int count);
	
	int passageCount();
	
	int categoryPassageCount(long id);
	
	List<Passage> search(String content);
	
	List<Passage> getBasic(long author);
	
	List<Passage> getBasicAll();
	
	List<Passage> getAll();
	
	List<Long> categoryPageIds(long category,int from ,int count);
	
	List<Long> pageIds(int from,int count);
	
}
