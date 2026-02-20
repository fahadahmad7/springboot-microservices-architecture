package by.rom.apigateway.controller;

import by.rom.apigateway.security.JwtUtil;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        // TODO: Validate username/password properly (DB check later)

        String token = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of(
                "token", token
        ));
    }
}
