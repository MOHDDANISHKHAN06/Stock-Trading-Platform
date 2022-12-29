package com.service.stprest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.stprest.dao.LoginPojo;
import com.service.stprest.dao.SignUpPojo;
import com.service.stprest.dao.UserDao;
import com.service.stprest.dao.WalletDao;
import com.service.stprest.entities.User;
import com.service.stprest.entities.Wallet;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;
    
    @Autowired
	private WalletDao walletDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginPojo loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmailId(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpPojo signUpPojo){

        // add check for email exists in DB
        if(userDao.findById(signUpPojo.getEmailId()).isPresent()){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setFullName(signUpPojo.getName());
        user.setUserName(signUpPojo.getUserName());
        user.setEmailId(signUpPojo.getEmailId());
        user.setPassword(passwordEncoder.encode(signUpPojo.getPassword()));

        Wallet wallet = new Wallet();		
		wallet.setBuyingPower(0);
		wallet.setCashAvailable(0);
		wallet.setUser(user);
		walletDao.save(wallet);
		user.setWallet(wallet);
		
        userDao.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
