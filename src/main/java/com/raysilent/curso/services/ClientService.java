package com.raysilent.curso.services;

import com.raysilent.curso.domain.Address;
import com.raysilent.curso.domain.City;
import com.raysilent.curso.domain.Client;
import com.raysilent.curso.domain.dto.ClientDTO;
import com.raysilent.curso.domain.dto.NewClientDTO;
import com.raysilent.curso.domain.enums.ClientType;
import com.raysilent.curso.domain.enums.Profile;
import com.raysilent.curso.repositories.AddressRepository;
import com.raysilent.curso.repositories.ClientRepository;
import com.raysilent.curso.security.UserSS;
import com.raysilent.curso.services.exception.AuthorizationException;
import com.raysilent.curso.services.exception.DataIntegrityException;
import com.raysilent.curso.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;
    @Autowired
    private AddressRepository addRepo;
    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private S3Service s3Service;

    public Client find(Integer id) {
        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Access denied.");
        }

        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
    }

    public Client findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user==null || user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Access denied.");
        }

        Client obj = repo.findByEmail(email);
        if (obj==null) {
            throw new ObjectNotFoundException("Object not found. Id: " + user.getId() + ", type: " + Client.class.getName());
        }
        return obj;
    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        obj = repo.save(obj);
        addRepo.saveAll(obj.getAddresses());
        return obj;
    }

    public Client update(Client obj) {
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public Client fromDto(ClientDTO objDto) {
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
    }

    public Client fromDto(NewClientDTO objDto) {
        Client cli = new Client(null, objDto.getName(), objDto.getEmail(),
                objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));

        City city = new City(objDto.getCityId(), null, null);

        Address add = new Address(null, objDto.getLocation(), objDto.getNumber(),
                objDto.getComplement(), objDto.getNeighbr(), objDto.getCep(), cli, city);

        cli.getAddresses().add(add);
        cli.getPhones().add(objDto.getPhone1());

        if (objDto.getPhone2()!=null) {
            cli.getPhones().add(objDto.getPhone2());
        }
        if (objDto.getPhone3()!=null) {
            cli.getPhones().add(objDto.getPhone3());
        }
        return cli;
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can't delete a client with requests associated");
        }
    }

    public List<Client> findAll() {
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public URI uploadProfilePicture (MultipartFile multipartFile) {
        return s3Service.uploadFile(multipartFile);
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
