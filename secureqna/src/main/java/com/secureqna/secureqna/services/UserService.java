package com.secureqna.secureqna.services;


import com.secureqna.secureqna.exceptions.userExceptions.InvalidUseForSecureQnAFunctions;
import com.secureqna.secureqna.exceptions.userExceptions.NoUsers;
import com.secureqna.secureqna.exceptions.userExceptions.UserNotFound;
import com.secureqna.secureqna.exceptions.userExceptions.UserOnRegisterAlreadyIn;
import com.secureqna.secureqna.objects.UserSqna;
import com.secureqna.secureqna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Collection<UserSqna> GetAllUsers()throws NoUsers {
        Collection<UserSqna>users= repository.findAll();
        if(users.isEmpty()){
            throw new NoUsers();
        }else {
            UserSqna admin=this.repository.findByUsername("admin").orElse(null);
            users.remove(admin);
            return users;
        }
    }

    public Collection<UserSqna>GetAllClientsWithoutPass()throws NoUsers {
        Collection<UserSqna>users= repository.findAll();
        if(users.isEmpty()){
            throw new NoUsers();
        }else {
            for(UserSqna e:users){
                UserSqna.returnWithoutPass(e);
            }
            UserSqna admin=this.repository.findByUsername("admin").orElse(null);
            users.remove(admin);
            return users;
        }
    }


    public void add(UserSqna user)throws UserOnRegisterAlreadyIn {
        UserSqna userPossible=this.repository.findByUsername(user.getUsername()).orElse(null);
        if(userPossible!=null){
            throw new UserOnRegisterAlreadyIn();
        }else{
            if(UserSqna.check(user)){
                user.getRoles().add("USERSQNA");
                String passToEncode=user.getPass();
                user.setPass(passwordEncoder.encode(passToEncode));
                this.repository.save(user);
            }else{
                throw new UserOnRegisterAlreadyIn();
            }
        }
    }

    public UserSqna getUser(String username)throws UserNotFound {
        UserSqna user=this.repository.findByUsername(username).orElse(null);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFound();
        }
    }

    public UserSqna rmUser(String username)throws UserNotFound{
        UserSqna userPossible=this.repository.findByUsername(username).orElse(null);
        if(userPossible!=null){
            this.repository.delete(userPossible);
            return userPossible;
        }else{
            throw new UserNotFound();
        }
    }

    public void rmAllUsers (){
        repository.deleteAll();
    }

    public UserSqna rmUserCheck(String username, String possibleUsername)throws UserNotFound{
        UserSqna clientPossible=this.repository.findByUsername(username).orElse(null);
        if(clientPossible!=null){
            if(clientPossible.getUsername().equals(possibleUsername)||possibleUsername.equals("admin")){
                this.repository.delete(clientPossible);
                return clientPossible;
            }else{
                throw new UserNotFound();
            }
        }else{
            throw new UserNotFound();
        }
    }

    public void modUser(String username, UserSqna user)throws UserNotFound{
        UserSqna clientPossible=this.repository.findByUsername(username).orElse(null);
        if(clientPossible!=null){
            if(UserSqna.check(user)){
                this.repository.save(user);
            }else{
                throw new UserNotFound();
            }
        }else{
            throw new UserNotFound();
        }
    }

    //For apply an admin/user form
    public void modFormUserAdmin(UserSqna userToChange,String lastPass)throws UserNotFound, InvalidUseForSecureQnAFunctions {
        UserSqna userPossible=this.repository.findByUsername(userToChange.getUsername()).orElse(null);
        if(userPossible!=null){
            if(UserSqna.check(userToChange)){
                if(lastPass==null || userToChange.getPass()==null || lastPass.isEmpty() || userToChange.getPass().isEmpty()){
                    userToChange.setPass(userPossible.getPass());
                }else{
                    if(passwordEncoder.matches(lastPass,userPossible.getPass())){
                        String encodedPass= passwordEncoder.encode(userToChange.getPass());
                        userToChange.setPass(encodedPass);
                    }else{
                        throw new InvalidUseForSecureQnAFunctions();
                    }
                }
                userToChange.setId(userPossible.getId());
                this.repository.save(userToChange);
            }else{
                throw new UserNotFound();
            }

        }else{
            throw new UserNotFound();
        }
    }

    //same that above
    public void modFormUser(String username, UserSqna user,String lastPass)throws UserNotFound, InvalidUseForSecureQnAFunctions {
        UserSqna clientPossible=this.repository.findByUsername(username).orElse(null);
        if(clientPossible!=null){
            if(UserSqna.check(user)){
                if(lastPass==null || user.getPass()==null || lastPass.length()==0 ||user.getPass().length()==0){
                    user.setPass(clientPossible.getPass());
                }else{
                    if(passwordEncoder.matches(lastPass,clientPossible.getPass())){
                        String encodedPass= passwordEncoder.encode(user.getPass());
                        user.setPass(encodedPass);
                    }else{
                        throw new InvalidUseForSecureQnAFunctions();
                    }
                }
                List<String> roles = new ArrayList<>();
                roles.add("USERSQNA");
                user.setId(clientPossible.getId());
                user.setUsername(username);
                user.setRoles(roles);
                this.repository.save(user);
            }else{
                throw new UserNotFound();
            }

        }else{
            throw new UserNotFound();
        }
    }



    public boolean exists(String username){
        return repository.existsByUsername(username);
    }

    public int getNumberUsers (){
        return (int) repository.count();
    }



}
