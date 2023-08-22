package com.springboot.blog.converter;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;

public interface PostConverter extends Converter<Post, PostDto> {

}
