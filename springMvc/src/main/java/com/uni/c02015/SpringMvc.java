package com.uni.c02015;

import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Type;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringMvc implements ApplicationRunner {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private TypeRepository typeRepository;

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
  }
}