package com.aperture.community.member.manager;

import com.aperture.community.member.dao.CollectionDao;
import com.aperture.community.member.dao.CollectionMapper;
import com.aperture.community.member.dao.FavoratesDao;
import com.aperture.community.member.dao.FavoratesMapper;
import com.aperture.community.member.service.CollectionService;
import com.aperture.community.member.service.FavoratesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 21:41
 * @Description:
 */
@Getter
@Service
public class CollectionManager {

    private CollectionMapper collectionMapper;
    private FavoratesMapper favoratesMapper;
    private CollectionService collectionService;
    private FavoratesService favoratesService;

    @Autowired
    public CollectionManager(CollectionMapper collectionMapper,
                             FavoratesMapper favoratesMapper,
                             CollectionService collectionService,
                             FavoratesService favoratesService) {
        this.collectionMapper = collectionMapper;
        this.favoratesMapper = favoratesMapper;
        this.collectionService = collectionService;
        this.favoratesService = favoratesService;
    }

}