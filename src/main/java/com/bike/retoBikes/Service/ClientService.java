package com.bike.retoBikes.Service;

import com.bike.retoBikes.Model.Client;
import com.bike.retoBikes.Repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public List<Client> getAll(){
        return clientRepo.getAll();
    }

    public Optional<Client> getClient(int clientId) {
        return clientRepo.getClient(clientId);
    }

    public Client save(Client client){
        if(client.getIdClient()==null){
            return clientRepo.save(client);
        }else{
            Optional<Client> e= clientRepo.getClient(client.getIdClient());
            if(e.isPresent()){
                return client;
            }else{
                return clientRepo.save(client);
            }
        }
    }
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e= clientRepo.getClient(client.getIdClient());
            if(e.isPresent()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                clientRepo.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }


    public boolean deleteClient (int id){
        boolean del = getClient(id).map(client -> {
            clientRepo.delete(client);
            return true;
        }).orElse(false);
        return del;
    }

}
