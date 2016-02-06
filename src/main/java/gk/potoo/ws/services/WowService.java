package gk.potoo.ws.services;

import gk.potoo.documents.Wow;
import gk.potoo.repositories.WowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WowService extends CrudService<Wow> {

    @Autowired
    public WowService(WowRepository repository) {
        super(repository);
    }

    @Override
    protected void copyUpdatedValues(Wow found, Wow data) {
        found.setText(data.getText());
    }

}
