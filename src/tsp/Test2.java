package tsp;


import tsp.temp.LunarCalender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Test2 {


    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 2006);
//        calendar.set(Calendar.MONTH, 0);
//        calendar.set(Calendar.DATE, 1);
//        // 1136087483981
//        long cutTime = calendar.getTimeInMillis() / 1000;
//        String lunarTimeString2 = getLunarTimeString(cutTime * 1000);
//        System.out.println(lunarTimeString2);

//        String serverVersion = "5.16.22.2";
//        String localVersion = "5.16.22.2";
//        System.out.println(serverVersion.compareTo(localVersion) >= 0);
        int rate = 96;
        String result = "";
        if (rate % 10 == 0){
            result = String.valueOf(rate / 10);
        }else {
            result = String.valueOf(rate / 10f);
        }
        System.out.println(result);


    }

    /**
     * 获取当前农历日期
     *
     * @param time 毫秒
     * @return
     */
    public static String getLunarTimeString(long time) {
        LunarCalender lunarCalender = new LunarCalender();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        LunarCalender.LunarCalendarInfo info = lunarCalender.getLunarCalendarInfo(calendar);
        String res = info.year + "年";
        List<String> mouthList = new ArrayList<>();
        for (int i = 0; i < lunarCalender.getMouthNum(info.year); i++) {
            String mouthStr;
            if (1 + i < 13) {
                mouthStr = LunarCalender.chineseMouthNumber[i] + "月";
                mouthList.add(mouthStr);
            } else {
                int index = lunarCalender.getLeapMouthIndex(info.year);
                mouthStr = "闰" + LunarCalender.chineseMouthNumber[index - 1] + "月";
                mouthList.add(index, mouthStr);
            }
        }
        res += mouthList.get(info.mouth - 1) + LunarCalender.chineseDayNumber[info.day - 1];
        return res;
    }


}
