package nl.op.service;

import nl.op.domain.Beer;

import java.util.List;

public interface DownloadDataService {
    void downloadData();
    List<Beer> getBeersByPage(int pageSize, int pageNumber);
}
