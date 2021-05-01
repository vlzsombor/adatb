package hu.szte.fenykepalbumok.service;

import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.repository.KepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Collections;
import java.util.List;

@Service
public class KepService {


    @Autowired
    private KepRepository kepRepository;

    public Page<Kep> findPaginated(Pageable pageable) {

        var kepek = kepRepository.findAll();

        Collections.reverse(kepek);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Kep> list;

        if (kepek.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, kepek.size());
            list = kepek.subList(startItem, toIndex);
        }

        Page<Kep> bookPage
                = new PageImpl<Kep>(list, PageRequest.of(currentPage, pageSize), kepek.size());

        return bookPage;
    }



}