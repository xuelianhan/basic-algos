package org.ict.algorithm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PageUtil {

    /**
     * @see <a href="https://stackoverflow.com/questions/19688235/how-to-implement-pagination-on-a-list"></a>
     * @param sourceList
     * @param page
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {

        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() < fromIndex){
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    /**
     * @see <a href="https://gist.github.com/mageddo/465028426734d2e7a15a42b9eae57490"></a>
     * @param fullList
     * @param pageSize
     * @param pageInterface
     * @param <T>
     */
    public static <T> void doPaginated(Collection<T> fullList, Integer pageSize, Page<T> pageInterface) {

        final List<T> list = new ArrayList<T>(fullList);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size()){
            pageSize = list.size();
        }

        final int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
        for (int pageNum = 0; pageNum < numPages;){
            final List<T> page = list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size()));
            pageInterface.run(page);
        }
    }

    public interface Page<T> {
        void run(List<T> item);
    }
}
