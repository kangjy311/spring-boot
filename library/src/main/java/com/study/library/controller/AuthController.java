package com.study.library.controller;

import com.study.library.aop.annotation.ParamsPrintAspect;
import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.OAuth2MergeReqDto;
import com.study.library.dto.OAuth2SignupReqDto;
import com.study.library.dto.SigninReqDto;
import com.study.library.dto.SignupReqDto;
import com.study.library.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) {
        // SignupReqDto 에 어노테이션 달고 @Valid 붙여줘야 됨
        //@Valid, BindingResult (같이 씀)


        authService.signup(signupReqDto);

        return ResponseEntity.created(null).body(true);
    }

    @ValidAspect
    @ParamsPrintAspect
    @PostMapping("/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupReqDto oauth2SignupReqDto, BindingResult bindingResult) {
        // SignupReqDto 에 어노테이션 달고 @Valid 붙여줘야 됨
        //@Valid, BindingResult (같이 씀)

        authService.oAuth2Signup(oauth2SignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(authService.signin(signinReqDto));
    }

    @PostMapping("/oauth2/merge")
    public ResponseEntity<?> oAuth2Merge(@RequestBody OAuth2MergeReqDto oAuth2MergeReqDto) {
        authService.oAuth2Merge(oAuth2MergeReqDto);
        return ResponseEntity.ok(true);
    }

}
