package com.techelevator.controller;

import com.techelevator.dao.CollectionDataDao;
import com.techelevator.model.Collection;
import com.techelevator.model.CreateCollectionDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CollectionController {

    private CollectionDataDao collectionDao;

    public CollectionController(CollectionDataDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    @RequestMapping(path = "collection", method = RequestMethod.POST)//make a user not found exception
    public boolean createTransfer(@RequestBody CreateCollectionDTO createCollectionDTO) {

        boolean collectionCreated = collectionDao.createCollection(createCollectionDTO.getUser_id(),createCollectionDTO.getCollection_name());

        return collectionCreated;

    }

}
