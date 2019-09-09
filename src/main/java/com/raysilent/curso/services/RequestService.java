package com.raysilent.curso.services;

import com.raysilent.curso.domain.BilletPayment;
import com.raysilent.curso.domain.Client;
import com.raysilent.curso.domain.Request;
import com.raysilent.curso.domain.RequestItem;
import com.raysilent.curso.domain.enums.PaymentStatus;
import com.raysilent.curso.repositories.PaymentRepository;
import com.raysilent.curso.repositories.RequestItemRepository;
import com.raysilent.curso.repositories.RequestRepository;
import com.raysilent.curso.security.UserSS;
import com.raysilent.curso.services.exception.AuthorizationException;
import com.raysilent.curso.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repo;
    @Autowired
    private BilletService billetService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RequestItemRepository requestItemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService cliService;
    @Autowired
    private EmailService emailService;

    public Request find(Integer id) {
        Optional<Request> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Request.class.getName()));
    }

    public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user==null) {
            throw new AuthorizationException("Access denied.");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        Client client = cliService.find(user.getId());
        return repo.findByClient(client, pageRequest);
    }

    public Request insert(Request obj) {
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setClient(cliService.find(obj.getClient().getId()));
        obj.getPayment().setStatus(PaymentStatus.PENDETE);
        obj.getPayment().setRequest(obj);
        if (obj.getPayment() instanceof BilletPayment) {
            BilletPayment pagto = (BilletPayment) obj.getPayment();
            billetService.fillBilletPayment(pagto, obj.getInstant());
        }
        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());
        for (RequestItem ip : obj.getItems()) {
            ip.setDiscount(0.0);
            ip.setProduct(productService.find(ip.getProduct().getId()));
            ip.setPrice(ip.getProduct().getPrice());
            ip.setRequest(obj);
        }
        requestItemRepository.saveAll(obj.getItems());
        emailService.sendRequestConfirmationHtmlEmail(obj);
        return obj;
    }
}
