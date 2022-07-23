/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.delivery.pojo.Customer;
import com.delivery.pojo.User;
import com.delivery.repository.UserRepository;
import com.delivery.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean checkUsername(String username) {
        return userRepository.checkUsername(username);
    }

    @Override
    public boolean addUser(User user) {
        try {
            Map r = this.cloudinary.uploader().upload(user.getImg().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            //Lấy api từ cloudinary về
            String img = (String) r.get("secure_url");
            user.setAvatar(img);
            return userRepository.addUser(user);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean addUserShipper(User user) {
        try {
            Map r = this.cloudinary.uploader().upload(user.getImg().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            //Lấy api từ cloudinary về
            String img = (String) r.get("secure_url");
            user.setAvatar(img);
            return userRepository.addUserShipper(user);
        } catch (IOException ex) {
            System.err.println("BUG: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.userRepository.getUsers(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy user trong hệ thống");
        }
        User u = users.get(0); // Lấy username ra
        System.out.println(u.getRole().name());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public List<User> getUsers(String username
    ) {
        return this.userRepository.getUsers(username);
    }

    @Override
    public User getUsersAuth() {
        return this.userRepository.getUsersAuth();
    }

    @Override
    public void xacnhantaixe(int i
    ) {
        this.userRepository.xacnhantaixe(i);
    }

    @Override
    public User getUserById(int i
    ) {
        return this.userRepository.getUserById(i);
    }

    @Override
    public boolean updateUser(User user) {
        user.setJoinDate(new Date());
        user.setActive(true);
        user.setRole(User.Role.ROLE_SHIPPER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getImg().isEmpty() == true) {
            return this.userRepository.updateUser(user);
        } else {
            try {
                Map r = this.cloudinary.uploader().upload(user.getImg().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar((String) r.get("secure_url"));
                return this.userRepository.updateUser(user);
            } catch (IOException ex) {
                System.out.println("Update User" + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(int i) {
        return this.userRepository.deleteUser(i);
    }

    @Override
    public Customer getCusById(int i) {
        return this.userRepository.getCusById(i);
    }

    @Override
    public List<User> getListEmailOfShipperWinner(int bookingId) {

    }

    @Override
    public List<User> getListEmailOfShipperLoser(int i) {

    }
}
