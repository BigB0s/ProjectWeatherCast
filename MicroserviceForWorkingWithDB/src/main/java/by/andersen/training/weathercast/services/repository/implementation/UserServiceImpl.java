package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.User;
import by.andersen.training.weathercast.repositories.UserRepository;
import by.andersen.training.weathercast.services.repository.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Iterable<User> itr = ((CrudRepository<User, Long>) userRepository).findAll();
        for (User user : itr) {
            users.add(user);
        }
        return users;
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> findWithAllLazyAll() {
        List<User> users = new ArrayList<>();
        Iterable<User> itr = userRepository.findAll();
        for (User user : itr) {
            users.add(user);
        }
        return users;
    }

    @Override
    public User findWithAllLazyById(Long id) {
        return userRepository.findLazyById(id).get();
    }

    @Override
    public User findByLogin(String login) {
        Optional<User> byLogin = userRepository.findByLogin(login);
        if (byLogin.isPresent()) {
            return byLogin.get();
        }
        return null;
    }
}
