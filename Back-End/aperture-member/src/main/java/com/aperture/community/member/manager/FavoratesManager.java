package com.aperture.community.member.manager;

import com.aperture.community.member.dao.CollectionDao;
import com.aperture.community.member.dao.CollectionMapper;
import com.aperture.community.member.dao.FavoratesDao;
import com.aperture.community.member.dao.FavoratesMapper;
import com.aperture.community.member.service.CollectionService;
import com.aperture.community.member.service.FavoratesService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 21:41
 * @Description:
 */
@Getter
@Service
public class FavoratesManager {

    private CollectionService collectionService;
    private FavoratesService favoratesService;
    private FavoratesMapper favoratesMapper;
    private CollectionManager collectionManager;
    private FavoratesDao favoratesDao;

    @Autowired
    public FavoratesManager(FavoratesMapper favoratesMapper,
                            CollectionManager collectionManager,
                            CollectionService collectionService,
                            FavoratesDao favoratesDao,
                            FavoratesService favoratesService) {
        this.collectionManager = collectionManager;
        this.favoratesDao = favoratesDao;
        this.favoratesMapper = favoratesMapper;
        this.collectionService = collectionService;
        this.favoratesService = favoratesService;
    }

}