package com.lug.service;

import com.lug.model.Admin;
import com.lug.model.User;
import com.lug.repository.AdminRepository;
import com.lug.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zzs on 2016/9/3.
 */
@Service
public class AdminService {

    private AdminRepository adminRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository){
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    //检验用户是否存在数据库中
    @Transactional
    public boolean checkUserExist(String username){
        User user = userRepository.findByUsername(username);
        if (user == null)
            return false;
        else
            return true;
    }

    @Transactional
    public void saveUser(User user) throws Exception{
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    @Transactional
    public void updateUserPwd(Long id, String password){
        userRepository.updateUserPwd(id, password);
    }


    @Transactional
    public List<User> getUserList(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Transactional
    public boolean loginValidate(HttpSession session, String username, String password){
        Admin admin = adminRepository.findByUsernameAndPwd(username, password);
        if (admin == null)
            return false;
        else{
            session.setAttribute("admin", admin);
            session.setAttribute("adminId", admin.getId());
            return true;
        }
    }

}
