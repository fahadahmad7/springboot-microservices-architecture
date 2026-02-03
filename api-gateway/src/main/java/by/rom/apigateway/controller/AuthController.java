package by.rom.apigateway.controller;

import by.rom.apigateway.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");

        // (Later: validate username/password)
        String token = JwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of(
                "token", token
        ));
    }
}

