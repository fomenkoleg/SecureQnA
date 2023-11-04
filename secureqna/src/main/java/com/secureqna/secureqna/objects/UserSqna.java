package com.secureqna.secureqna.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSqna {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> roles = new ArrayList<>();

    private String username;

    private String pass;

    private String email;


    public static boolean check(UserSqna user){
        if (user.fullName.length()>81){
            return false;
        }else if(user.email.length()>30){
            return false;
        }else if(user.username!=null && user.username.length()>20){
            return false;
        }else if(user.pass.length()>20) {
            return false;
        }else{
            return true;
        }
    }

    public static void returnWithoutPass(UserSqna user){
        List<String>roles=new ArrayList<>();roles.add("Private");
        user.setPass("Private");
        user.setEmail("Private");
        user.setRoles(roles);
    }




}
