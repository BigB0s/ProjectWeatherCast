package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.Country;
import by.andersen.training.weathercast.repositories.CountryRepository;
import by.andersen.training.weathercast.services.repository.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void saveAll(List<Country> countries) {
        countryRepository.saveAll(countries);
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return countryRepository.existsById(id);
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        for (Country country : countries) {
            countries.add(country);
        }
        return countries;
    }

    @Override
    public long count() {
        return countryRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public void delete(Country country) {
        countryRepository.delete(country);
    }

    @Override
    public void deleteAll() {
        countryRepository.deleteAll();
    }
}
