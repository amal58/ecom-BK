package tn.formalab.ecommerce.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.formalab.ecommerce.models.Category;
import tn.formalab.ecommerce.models.Product;
import tn.formalab.ecommerce.models.User;
import tn.formalab.ecommerce.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin//pour enlever l'erreur de sécurite
@RestController  //  protocole
@RequestMapping("users")
public class UserController {
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();//

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.password = bCryptPasswordEncoder.encode(user.password);
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @GetMapping(path = "all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return ResponseEntity.status(200).body(users);
    }




    /*
     * 1 - findByEmail ? User : null
     * 2 - comparing passwords
     * 3 - verification account state
     * 4 - generate token ( hashed string , m5abin fiha les infos mta3 el user kima role ,id , email ,... )
     * 5 - nab3thou e token lel front
     * */


    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {

        HashMap<String, Object> response = new HashMap<>();

        User userFromDB = userRepository.findByEmail(user.email);

        if (userFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = bCryptPasswordEncoder.matches(user.password, userFromDB.password);

            if (!compare) {
                response.put("message", "user not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {

                if (!userFromDB.accountState) {
                    response.put("message", "user not allowed !");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                } else {

                    String token = Jwts.builder()
                            //.claim("data", userFromDB)
                            .claim("id", userFromDB.id)
                            .claim("role", userFromDB.role)
                            .signWith(SignatureAlgorithm.HS256, "SECRET")
                            .compact();

                    response.put("token", token);

                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id) {
        this.userRepository.deleteById(id);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "user Deleted");
        return ResponseEntity.status(200).body(response);
    }



    @PatchMapping(path = "update")

    public ResponseEntity<User> updateCategory(@RequestBody User user) {
        User savedUser = this.userRepository.save( user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
