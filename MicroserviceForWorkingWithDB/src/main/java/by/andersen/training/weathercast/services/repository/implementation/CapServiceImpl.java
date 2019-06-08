package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.Cap;
import by.andersen.training.weathercast.repositories.CapRepository;
import by.andersen.training.weathercast.services.repository.interfaces.CapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapServiceImpl implements CapService {

    @Autowired
    private CapRepository capRepository;

    @Override
    public void save(Cap cap) {
        capRepository.save(cap);
    }

    @Override
    public void saveAll(List<Cap> caps) {
        capRepository.saveAll(caps);
    }

    @Override
    public Cap findById(Long id) {
        return capRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return capRepository.existsById(id);
    }

    @Override
    public List<Cap> findAll() {
        List<Cap> caps = new ArrayList<>();
        for (Cap cap : ((CrudRepository<Cap, Long>) capRepository).findAll()) {
            caps.add(cap);
        }
        return caps;
    }

    @Override
    public long count() {
        return capRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        capRepository.deleteById(id);
    }

    @Override
    public void delete(Cap cap) {
        capRepository.delete(cap);
    }

    @Override
    public void deleteAll() {
        capRepository.deleteAll();
    }

    @Override
    public List<Cap> findWithAllLazyAll() {
        List<Cap> caps = new ArrayList<>();
        for (Cap cap : capRepository.findAll()) {
            caps.add(cap);
        }
        return caps;
    }

    @Override
    public Cap findWithAllLazyById(Long id) {
        return capRepository.findLazyById(id).get();
    }

}
