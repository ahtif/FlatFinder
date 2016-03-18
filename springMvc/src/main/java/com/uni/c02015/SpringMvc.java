package com.uni.c02015;

import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Type;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class SpringMvc implements ApplicationRunner {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private TypeRepository typeRepository;

  @Autowired
  private MessageRepository messageRepo;

  public static final int ROLE_ADMINISTRATOR_ID = 1;
  public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";
  public static final int ROLE_LANDLORD_ID = 2;
  public static final String ROLE_LANDLORD = "LANDLORD";
  public static final int ROLE_SEARCHER_ID = 3;
  public static final String ROLE_SEARCHER = "SEARCHER";

  /**
   * Psvm.
   * @param args String[]
   */
  public static void main(String[] args) {

    SpringApplication.run(SpringMvc.class, args);
  }

  /**
   * Run the application.
   * @param args ApplicationArguments
   * @throws Exception Throws on error
   */
  @Override
  public void run(ApplicationArguments args) throws Exception {

    
    //Set up Roles
    Role role = new Role();
    role.setId(ROLE_ADMINISTRATOR_ID);
    role.setRole(ROLE_ADMINISTRATOR);
    roleRepo.save(role);
    
    role = new Role();
    role.setId(ROLE_LANDLORD_ID);
    role.setRole(ROLE_LANDLORD);
    roleRepo.save(role);

    role = new Role();
    role.setId(ROLE_SEARCHER_ID);
    role.setRole(ROLE_SEARCHER);
    roleRepo.save(role);

    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

    // Set up admin user
    User user = new User();
    user.setLogin("admin");
    user.setPassword(pe.encode("password"));
    role = new Role();
    role.setId(ROLE_ADMINISTRATOR_ID);
    role.setRole(ROLE_ADMINISTRATOR);
    user.setRole(role);
    userRepo.save(user);

    // Set up property types
    Type type = new Type();
    type.setType("Flat");
    typeRepository.save(type);

    type = new Type();
    type.setType("House");
    typeRepository.save(type);

    // Set up users
    User user1 = new User();
    user1.setLogin("alice");
    user1.setPassword(pe.encode("searcher"));
    role = new Role();
    role.setId(ROLE_SEARCHER_ID);
    role.setRole(ROLE_SEARCHER);
    user1.setRole(role);
    userRepo.save(user1);
    
    Message message = new Message();
    message.setSenderName("admin");
    message.setMessage("hello");
    message.setSender(user);
    message.setReceiver(user);
    message.setSubject("test");
    message.setMessageDate(new Date());
    message.setIsRead(false);
    message.setParent(null);
    message.setChildren(null);
    messageRepo.save(message);
    
    Message message1 = new Message();
    message1.setSenderName("alice");
    message1.setMessage("hello1");
    message1.setSender(user1);
    message1.setReceiver(user);
    message1.setSubject("test1");
    message1.setMessageDate(new Date());
    message1.setIsRead(false);
    message1.setParent(message);
    message1.setChildren(null);
    messageRepo.save(message1);
  }
}