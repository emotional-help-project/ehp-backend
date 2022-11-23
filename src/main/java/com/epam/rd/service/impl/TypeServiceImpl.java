package com.epam.rd.service.impl;

import com.epam.rd.exceptions.BadRequest;
import com.epam.rd.model.entity.Type;
import com.epam.rd.repository.TypeRepository;
import com.epam.rd.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Override
    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> getById(Long ID) {
        return typeRepository.findById(ID);
    }

    @Override
    public Type create(Type entity) {
        if (entity.getId()==null){
            return typeRepository.save(entity);
        }
        throw new BadRequest("ID  must be  null");
    }

    @Override
    public Type update(Type entity) {
        if (entity.getId()!=null){
        return typeRepository.save(entity);
        }
        throw new BadRequest("ID must not be null");
    }


    @Override
    public void deleteById(Long ID) {
        typeRepository.deleteById(ID);

    }

   @Override
    public boolean delete(Long ID){
        typeRepository.deleteById(ID);
        return true;
   }
}
