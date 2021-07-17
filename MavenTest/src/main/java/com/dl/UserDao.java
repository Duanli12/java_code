package com.dl;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author:duanli
 * @createDate:2020/9/14 10:11
 */
public class UserDao {
   List<User> userList;

   public UserDao(){
       userList = new ArrayList<>();
   }

   public List<User> getAll(){
       return new ArrayList<>(userList);
   }

   public boolean add(User user){
       userList.add(user);
       return true;
   }
   public boolean delete(User user){
       for (User itme:userList){
           if (itme.getId()==user.getId()){

           }
       }
       userList.remove(user);
       return true;
   }
   public boolean update(User user){
       for (User item:userList){
           if (user.getId()==item.getId()){
               item.setName(user.getName());
           }
       }
       return true;
   }

}
