/*
 * ϵͳ����:
 * ģ������: 
 * �� �� ��   : DateUtil.java
 * �����Ȩ:
 * ����ĵ�:
 * �޸ļ�¼:
 * �޸�����		�޸���Ա		�޸�˵��<BR>
 * ==========================================================
 * ==========================================================
 * �����¼��
 * 
 * ������Ա��
 * �������ڣ�
 * �������⣺
 */
package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ����˵��: �����ڽ���date��stringת��<br>
 * ϵͳ�汾: v1.0 <br>
 * ������Ա: zhengbin@fingard.com.cn <br>
 * ����ʱ��: 2010-7-15<br>
 * <br>
 */
public class DateUtil {
  	private static final String TIME_PATTERN             = "HH:mm";
    public static final String DATE_PATTERN_YYYY_MM_DD  = "yyyy-MM-dd";
    public static final String DATE_PATTERN_YYYYMMDD    = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS      = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH            = "yyyy-MM-dd HH";
    public static final String CHINESE_YYYY_MM_DD_HH_MM = "yyyy��MM��dd��  HH��mm��";
    public static final String YYYY_MM_DD_HH_00         = "yyyy-MM-dd HH:00";
    public static final String YYYYMMDDHH               = "yyyyMMddHH";
    public static final String YYYYMMDDHHMMSS           = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMM             = "yyMdHHmmss";
    public static final String MMDDHHMMSS               = "MMddHHmmss";
	                                                                          
	private static final String[] dayFormatter = {
			"yyyy��MM��dd��",// 0
			"yyyy-MM-dd",// 1
			"yyyy/MM/dd",// 2
			"yyyy��MM��dd��HHʱmm��ss��",// 3
			"yyyy/MM/dd-HH:mm:ss",// 4
			"yyyy-MM-dd HH:mm:ss",// 5
			"HHmmss", // 6
			"yyyyMMddHHmmss",// 7
			"yyyy-MM-dd HH:mm",// 8
			"HH:mm",// 9
			"dd/MM/yyyy",// 10
			"MM-dd",// 11
			"yyyy",// 12
			"yyyy-MM-dd HH",// 13
			"yyyyMMdd",// 14
			"yyyy-MM",// 15
			"ss mm HH dd MM E yyyy", // 16
			"yyyyMMddHHmm", // 17
			"yyyy/MM/dd HH:mm:ss", // 18
			"MMM dd yyyy HH:mm:ss",// 19
			"MMM dd yyyy", // 20
			"yyyyMMdd" // 21
	};
	
    private static Locale[] localArray = new Locale[]{
        Locale.getDefault(),//0
        Locale.getDefault(),//1
        Locale.getDefault(),//2
        Locale.getDefault(),//3
        Locale.getDefault(),//4
        Locale.getDefault(),//5
        Locale.getDefault(),//6
        Locale.getDefault(),//7
        Locale.getDefault(),//8
        Locale.getDefault(),//9
        Locale.getDefault(),//10
        Locale.getDefault(),//11
        Locale.getDefault(),//12
        Locale.getDefault(),//13
        Locale.getDefault(),//14
        Locale.getDefault(),//15
        Locale.US,//16
        Locale.getDefault(),//17
        Locale.getDefault(),//18
        Locale.US,//19
        Locale.US,//20
        Locale.getDefault()//21
    };

    /**
     * ����<code>kind</code>����������string��ʽ
     * @param date
     * @param kind
     * @return
     */
    public static String dateString(Date date, int kind) {
        if (kind < 0 || kind >= dayFormatter.length) {
            kind = 0;
        }
        if (date == null)
            return "";
        return (new SimpleDateFormat(dayFormatter[kind],localArray[kind]).format(date));
    }
    
