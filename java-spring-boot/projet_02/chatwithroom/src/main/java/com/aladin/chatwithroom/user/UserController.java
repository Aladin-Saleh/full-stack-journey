package com.aladin.chatwithroom.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    
    
    private final UserService userService;



    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload User user) {
        this.userService.saveUser(user);
        return user;
    }


    // Cette méthode permet de déconnecter un utilisateur
    // Elle est appelée lorsqu'un utilisateur se déconnecte
    // Elle envoie un message à tous les utilisateurs connectés pour les informer de la déconnexion de l'utilisateur (dans le cas présent elle envoie au recipientId)
    // /user.disconnectUser est l'endpoint pour se déconnecter, il est appelé lorsqu'un utilisateur se déconnecte (lorsqu'il ferme la page par exemple), cela permet de mettre à jour la liste des utilisateurs connectés
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user) {
        this.userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(this.userService.findConnectedUsers());
    }

}
