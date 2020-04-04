package com.myframework.sbase.sys.service;

import com.myframework.sbase.sys.repository.GenericRepository;
import com.myframework.sbase.sys.domain.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericService<Type extends BaseModel> {

    @Autowired
    private GenericRepository<Type> genericRepository;

    public GenericRepository getGenericRepository() {
        return genericRepository;
    }

    public void setGenericRepository(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    public List<Type> getAll() {
        return (List<Type>) genericRepository.findByIsDeleted(false);
    }

    public Type getOne(Long id) {
        return (Type) genericRepository.findById(id).get();
    }
    public void delete(BaseModel type) {
        type.setDeleted(true);
        genericRepository.save(type);
    }

    public Type save(BaseModel type) {
        return (Type) genericRepository.save(type);
    }

    public List<Type> saveAll(List<Type> types) {
        return (List<Type>) genericRepository.saveAll(types);
    }
}
