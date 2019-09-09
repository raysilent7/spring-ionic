package com.raysilent.curso.services;

import com.raysilent.curso.domain.*;
import com.raysilent.curso.domain.enums.ClientType;
import com.raysilent.curso.domain.enums.PaymentStatus;
import com.raysilent.curso.domain.enums.Profile;
import com.raysilent.curso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RequestItemRepository requestItemRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateDatabase() throws ParseException {
        Category cat1 = new Category(null, "Informatica");
        Category cat2 = new Category(null, "Escritorio");
        Category cat3 = new Category(null, "Cama, mesa e banho");
        Category cat4 = new Category(null, "Eletronicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoracao");
        Category cat7 = new Category(null, "Higiene");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de Escritorio", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Endredom", 200.00);
        Product p7 = new Product(null, "Smart TV True Color", 1200.00);
        Product p8 = new Product(null, "Rocadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Mesa de centro", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p2, p4));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        State st1 = new State(null, "Minas Gerais");
        State st2 = new State(null, "Rio de Janeiro");

        City c1 = new City(null, "Uberlandia", st1);
        City c2 = new City(null, "Rio de Janeiro", st2);
        City c3 = new City(null, "Saquarema", st2);

        st1.getCities().addAll(Arrays.asList(c1));
        st2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Rayan Garcia", "rayan.garcia@efabrika.com.br",
                "13331036706", ClientType.PESSOAFISICA, pe.encode("123456"));
        Client cli2 = new Client(null, "Livia Flores", "ray.silent7@gmail.com",
                "48758457070", ClientType.PESSOAFISICA, pe.encode("221133"));
        cli1.getPhones().addAll(Arrays.asList("22223333", "33331111"));
        cli1.getPhones().addAll(Arrays.asList("55554444", "99997777"));
        cli2.addProfiles(Profile.ADMIN);

        Address add1 = new Address(null, "Rua Vaz de Caminha", "190", "Casa 1", "Cachambi","20780-330", cli1, c2);
        Address add2 = new Address(null, "Rua 96", "486", "Casa 3", "Jacone","11111-222", cli1, c3);
        Address add3 = new Address(null, "Rua Getulio", "486", "Casa 7", "Cachambi","22222-777", cli2, c2);

        cli1.getAddresses().addAll(Arrays.asList(add1, add2));
        cli2.getAddresses().addAll(Arrays.asList(add3));

        clientRepository.saveAll(Arrays.asList(cli1, cli2));
        addressRepository.saveAll(Arrays.asList(add1, add2, add3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Request req1 = new Request(null, sdf.parse("30/09/2018 10:32"), cli1, add1);
        Request req2 = new Request(null, sdf.parse("10/08/2018 16:40"), cli1, add2);

        Payment  pmnt1 = new CardPayment(null, PaymentStatus.QUITADO, req1, 6);
        Payment  pmnt2 = new BilletPayment(null, PaymentStatus.PENDETE, req2, sdf.parse("20/10/2017 00:00"), null);
        req1.setPayment(pmnt1);
        req2.setPayment(pmnt2);
        cli1.getRequests().addAll(Arrays.asList(req1, req2));

        requestRepository.saveAll(Arrays.asList(req1, req2));
        paymentRepository.saveAll(Arrays.asList(pmnt1, pmnt2));

        RequestItem items1 = new RequestItem(req1, p1, 0.0, 1, 2000.00);
        RequestItem items2 = new RequestItem(req1, p3, 0.0, 2, 80.00);
        RequestItem items3 = new RequestItem(req2, p2, 100.0, 1, 800.00);

        req1.getItems().addAll(Arrays.asList(items1, items2));
        req2.getItems().addAll(Arrays.asList(items3));

        p1.getItems().addAll(Arrays.asList(items1));
        p2.getItems().addAll(Arrays.asList(items3));
        p3.getItems().addAll(Arrays.asList(items2));

        requestItemRepository.saveAll(Arrays.asList(items1, items2, items3));
    }
}
