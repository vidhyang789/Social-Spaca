package com.example.blog_app.blog_app_controllers;


import com.example.blog_app.blog_app_exception.ApiException;
import com.example.blog_app.blog_app_payloads.JwtAuthRequest;
import com.example.blog_app.blog_app_payloads.JwtAuthResponse;
import com.example.blog_app.blog_app_payloads.userdto;
import com.example.blog_app.blog_app_services.userservice;
import com.example.blog_app.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userservice Userservice;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
            ){
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());


        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch(BadCredentialsException e){
            System.out.println("Invalid Details... ");
            throw new ApiException("Invalid username or password !! ");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registeruser(@RequestBody userdto Userdto){
        userdto registereduser = Userservice.registeruser(Userdto);
        return new ResponseEntity<>(registereduser,HttpStatus.CREATED);
    }

}
