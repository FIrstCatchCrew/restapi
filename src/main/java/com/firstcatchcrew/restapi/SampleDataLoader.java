//package com.firstcatchcrew.restapi;
//
//import com.firstcatchcrew.restapi.fishCatch.*;
//import com.firstcatchcrew.restapi.fishCatch.embedded.GeoLocation;
//import com.firstcatchcrew.restapi.fishCatch.embedded.PickupInfo;
//import com.firstcatchcrew.restapi.fisherProfile.*;
//import com.firstcatchcrew.restapi.landing.*;
//import com.firstcatchcrew.restapi.order.*;
//import com.firstcatchcrew.restapi.orderItem.*;
//import com.firstcatchcrew.restapi.person.*;
//import com.firstcatchcrew.restapi.species.*;
//import com.firstcatchcrew.restapi.userRole.*;
//
//import jakarta.transaction.Transactional;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.StreamSupport;
//
//@Component
//public class SampleDataLoader implements CommandLineRunner {
//
//    private final UserRoleRepository roleRepo;
//    private final PersonRepository personRepo;
//    private final FisherProfileRepository fisherRepo;
//    private final LandingRepository landingRepo;
//    private final SpeciesRepository speciesRepo;
//    private final CatchRepository catchRepo;
//    private final OrderRepository orderRepo;
//    private final OrderItemRepository orderItemRepo;
//
//    public SampleDataLoader(UserRoleRepository roleRepo, PersonRepository personRepo, FisherProfileRepository fisherRepo,
//                            LandingRepository landingRepo, SpeciesRepository speciesRepo,
//                            CatchRepository catchRepo, OrderRepository orderRepo,
//                            OrderItemRepository orderItemRepo) {
//        this.roleRepo = roleRepo;
//        this.personRepo = personRepo;
//        this.fisherRepo = fisherRepo;
//        this.landingRepo = landingRepo;
//        this.speciesRepo = speciesRepo;
//        this.catchRepo = catchRepo;
//        this.orderRepo = orderRepo;
//        this.orderItemRepo = orderItemRepo;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) {
//        loadUserRoles();
//        List<Person> people = loadPeople();
//        List<Landing> landings = loadLandings();
//        List<Species> species = loadSpecies();
//        List<FisherProfile> fishers = loadFisherProfiles(people, landings);
//        List<Catch> catches = loadCatches(fishers, species);
//        loadOrders(people, catches);
//    }
//
//    private void loadUserRoles() {
//        if (roleRepo.count() == 0) {
//            roleRepo.save(new UserRole(UserRoleType.ADMIN, "Admin", "Full system access"));
//            roleRepo.save(new UserRole(UserRoleType.FISHER, "Fisher", "Can list catches"));
//            roleRepo.save(new UserRole(UserRoleType.CUSTOMER, "Customer", "Can browse and buy"));
//        }
//    }
//
//    private List<Person> loadPeople() {
//        List<UserRole> roles = (List<UserRole>) roleRepo.findAll();
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 1; i <= 20; i++) {
//            Person p = new Person();
//            p.setUsername("user" + i);
//            p.setEmail("user" + i + "@sea.ca");
//            p.setPassword("password" + i);
//            if (i <= 12) p.setRole(findRole(roles, UserRoleType.CUSTOMER));
//            else if (i <= 18) p.setRole(findRole(roles, UserRoleType.FISHER));
//            else p.setRole(findRole(roles, UserRoleType.ADMIN));
//            people.add(p);
//        }
//
//        return StreamSupport
//                .stream(personRepo.saveAll(people).spliterator(), false)
//                .toList();
//    }
//
//
//    private List<Landing> loadLandings() {
//        String[] names = {"St. John's", "Corner Brook", "Bonavista", "Gander", "Port aux Basques", "Twillingate", "Burgeo", "L'Anse-au-Loup"};
//        List<Landing> landings = new ArrayList<>();
//
//        for (String name : names) {
//            Landing l = new Landing();
//            l.setName(name); // Make sure Landing.java has this
//            landings.add(l);
//        }
//
//        return StreamSupport
//                .stream(landingRepo.saveAll(landings).spliterator(), false)
//                .toList();
//    }
//
//
//    private List<Species> loadSpecies() {
//        String[] speciesNames = {"Cod", "Halibut", "Lobster", "Snow Crab", "Herring", "Mackerel", "Turbot", "Capelin", "Salmon", "Scallop"};
//
//        List<Species> speciesList = new ArrayList<>();
//        for (String name : speciesNames) {
//            Species s = new Species();
//            s.setSpeciesName(name);
//            s.setDescription("Sample info for " + name);
//            s.setImageUrl("https://example.com/images/" + name.toLowerCase() + ".jpg");
//            s.setInfoLink("https://en.wikipedia.org/wiki/" + name.replace(" ", "_"));
//            speciesList.add(s);
//        }
//
//        return StreamSupport
//                .stream(speciesRepo.saveAll(speciesList).spliterator(), false)
//                .toList();
//    }
//
//
//    private List<FisherProfile> loadFisherProfiles(List<Person> people, List<Landing> landings) {
//        List<FisherProfile> fishers = new ArrayList<>();
//
//        for (int i = 12; i < 18; i++) {
//            FisherProfile fisher = new FisherProfile();
//            fisher.setPerson(people.get(i));
//            fisher.setFishingLicenseNumber("LIC" + (i + 1));
//            fisher.setDefaultLanding(landings.get(i % landings.size()));
//            fishers.add(fisher);
//        }
//
//        return StreamSupport
//                .stream(fisherRepo.saveAll(fishers).spliterator(), false)
//                .toList();
//    }
//
//
//    private List<Catch> loadCatches(List<FisherProfile> fishers, List<Species> species) {
//        List<Catch> catches = new ArrayList<>();
//
//        for (int i = 0; i < 20; i++) {
//            Catch c = new Catch();
//            c.setFisher(fishers.get(i % fishers.size()));
//            c.setSpecies(species.get(i % species.size()));
//            c.setQuantityInKg(BigDecimal.valueOf(10 + i));
//            c.setPrice(BigDecimal.valueOf(12 + i));
//            c.setCatchDate(LocalDateTime.now().minusDays(i));
//            c.setGeoLocation(new GeoLocation(48.0 + (i % 5), -53.0 - (i % 5)));
//            c.setPickupInfo(new PickupInfo("Dock " + i, "Somewhere NL", LocalDateTime.now().plusDays(3)));
//            c.updateAvailabilityStatus();
//            catches.add(c);
//        }
//
//        return StreamSupport
//                .stream(catchRepo.saveAll(catches).spliterator(), false)
//                .toList();
//    }
//
//
//    private List<Order> loadOrders(List<Person> people, List<Catch> catches) {
//        List<Order> orders = new ArrayList<>();
//
//        for (int i = 0; i < 20; i++) {
//            Order order = new Order();
//            order.setCustomer(people.get(i % 12)); // First 12 are customers
//            order.setOrderDateTime(LocalDateTime.now().minusDays(i));
//            order.setOrderStatus(i < 5); // First 5 are active, next 5 shipped, etc.
//
//            OrderItem orderItem = new OrderItem();
//
//            Long catchId = 5L; // example catch id
//            Catch fishCatch = catchRepo.findById(catchId)
//                    .orElseThrow(() -> new RuntimeException("Catch not found"));
//
//            orderItem.setFishCatch(fishCatch); //
//            orderItem.setQuantity(2L);
//            orderItem.setOrder(order); // back-reference
//
//            order.setOrderItems(List.of(orderItem));
//
//            orderRepo.save(order); // cascade will save the OrderItem too
//        }
//
//        return StreamSupport
//                .stream(orderRepo.saveAll(orders).spliterator(), false)
//                .toList();
//    }
//
//
//    private UserRole findRole(List<UserRole> roles, UserRoleType type) {
//        return roles.stream().filter(r -> r.getType() == type).findFirst().orElse(null);
//    }
//}
