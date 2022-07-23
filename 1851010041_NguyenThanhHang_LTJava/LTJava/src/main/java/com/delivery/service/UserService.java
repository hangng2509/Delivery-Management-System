/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service;

import com.delivery.pojo.Customer;
import com.delivery.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean checkUsername(String username);

    boolean addUser(User user);

    boolean addUserShipper(User user);

    List<User> getUsers(String username);

    User getUsersAuth();

    void xacnhantaixe(int shipperid);

    User getUserById(int id);

    boolean updateUser(User user);

    boolean deleteUser(int shipperId);

    Customer getCusById(int cusId);

    List<User> getListEmailOfShipperWinner(int booking);

    List<User> getListEmailOfShipperLoser(int booking);
}
