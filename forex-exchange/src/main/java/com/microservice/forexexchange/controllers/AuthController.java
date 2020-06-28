package com.microservice.forexexchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.forexexchange.security.JwtToken;

import java.util.Optional;

import com.microservice.forexexchange.models.Admin;
import com.microservice.forexexchange.payloads.JwtRequest;
import com.microservice.forexexchange.payloads.JwtResponse;
import com.microservice.forexexchange.repositories.AdminRepository;
import com.microservice.forexexchange.response.ResponseMessage;
// import com.microservice.forexexchange.response.ResponseMessage;
import com.microservice.forexexchange.services.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AdminRepository adminRepository;

    // login new user
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final JwtRequest authenticationRequest)
            throws Exception {
            try{
                authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

                final UserDetails userDetails = jwtUserDetailsService

                        .loadUserByUsername(authenticationRequest.getUsername());

                Optional<Admin> userData = adminRepository.findByUsername(authenticationRequest.getUsername());

                final String token = jwtToken.generateToken(userDetails);

                return ResponseEntity.ok(new JwtResponse(token, userData.get().getFullname(), userData.get().getUsername(), userData.get().getEmail(), userData.get().getPhone()));
            }catch(Exception ex){
                return new ResponseEntity<>(new ResponseMessage(true, ex.getMessage()), HttpStatus.OK);
            }
    }

    private void authenticate(final String username, final String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (final DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (final BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        }

    }

}

