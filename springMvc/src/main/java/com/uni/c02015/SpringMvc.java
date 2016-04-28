package com.uni.c02015;

import com.uni.c02015.domain.property.Property;
import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Type;
import com.uni.c02015.persistence.repository.*;
import com.uni.c02015.persistence.repository.property.PropertyRepository;
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
  @Autowired
  private PropertyRepository propertyRepo;
  @Autowired
  private LandlordRepository landlordRepo;
  @Autowired
  private SearcherRepository searcherRepo;
  @Autowired
  private MessageRepository messageRepo;

  public static final int ROLE_ADMINISTRATOR_ID;
  public static final String ROLE_ADMINISTRATOR;
  public static final int ROLE_LANDLORD_ID;
  public static final String ROLE_LANDLORD;
  public static final int ROLE_SEARCHER_ID;
  public static final String ROLE_SEARCHER;

  static {

    ROLE_ADMINISTRATOR_ID = 1;
    ROLE_ADMINISTRATOR = "ADMINISTRATOR";
    ROLE_LANDLORD_ID = 2;
    ROLE_LANDLORD = "LANDLORD";
    ROLE_SEARCHER_ID = 3;
    ROLE_SEARCHER = "SEARCHER";
  }

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

    // Add Roles
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

    // Add property types
    Type type = new Type();
    type.setType("Flat");
    typeRepository.save(type);

    type = new Type();
    type.setType("House");
    typeRepository.save(type);

    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

    // Admin
    User user = new User();
    user.setLogin("admin");
    user.setConfirmed(true);
    user.setPassword(pe.encode("password"));
    user.setEmailAddress("admin@flatfinder.com");
    role = new Role();
    role.setId(ROLE_ADMINISTRATOR_ID);
    role.setRole(ROLE_ADMINISTRATOR);
    user.setRole(role);
    userRepo.save(user);

    // Searcher
    User user1 = new User();
    user1.setLogin("alice");
    user1.setConfirmed(true);
    user1.setPassword(pe.encode("searcher"));
    user1.setEmailAddress("alice@flatfinder.com");
    role = new Role();
    role.setId(ROLE_SEARCHER_ID);
    role.setRole(ROLE_SEARCHER);
    user1.setRole(role);
    userRepo.save(user1);
    
    Searcher alice = new Searcher(user1.getId());
    alice.setFirstName("alice");
    alice.setLastName("searcher");
    alice.setBuddyPref(true);
    searcherRepo.save(alice);

    // Searcher
    user1 = new User();
    user1.setLogin("tim");
    user1.setConfirmed(true);
    user1.setPassword(pe.encode("hello123"));
    user1.setEmailAddress("tim@flatfinder.com");
    role = new Role();
    role.setId(ROLE_SEARCHER_ID);
    role.setRole(ROLE_SEARCHER);
    user1.setRole(role);
    userRepo.save(user1);

    Searcher tim = new Searcher(user1.getId());
    tim.setFirstName("tim");
    tim.setLastName("buddy");
    tim.setBuddyPref(true);
    searcherRepo.save(tim);

    // Admin
    User user2 = new User();
    user2.setLogin("ahtif");
    user2.setConfirmed(true);
    user2.setPassword(pe.encode("admin"));
    user2.setEmailAddress("ahtif.a@hotmail.co.uk");
    role = new Role();
    role.setId(ROLE_ADMINISTRATOR_ID);
    role.setRole(ROLE_ADMINISTRATOR);
    user2.setRole(role);
    userRepo.save(user2);
    
    // Landlord
    User user3 = new User();
    user3.setLogin("larry");
    user3.setConfirmed(true);
    user3.setPassword(pe.encode("landlord"));
    user3.setEmailAddress("larry@landlord.com");
    role = new Role();
    role.setId(ROLE_LANDLORD_ID);
    role.setRole(ROLE_LANDLORD);
    user3.setRole(role);
    userRepo.save(user3);
    
    Landlord larry = new Landlord(user3.getId());
    larry.setFirstName("larry");
    larry.setLastName("landlord");
    landlordRepo.save(larry);

////    //set up default properties
//    Property property = new Property();
//    property.setNumber("5");
//    property.setStreet("University Road");
//    property.setCity("Leicester");
//    property.setPostcode("LE1 7RA");
//    property.setType(typeRepository.findById(1));
//    property.setRooms(2);
//    property.setLatitude(52.627717);
//    property.setLongitude(-1.121204);
//    property.setLandlord(larry);
//    propertyRepo.save(property);
//    
//    property = new Property();
//    property.setNumber("2");
//    property.setStreet("Manor Rd");
//    property.setCity("Leicester");
//    property.setPostcode("LE2 2LJ");
//    property.setType(typeRepository.findById(2));
//    property.setRooms(4);
//    property.setLatitude(52.609993);
//    property.setLongitude(-1.088549);
//    property.setLandlord(larry);
//    propertyRepo.save(property); 
    
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