package internaltest.springsecuritylogin.auth;

import internaltest.springsecuritylogin.auth.dto.UserForm;
import internaltest.springsecuritylogin.auth.entities.User;
import internaltest.springsecuritylogin.common.dto.ResponseDto;
import internaltest.springsecuritylogin.common.dto.ResponseWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@SpringSessionDataSource
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.service = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseWrapper register(@Valid @RequestBody UserForm userForm, BindingResult bindingResult,
                                    HttpServletResponse response) {

        ResponseWrapper responseWrapper = new ResponseWrapper();

        if (!service.isAddUserPermitted()) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            responseWrapper.setCode("EAUTH403-02");
            responseWrapper.setMessage("회원가입이 허가되지 않았습니다");
            responseWrapper.setContents(null);
            return responseWrapper;
        }

        if (bindingResult.hasErrors()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            responseWrapper.setCode("EAUTH400-01");
            responseWrapper.setMessage("잘못된 사용자 폼 형식입니다");
            for (FieldError fe : bindingResult.getFieldErrors()) {
                responseWrapper.appendResponseContent(new ResponseDto(fe.getField(), fe.getDefaultMessage()));
            }
            return responseWrapper;
        }

        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));

        User user = User.getUserByForm(userForm);
        switch (service.addUser(user)) {
            case AuthService.DUPLICATED:
                response.setStatus(HttpStatus.CONFLICT.value());
                responseWrapper.setCode("EAUTH409-01");
                responseWrapper.setMessage("이미 등록된 사용자입니다");
                responseWrapper.setContents(null);
                return responseWrapper;
            case AuthService.EMAIL_NOT_VERIFIED:
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                responseWrapper.setCode("EAUTH401-02");
                responseWrapper.setMessage("이메일이 인증되지 않았습니다");
                responseWrapper.setContents(null);
                return responseWrapper;
        }

        responseWrapper.setCode("OK");
        responseWrapper.setMessage("등록되었습니다");
        responseWrapper.setContents(null);
        return responseWrapper;
    }

    @GetMapping("/registration-permission")
    public ResponseWrapper getRegistrationPermission() {
        String permission = service.getAddUserPermission();

        ResponseWrapper responseWrapper = new ResponseWrapper("OK", "관리자 가입 허가 여부");
        responseWrapper.appendResponseContent(new ResponseDto("permitRegister", permission));

        return responseWrapper;
    }

    @PutMapping("/permit-register/{value}")
    public ResponseWrapper permitRegister(@Valid @PathVariable("value") String value, HttpServletResponse response) {
        ResponseWrapper responseWrapper = new ResponseWrapper();

        if (!service.setAddUserPermission(value)) {
            responseWrapper.setCode("UNCHANGED");
            responseWrapper.setMessage("이전 정보와 동일하거나 요청사항이 없습니다");
            responseWrapper.setContents(null);
            return responseWrapper;
        }

        responseWrapper.setCode("OK");
        responseWrapper.setMessage("등록 허가 여부");
        responseWrapper.appendResponseContent(new ResponseDto("permitRegister", value));
        return responseWrapper;
    }
}
