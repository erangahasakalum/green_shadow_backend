//package lk.ijse.gdse67.greenShadow.controller;
//
//import lk.ijse.gdse67.greenShadow.service.AuthenticationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/v1/auth")
//@RequiredArgsConstructor
//public class UserController {
//    private final AuthenticationService authenticationService;
//
//    @PostMapping("/signUp")
//    public ResponseEntity<JwtAuthResponse> signup(@RequestBody SignUp signup) {
//        System.out.println("AMODDDDDDDDDDDDDDDDDD");
//        return ResponseEntity.ok(authenticationService.signUp(signup));
//    }
//
//    @PostMapping("/signIn")
//    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SingIn signIn) {
//        System.out.println("SIGN IN OBJ  :" + signIn);
//        return ResponseEntity.ok(authenticationService.signIn(signIn));
//    }
//
//    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody Token token) {
//        System.out.println(token.getToken());
//        return ResponseEntity.ok(authenticationService.refreshToken(token.getToken()));
//    }
//
//
//    @PostMapping(value = "/sendCode")
//    public ResponseEntity<Void> sendCode(@RequestBody()UserWithKey userWithKey){
//        if (userService.sendCodeToChangePassword(userWithKey)) {
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping(value = "/changePassword")
//    public ResponseEntity<Void> changePassword(@RequestBody() ChangePasswordDTO changePasswordDTO){
//        try {
//            authenticationService.changePassword(changePasswordDTO);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
