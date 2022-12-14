package com.example.ecommerce.service;

import com.example.ecommerce.entity.dao.login.Role;
import com.example.ecommerce.entity.dao.login.User;
import com.example.ecommerce.entity.dao.sales.Bag;
import com.example.ecommerce.entity.dto.UserRegistrationDto;
import com.example.ecommerce.repository.IBagRepository;
import com.example.ecommerce.repository.IRoleRepository;
import com.example.ecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBagRepository bagRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }



    @Override
    public User save(UserRegistrationDto registrationDto) {
        Bag bag =bagRepository.save(new Bag(
                0.0
        ));

        User user = new User(
                registrationDto.getFirstName()
                ,registrationDto.getLastName()
                , registrationDto.getUserName()
                ,passwordEncoder.encode(registrationDto.getPassword())
                ,bag
                , List.of(roleRepository.findByName("ROLE_ADMIN"))
        );

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByEmail(username);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
