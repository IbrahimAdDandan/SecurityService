package com.myframework.sbase.sys.repository;

import com.myframework.sbase.sys.domain.BaseModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenericRepository<Type extends BaseModel> extends CrudRepository<BaseModel, Long> {

    public List<Type> findByIsDeleted(boolean isDeleted);
}
