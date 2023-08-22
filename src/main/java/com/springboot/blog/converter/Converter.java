package com.springboot.blog.converter;

public interface Converter<T,V>{

    T mapToEntity(V entityDto);
    V mapToDto(T entity);
}
