package com.ydk.service.impl;

import com.ydk.mapper.BlogMapper;
import com.ydk.model.Blog;
import com.ydk.service.BlogService;

public class BlogServiceImpl implements BlogService{
	
	private BlogMapper mapper;
	
	public void addBlog(Blog blog){
		mapper.addBlog(blog);
	}
}
