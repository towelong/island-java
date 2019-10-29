package com.example.demo.service.Impl;

import com.amdelamar.jhash.exception.InvalidHashException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.exception.Forbidden;
import com.example.demo.exception.NotFound;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Encryption;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


/**
 * @Author WeLong
 * @create 2019/10/25 13:32
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void createUser(User user){
        User exist = this.findByNickname(user.getNickname());
        if(exist != null){
            throw new Forbidden("用户已存在");
        }
        user.setNickname(user.getNickname());
        user.setEmail(user.getEmail());
        user.setPasswordEncrypt(user.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        userMapper.insert(user);
    }
    @Override
    public long createMiniUser(String openid){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        User exit = userMapper.selectOne(wrapper);
        User user = new User();
        if(exit != null){
            throw new Forbidden("小程序用户已存在");
        }
        user.setOpenid(openid);
        user.setCreatedAt(LocalDateTime.now());
        userMapper.insert(user);
        long uid = user.getId();
        return uid;
    }

    @Override
    public User findByNickname(String nickname) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("nickname", nickname);
        User exist = userMapper.selectOne(wrapper);
        return exist;
    }

   @Override
   public String verifyUser(User user) throws InvalidHashException {
        User exist= this.findByNickname(user.getNickname());
        if(exist == null){
            throw new NotFound("用户不存在");
        }
        String password = this.findByNickname(user.getNickname()).getPassword();
        Long uid = this.findByNickname(user.getNickname()).getId();
        boolean rightPsw=Encryption.encrypt(user.getPassword(),password);
        if(rightPsw) {
            TokenUtils tokenUtils = new TokenUtils();
            String res = tokenUtils.CreateToken(uid,16);
            return res;
        }
        throw new Forbidden("密码错误");
   }
}
