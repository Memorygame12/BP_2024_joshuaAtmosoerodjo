package service;

import entity.Lid;
import repository.LidDAO;
import java.util.List;

public class LidService {
    private LidDAO lidDAO;

    public LidService(LidDAO lidDAO) {
        this.lidDAO = lidDAO;
    }

    public void addLid(Lid lid) {
        lidDAO.saveLid(lid);
    }

    public Lid getLid(int id) {
        return lidDAO.getLid(id);
    }

    public void updateLid(Lid lid) {
        lidDAO.updateLid(lid);
    }

    public void deleteLid(int id) {
        lidDAO.deleteLid(id);
    }

    public List<Lid> getAllLeden() {
        return lidDAO.getAllLeden();
    }
}
