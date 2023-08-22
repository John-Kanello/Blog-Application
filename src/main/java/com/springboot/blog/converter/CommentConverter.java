package com.springboot.blog.converter;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;

public interface CommentConverter extends Converter<Comment, CommentDto> {


}
