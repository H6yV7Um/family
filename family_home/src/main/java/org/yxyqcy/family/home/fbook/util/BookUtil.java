package org.yxyqcy.family.home.fbook.util;

import org.yxyqcy.family.home.fbook.entity.FBook;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lcy on 17/11/19.
 */
public class BookUtil implements Serializable {


    private static final long serialVersionUID = 2378886324586247397L;

    /**
     * 设置所属
     * @param bookList
     * @param businessId
     */
    public static void setOwnsFbooks(List<FBook> bookList, String businessId) {
        if(null == bookList || 0 == bookList.size())
            return;

        for (FBook book: bookList) {
            if(!businessId.equals(book.getCreateBy()))
                book.setOwn("0");
        }

    }
}
