package org.yxyqcy.family.home.timesheet.util;

import org.apache.commons.collections4.ListUtils;
import org.yxyqcy.family.home.timesheet.entity.Timesheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/10/11.
 */
public class TimeSheetUtil {

    /**
     *  颜色列表
     */
    public static String[] colors = new String[]{"#27a0c9","#4eaa4c","#dd514c","#808080","#F8F8FF","#E0FFFF"
        ,"D1D1D1","#C1FFC1","#90EE90","#8A2BE2","#4F4F4F","#00EE00","#FFFAFA","#FFFF00","#FFE1FF"};


    /**
     * 转换timesheet color
     * @param list
     */
    public static void transferColorByUser(List<Timesheet> list){
        Map<String,Integer> map = new HashMap<String,Integer>();
        ListUtils.emptyIfNull(list);
        int colorIndex = 0;
        for (int i = 0 ; i < list.size() ; i++){
            Timesheet timesheet = list.get(i);
            timesheet.setColorByUser(true);
            timesheet.setTitle(timesheet.getCreateUser() + " :  " + timesheet.getTitle());
            /*获取索引*/
            Integer index = map.get(timesheet.getCreateBy());
            if(null != index)
                timesheet.setColor(colors[index]);
            else{
                if(colorIndex - colors.length == 0)
                    colorIndex = 0;
                map.put(timesheet.getCreateBy(),colorIndex);
                timesheet.setColor(colors[colorIndex++]);
            }

        }
    }
}
