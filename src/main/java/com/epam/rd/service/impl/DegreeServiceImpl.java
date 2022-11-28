package com.epam.rd.service.impl;

import com.epam.rd.exceptions.BadRequest;
import com.epam.rd.model.entity.Degree;
import com.epam.rd.repository.DegreeRepository;
import com.epam.rd.service.DegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {
    private final DegreeRepository degreeRepository;

    @Override
    public List<Degree> getAll() {
        return degreeRepository.findAll();
    }

    @Override
    public Optional<Degree> getById(Long ID) {
        return degreeRepository.findById(ID);
    }

    @Override
    public Degree create(Degree entity) {
        if (entity.getId() == null) {
            return degreeRepository.save(entity);
        }
        throw new BadRequest("ID  must be  null");
    }

    @Override
    public Degree update(Degree entity) {
        if (entity.getId() != null) {
            return degreeRepository.save(entity);
        }
        throw new BadRequest("ID  must not be  null");
    }

    @Override
    public void deleteById(Long ID) {
        degreeRepository.deleteById(ID);
    }

    @Override
    public boolean delete(Long ID) {
        degreeRepository.deleteById(ID);
        return true;
    }
}
