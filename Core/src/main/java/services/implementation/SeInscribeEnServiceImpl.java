package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ISeInscribeEnDAO;
import persistence.entities.SeInscribeEn;
import services.interfaces.ISeInscribeEnService;

@Service
public class SeInscribeEnServiceImpl implements ISeInscribeEnService {
    @Autowired
    private ISeInscribeEnDAO seInscribeEnDAO;


    @Override
    public void save(SeInscribeEn seInscribeEn) {
        seInscribeEnDAO.save(seInscribeEn);
    }
}
