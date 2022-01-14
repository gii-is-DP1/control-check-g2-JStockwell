package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {
    @Autowired
    private FeedingRepository feedingRepository;

    public List<Feeding> getAll(){
        return feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return feedingRepository.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepository.findFeedingTypeByName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws DataAccessException, UnfeasibleFeedingException {
        PetType petType = p.getPet().getType();
        FeedingType feedType = p.getFeedingType();
        if(!petType.equals(feedType.getPetType())) {
            throw new UnfeasibleFeedingException();
        } else {
            feedingRepository.save(p);
            return p;
        }
    }

    
}
