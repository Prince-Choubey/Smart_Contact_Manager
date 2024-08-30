// package com.scm.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.scm.entities.User;
// import com.scm.helpers.Message;
// import com.scm.helpers.MessageType;
// import com.scm.repositories.UserRepo;

// import jakarta.servlet.http.HttpSession;

// @Controller
// @RequestMapping("/auth")
// public class AuthController {

//     // verify email

//     @Autowired
//     public UserRepo userRepo;


//     @GetMapping("/verify-email")
//     public String verifyEmail(
//         @RequestParam("token") String token,
//         HttpSession session
//     ){

//         User user = userRepo.findByEmailToken(token).orElse(null);

//         if(user != null){

//             if(user.getEmailToken().equals(token)){
//                 user.setEmailVerified(true);
//                 user.setEnabled(true);
//                 userRepo.save(user);
//                 session.setAttribute("message", Message.builder().type(MessageType.green).content("Your email is verified, Now you can login").build());

//                 return "success_page";
//             }
//             session.setAttribute("message", Message.builder().type(MessageType.red).content("Email is not verified !! Token is not associated with user").build());

//             return "error_page";
//         }


//         session.setAttribute("message", Message.builder().type(MessageType.red).content("Email is not verified !! Token is not associated with user").build());
//         return "error_page";

       
//     }
    

// }