    /**
     * ����<code>kind</code>������date��������
     * @param inputDate
     * @param kind
     * @return
     */
    public static Date stringDate(String inputDate, int kind) {
        Date tmp = null;
        try {
           tmp= new SimpleDateFormat(dayFormatter[kind],localArray[kind]).parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    
    /**
     * ��������յ�����ֵ
     * e.g. 20100715 [yyyyMMdd]
     * @param date
     * @return
     */
    public static int getYearMonthDay(Date date) {
        return Integer.parseInt(dateString(date, 21));
    }
        
    /**
     * ���Сʱ��������ֵ
     * e.g. 135630 [HHmmss]
     * @param date
     * @return
     */
    public static int getHourMinuteSecond(Date date){
    	return Integer.parseInt(dateString(date, 6));
    }
    
    /**
     * ���������ʱ���������ֵ
     * @param date
     * @return
     */
    public static String getYearMonDayHourMinSec(Date date) {
        return dateString(date, 17);
    }
    
    /**
     * ���������֡�20100720��ת�ɺ�����
     * @param date
     * @return ʱ��
     */
    public static long getTime(Integer date){
    	 String timeStr = String.valueOf(date);
    	 if(timeStr.length()==8){
    		 try {
				return (new SimpleDateFormat(dayFormatter[14],localArray[14]).parse(timeStr).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	 }
    	 return -1; 
    }
    

    /**
     * ת���ַ���Ϊʱ������
     */
    public static Date convertStringTODate(String dateStr, String pattern) throws ParseException {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.parse(dateStr);
    }

    /**
     * װ��ʱ������Ϊ�ַ�������
     */
    public static String convertDateToString(Date date, String pattern) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        sf.setLenient(false);
        return sf.format(date);
    }

    /**
     * ��ʱ���ַ���ת��Ϊ��һ�ָ�ʽ
     * ��yyyy-MM-dd HH:mm:ss ����> yyyyMMddHHmmss
     * @param dateStr:ʱ���ַ���
     * @param srcPattern:�ַ���������ʱ����ʽ����yyyy-MM-dd HH:mm:ss
     * @param targetPattern:Ҫ���ɵ�ʱ����ʽ����yyyyMMddHHmmss
     */
    public static String convertToSpecialDateStr(String dateStr, String srcPattern, String targetPattern) throws ParseException {
        Date date = convertStringTODate(dateStr, srcPattern);
        return convertDateToString(date, targetPattern);
    }

    /**
     * �õ���ǰ������ʱ�䣬Ĭ�ϸ�ʽΪ yyyy-MM-dd HH:mm:ss
     */
    public static Date getCurrentDateTime() {
        Calendar calNow = Calendar.getInstance();
        Date dtNow = calNow.getTime();
        return dtNow;
    }

    /**
     * �õ���ǰ������ʱ��
     */
    public static Date getCurrentDateTime(String pattern) throws ParseException {
        return convertStringTODate(getCurrentDateString(pattern), pattern);
    }

    /**
     * �õ���ǰ������ Ĭ�ϸ�ʽΪ yyyy-MM-dd
     */
    public static String getCurrentDateString() throws ParseException {
        return getCurrentDateString(DATE_PATTERN_YYYY_MM_DD);
    }

    /**
     * �õ���ǰ������ Ĭ�ϸ�ʽΪ yyyy-MM-dd
     */
    public static String getCurrentDateString(String pattern) throws ParseException {
        return convertDateToString(getCurrentDateTime(), pattern);
    }
  /**
     * �õ���ǰ������ Ĭ�ϸ�ʽΪ yyyy-MM-dd
     */
    public static String getCurrentDateTimeString()  {
        try {
            return convertDateToString(getCurrentDateTime(), YYYY_MM_DD_HH_MM_SS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * �õ���һ��
     */
    public static Date getAfterDate(String date) throws ParseException {
        return getAfterDate(convertStringTODate(date, DateUtil.DATE_PATTERN_YYYY_MM_DD));
    }

    /**
     * �õ�ǰһ��
     */
    public static Date getBeforeDate(String date) throws ParseException {
        return getBeforeDate(convertStringTODate(date, DateUtil.DATE_PATTERN_YYYY_MM_DD));
    }

    public static Date getBeforeDate(Date date) throws ParseException {
        return new Date(date.getTime() - 1000 * 3600 * 24);
    }

    public static Date getAfterDate(Date date) {
        return new Date(date.getTime() + 1000 * 3600 * 24);
    }

    public static Date addDate(Date date, int interval) {
        return new Date(date.getTime() + ((1000 * 3600 * 24) * interval));
    }

    /**
     * �Ӽ�����
     */
    public static Date addMinutes(Date startTime, int interval) {
        if (startTime == null) {
            return null;
        }
        long ms = startTime.getTime() + interval * 60 * 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        return calendar.getTime();
    }

    /**
     * �õ��¸�ʱ������
     */
    public static Date getNextHour(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.HOUR_OF_DAY, +1);
        return calendar.getTime();
    }

    /**
     * �õ��¸�ʱ������
     * 
     * @param time
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getPreviousHour(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(calendar.HOUR_OF_DAY, -1);
        return calendar.getTime();
    }

    /**
     * �õ��¸�ʱ����
     * 
     * @param time
     * @return
     */
    public static Date getNextHalfHour(Date time) {
        Date result = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int minute = calendar.get(Calendar.MINUTE);
        if (minute >= 30) {
            result = getNextHour(time);
        } else {
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            result = calendar.getTime();
        }
        return result;
    }

    public static String getCurrentTimeChineseStr() {
    	SimpleDateFormat CHINESE_DATE_FORMAT      = new SimpleDateFormat(CHINESE_YYYY_MM_DD_HH_MM);
        return CHINESE_DATE_FORMAT.format(getCurrentDateTime());
    }

    /**
     * У�����ڸ�ʽ
     * 
     * @param strDate
     * @return
     */
    public static boolean isDateFormat(String strDate) {
        String eL = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(strDate);
        boolean b = m.matches();

        return b;
    }

    /**
     * �����������֮ǰ�����·�<br>
     * 
     * @param start
     * @param
     * @return
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {

            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * �����������֮ǰ��������<br>
     * 
     * @param start
     * @param e
     * @return
     */
    public static long differ(Date fromDate, Date toDate) {
        //return date1.getTime() / (24*60*60*1000) - date2.getTime() / (24*60*60*1000);
        return toDate.getTime() / 86400000 - fromDate.getTime() / 86400000; //�������������ٳ˷�����Ŀ���
    }

    /**
     * ��ȡСʱ
     * 
     * @param date Date
     * @return int
     */
    public static int getHourNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡ����
     * 
     * @param date Date
     * @return int
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    public DateUtil() {
    }

    //Timestamp��String֮��ת���ĺ�����
    public static String getTimestampToString(Timestamp obj) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�����ʽ������ʾ����
        String str = df.format(obj);
        return str;
    }

    /*
     * �Զ��� ת��ģʽ��Timestamp ���
     */
    public static String getTimestampToString(String formatPattern, Timestamp obj) {
        SimpleDateFormat df = new SimpleDateFormat(formatPattern);
        String str = df.format(obj);
        return str;
    }

    //Stringת��ΪTimestamp:
    public static Timestamp getStringToTimestamp(String str) {
        Timestamp ts = Timestamp.valueOf(str);
        return ts;
    }

    public static Date strToDate(String str, String pattern) {
        Date dateTemp = null;
        SimpleDateFormat formater2 = new SimpleDateFormat(pattern);
        try {
            dateTemp = formater2.parse(str);
        } catch (Exception e) {
            
        }
        return dateTemp;
    }



    /**
     * This method generates a string representation of a date/time in the
     * format you specify on input
     * 
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);


        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format: MM/dd/yyyy HH:MM
     * a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }


    public static Calendar getCurrentDay() throws ParseException {
        Calendar cal = Calendar.getInstance();
        return cal;

    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }



    /**
     * @param aDate
     * @return
     */
    public static String convertDateToString(String pattern, Date aDate) {
        return getDateTime(pattern, aDate);
    }

    /**
     * ȡ�ô�startDate��ʼ��ǰ(��)/��(��)day��
     * 
     * @param startDate
     * @param day
     * @return
     */
    public static Date getRelativeDate(Date startDate, int day) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, day);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    /**
     * ��ע���������,�����ӻ��߼��ٵ����������new Date()��˵�ģ����������startDate;Ϊ�˱�����գ����ﲻ���޸�
     * 
     * @param startdate
     * @param days
     * @return
     */
    @Deprecated
    public static Date getDate(Date startdate, int days) {
        Date dateresult = startdate;
        try {
            GregorianCalendar cal = new GregorianCalendar();

            cal.setTime(new Date());
            cal.add(GregorianCalendar.DAY_OF_MONTH, days);
            dateresult = cal.getTime();
        } catch (Exception e) {
        }
        return dateresult;
    }

    /**
     * �������ڻ�ȡ���ڼ�
     * 
     * @param date java.util.Date����,����Ϊnull
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * ͳ������ʱ�����ص�������(��24Сʱ��һ�죬����24Сʱ��Ϊ0���������ʱ����ð�Сʱ�����ӵ�ȥ��)
     * 
     * @param begin ��ʼʱ��
     * @param end
     * @return
     */
    public static int countDays(String beginStr, String endStr, String Foramt) {
        Date end = strToDate(endStr, Foramt);
        Date begin = strToDate(beginStr, Foramt);
        long times = end.getTime() - begin.getTime();
        return (int) (times / 60 / 60 / 1000 / 24);
    }

    /**
     * ��ȡ���
     * 
     * @param date Date
     * @return int
     */
    public static final int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * ��ȡ���
     * 
     * @param millis long
     * @return int
     */
    public static final int getYear(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * ��ȡ�·�
     * 
     * @param date Date
     * @return int
     */
    public static final int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * ��ȡ�·�
     * 
     * @param millis long
     * @return int
     */
    public static final int getMonth(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * ��ȡ����
     * 
     * @param millis long
     * @return int
     */
    public static final int getDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DATE);
    }

    /**
     * ��ȡСʱ
     * 
     * @param date Date
     * @return int
     */
    public static final int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡСʱ
     * 
     * @param millis long
     * @return int
     */
    public static final int getHour(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * ���ĳ���ĳ���ж�����
     * 
     * @return
     */
    public static final int getMonthNumberByYear() {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, getYear(new Date()));
        time.set(Calendar.MONTH, getMonth(new Date()) - 1);//Calendar����Ĭ��һ��Ϊ0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//���·ݵ�����
        return day;
    }

    /**
     * ���µ����һ��
     * 
     * @param sDate1
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getLastDayOfMonth(Date sDate) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(sDate);
        final int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        //System.out.print(StringUtil.equals(String.valueOf(DateUtil.getDate(lastDate)), String.valueOf(DateUtil.getDate(new Date()))));
        return lastDate;
    }

    public static void main(String args[]) {
    	  System.out.println(stringDate("2013/12/10",2));

    }

    public static String[] splitStringBySix(String str) {
        if (null == str || "".equals(str)) {
            return null;
        }
        // ���������ݵ�����ĳ���
        int arrLength = (str.length() % 6 == 0) ? str.length() / 6 : str.length() / 6 + 1;
        String[] arr = new String[arrLength];

        for (int i = 0; i < arr.length; i++) {
            // ��Ҫ�Ǵ������ļ�λ����6λ�����
            if (i == arr.length - 1) {
                arr[i] = str.substring(i * 6);
            }
            arr[i] = str.substring(i * 6, (i + 1) * 6);
        }
        return arr;
    }
    
    /**
     * ��װ�����ַ���
     */
	public static String dateStr(Integer date){
		if(date==null||date==0) return "";
		String dateString = date.toString();
		String year = dateString.substring(0,4);
		String month = dateString.substring(4,6);
		String day = dateString.substring(6,8);
		return year+"-"+month+"-"+day;
	}
	
	/**
	 * ��װʱ���ַ���
	 * @param time
	 * @return
	 */
	public static String timeStr(Integer time){
		if(time==null||time==0) return "";
		String timeString = time.toString();
		//СʱС��10�ģ�ǰ�油0
		if(timeString.length()==5)timeString ="0"+timeString;
		//TASK #9042=>zhouzx [����][������Ʒ��ҵ��-������][TS:201312130314]-0�����Ҵ������û����ᱨ�±�Խ���쳣
		String h = "00";
		String s = "00";
		String m = "00";
		if(timeString.length()<=2){
			m = timeString;
		}
		if(timeString.length()>=2&&timeString.length()<6){
			 s = timeString.substring(0,2);
			 if(timeString.length()==2){
				 if(time>60){
						s = "0"+timeString.substring(0,1);
						m = "0"+timeString.substring(1,2);
				}else{
					s = timeString;
				}
			 }else if(timeString.length()==3){
				 s = timeString.substring(0,2);
				 m = "0"+timeString.substring(2,3);
			 }else if(timeString.length()==4){
				 s = timeString.substring(0,2);
				 m = timeString.substring(2,4);
			 }
		}
		if(timeString.length()>=6){
			 h = timeString.substring(0,2);
			 s = timeString.substring(2,4);
			 m = timeString.substring(4,6);
		}
		
		return h+":"+s+":"+m;
	}
	
	/**
     * ���ַ���ת��Ϊ����ʱ������
     *
     * @param str �����ַ��� yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date convertToDateTimeByDateTimeString(String str) {
        return convertToDate(str, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * ���ַ���ת������ʱ������
     *
     * @param str    �ַ���
     * @param format ��ʽ���ַ���
     * @return ת��������ڶ���
     */
    public static Date convertToDate(String str, String format) {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            return date;
        }
        return date;
    }
    
    /**
     * ������
     *
     * @param date   ���ڣ�����Ϊ��
     * @param amount ����
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }
    
    /**
     * ���ڵĲ���
     *
     * @param date          ���ڣ�����Ϊ��
     * @param calendarField ���ӵ����ͣ�ΪCalendar�ĳ����ֶ�
     * @param amount        ����
     * @return
     */
    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
    
    
    /**
     * ��ȡ�������������·ݵĵ�n����ݼ�������
     *
     * @param date         ����
     * @param weekNum      ����
     * @param dayofweekNum ���ڼ�
     * @return
     */
    public static Date getDateInAnyWeekOfMonth(Date date, int weekNum, int dayofweekNum) {
        Date firstDateOfMonth = getFirstDateOfMonth(date);
        int dayOfWeek = getDayOfWeek(firstDateOfMonth);//����µĵ�һ�������ڼ�
        int in = dayofweekNum - dayOfWeek;//����µ�һ������ָ������ǰ���Ǻ���
        int a = (weekNum - 1) * 7 + dayofweekNum - dayOfWeek;//���ָ��������һ���µĵڼ���
        if (in < 0) {
            a = 7 + in + (weekNum - 1) * 7;
        } else {
            a = (weekNum - 1) * 7 + dayofweekNum - dayOfWeek;
        }
        firstDateOfMonth = addDays(firstDateOfMonth, a);
        return firstDateOfMonth;
    }
	
    /**
     * ����һ�����ڣ��ó�������ڶ�Ӧ�·ݵĵ�һ�������
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.DAY_OF_MONTH, 1);
        return c.getTime();
    }
    
    
    /**
     * ��ȡ�������������ڼ�; ע�⣺������(1).....������(7)
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        return get(date, Calendar.DAY_OF_WEEK);
    }
    
    /**
     * ���ڻ�ȡ
     *
     * @param date
     * @param calendarField
     * @return
     */
    private static int get(Date date, int calendarField) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(calendarField);
    }
    
    /**
     * ��ȡ�������������·ݵ����һ����ݼ�������
     *
     * @param date      ����
     * @param dayofweek ��ݼ�
     * @return
     */
    public static Date getDateInLastWeekOfMonth(Date date, int dayofweek) {
        Date lastDateOfMonth = getLastDateOfMonth(date);
        int wd = getDayOfWeek(lastDateOfMonth);
        int a = 0;
        if (dayofweek != wd) {
            a = dayofweek - wd - 7;
            if (Math.abs(a) > 7) {
                a = a + 7;
            }
        }
        lastDateOfMonth = addDays(lastDateOfMonth, a);
        return lastDateOfMonth;
    }
    
    /**
     * ����һ�����ڣ��ó�������ڶ�Ӧ�·ݵ����һ�������
     *
     * @param date ����
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.DAY_OF_MONTH, 1);
        c.roll(c.DAY_OF_MONTH, -1);
        // ÿ��ӵ������
        return c.getTime();
    }
    
    /**
     * ת����oracle����Ҫ������ʱ���ʽ("yyyy-MM-dd HH24:mi:ss")
     *
     * @param date ����
     * @return ����oracleʶ���to_date����������ʱ���ַ���
     */
    public static String toOracleDateTime(Date date) {
        StringBuffer bf = new StringBuffer();
        bf.append("to_date('");
        bf.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        bf.append("','");
        bf.append("yyyy-MM-dd HH24:mi:ss");
        bf.append("')");
        return bf.toString();
    }

    /**
     * ��ȡ��ǰ�����������ڵĵ�һ��(��һ)�����һ��(����)������
     * @return weekday:weekday[0]��һ��weekday[1]����
     */
    public static Date[] getThisWeekBeginEndDay(){
        Calendar calendar = Calendar.getInstance();
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK)+1; //��ȡһ�ܵĿ�ʼ���ܼ�,����һ�ܵĿ�ʼ�����գ�+1��Ϊ�˰���һ������ʼ
        int current = calendar.get(Calendar.DAY_OF_WEEK); //��ȡ�������ܼ�
        calendar.add(Calendar.DAY_OF_WEEK, min-current); //����-��׼����ȡ�ܿ�ʼ����
        Date start = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, 6); //��ʼ+6����ȡ�ܽ�������
        Date end = calendar.getTime();
        Date[] weekday = new Date[]{start, end};
        return weekday;
    }
    /**
     * ����date str�ĳ����жϳ���ʽת��Ϊdate
     *
     * @param str
     * @return
     */
    public static Date convertToDate(String str) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = null;
        if (str.length() == 8) {
            if (str.indexOf('-') > 0
                    && str.indexOf('-') != str.lastIndexOf('-')) {
                simpleDateFormat = new SimpleDateFormat("yyyy-M-d");
            } else if (str.indexOf('/') > 0
                    && str.indexOf('/') != str.lastIndexOf('/')) {
                simpleDateFormat = new SimpleDateFormat("yyyy/M/d");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            }
        } else if (str.length() == 9) {
            if (str.indexOf('-') > 0
                    && str.indexOf('-') != str.lastIndexOf('-')) {
                if (str.lastIndexOf('-') - str.indexOf('-') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-d");
                } else if (9 - str.lastIndexOf('-') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
                }
            } else if (str.indexOf('/') > 0
                    && str.indexOf('/') != str.lastIndexOf('/')) {
                if (str.lastIndexOf('/') - str.indexOf('/') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy/MM/d");
                } else if (9 - str.lastIndexOf('/') == 2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");
                }
            }
        } else if (str.length() == 10 && str.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else if (str.length() == 10 && str.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        } else if (str.length() == 19 && str.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (str.length() == 21 && str.indexOf('.') == 19
                && str.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        } else if (str.length() == 23 && str.indexOf('.') == 19
                && str.indexOf('-') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        } else if (str.length() == 19 && str.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else if (str.length() == 21 && str.indexOf('.') == 19
                && str.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");
        } else if (str.length() == 23 && str.indexOf('.') == 19
                && str.indexOf('/') > -1) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        } else if (str.length() == 15) {
            simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        } else if (str.length() == 14) {
            simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        }else if (!(str.indexOf(".") > -1) && !(str.indexOf("-") > -1) && !(str.indexOf("/") > -1)) {
            simpleDateFormat = findStringdateFormat(str);
        }
        Date d = null;
        if (simpleDateFormat == null) {
            if (str.contains("-")) {
                //2016-03-21 00:01:40���ָ�ʽ��֧�֣�Ҫת��б��
                str = str.replace('-', '/');
            }
            try {
                return new Date(str);
            } catch (Exception e) {
                throw new ParseException("�޷�ʶ�����ڸ�ʽ", 1);
            }
        } else {
            d = simpleDateFormat.parse(str);
        }
        return d;
    }
    /**
     * ��ȡ��ʽ���ַ���
     *
     * @param target
     * @return
     */
    private static SimpleDateFormat findStringdateFormat(String target) {
        //�޷�����2015111��
        //yyyyMMd HH:mm:ss
        //yyyyMdd HH:mm:ss
        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(target);
        String regex = "";
        if (matcher.find()) {
            int len = target.length();
            if (!(target.indexOf(" ") > -1))
                if (len == 14)
                    regex = "yyyyMdHH:mm:ss";
                else
                    regex = "yyyyMMddHH:mm:ss";
            else if (len == 15)
                regex = "yyyyMd HH:mm:ss";
            else
                regex = "yyyyMMdd HH:mm:ss";
        }
        return new SimpleDateFormat(regex);
    }

    /**
     *
     * @param time
     * @return
     */
    public static Date convertToDate(long time) {
        Date date = new Date(time);
        return date;
    }

}
